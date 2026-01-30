package top.dreamcenter.openlistproxy.service;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import top.dreamcenter.openlistproxy.model.FsLinkResult;
import top.dreamcenter.openlistproxy.model.RetResult;
import top.dreamcenter.openlistproxy.model.UserConfigProperties;
import top.dreamcenter.openlistproxy.util.ProxyUtil;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class ProxyService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserConfigProperties userConfigProperties;

    /**
     * CHECK SIGN
     * @param sign SIGN
     * @param path PATH
     * @return RES
     */
    public boolean CheckSign(String sign, String path) {
        String token = userConfigProperties.getToken();
        try {
            boolean res = ProxyUtil.checkSign(sign, path, token);
            log.info("CHECK SIGN: {}", res);
            return res;
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            log.error("CHECK SIGN FAIL: {}", e.getMessage());
            return false;
        }
    }

    /**
     * GET DOWNLOAD LINK FROM PATH
     * @param path PATH
     * @return RES
     */
    public RetResult<FsLinkResult> GetDownloadUrl(String path) {
        String token = userConfigProperties.getToken();
        String homeAddress = userConfigProperties.getAddress();

        String url = homeAddress + "/api/fs/link";

        // BODY
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("path", path);

        // HEADER
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", token);

        // REQ ENTITY
        HttpEntity<String> entity = new HttpEntity<>(
                JSONObject.toJSONString(requestBody),
                headers
        );

        try {
            ResponseEntity<RetResult<FsLinkResult>> result = restTemplate.exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<>() {});

            log.info("REQ RES: {}", result);

            RetResult<FsLinkResult> res = result.getBody();

            if (res == null || res.getCode() != 200)
                return new RetResult<>(500, "FAIL: GET DOWNLOAD LINK FROM PATH FAIL !", null);

            return result.getBody();
        } catch (Exception e) {
            log.error("REQ FAIL: {}", e.getMessage());
            return new RetResult<>(500, "FAIL: SEND REQUEST TO" + url + " FAIL !", null);
        }

    }

}

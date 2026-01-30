package top.dreamcenter.openlistproxy.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.dreamcenter.openlistproxy.model.FsLinkResult;
import top.dreamcenter.openlistproxy.model.RetResult;
import top.dreamcenter.openlistproxy.service.ProxyService;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Controller
public class ProxyController {

    @Autowired
    private ProxyService proxyService;

    @RequestMapping("/endpoint/**")
    public void proxy(
            @RequestParam String sign,
            HttpServletRequest request,
            HttpServletResponse servletResponse) throws IOException {
        // PATH
        String path = request.getRequestURI().replaceFirst("/endpoint", "");
        path = URLDecoder.decode(path, StandardCharsets.UTF_8);

        // SIGN
        boolean checkSignRes = proxyService.CheckSign(sign, path);
        if (!checkSignRes) {
            servletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            servletResponse.getWriter().write("SIGN CHECK ERROR");
            return;
        }
        
        // GET RESULT
        RetResult<FsLinkResult> downloadUrlRes = proxyService.GetDownloadUrl(path);

        if (downloadUrlRes.getCode() == 200) {
            servletResponse.sendRedirect(downloadUrlRes.getData().getUrl());
        } else {
            servletResponse.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
            servletResponse.getWriter().write(downloadUrlRes.getMsg());
        }

    }

}

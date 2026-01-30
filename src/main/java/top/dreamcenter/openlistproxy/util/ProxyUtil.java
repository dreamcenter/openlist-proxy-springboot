package top.dreamcenter.openlistproxy.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class ProxyUtil {


    public static boolean checkSign(String sign, String path, String token) throws NoSuchAlgorithmException, InvalidKeyException {

        String toSignStr = path + ":0";
        String _sign = safeBase64(toSignStr, token);
        return sign.equals(_sign + ":0");
    }

    private static String safeBase64(String toSignStr, String token) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] secretKey = token.getBytes();

        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, "HmacSHA256");
        mac.init(secretKeySpec);
        byte[] hash = mac.doFinal(toSignStr.getBytes());

        return Base64.getUrlEncoder().encodeToString(hash);
    }

}

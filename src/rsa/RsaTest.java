package rsa;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class RsaTest {
    public static final String KEY_ALGORITHM = "RSA";
    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";
    public static final String SIGNATURE_ALGORITHM = "SHA256withRSA";

    public static final String pub =
                    "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDKZ3091lir5BgrvyP0H/NTg1c0F1BiCvwhsZcqJm4qxfjenpL2fSnAPQYNAPY9frY8rqiJbb5/WfqtZaATKEmdP6Wd71DpTEXUtqJbCcTjL+72kA3Ovi1+rUn/O5L0wO83i0itlwZ3motPNwjBdqD/1Wt4/teqhQprQTN0K/YiZQIDAQAB";
    public static final String pri =
                    "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMpnfT3WWKvkGCu/I/Qf81ODVzQXUGIK/CGxlyombirF+N6ekvZ9KcA9Bg0A9j1+tjyuqIltvn9Z+q1loBMoSZ0/pZ3vUOlMRdS2olsJxOMv7vaQDc6+LX6tSf87kvTA7zeLSK2XBneai083CMF2oP/Va3j+16qFCmtBM3Qr9iJlAgMBAAECgYA5pE9elPSbv1HC46RxmcfKONVU2G7j/Qzg65qJPniRZeACRlbEK9EAFmDqwyNdUADGN1d4C/D8g7uINjDoQ8jypcdwZZnGskaSgUoKbBxzbe/UKqMBwMEmMxEsYctN3lTW8VtF14lOVE4Mu0kCtxrsDp/3whP/CR26cxueklb6AQJBAOhNXzkp3ghM8A1BnLSsqx6uLen3rJYHRwrsn3KwMPKRrzhJxZpltfvuNu73DZ4zxXDYwMGExQkUdc5YkC5lwdECQQDfDVMNW6fLap4bb0lit3GIfB5sfVRAeDMv7Mx8hJ8GLka6/UVwL28+wmOJt/iI56SzMb2Wl66MkwsN0fq7ikhVAkBxCm2C8Jel7GKH4Nyzoq5mcJXRG3+1RfdRHpH8ijZXY4MjXeq1Vjc3T9UVdcz/peu3HRK3WLdU0sr8zWXj3gpxAkBPokwyirSfdVM5bEQBEIliNx4NkblQwxlTVPG0ywyTglYSUHZIFUdcWr563snG5xzwNSQA7eqNmbnqlzszVa15AkAf31H1mESSnWxoOe7jzJhfvCXBB5MOU9y4lMnfOQAfihafuEzXCIY7Gb4cK1PZaKq/VAKXLt/mDHY7lLpbwqCa";


    // map对象中存放公私钥
    public static Map<String, Object> initKey() throws Exception {
        // 获得对象 KeyPairGenerator 参数 RSA 1024个字节
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        // 通过对象 KeyPairGenerator 获取对象KeyPair
        KeyPair keyPair = keyPairGen.generateKeyPair();

        // 通过对象 KeyPair 获取RSA公私钥对象RSAPublicKey RSAPrivateKey
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        // 获取公、私钥值
        String publicKeyValue = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        String privateKeyValue = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        // 公私钥对象存入map中
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKeyValue);
        keyMap.put(PRIVATE_KEY, privateKeyValue);
        return keyMap;
    }

    /**
     * 解码PublicKey
     * 
     * @param key
     * @return
     */
    public static PublicKey getPublicKey(String key) {
        try {
            byte[] byteKey = Base64.getDecoder().decode(key);
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(byteKey);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            return keyFactory.generatePublic(x509EncodedKeySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解码PrivateKey
     * 
     * @param key
     * @return
     */
    public static PrivateKey getPrivateKey(String key) {
        try {
            byte[] byteKey = Base64.getDecoder().decode(key);
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(byteKey);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            return keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Map<String, Object> keyMap;
        try {
            // keyMap = initKey();
            // System.out.println(keyMap.get(PUBLIC_KEY));
            // System.out.println(keyMap.get(PRIVATE_KEY));
            String sign = sign(pri, "{\"bizToken\":\"mp_login\",\"mobile\":\"13266828015\"}s5d6f7g8");
            verifySign(pub, "{\"bizToken\":\"mp_login\",\"mobile\":\"13266828015\"}s5d6f7g8", sign);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 签名
     * 
     * @param key 私钥
     * @param requestData 请求参数
     * @return
     */
    public static String sign(String key, String requestData) {
        String signature = null;
        byte[] signed = null;
        try {
            PrivateKey privateKey = getPrivateKey(key);

            Signature Sign = Signature.getInstance(SIGNATURE_ALGORITHM);
            Sign.initSign(privateKey);
            Sign.update(requestData.getBytes());
            signed = Sign.sign();

            signature = Base64.getEncoder().encodeToString(signed);
            System.out.println("===签名结果：" + signature);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return signature;
    }

    /**
     * 验签
     * 
     * @param key 公钥
     * @param requestData 请求参数
     * @param signature 签名
     * @return
     */
    public static boolean verifySign(String key, String requestData, String signature) {
        boolean verifySignSuccess = false;
        try {
            PublicKey publicKey = getPublicKey(key);

            Signature verifySign = Signature.getInstance(SIGNATURE_ALGORITHM);
            verifySign.initVerify(publicKey);
            verifySign.update(requestData.getBytes());

            verifySignSuccess = verifySign.verify(Base64.getDecoder().decode(signature));
            System.out.println("===验签结果：" + verifySignSuccess);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return verifySignSuccess;
    }
}

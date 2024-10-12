package des

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * 匹配麦通的加密规则
 */
public class MaitongDesUtil {

    // 密钥
    private static String DES_KEY = "$9$@#32e".substring(0, 8);

    // 密钥向量
    private static byte[] DES_IV = {(byte) 0x12, (byte) 0x34, (byte) 0x56, (byte) 0x78, (byte) 0x90, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF};

    private static AlgorithmParameterSpec IV = null;

    private static Key KEY = null;

    static {
        try {
            DESKeySpec keySpec = new DESKeySpec(DES_KEY.getBytes());
            IV = new IvParameterSpec(DES_IV);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            KEY = keyFactory.generateSecret(keySpec);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String encode(String data) throws Exception {
        Cipher enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        enCipher.init(Cipher.ENCRYPT_MODE, KEY, IV);
        byte[] pasByte = enCipher.doFinal(data.getBytes("utf-8"));
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(pasByte);
    }

    public static String decode(String data) throws Exception {
        Cipher deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        deCipher.init(Cipher.DECRYPT_MODE, KEY, IV);
        BASE64Decoder base64Decoder = new BASE64Decoder();
        byte[] pasByte = deCipher.doFinal(base64Decoder.decodeBuffer(data));
        return new String(pasByte, "UTF-8");
    }

    public static String tryDecode(String data) {
        String decodeValue = "";
        try {
            decodeValue = decode(data);
        } catch (Exception ex) {
            System.out.println("尝试解码" + data + " 失败", ex);
        }
        return decodeValue;
    }

    public static void main(String[] args) throws Exception {
        // System.out.println(encode("2000074"));
        System.out.println(HzDESUtil.encryptBase64("2069635", DES_KEY));
    }
}

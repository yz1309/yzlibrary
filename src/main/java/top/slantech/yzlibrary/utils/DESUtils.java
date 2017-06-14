package top.slantech.yzlibrary.utils;

import android.util.Base64;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * DES 加密工具类
 * 功能描述：
 * 1、加密 encrypt(String key, String data);
 * 2、解密 decrypt(String key, String data);
 */
public class DESUtils {
    /**
     * DES是加密方式 CBC是工作模式 PKCS5Padding是填充模式
     */
    private final static String TRANSFORMATION = "DES/CBC/PKCS5Padding";
    /**
     * 初始化向量参数，AES 为16bytes. DES 为8bytes
     */
    private final static String IVPARAMETERSPEC = "01020304";
    /**
     * DES是加密方式
     */
    private final static String ALGORITHM = "DES";

    /**
     * DES算法，加密
     *
     * @param data 待加密字符串
     * @param key  加密私钥，长度不能够小于8位
     * @return 加密后的字节数组，一般结合Base64编码使用
     */
    public static String encrypt(String key, String data) {
        return encrypt(key, data.getBytes());
    }


    /**
     * DES算法，加密
     *
     * @param data 待加密字符串
     * @param key  加密私钥，长度不能够小于8位
     * @return 加密后的字节数组，一般结合Base64编码使用
     */
    public static String encrypt(String key, byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            IvParameterSpec iv = new IvParameterSpec(IVPARAMETERSPEC.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, getRawKey(key), iv);
            byte[] bytes = cipher.doFinal(data);
            return Base64.encodeToString(bytes, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * DES算法，解密
     *
     * @param key
     * @param data
     * @return
     */
    public static String decrypt(String key, String data) {
        return decrypt(key, Base64.decode(data, Base64.DEFAULT));
    }

    /**
     * DES算法，解密
     *
     * @param data 待解密字符串
     * @param key  解密私钥，长度不能够小于8位
     * @return 解密后的字节数组
     */
    public static String decrypt(String key, byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            IvParameterSpec iv = new IvParameterSpec(IVPARAMETERSPEC.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, getRawKey(key), iv);
            byte[] original = cipher.doFinal(data);
            String originalString = new String(original);
            return originalString;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 对密钥进行处理
     * @param key
     * @return Key
     * @throws Exception
     */
    private static Key getRawKey(String key) throws Exception {
        DESKeySpec dks = new DESKeySpec(key.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        return keyFactory.generateSecret(dks);
    }
}

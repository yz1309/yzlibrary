package top.slantech.yzlibrary.utils;

import java.security.MessageDigest;

/**
 * MD5加密
 * 功能描述：
 * 1、加密 encrypt(String sourceStr)
 */
public class MD5Utils {
    /**
     * 加密
     * @param sourceStr
     * @return 加密后的字符串
     */
    public static String encrypt(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

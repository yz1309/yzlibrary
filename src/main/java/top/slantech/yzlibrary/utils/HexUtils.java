package top.slantech.yzlibrary.utils;

import java.io.ByteArrayOutputStream;

/**
 * 进制转换工具
 * 功能描述：
 * 1、将字符串编码成16进制数字,适用于所有字符（包括中文） encode(String str)
 * 2、将16进制数字解码成字符串,适用于所有字符（包括中文） decode(String bytes)
 * 3、把16进制字符串转换成字节数组 hexStringToByte(String hex)
 * 4、把字节数组转换成16进制字符串 bytesToHexString(byte[] bArray)
 * 5、BCD码转为10进制串(阿拉伯数据) bcd2Str(byte[] bytes)
 * 6、10进制串转为BCD码 str2Bcd(String asc)
 * Created by slantech on 2016/08/29 11:27
 */
public class HexUtils {
    private static String hexString = "0123456789abcdef";

    public static void main(String[] arg) {
        /**
         * 68 65 6C 6C 6 F 0A
         * C4 E3 BA C3
         */
        String[] str = {"C4", "E3", "BA", "C3"};
        int len = str.length;
        // String[] str = {"7E","02","04","00","07","10","00","00","00","00","13","08","4F","01","0B","0B","15","10","14","13","44","7E"};
        byte[] b = new byte[len];
        for (int i = 0; i < len; i++) {
            b[i] = hexStringToByte(str[i])[0];
        }
        System.out.println(new String(b));
        String strC = "你好";
        String bth = bytesToHexString(strC.getBytes());
        System.out.println(bth);
        System.out.println(Short.MAX_VALUE);
        System.out.println(Integer.toBinaryString(280));
    }

    /*
    * 将字符串编码成16进制数字,适用于所有字符（包括中文）
    */
    public static String encode(String str) {
        //根据默认编码获取字节数组
        byte[] bytes = str.getBytes();
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        //将字节数组中每个字节拆解成2位16进制整数
        for (byte model:bytes){
            sb.append(hexString.charAt((model & 0xf0) >> 4));
            sb.append(hexString.charAt((model & 0x0f)));
        }
        return sb.toString();
    }

    /*
    * 将16进制数字解码成字符串,适用于所有字符（包括中文）
    */
    public static String decode(String bytes) {
        int len = bytes.length();
        ByteArrayOutputStream baos = new ByteArrayOutputStream(len / 2);
        //将每2位16进制整数组装成一个字节
        for (int i = 0; i < len; i += 2)
            baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString.indexOf(bytes.charAt(i + 1))));
        return new String(baos.toByteArray());
    }

    /*
    * 把16进制字符串转换成字节数组
    * @param hex @return
    */
    public static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    private static byte toByte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        return b;
    }

    /**
     * 把字节数组转换成16进制字符串
     *
     * @param bArray
     * @return
     */
    public static final String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (byte model:bArray){
            sTemp = Integer.toHexString(0xFF & model);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * BCD码转为10进制串(阿拉伯数据)
     * @param bytes BCD码
     * @return 10进制串
     */
    public static String bcd2Str(byte[] bytes) {
        StringBuffer temp = new StringBuffer(bytes.length * 2);
        for (byte model:bytes){
            temp.append((byte) ((model & 0xf0) >>> 4));
            temp.append((byte) (model & 0x0f));
        }
        return temp.toString().substring(0, 1).equalsIgnoreCase("0") ? temp.toString().substring(1) : temp.toString();
    }

    /**
     * 10进制串转为BCD码
     * @param asc 10进制串
     * @return BCD码
     */
    public static byte[] str2Bcd(String asc) {
        int len = asc.length();
        int mod = len % 2;
        if (mod != 0) {
            asc = "0" + asc;
            len = asc.length();
        }
        byte abt[] = new byte[len];
        if (len >= 2) {
            len = len / 2;
        }
        byte bbt[] = new byte[len];
        abt = asc.getBytes();
        int j, k;
        int len2 = asc.length();
        for (int p = 0; p < len2 / 2; p++) {
            if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
                j = abt[2 * p] - '0';
            } else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
                j = abt[2 * p] - 'a' + 0x0a;
            } else {
                j = abt[2 * p] - 'A' + 0x0a;
            }
            if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
                k = abt[2 * p + 1] - '0';
            } else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
                k = abt[2 * p + 1] - 'a' + 0x0a;
            } else {
                k = abt[2 * p + 1] - 'A' + 0x0a;
            }
            int a = (j << 4) + k;
            byte b = (byte) a;
            bbt[p] = b;
        }
        return bbt;
    }
}

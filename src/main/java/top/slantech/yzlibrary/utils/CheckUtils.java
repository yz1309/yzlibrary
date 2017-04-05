package top.slantech.yzlibrary.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数据格式检查
 * 功能描述：
 * 1、检查手机号码合法性 checkMDN("18224411300",true); or isMobileNo("18224411300");
 * 2、检测邮箱合法性  checkEmailValid("yz130@163.com");
 * 3、检查是否是IPV4 isIPv4Address(12.12.10.26);
 * 4、检查是否是IPV6 isIPv6StdAddress("9489:1572:28fa:1bda%28");
 * 5、是否只是字母和数字 isNumberLetter("test");
 * 6、是否只是数字 isNumber("test");
 * 7、是否是中文 isChinese("test");
 * 8、是否包含中文 isContainChinese("test");
 * 9、判断是否为一个合法的url地址 isUrl("http://www.slantech.top");
 */
public class CheckUtils {
    public static final String[] PHONE_PREFIX = new String[]{"130", "131",
            "132", "133", "134", "135", "136", "137", "138", "139", "150",
            "151", "152", "153", "154", "155", "156", "157", "158", "159",
            "180", "181", "182", "183", "184", "185", "186", "187", "188",
            "189"};

    private final static Pattern URL = Pattern
            .compile("^(https|http)://.*?$(net|com|.com.cn|org|me|)");

    public static boolean checkLocation(String mdn) {
        return checkMDN(mdn, false);
    }

    public static boolean checkMDN(String mdn) {
        return checkMDN(mdn, true);
    }

    /**
     * 检查手机号码合法性
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNo(String mobiles) {
        Pattern p = Pattern
                .compile("^((13[0-9])|(14[0-9])|(15[^4,\\D])|(17[0-9])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);

        return m.matches();
    }


    /**
     * 检查手机号码合法性
     *
     * @param mdn
     * @return
     */
    public static boolean checkMDN(String mdn, boolean checkLen) {
        if (mdn == null || mdn.equals("")) {
            return false;
        }
        //modify by zhangyp 去掉号码前边的+86
        if (mdn.startsWith("+86")) {
            mdn = mdn.substring(3);
        }
        if (checkLen && mdn.length() != 11) {
            return false;
        }
        boolean flag = false;
        String p = mdn.length() > 3 ? mdn.substring(0, 3) : mdn;
        int len = PHONE_PREFIX.length;
        for (int i = 0; i < len; i++) {
            if (p.equals(PHONE_PREFIX[i])) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            return false;
        }
        return true;
    }

    public static final char[] INVALID_CH_CN = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
            'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '.', ',', ';', ':', '!', '@', '/', '(', ')', '[', ']', '{',
            '}', '|', '#', '$', '%', '^', '&', '<', '>', '?', '\'', '+',
            '-', '*', '\\', '\"'};

    public static boolean checkCN(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        char[] cArray = str.toCharArray();
        int len = cArray.length;
        for (int i = 0; i < len; i++) {
            int len2 = INVALID_CH_CN.length;
            for (int j = 0; j < len2; j++) {
                if (cArray[i] == INVALID_CH_CN[j]) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * 检测邮箱合法性
     *
     * @param email
     * @return
     */
    public static boolean checkEmailValid(String email) {
        if ((email == null) || (email.trim().length() == 0)) {
            return false;
        }
        String regEx = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(email.trim().toLowerCase());

        return m.find();
    }

    private static final Pattern IPV4_PATTERN =
            Pattern.compile(
                    "^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");

    private static final Pattern IPV6_STD_PATTERN =
            Pattern.compile("^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$");

    private static final Pattern IPV6_HEX_COMPRESSED_PATTERN =
            Pattern.compile("^((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)$");

    static String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
            + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
            + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
            + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\:\\d{1,5}$";

    private static final Pattern IP_PORT = Pattern.compile(regex);

    /**
     * 检查是否IPV4地址
     *
     * @param input
     * @return
     */
    public static boolean isIPv4Address(final String input) {
        return IPV4_PATTERN.matcher(input).matches();
    }

    /**
     * 检查是否IPV6地址
     *
     * @param input
     * @return
     */
    public static boolean isIPv6StdAddress(final String input) {
        return IPV6_STD_PATTERN.matcher(input).matches();
    }

    public static boolean isIPv6HexCompressedAddress(final String input) {
        return IPV6_HEX_COMPRESSED_PATTERN.matcher(input).matches();
    }

    public static boolean isIPv6Address(final String input) {
        return isIPv6StdAddress(input) || isIPv6HexCompressedAddress(input);
    }

    public static boolean validateIpPort(final String input) {
        return IP_PORT.matcher(input).matches();
    }


    /**
     * 描述：是否只是字母和数字.
     *
     * @param str 指定的字符串
     * @return 是否只是字母和数字:是为true，否则false
     */
    public static Boolean isNumberLetter(String str) {
        Boolean isNoLetter = false;
        String expr = "^[A-Za-z0-9]+$";
        if (str.matches(expr)) {
            isNoLetter = true;
        }
        return isNoLetter;
    }

    /**
     * 描述：是否只是数字.
     *
     * @param str 指定的字符串
     * @return 是否只是数字:是为true，否则false
     */
    public static Boolean isNumber(String str) {
        Boolean isNumber = false;
        String expr = "^[0-9]+$";
        if (str.matches(expr)) {
            isNumber = true;
        }
        return isNumber;
    }


    /**
     * 描述：是否是中文.
     *
     * @param str 指定的字符串
     * @return 是否是中文:是为true，否则false
     */
    public static Boolean isChinese(String str) {
        Boolean isChinese = true;
        String chinese = "[\u0391-\uFFE5]";
        if (!StringUtils.isEmpty(str)) {
            //获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
            int len = str.length();
            for (int i = 0; i < len; i++) {
                //获取一个字符
                String temp = str.substring(i, i + 1);
                //判断是否为中文字符
                if (temp.matches(chinese)) {
                } else {
                    isChinese = false;
                }
            }
        }
        return isChinese;
    }

    /**
     * 描述：是否包含中文.
     *
     * @param str 指定的字符串
     * @return 是否包含中文:是为true，否则false
     */
    public static Boolean isContainChinese(String str) {
        Boolean isChinese = false;
        String chinese = "[\u0391-\uFFE5]";
        if (!StringUtils.isEmpty(str)) {
            //获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
            int len = str.length();
            for (int i = 0; i < len; i++) {
                //获取一个字符
                String temp = str.substring(i, i + 1);
                //判断是否为中文字符
                if (temp.matches(chinese)) {
                    isChinese = true;
                } else {
                }
            }
        }
        return isChinese;
    }


    /**
     * 判断是否为一个合法的url地址
     *
     * @param str
     * @return
     */
    public static boolean isUrl(String str) {
        if (str == null || str.trim().length() == 0)
            return false;
        return URL.matcher(str).matches();
    }
}

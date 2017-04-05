package top.slantech.yzlibrary.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;


/**
 * 拼音转换工具
 * 1、将字符串中的中文转化为拼音,其他字符不变 getPingYin(String inputString)
 * 2、汉字转换位汉语拼音首字母，英文字符不变 converterToFirstSpell(String chines)
 */
public class PingYinUtil {
	/**
	 * 将字符串中的中文转化为拼音,其他字符不变
	 * 
	 * @param inputString
	 * @return
	 */
	public static String getPingYin(String inputString) {
		if ("".equals(inputString)) {
			return "";
		}
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_V);
		char[] input = inputString.trim().toCharArray();
		String output = "";
		if (input != null && input.length > 0) {
			if ((input[0] > 97 && input[0] < 122) || (input[0] > 65 && input[0] < 90)) {
				output = String.valueOf(input[0]).toLowerCase();
			} else {
				try {
					int len = input.length;
					for (int i = 0; i < len; i++) {
						if (Character.toString(input[i]).matches("[\\u4E00-\\u9FA5]+")) {
							String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);
							output += temp[0];
						} else
							output += Character.toString(input[i]);
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					ULog.e(e.toString());
				}
			}

		}
		return output.toUpperCase();
	}

	/**
	 * 汉字转换位汉语拼音首字母，英文字符不变
	 * 
	 * @param chines 汉字
	 * @return 拼音
	 */
	public static String converterToFirstSpell(String chines) {
		if ("".equals(chines)) {
			return "";
		}
		chines = chines.substring(0, 1);
		String pinyinName = "";
		char[] nameChar = chines.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		int len = nameChar.length;
		for (int i = 0; i < len; i++) {
			if (nameChar[i] > 128) {
				try {
					if (null == nameChar || len == 0) {
						return "";
					}
					pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0].charAt(0);
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					ULog.e(e.toString());
				}
			} else {
				pinyinName += nameChar[i];
			}
		}
		return pinyinName.toUpperCase();
	}

}

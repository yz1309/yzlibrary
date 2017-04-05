package top.slantech.yzlibrary.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import top.slantech.yzlibrary.R;
import top.slantech.yzlibrary.bean.ChatEmoji;


/**
 * @文件描述 : 表情转换工具
 */
public class FaceConversionUtil {

    /**
     * 每一页表情的个数
     */
    private int pageSize = 20;

    private static FaceConversionUtil mFaceConversionUtil;

    /**
     * 保存于内存中的表情HashMap
     */
    private HashMap<String, String> emojiMap = new HashMap<String, String>();

    /**
     * 保存于内存中的表情集合
     */
    private List<ChatEmoji> emojis = new ArrayList<ChatEmoji>();

    /**
     * 表情分页的结果集合
     */
    public List<List<ChatEmoji>> emojiLists = new ArrayList<List<ChatEmoji>>();

    String[] emoStrs = new String[]
            {"emoji_1.png,[可爱]",
                    "emoji_2.png,[笑脸]",
                    "emoji_3.png,[囧]",
                    "emoji_4.png,[生气]",
                    "emoji_5.png,[鬼脸]",
                    "emoji_6.png,[花心]",
                    "emoji_7.png,[害怕]",
                    "emoji_8.png,[我汗]",
                    "emoji_9.png,[尴尬]",
                    "emoji_10.png,[哼哼]",
                    "emoji_11.png,[忧郁]",
                    "emoji_12.png,[呲牙]",
                    "emoji_13.png,[媚眼]",
                    "emoji_14.png,[累]",
                    "emoji_15.png,[苦逼]",
                    "emoji_16.png,[瞌睡]",
                    "emoji_17.png,[哎呀]",
                    "emoji_18.png,[刺瞎]",
                    "emoji_19.png,[哭]",
                    "emoji_20.png,[激动]",
                    "emoji_21.png,[难过]",
                    "emoji_22.png,[害羞]",
                    "emoji_23.png,[高兴]",
                    "emoji_24.png,[愤怒]",
                    "emoji_25.png,[亲]",
                    "emoji_26.png,[飞吻]",
                    "emoji_27.png,[得意]",
                    "emoji_28.png,[惊恐]",
                    "emoji_29.png,[口罩]",
                    "emoji_30.png,[惊讶]",
                    "emoji_31.png,[委屈]",
                    "emoji_32.png,[生病]",
                    "emoji_33.png,[红心]",
                    "emoji_34.png,[心碎]",
                    "emoji_35.png,[玫瑰]",
                    "emoji_36.png,[花]",
                    "emoji_37.png,[外星人]",
                    "emoji_38.png,[金牛座]",
                    "emoji_39.png,[双子座]",
                    "emoji_40.png,[巨蟹座]",
                    "emoji_41.png,[狮子座]",
                    "emoji_42.png,[处女座]",
                    "emoji_43.png,[天平座]",
                    "emoji_44.png,[天蝎座]",
                    "emoji_45.png,[射手座]",
                    "emoji_46.png,[摩羯座]",
                    "emoji_47.png,[水瓶座]",
                    "emoji_48.png,[白羊座]",
                    "emoji_49.png,[双鱼座]",
                    "emoji_50.png,[星座]",
                    "emoji_51.png,[男孩]",
                    "emoji_52.png,[女孩]",
                    "emoji_53.png,[嘴唇]",
                    "emoji_54.png,[爸爸]",
                    "emoji_55.png,[妈妈]",
                    "emoji_56.png,[衣服]",
                    "emoji_57.png,[皮鞋]",
                    "emoji_58.png,[照相]",
                    "emoji_59.png,[电话]",
                    "emoji_60.png,[石头]",
                    "emoji_61.png,[胜利]",
                    "emoji_62.png,[禁止]",
                    "emoji_63.png,[滑雪]",
                    "emoji_64.png,[高尔夫]",
                    "emoji_65.png,[网球]",
                    "emoji_66.png,[棒球]",
                    "emoji_67.png,[冲浪]",
                    "emoji_68.png,[足球]",
                    "emoji_69.png,[小鱼]",
                    "emoji_70.png,[问号]",
                    "emoji_71.png,[叹号]",
                    "emoji_179.png,[顶]",
                    "emoji_180.png,[写字]",
                    "emoji_181.png,[衬衫]",
                    "emoji_182.png,[小花]",
                    "emoji_183.png,[郁金香]",
                    "emoji_184.png,[向日葵]",
                    "emoji_185.png,[鲜花]",
                    "emoji_186.png,[椰树]",
                    "emoji_187.png,[仙人掌]",
                    "emoji_188.png,[气球]",
                    "emoji_189.png,[炸弹]",
                    "emoji_190.png,[喝彩]",
                    "emoji_191.png,[剪子]",
                    "emoji_192.png,[蝴蝶结]",
                    "emoji_193.png,[机密]",
                    "emoji_194.png,[铃声]",
                    "emoji_195.png,[女帽]",
                    "emoji_196.png,[裙子]",
                    "emoji_197.png,[理发店]",
                    "emoji_198.png,[和服]",
                    "emoji_199.png,[比基尼]",
                    "emoji_200.png,[拎包]",
                    "emoji_201.png,[拍摄]",
                    "emoji_202.png,[铃铛]",
                    "emoji_203.png,[音乐]",
                    "emoji_204.png,[心星]",
                    "emoji_205.png,[粉心]",
                    "emoji_206.png,[丘比特]",
                    "emoji_207.png,[吹气]",
                    "emoji_208.png,[口水]",
                    "emoji_209.png,[对]",
                    "emoji_210.png,[错]",
                    "emoji_211.png,[绿茶]",
                    "emoji_212.png,[面包]",
                    "emoji_213.png,[面条]",
                    "emoji_214.png,[咖喱饭]",
                    "emoji_215.png,[饭团]",
                    "emoji_216.png,[麻辣烫]",
                    "emoji_217.png,[寿司]",
                    "emoji_218.png,[苹果]",
                    "emoji_219.png,[橙子]",
                    "emoji_220.png,[草莓]",
                    "emoji_221.png,[西瓜]",
                    "emoji_222.png,[柿子]",
                    "emoji_223.png,[眼睛]",
                    "emoji_224.png,[好的"};

    private FaceConversionUtil() {

    }

    public static FaceConversionUtil getInstace() {
        if (mFaceConversionUtil == null) {
            mFaceConversionUtil = new FaceConversionUtil();
        }
        return mFaceConversionUtil;
    }

    /**
     * 得到一个SpanableString对象，通过传入的字符串,并进行正则判断
     *
     * @param context
     * @param str
     * @return
     */
    public SpannableString getExpressionString(Context context, String str) {
        SpannableString spannableString = new SpannableString(str);
        // 正则表达式比配字符串里是否含有表情，如： 我好[开心]啊
        String zhengze = "\\[[^\\]]+\\]";
        // 通过传入的正则表达式来生成一个pattern
        Pattern sinaPatten = Pattern.compile(zhengze, Pattern.CASE_INSENSITIVE);
        try {
            dealExpression(context, spannableString, sinaPatten, 0);
        } catch (Exception e) {
            Log.e("dealExpression", e.getMessage());
        }
        return spannableString;
    }

    /**
     * 添加表情
     *
     * @param context
     * @param imgId
     * @param spannableString
     * @return
     */
    public SpannableString addFace(Context context, int imgId,
                                   String spannableString) {
        if (TextUtils.isEmpty(spannableString)) {
            return null;
        }
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
                imgId);
        bitmap = Bitmap.createScaledBitmap(bitmap, 35, 35, true);
        ImageSpan imageSpan = new ImageSpan(context, bitmap);
        SpannableString spannable = new SpannableString(spannableString);
        spannable.setSpan(imageSpan, 0, spannableString.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }

    /**
     * 对spanableString进行正则判断，如果符合要求，则以表情图片代替
     *
     * @param context
     * @param spannableString
     * @param patten
     * @param start
     * @throws Exception
     */
    private void dealExpression(Context context,
                                SpannableString spannableString, Pattern patten, int start)
            throws Exception {
        Matcher matcher = patten.matcher(spannableString);
        while (matcher.find()) {
            String key = matcher.group();
            // 返回第一个字符的索引的文本匹配整个正则表达式,ture 则继续递归
            if (matcher.start() < start) {
                continue;
            }
            String value = emojiMap.get(key);
            if (TextUtils.isEmpty(value)) {
                continue;
            }
            int resId = context.getResources().getIdentifier(value, "drawable",
                    context.getPackageName());

            // 通过上面匹配得到的字符串来生成图片资源id
            // Field field=R.drawable.class.getDeclaredField(value);
            // int resId=Integer.parseInt(field.get(null).toString());
            if (resId != 0) {
                Bitmap bitmap = BitmapFactory.decodeResource(
                        context.getResources(), resId);
                bitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
                // 通过图片资源id来得到bitmap，用一个ImageSpan来包装
                ImageSpan imageSpan = new ImageSpan(bitmap);
                // 计算该图片名字的长度，也就是要替换的字符串的长度
                int end = matcher.start() + key.length();
                // 将该图片替换字符串中规定的位置中
                spannableString.setSpan(imageSpan, matcher.start(), end,
                        Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                if (end < spannableString.length()) {
                    // 如果整个字符串还未验证完，则继续。。
                    dealExpression(context, spannableString, patten, end);
                }
                break;
            }
        }
    }

    /**
     * 将带有表情标签的数据解析成对应的图片
     *
     * @param tv
     * @param source
     * @return
     */
    public SpannableString getEmojiContent(final Context context, final TextView tv, String source) {
        SpannableString spannableString = new SpannableString(source);
        try {
            // 表情保存的模式 []
            String emojiRegex = "\\[[^\\]]+\\]";
            Pattern pattern = Pattern.compile(emojiRegex);
            Matcher matcher = pattern.matcher(spannableString);
            while (matcher.find()) {
                String key = matcher.group();
                int start = matcher.start();
                String value = emojiMap.get(key);
                if (TextUtils.isEmpty(value)) {
                    continue;
                }
                int resId = context.getResources().getIdentifier(value, "drawable",
                        context.getPackageName());
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId);
                if (bitmap != null) {
                    int size = (int) tv.getTextSize();
                    bitmap = Bitmap.createScaledBitmap(bitmap, size, size, true);
                    ImageSpan imageSpan = new ImageSpan(context, bitmap);
                    spannableString.setSpan(imageSpan, start, start + key.length(),
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return spannableString;
    }

    public void getFileText(Context context) {
        ParseData(getEmojiFile(), context);
    }

    /**
     * 解析字符
     *
     * @param data
     */
    private void ParseData(List<String> data, Context context) {
        if (data == null) {
            return;
        }
        ChatEmoji emojEentry;
        try {
            for (String str : data) {

                String[] text = str.split(",");
                String fileName = text[0]
                        .substring(0, text[0].lastIndexOf("."));
                emojiMap.put(text[1], fileName);
                int resID = context.getResources().getIdentifier(fileName,
                        "drawable", context.getPackageName());

                if (resID != 0) {
                    emojEentry = new ChatEmoji();
                    emojEentry.setId(resID);
                    emojEentry.setCharacter(text[1]);
                    emojEentry.setFaceName(fileName);
                    emojis.add(emojEentry);
                }
            }
            int pageCount = (int) Math.ceil(emojis.size() / 20 + 0.1);

            for (int i = 0; i < pageCount; i++) {
                emojiLists.add(getData(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取分页数据
     *
     * @param page
     * @return
     */
    private List<ChatEmoji> getData(int page) {
        int startIndex = page * pageSize;
        int endIndex = startIndex + pageSize;

        if (endIndex > emojis.size()) {
            endIndex = emojis.size();
        }
        // 不这么写，会在viewpager加载中报集合操作异常，我也不知道为什么
        List<ChatEmoji> list = new ArrayList<ChatEmoji>();
        list.addAll(emojis.subList(startIndex, endIndex));
        if (list.size() < pageSize) {
            for (int i = list.size(); i < pageSize; i++) {
                ChatEmoji object = new ChatEmoji();
                list.add(object);
            }
        }
        if (list.size() == pageSize) {
            ChatEmoji object = new ChatEmoji();
            object.setId(R.drawable.face_del_icon);
            list.add(object);
        }
        return list;
    }


    public List<String> getEmojiFile() {
        List<String> list = new ArrayList<String>();
        int len = emoStrs.length;
        for (int i = 0; i < len; i++) {
            list.add(emoStrs[i]);
        }
        return list;
    }
}
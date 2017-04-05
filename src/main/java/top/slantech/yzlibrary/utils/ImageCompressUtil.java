package top.slantech.yzlibrary.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * 图片压缩工具类
 * 功能描述：
 * 1、压缩单张图片方法 compressImage(Activity,"/sd/xx.png",new ProcessImgCallBack);
 * 2、压缩图片集合方法 compressImageList(Activity,List<String>,new ProcessImgCallBack);
 */
public class ImageCompressUtil {

    private static List<String> mImageList = new ArrayList<String>();// 临时图片集合
    private static String mImagePath = ""; // 单个临时图片
    public static String cachePath = "";
    public static int reqWidth = 480;//320;
    public static int reqHeight = 800;//480;

    /**
     * 压缩单张图片方法
     * @param ctx
     * @param filePath
     * @param callBack
     */
    public static void compressImage(final Context ctx, final String filePath, final ProcessImgCallBack callBack) {
        mImagePath = "";//清空路径

        new Thread(new Runnable() {
            @Override
            public void run() {
                //如果路径是图片，则进行压缩
                if (FileUtils.isPic(filePath)) {
                    mImagePath = compress(ctx, filePath);
                }
                callBack.compressSuccess(mImagePath);
            }
        }).start();
    }

    /**
     * 压缩图片集合方法
     * @param ctx
     * @param fileList
     * @param callBack
     */
    public static void compressImageList(final Context ctx, final List<String> fileList, final ProcessImgListCallBack callBack) {
        mImageList.clear();//清空集合
        if (fileList == null || fileList.isEmpty()) {
            callBack.compressSuccess(mImageList);
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                String tempPath = "";
                for (String imagePath : fileList) {
                    if (FileUtils.isPic(imagePath)) {
                        tempPath = compress(ctx, imagePath);
                        mImageList.add(tempPath);
                    }
                }
                callBack.compressSuccess(mImageList);
            }
        }).start();

    }

    /**
     * 图片压缩的方法
     * @param ctx
     * @param filePath
     * @return
     */
    public static String compress(Context ctx, String filePath) {

        if (TextUtils.isEmpty(filePath))
            return filePath;

        File file = new File(filePath);
        if (!file.exists())//判断路径是否存在
            return filePath;

        if (file.length() < 1)//文件是否为空
            return null;

        File tempFile = getDiskCacheDir(ctx);
        String outImagePath = tempFile.getAbsolutePath(); // 输出图片文件路径


        int degree = ImageRotateUtil.getBitmapDegree(filePath); // 检查图片的旋转角度

        //谷歌官网压缩图片
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);

        // 旋转:这步处理主要是为了处理三星手机拍的照片
        if (degree > 0) {
            bitmap = ImageRotateUtil.rotateBitmapByDegree(bitmap, degree);
        }

        // 写入文件
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(tempFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, fos);
            fos.flush();
            fos.close();
            bitmap.recycle();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return filePath;
        } catch (Exception e) {
            e.printStackTrace();
            return filePath;
        }

        return outImagePath;
    }


    /**
     * 计算压缩比例值
     * 按照2、3、4...倍压缩
     *
     * @param options   解析图片的配置信息
     * @param reqWidth  所需图片压缩尺寸最小宽度
     * @param reqHeight 所需图片压缩尺寸最小高度
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int picheight = options.outHeight;
        final int picwidth = options.outWidth;
        Log.e("yz", "原尺寸:" + picwidth + "*" + picheight);

        int targetheight = picheight;
        int targetwidth = picwidth;
        int inSampleSize = 1;

        if (targetheight > reqHeight || targetwidth > reqWidth) {
            while (targetheight >= reqHeight && targetwidth >= reqWidth) {
                inSampleSize += 1;
                targetheight = picheight / inSampleSize;
                targetwidth = picwidth / inSampleSize;
            }
        }

        Log.e("yz", "最终压缩比例:" + inSampleSize + "倍/新尺寸:" + targetwidth + "*" + targetheight);
        return inSampleSize;
    }


    //图片集合压缩成功后的回调接口
    public interface ProcessImgListCallBack {
        void compressSuccess(List<String> imgList);
    }

    //单张图片压缩成功后的回调接口
    public interface ProcessImgCallBack {
        void compressSuccess(String imgPath);
    }



    /**
     * 将压缩后的图片存储在缓存中
     */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static File getDiskCacheDir(Context ctx) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = ctx.getExternalCacheDir().getPath();
        } else {
            cachePath = ctx.getCacheDir().getPath();
        }
        String uniqueName = System.currentTimeMillis() + "_tmp.jpg";
        return new File(cachePath + File.separator + uniqueName);
    }
}
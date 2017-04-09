package top.slantech.yzlibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.provider.MediaStore;
import android.text.TextUtils;

import org.apache.http.Header;
import org.apache.http.HttpResponse;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import top.slantech.yzlibrary.R;

/**
 * 文件操作类
 * 功能
 * 1、通过文件的网络地址从SD卡中读取图片，如果SD中没有则自动下载并保存 getBitmapFromSD("/sd/ese","http://xx.png",ImageUtils.CUTIMG,100,100);
 * 2、通过文件的本地地址从SD卡读取图片 getBitmapFromSD("/sd/ese/xx.png",100,100);
 * 3、通过文件的本地地址从SD卡读取图片 getBitmapFromSD(file),getBitmapFromSD("/sd/ese/xx.png");
 * 4、将图片的byte[]写入本地文件 getBitmapFromByte("/sd/ese",byte[],"test.png",ImageUtils.CUTIMG,100,100);
 * 5、根据URL从互连网获取图片 getBitmapFromURL("http://xx.png",ImageUtils.CUTIMG,100,100);
 * 6、获取src中的图片资源 getBitmapFromSrc("image/arrow.png");
 * 7、获取Asset中的图片资源 getBitmapFromAsset(Activity,"test");
 * 8、获取Asset中的图片资源 getDrawableFromAsset(Activity,"test");
 * 9、下载网络文件到SD卡中.如果SD中存在同名文件将不再下载 downloadFile("http://xx.png","/sd/ese");
 * 10、获取网络文件的大小 getContentLengthFromUrl("http://xx.png");
 * 11、通过网络获取文件名 getRealFileNameFromUrl("http://xx.png");
 * 12、通过网络获取真实文件名(xx.后缀)  getRealFileName(HttpURLConnection);
 * 13、通过网络获取真实文件名(xx.后缀)  getRealFileName(HttpResponse);
 * 14、获取文件名（不含后缀） getCacheFileNameFromUrl("http://xx.png");
 * 15、外链模式和通过网络获取文件名（.后缀） getCacheFileNameFromUrl("http://xx.png",HttpResponse);
 * 16、外链模式和通过网络获取文件名（.后缀） getCacheFileNameFromUrl("http://xx.png",HttpURLConnection);
 * 17、获取文件后缀 getMIMEFromUrl("http://xx.png",HttpURLConnection);
 * 18、获取文件后缀 getMIMEFromUrl("http://xx.png",HttpResponse);
 * 19、从sd卡中的文件读取到byte[] getByteArrayFromSD("/sd/xx.txt");
 * 20、将byte数组写入文件 writeByteArrayToSD("/sd/xx",byte[],true);
 * 21、SD卡是否能用 isCanUseSD();
 * 22、计算sdcard上的剩余空间 freeSpaceOnSD();
 * 23、根据文件的最后修改时间进行排序
 * 24、获取文件夹下的文件列表 直级 getFileByPath("/sd/xx");
 * 25、删除指定文件夹下的所有文件 clearFile("/sd/xx");
 * 26、读取Assets目录的文件内容 readAssetsByName(Activity,"test","UTF-8");
 * 27、读取Assets目录的文件内容 readAssetsByName(Activity,"test");
 * 28、读取Assets目录的文件内容 readAssetsByName(InputStream);
 * 29、读取Raw目录的文件内容 readRawByName(Activity,R.raw.test,"UTF-8");
 * 30、保存图片至本地并将图片目录显示到系统图库中 saveFile(Bitmap,"/sd/xx","test.png",Activity);
 * 31、保存文件 至指定文件夹的本地中同时保存了2份一份系统图库一份指定目录 通知系统图库更新显示 saveFile2(Bitmap,"/sd/xx","test.png",Activity);
 * 32、Drawable 转 Bitmap drawable2Bitmap(Drawable);
 * 33、Bitmap对象转换Drawable对象 bitmap2Drawable(Bitmap);
 * 34、Gets the extension of a file name, like ".png" or ".jpg" getExtension("/sd/xx.png");
 * 35、是否图片 isPic("/sd/xx.png");
 * 36、获取文件后缀名(png) getRealFileNameFromPath("/sd/xx.png");
 * 37、【通过Okio方法】，通过路径得到文件内容 readContentFromPath(String path);
 * 38、【通过Okio方法】，向指定文件写入文件内容 writeContentFromPath(String path, String content);
 * 39、读取txt文件的内容 readTxtFile("xx/xx.txt")
 * 40、根据文件后缀名获得对应的MIME类型 getMIMEType(File file)
 * 41、获取文件名称对应的图标 getFileIcon(String fileName)
 * 42、解压压缩文件 unZipResourcePackage(String savePath, String toPath)
 * 43、打开文件 openFile(context,file)
 */
public class FileUtils {
    // 保存的路径
    //private final static String PATH = Environment.getExternalStorageDirectory().toString();
    // 图片的保存的路径[根据项目修改]
    // private final static String ALBUM_PATH = PATH + "/slantech/xiaketoys/img/";


    /** 默认APP根目录. */
    //private static  String downloadRootDir = null;

    /** 默认下载图片文件目录. */
    //private static  String imageDownloadDir = null;

    /** 默认下载文件目录. */
    //private static  String fileDownloadDir = null;

    /** 默认缓存目录. */
    //private static  String cacheDownloadDir = null;


    /**
     * 剩余空间大于200M才使用SD缓存.
     */
    private static int freeSdSpaceNeededToCache = 200 * 1024 * 1024;

    /**
     * 通过文件的网络地址从SD卡中读取图片，如果SD中没有则自动下载并保存.
     *
     * @param url           文件的网络地址
     * @param type          图片的处理类型（剪切或者缩放到指定大小，参考AbImageUtil类）
     *                      如果设置为原图，则后边参数无效，得到原图
     * @param desiredWidth  新图片的宽
     * @param desiredHeight 新图片的高
     * @return Bitmap 新图片
     */
    public static Bitmap getBitmapFromSD(String imageDownloadDir, String url, int type, int desiredWidth, int desiredHeight) {
        Bitmap bitmap = null;
        try {
            if (TextUtils.isEmpty(url)) {
                return null;
            }

            //SD卡不存在 或者剩余空间不足了就不缓存到SD卡了
            if (!isCanUseSD() || freeSdSpaceNeededToCache < freeSpaceOnSD()) {
                bitmap = getBitmapFromURL(url, type, desiredWidth, desiredHeight);
                return bitmap;
            }
            //下载文件，如果不存在就下载，存在直接返回地址
            String downFilePath = downloadFile(url, imageDownloadDir);
            if (downFilePath != null) {
                //获取图片
                return getBitmapFromSD(new File(downFilePath), type, desiredWidth, desiredHeight);
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }


    /**
     * 通过文件的本地地址从SD卡读取图片.
     *
     * @param file          the file
     * @param type          图片的处理类型（剪切或者缩放到指定大小，参考AbConstant类）
     *                      如果设置为原图，则后边参数无效，得到原图
     * @param desiredWidth  新图片的宽
     * @param desiredHeight 新图片的高
     * @return Bitmap 新图片
     */
    public static Bitmap getBitmapFromSD(File file, int type, int desiredWidth, int desiredHeight) {
        Bitmap bitmap = null;
        try {
            //SD卡是否存在
            if (!isCanUseSD()) {
                return null;
            }

            //文件是否存在
            if (!file.exists()) {
                return null;
            }

            //文件存在
            if (type == ImageUtils.CUTIMG) {
                bitmap = ImageUtils.cutImg(file, desiredWidth, desiredHeight);
            } else if (type == ImageUtils.SCALEIMG) {
                bitmap = ImageUtils.scaleImg(file, desiredWidth, desiredHeight);
            } else {
                bitmap = ImageUtils.getBitmap(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 通过文件的本地地址从SD卡读取图片.
     *
     * @param file the file
     * @return Bitmap 图片
     */
    public static Bitmap getBitmapFromSD(File file) {
        Bitmap bitmap = null;
        try {
            //SD卡是否存在
            if (!isCanUseSD()) {
                return null;
            }
            //文件是否存在
            if (!file.exists()) {
                return null;
            }
            //文件存在
            bitmap = ImageUtils.getBitmap(file);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 通过文件的本地地址从SD卡读取图片.
     *
     * @param imagePath imagePath
     * @return Bitmap
     */
    public static Bitmap getBitmapFromSD(String imagePath) {
        Bitmap bitmap = null;
        try {
            File file = new File(imagePath);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            FileInputStream fis = new FileInputStream(file);
            bitmap = BitmapFactory.decodeStream(fis, null, options);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }


    /**
     * 将图片的byte[]写入本地文件.
     *
     * @param imgByte       图片的byte[]形势
     * @param fileName      文件名称，需要包含后缀，如.jpg
     * @param type          图片的处理类型（剪切或者缩放到指定大小，参考AbConstant类）
     * @param desiredWidth  新图片的宽
     * @param desiredHeight 新图片的高
     * @return Bitmap 新图片
     */
    public static Bitmap getBitmapFromByte(String imageDownloadDir, byte[] imgByte, String fileName, int type, int desiredWidth, int desiredHeight) {
        FileOutputStream fos = null;
        DataInputStream dis = null;
        ByteArrayInputStream bis = null;
        Bitmap bitmap = null;
        File file = null;
        try {
            if (imgByte != null) {

                file = new File(imageDownloadDir + fileName);
                if (!file.exists()) {
                    file.createNewFile();
                }
                fos = new FileOutputStream(file);
                int readLength = 0;
                bis = new ByteArrayInputStream(imgByte);
                dis = new DataInputStream(bis);
                byte[] buffer = new byte[1024];

                while ((readLength = dis.read(buffer)) != -1) {
                    fos.write(buffer, 0, readLength);
                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                    }
                }
                fos.flush();

                bitmap = getBitmapFromSD(file, type, desiredWidth, desiredHeight);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (dis != null) {
                try {
                    dis.close();
                } catch (Exception e) {
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (Exception e) {
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception e) {
                }
            }
        }
        return bitmap;
    }

    /**
     * 根据URL从互连网获取图片.
     *
     * @param url           要下载文件的网络地址
     * @param type          图片的处理类型（剪切或者缩放到指定大小，参考AbConstant类）
     * @param desiredWidth  新图片的宽
     * @param desiredHeight 新图片的高
     * @return Bitmap 新图片
     */
    public static Bitmap getBitmapFromURL(String url, int type, int desiredWidth, int desiredHeight) {
        Bitmap bit = null;
        try {
            bit = ImageUtils.getBitmap(url, type, desiredWidth, desiredHeight);
        } catch (Exception e) {
            //AbLogUtil.d(AbFileUtil.class, "下载图片异常："+e.getMessage());
        }
        return bit;
    }

    /**
     * 获取src中的图片资源.
     *
     * @param src 图片的src路径，如（“image/arrow.png”）
     * @return Bitmap 图片
     */
    public static Bitmap getBitmapFromSrc(String src) {
        Bitmap bit = null;
        try {
            bit = BitmapFactory.decodeStream(FileUtils.class.getResourceAsStream(src));
        } catch (Exception e) {
            //AbLogUtil.d(AbFileUtil.class, "获取图片异常："+e.getMessage());
        }
        return bit;
    }

    /**
     * 获取Asset中的图片资源.
     *
     * @param context  the context
     * @param fileName the file name
     * @return Bitmap 图片
     */
    public static Bitmap getBitmapFromAsset(Context context, String fileName) {
        Bitmap bit = null;
        try {
            AssetManager assetManager = context.getAssets();
            InputStream is = assetManager.open(fileName);
            bit = BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            //AbLogUtil.d(AbFileUtil.class, "获取图片异常："+e.getMessage());
        }
        return bit;
    }

    /**
     * 获取Asset中的图片资源.
     *
     * @param context  the context
     * @param fileName the file name
     * @return Drawable 图片
     */
    public static Drawable getDrawableFromAsset(Context context, String fileName) {
        Drawable drawable = null;
        try {
            AssetManager assetManager = context.getAssets();
            InputStream is = assetManager.open(fileName);
            drawable = Drawable.createFromStream(is, null);
        } catch (Exception e) {
            //AbLogUtil.d(AbFileUtil.class, "获取图片异常："+e.getMessage());
        }
        return drawable;
    }

    /**
     * 下载网络文件到SD卡中.如果SD中存在同名文件将不再下载
     *
     * @param url              要下载文件的网络地址
     * @param imageDownloadDir the dir path
     * @return String 下载好的本地文件地址
     */
    public static String downloadFile(String url, String imageDownloadDir) {
        InputStream in = null;
        FileOutputStream fileOutputStream = null;
        HttpURLConnection connection = null;
        String downFilePath = null;
        File file = null;
        try {
            if (!isCanUseSD()) {
                return null;
            }
            //先判断SD卡中有没有这个文件，不比较后缀部分比较
            String fileNameNoMIME = getCacheFileNameFromUrl(url);
            File parentFile = new File(imageDownloadDir);
            File[] files = parentFile.listFiles();
            int len = files.length;
            for (int i = 0; i < len; ++i) {
                String fileName = files[i].getName();
                String name = fileName.substring(0, fileName.lastIndexOf("."));
                if (name.equals(fileNameNoMIME)) {
                    //文件已存在
                    return files[i].getPath();
                }
            }

            URL mUrl = new URL(url);
            connection = (HttpURLConnection) mUrl.openConnection();
            connection.connect();
            //获取文件名，下载文件
            String fileName = getCacheFileNameFromUrl(url, connection);

            file = new File(imageDownloadDir, fileName);
            downFilePath = file.getPath();
            if (!file.exists()) {
                file.createNewFile();
            } else {
                //文件已存在
                return file.getPath();
            }
            in = connection.getInputStream();
            fileOutputStream = new FileOutputStream(file);
            byte[] b = new byte[1024];
            int temp = 0;
            while ((temp = in.read(b)) != -1) {
                fileOutputStream.write(b, 0, temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //AbLogUtil.e(AbFileUtil.class, "有文件下载出错了,已删除");
            //检查文件大小,如果文件为0B说明网络不好没有下载成功，要将建立的空文件删除
            if (file != null) {
                file.delete();
            }
            file = null;
            downFilePath = null;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return downFilePath;
    }

    /**
     * 获取网络文件的大小.
     *
     * @param Url 图片的网络路径
     * @return int 网络文件的大小
     */
    public static int getContentLengthFromUrl(String Url) {
        int mContentLength = 0;
        try {
            URL url = new URL(Url);
            HttpURLConnection mHttpURLConnection = (HttpURLConnection) url.openConnection();
            mHttpURLConnection.setConnectTimeout(5 * 1000);
            mHttpURLConnection.setRequestMethod("GET");
            mHttpURLConnection.setRequestProperty("Accept", "image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*");
            mHttpURLConnection.setRequestProperty("Accept-Language", "zh-CN");
            mHttpURLConnection.setRequestProperty("Referer", Url);
            mHttpURLConnection.setRequestProperty("Charset", "UTF-8");
            mHttpURLConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
            mHttpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            mHttpURLConnection.connect();
            if (mHttpURLConnection.getResponseCode() == 200) {
                // 根据响应获取文件大小
                mContentLength = mHttpURLConnection.getContentLength();
            }
        } catch (Exception e) {
            e.printStackTrace();
            //AbLogUtil.d(AbFileUtil.class, "获取长度异常："+e.getMessage());
        }
        return mContentLength;
    }

    /**
     * 通过网络获取文件名.
     *
     * @param url 文件地址
     * @return String 文件名
     */
    public static String getRealFileNameFromUrl(String url) {
        String name = null;
        try {
            if (TextUtils.isEmpty(url)) {
                return name;
            }

            URL mUrl = new URL(url);
            HttpURLConnection mHttpURLConnection = (HttpURLConnection) mUrl.openConnection();
            mHttpURLConnection.setConnectTimeout(5 * 1000);
            mHttpURLConnection.setRequestMethod("GET");
            mHttpURLConnection.setRequestProperty("Accept", "image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*");
            mHttpURLConnection.setRequestProperty("Accept-Language", "zh-CN");
            mHttpURLConnection.setRequestProperty("Referer", url);
            mHttpURLConnection.setRequestProperty("Charset", "UTF-8");
            mHttpURLConnection.setRequestProperty("User-Agent", "");
            mHttpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            mHttpURLConnection.connect();
            if (mHttpURLConnection.getResponseCode() == 200) {
                for (int i = 0; ; i++) {
                    String mine = mHttpURLConnection.getHeaderField(i);
                    if (mine == null) {
                        break;
                    }
                    if ("content-disposition".equals(mHttpURLConnection.getHeaderFieldKey(i).toLowerCase())) {
                        Matcher m = Pattern.compile(".*filename=(.*)").matcher(mine.toLowerCase());
                        if (m.find())
                            return m.group(1).replace("\"", "");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            //AbLogUtil.e(AbFileUtil.class, "网络上获取文件名失败");
        }
        return name;
    }


    /**
     * 通过网络获取真实文件名(xx.后缀).
     *
     * @param connection 连接
     * @return String 文件名
     */
    public static String getRealFileName(HttpURLConnection connection) {
        String name = null;
        try {
            if (connection == null) {
                return name;
            }
            if (connection.getResponseCode() == 200) {
                for (int i = 0; ; i++) {
                    String mime = connection.getHeaderField(i);
                    if (mime == null) {
                        break;
                    }
                    // "Content-Disposition","attachment; filename=1.txt"
                    // Content-Length
                    if ("content-disposition".equals(connection.getHeaderFieldKey(i).toLowerCase())) {
                        Matcher m = Pattern.compile(".*filename=(.*)").matcher(mime.toLowerCase());
                        if (m.find()) {
                            return m.group(1).replace("\"", "");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            //AbLogUtil.e(AbFileUtil.class, "网络上获取文件名失败");
        }
        return name;
    }

    /**
     * 通过网络获取真实文件名(xx.后缀).
     *
     * @param response the response
     * @return String 文件名
     */
    public static String getRealFileName(HttpResponse response) {
        String name = null;
        try {
            if (response == null) {
                return name;
            }
            //获取文件名
            Header[] headers = response.getHeaders("content-disposition");
            int len = headers.length;
            for (int i = 0; i < len; i++) {
                Matcher m = Pattern.compile(".*filename=(.*)").matcher(headers[i].getValue());
                if (m.find()) {
                    name = m.group(1).replace("\"", "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            //AbLogUtil.e(AbFileUtil.class, "网络上获取文件名失败");
        }
        return name;
    }

    /**
     * 获取文件名（不含后缀）.
     *
     * @param url 文件地址
     * @return String 文件名
     */
    public static String getCacheFileNameFromUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        return url;
    }


    /**
     * 外链模式和通过网络获取文件名（.后缀）.
     *
     * @param url      文件地址
     * @param response the response
     * @return String 文件名
     */
    public static String getCacheFileNameFromUrl(String url, HttpResponse response) {
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        String name = null;
        try {
            //获取后缀
            String suffix = getMIMEFromUrl(url, response);
            if (TextUtils.isEmpty(suffix)) {
                suffix = ".ab";
            }
            name = url + suffix;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }


    /**
     * 外链模式和通过网络获取文件名（.后缀）.
     *
     * @param url        文件地址
     * @param connection the connection
     * @return String 文件名
     */
    public static String getCacheFileNameFromUrl(String url, HttpURLConnection connection) {
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        String name = null;
        try {
            //获取后缀
            String suffix = getMIMEFromUrl(url, connection);
            if (TextUtils.isEmpty(suffix)) {
                suffix = ".ab";
            }
            name = url + suffix;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }


    /**
     * 获取文件后缀，本地.
     *
     * @param url        文件地址
     * @param connection the connection
     * @return String 文件后缀
     */
    public static String getMIMEFromUrl(String url, HttpURLConnection connection) {

        if (TextUtils.isEmpty(url)) {
            return null;
        }
        String suffix = null;
        try {
            //获取后缀
            if (url.lastIndexOf(".") != -1) {
                suffix = url.substring(url.lastIndexOf("."));
                if (suffix.indexOf("/") != -1 || suffix.indexOf("?") != -1 || suffix.indexOf("&") != -1) {
                    suffix = null;
                }
            }
            if (TextUtils.isEmpty(suffix)) {
                //获取文件名  这个效率不高
                String fileName = getRealFileName(connection);
                if (fileName != null && fileName.lastIndexOf(".") != -1) {
                    suffix = fileName.substring(fileName.lastIndexOf("."));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return suffix;
    }

    /**
     * 获取文件后缀，本地和网络.
     *
     * @param url      文件地址
     * @param response the response
     * @return String 文件后缀
     */
    public static String getMIMEFromUrl(String url, HttpResponse response) {

        if (TextUtils.isEmpty(url)) {
            return null;
        }
        String mime = null;
        try {
            //获取后缀
            if (url.lastIndexOf(".") != -1) {
                mime = url.substring(url.lastIndexOf("."));
                if (mime.indexOf("/") != -1 || mime.indexOf("?") != -1 || mime.indexOf("&") != -1) {
                    mime = null;
                }
            }
            if (TextUtils.isEmpty(mime)) {
                //获取文件名  这个效率不高
                String fileName = getRealFileName(response);
                if (fileName != null && fileName.lastIndexOf(".") != -1) {
                    mime = fileName.substring(fileName.lastIndexOf("."));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mime;
    }

    /**
     * 从sd卡中的文件读取到byte[].
     *
     * @param path sd卡中文件路径
     * @return byte[]
     */
    public static byte[] getByteArrayFromSD(String path) {
        byte[] bytes = null;
        ByteArrayOutputStream out = null;
        try {
            File file = new File(path);
            //SD卡是否存在
            if (!isCanUseSD()) {
                return null;
            }
            //文件是否存在
            if (!file.exists()) {
                return null;
            }

            long fileSize = file.length();
            if (fileSize > Integer.MAX_VALUE) {
                return null;
            }

            FileInputStream in = new FileInputStream(path);
            out = new ByteArrayOutputStream(1024);
            byte[] buffer = new byte[1024];
            int size = 0;
            while ((size = in.read(buffer)) != -1) {
                out.write(buffer, 0, size);
            }
            in.close();
            bytes = out.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                }
            }
        }
        return bytes;
    }

    /**
     * 将byte数组写入文件.
     *
     * @param path    the path
     * @param content the content
     * @param create  the create
     */
    public static void writeByteArrayToSD(String path, byte[] content, boolean create) {

        FileOutputStream fos = null;
        try {
            File file = new File(path);
            //SD卡是否存在
            if (!isCanUseSD()) {
                return;
            }
            //文件是否存在
            if (!file.exists()) {
                if (create) {
                    File parent = file.getParentFile();
                    if (!parent.exists()) {
                        parent.mkdirs();
                        file.createNewFile();
                    }
                } else {
                    return;
                }
            }
            fos = new FileOutputStream(path);
            fos.write(content);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception e) {
                }
            }
        }
    }

    /**
     * SD卡是否能用.
     *
     * @return true 可用,false不可用
     */
    public static boolean isCanUseSD() {
        try {
            return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 计算sdcard上的剩余空间.
     *
     * @return the int
     */
    public static int freeSpaceOnSD() {
        StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
        double sdFreeMB = ((double) stat.getAvailableBlocks() * (double) stat.getBlockSize()) / 1024 * 1024;
        return (int) sdFreeMB;
    }

    /**
     * 根据文件的最后修改时间进行排序.
     */
    public static class FileLastModifSort implements Comparator<File> {

        /* (non-Javadoc)
         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
         */
        public int compare(File arg0, File arg1) {
            if (arg0.lastModified() > arg1.lastModified()) {
                return 1;
            } else if (arg0.lastModified() == arg1.lastModified()) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    /**
     * 获取文件夹下的文件列表 直级
     *
     * @param path path
     * @return List file
     */
    public static List<File> getFileByPath(String path) {
        List<File> list = new ArrayList<>();
        File file = new File(path);
        File[] files = file.listFiles();
        if (file != null) {
            for (File model : files) {
                list.add(model);
            }
        }
        return list;
    }


    /**
     * 删除指定文件夹下的所有文件
     *
     * @param path path
     * @return true, if successful
     */
    public static boolean clearFile(String path) {
        try {
            if (!isCanUseSD()) {
                return false;
            }
            File fileDirectory = new File(path);
            File[] files = fileDirectory.listFiles();
            if (files == null) {
                return true;
            }
            int len = files.length;
            for (int i = 0; i < len; i++) {
                files[i].delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 读取Assets目录的文件内容.
     *
     * @param context  the context
     * @param name     the name
     * @param encoding the encoding
     * @return String
     */
    public static String readAssetsByName(Context context, String name, String... encoding) {
        String text = null;
        InputStreamReader inputReader = null;
        BufferedReader bufReader = null;
        try {
            inputReader = new InputStreamReader(context.getAssets().open(name));
            bufReader = new BufferedReader(inputReader);
            String line = null;
            StringBuffer buffer = new StringBuffer();
            while ((line = bufReader.readLine()) != null) {
                buffer.append(line);
            }
            if (encoding.length > 0)
                text = new String(buffer.toString().getBytes(), encoding[0]);
            else
                text = new String(buffer.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufReader != null) {
                    bufReader.close();
                }
                if (inputReader != null) {
                    inputReader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return text;
    }

    /**
     * 读取Assets目录的文件内容.
     *
     * @param context   context
     * @param assetFile assetFile
     * @return String
     * @throws IOException
     */
    public static String readAssetsByName(Context context, String assetFile) throws IOException {
        InputStream inputStream = context.getAssets().open(assetFile);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte buf[] = new byte[1024];
        int len;
        while ((len = inputStream.read(buf)) != -1) {
            outputStream.write(buf, 0, len);
        }
        outputStream.close();
        inputStream.close();

        return outputStream.toString();
    }

    /**
     * 读取Assets目录的文件内容
     *
     * @param inputStream inputStream
     * @return String
     */
    static public String readAssetsByName(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputStream.toString();
    }

    /**
     * 读取Raw目录的文件内容.
     *
     * @param context  the context
     * @param id       the id
     * @param encoding the encoding
     * @return String
     */
    public static String readRawByName(Context context, int id, String encoding) {
        String text = null;
        InputStreamReader inputReader = null;
        BufferedReader bufReader = null;
        try {
            inputReader = new InputStreamReader(context.getResources().openRawResource(id));
            bufReader = new BufferedReader(inputReader);
            String line = null;
            StringBuffer buffer = new StringBuffer();
            while ((line = bufReader.readLine()) != null) {
                buffer.append(line);
            }
            text = new String(buffer.toString().getBytes(), encoding);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufReader != null) {
                    bufReader.close();
                }
                if (inputReader != null) {
                    inputReader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return text;
    }


    /**
     * Gets the free sd space needed to cache.
     *
     * @return int the free sd space needed to cache
     */
    public static int getFreeSdSpaceNeededToCache() {
        return freeSdSpaceNeededToCache;
    }

    /**
     * 保存图片至本地并将图片目录显示到系统图库中
     *
     * @param bm       BitMap
     * @param fileName 文件名称 eg xx.jpeg
     * @param context  上下文
     */
    public static void saveFile(Bitmap bm, String filepath, String fileName, Context context)
            throws IOException {
        File foder = new File(filepath);
        if (!foder.exists()) {
            foder.mkdirs();
        }
        File myCaptureFile = new File(filepath, fileName);
        if (!myCaptureFile.exists()) {
            myCaptureFile.createNewFile();
        }
        BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream(myCaptureFile));
        bm.compress(CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(myCaptureFile);
        intent.setData(uri);
        context.sendBroadcast(intent);
    }


    /**
     * 保存文件 至指定文件夹的本地中同时保存了2份一份系统图库一份指定目录 通知系统图库更新显示
     *
     * @param bmp      BitMap
     * @param fileName 文件名称 eg xx.jpeg
     * @param context  上下文
     */
    public static void saveFile2(Bitmap bmp, String filepath,
                                 String fileName, Context context) throws IOException {
        // 首先保存图片
        File appDir = new File(filepath);
        if (!appDir.exists()) {
            // 创建多层目录 mkdir 只能创建一层目录
            appDir.mkdirs();
        }
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.parse("file://" + filepath)));
    }

    /**
     * Drawable 转 Bitmap
     *
     * @param drawable drawable
     * @return Bitmap
     */
    public static Bitmap drawable2Bitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof NinePatchDrawable) {
            Bitmap bitmap = Bitmap
                    .createBitmap(
                            drawable.getIntrinsicWidth(),
                            drawable.getIntrinsicHeight(),
                            drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                    : Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight());
            drawable.draw(canvas);
            return bitmap;
        } else
            return null;
    }

    /**
     * Bitmap对象转换Drawable对象.
     *
     * @param bitmap 要转化的Bitmap对象
     * @return Drawable 转化完成的Drawable对象
     */
    public static Drawable bitmap2Drawable(Bitmap bitmap) {
        BitmapDrawable mBitmapDrawable = null;
        try {
            if (bitmap == null) {
                return null;
            }
            mBitmapDrawable = new BitmapDrawable(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mBitmapDrawable;
    }

    /**
     * Gets the extension of a file name, like ".png" or ".jpg".
     *
     * @param uri uri
     * @return String Extension including the dot("."); "" if there is no extension;null if uri was null.
     */
    public static String getExtension(String uri) {
        if (uri == null) {
            return null;
        }

        int dot = uri.lastIndexOf(".");
        if (dot >= 0) {
            return uri.substring(dot);
        } else {
            // No extension.
            return "";
        }
    }

    /**
     * 是否图片
     *
     * @param fileName fileName
     * @return boolean
     */
    public static boolean isPic(String fileName) {
        String lowerCase = StringUtils.parseEmpty(fileName).toLowerCase();
        return lowerCase.endsWith(".bmp")
                || lowerCase.endsWith(".png")
                || lowerCase.endsWith(".jpg")
                || lowerCase.endsWith(".jpeg")
                || lowerCase.endsWith(".gif")
                || lowerCase.endsWith(".wbmp")
                || lowerCase.endsWith(".ico")
                || lowerCase.endsWith(".jpe")

                ;
    }

    /**
     * 获取文件后缀名(png)
     *
     * @param fileName fileName
     * @return String 文件后缀名
     */
    public static String getRealFileNameFromPath(String fileName) {
        if (!TextUtils.isEmpty(fileName)) {
            int typeIndex = fileName.lastIndexOf(".");
            if (typeIndex != -1) {
                String fileType = fileName.substring(typeIndex + 1).toLowerCase();
                return fileType;
            }
        }
        return "";
    }

    /**
     * 通过Okio方法，通过路径得到文件内容
     *
     * @param path 带文件名
     * @return String
     */
    public static String readContentFromPath(String path) {
        String content = "";
        Source source = null;
        BufferedSource buffer = null;

        try {
            File file = new File(path);
            source = Okio.source(file);
            buffer = Okio.buffer(source);
            content = buffer.readUtf8();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeQuietly(buffer);

        }
        return content;
    }

    /**
     * 通过Okio方法，向指定文件写入文件内容
     *
     * @param path    带文件名
     * @param content content
     */
    public static void writeContentFromPath(String path, String content) {
        Sink sink = null;
        BufferedSink bufferedSink = null;
        try {
            File dest = new File(path);
            sink = Okio.sink(dest);
            bufferedSink = Okio.buffer(sink);
            bufferedSink.writeUtf8(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(bufferedSink);
        }
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception ex) {
            }
        }
    }

    /**
     * 读取txt文件的内容
     * 1：先获得文件句柄
     * 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
     * 3：读取到输入流后，需要读取生成字节流
     * 4：一行一行的输出 readline()
     *
     * @param filePath filePath
     * @return String
     */
    public static String readTxtFile(String filePath) {
        StringBuffer sb = new StringBuffer();
        try {
            String encoding = "GBK";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    sb.append(lineTxt);
                }
                read.close();
                return sb.toString();
            } else {
                return "找不到指定的文件";
            }
        } catch (Exception e) {
            return "读取文件内容出错";
        }
    }

    /**
     * 根据文件后缀名获得对应的MIME类型。
     *
     * @param file file
     * @return String
     */
    public static String getMIMEType(File file) {

        String type = "*/*";
        String fName = file.getName();
        // 获取后缀名前的分隔符"."在fName中的位置。
        int dotIndex = fName.lastIndexOf(".");
        if (dotIndex < 0) {
            return type;
        }
        /* 获取文件的后缀名 */
        String end = fName.substring(dotIndex, fName.length()).toLowerCase();
        if ("".equals(end)) {
            return type;
        }
        int len = MIME_MapTable.length;
        // 在MIME和文件类型的匹配表中找到对应的MIME类型。
        for (int i = 0; i < len; i++) {
            if (end.equals(MIME_MapTable[i][0]))
                type = MIME_MapTable[i][1];
        }
        return type;
    }

    /**
     * 获取文件名称对应的图标
     *
     * @param fileName fileName
     * @return int
     */
    public static int getFileIcon(String fileName) {
        String ext = "";
        if (fileName.contains(".")) {
            ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        }

        if ("doc".equals(ext) || "docx".equals(ext) || "rtf".equals(ext) || "wps".equalsIgnoreCase(ext)) {
            return R.drawable.fdq;
        } else if ("xls".equalsIgnoreCase(ext) || "xlsx".equalsIgnoreCase(ext)) {
            return R.drawable.fcg;
        } else if ("ppt".equalsIgnoreCase(ext) || "pptx".equalsIgnoreCase(ext)) {
            return R.drawable.fcz;
        } else if ("xml".equalsIgnoreCase(ext) || "html".equalsIgnoreCase(ext)) {
            return R.drawable.fdb;
        } else if ("pdf".equalsIgnoreCase(ext)) {
            return R.drawable.fda;
        } else if ("txt".equalsIgnoreCase(ext)) {
            return R.drawable.fcv;
        } else if ("png".equalsIgnoreCase(ext) || "jpg".equalsIgnoreCase(ext) || "gif".equalsIgnoreCase(ext) || "bmp".equalsIgnoreCase(ext) || "jpeg".equalsIgnoreCase(ext)) {
            return R.drawable.fdd;
        } else if ("swf".equalsIgnoreCase(ext) || "rmvb".equalsIgnoreCase(ext) || "avi".equalsIgnoreCase(ext) || "mp4".equalsIgnoreCase(ext) || "rm".equalsIgnoreCase(ext) || "mkv".equalsIgnoreCase(ext)) {
            return R.drawable.fch;
        } else if ("amr".equalsIgnoreCase(ext) || "wav".equalsIgnoreCase(ext) || "mp3".equalsIgnoreCase(ext)) {
            return R.drawable.fdc;
        } else if ("rar".equalsIgnoreCase(ext) || "zip".equalsIgnoreCase(ext) || "7z".equalsIgnoreCase(ext)) {
            return R.drawable.fcf;
        } else {
            return R.drawable.fco;
        }
    }

    private static int BUFFER = 4096;

    /**
     * 解压压缩文件
     *
     * @param savePath savePath
     * @param toPath   toPath
     * @return boolean
     */
    public static boolean unZipResourcePackage(String savePath, String toPath) {
        try {
            File fileToFile = new File(toPath);
            if (!fileToFile.exists()) {
                fileToFile.mkdirs();
            }
            ZipFile zipFile = new ZipFile(savePath);
            Enumeration<? extends ZipEntry> emu = zipFile.entries();
            while (emu.hasMoreElements()) {
                ZipEntry entry = emu.nextElement();

                if (entry.isDirectory()) {
                    new File(toPath + entry.getName()).mkdirs();
                    continue;
                }
                BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(entry));
                File file = new File(toPath + entry.getName());

                File parent = file.getParentFile();
                if (parent != null && (!parent.exists())) {
                    parent.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(fos, BUFFER);

                int count;
                byte data[] = new byte[BUFFER];
                while ((count = bis.read(data, 0, BUFFER)) != -1) {
                    bos.write(data, 0, count);
                }
                bos.flush();
                bos.close();
                bis.close();
            }
            zipFile.close();
        } catch (Exception e) {
            ULog.e(e.toString());
            return false;
        }

        return true;
    }

    /**
     * 打开文件
     *
     * @param context context
     * @param file    file
     */
    public static void openFile(Context context, File file) {
        try {
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(android.content.Intent.ACTION_VIEW);
            String type = getMIMEType(file);
            intent.setDataAndType(Uri.fromFile(file), type);
            context.startActivity(intent);
        } catch (Exception ex) {
        }
    }

    private final static String[][] MIME_MapTable = {
            // {后缀名，MIME类型}
            {".3gp", "video/3gpp"}, {".apk", "application/vnd.android.package-archive"},
            {".asf", "video/x-ms-asf"}, {".avi", "video/x-msvideo"}, {".bin", "application/octet-stream"},
            {".bmp", "image/bmp"}, {".c", "text/plain"}, {".class", "application/octet-stream"},
            {".conf", "text/plain"}, {".cpp", "text/plain"}, {".doc", "application/msword"},
            {".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"},
            {".xls", "application/vnd.ms-excel"},
            {".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"},
            {".exe", "application/octet-stream"}, {".gif", "image/gif"}, {".gtar", "application/x-gtar"},
            {".gz", "application/x-gzip"}, {".h", "text/plain"}, {".htm", "text/html"}, {".html", "text/html"},
            {".jar", "application/java-archive"}, {".java", "text/plain"}, {".jpeg", "image/jpeg"},
            {".jpg", "image/jpeg"}, {".js", "application/x-javascript"}, {".log", "text/plain"},
            {".m3u", "audio/x-mpegurl"}, {".m4a", "audio/mp4a-latm"}, {".m4b", "audio/mp4a-latm"},
            {".m4p", "audio/mp4a-latm"}, {".m4u", "video/vnd.mpegurl"}, {".m4v", "video/x-m4v"},
            {".mov", "video/quicktime"}, {".mp2", "audio/x-mpeg"}, {".mp3", "audio/x-mpeg"},
            {".mp4", "video/mp4"}, {".mpc", "application/vnd.mpohun.certificate"}, {".mpe", "video/mpeg"},
            {".mpeg", "video/mpeg"}, {".mpg", "video/mpeg"}, {".mpg4", "video/mp4"}, {".mpga", "audio/mpeg"},
            {".msg", "application/vnd.ms-outlook"}, {".ogg", "audio/ogg"}, {".pdf", "application/pdf"},
            {".png", "image/png"}, {".pps", "application/vnd.ms-powerpoint"},
            {".ppt", "application/vnd.ms-powerpoint"},
            {".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"},
            {".prop", "text/plain"}, {".rc", "text/plain"}, {".rmvb", "audio/x-pn-realaudio"},
            {".rtf", "application/rtf"}, {".sh", "text/plain"}, {".tar", "application/x-tar"},
            {".tgz", "application/x-compressed"}, {".txt", "text/plain"}, {".wav", "audio/x-wav"},
            {".wma", "audio/x-ms-wma"}, {".wmv", "audio/x-ms-wmv"}, {".wps", "application/vnd.ms-works"},
            {".xml", "text/plain"}, {".z", "application/x-compress"}, {".zip", "application/x-zip-compressed"},
            {"", "*/*"}};

}

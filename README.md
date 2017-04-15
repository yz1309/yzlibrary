
# Android-Library #

## Getting started

The first step is to include yzlibrary  into your project, for example, as a Gradle compile dependency:

```groovy
compile 'top.slantech:yzlibrary:1.1.1'
```
## Features ##

- Custom Application(自定义Application)
- ArrayTool(数组工具)
- CalendarTool(时间工具类)
- CharacterParseUtils(汉字转拼音)
- CheckUtils(数据格式检查)
- ChineseCalendarGB(农历工具)
- DataCleanUtils(数据清除工具)
- DisplayUtil(屏幕工具)
- Encode(编码工具)
- EquipInfoUtils(设备基本信息)
- FaceConversionUtil(表情转换工具)
- FileUtils(文件操作类)
- HexUtils(进制转换工具)
- HTMLUtil(HTML工具)
- IdcardValidator(身份证合法性校验)
- ImageCompressUtil(图片压缩工具类)
- ImageRotateUtil(图片旋转工具类)
- ImageUtils(图片操作类)
- KeyBoardUtils(软键盘操作类)
- MathUtils(数学工具类)
- NetUtils(网络状态工具)
- SettingUtils(系统设置工具类)
- SPUtils(SharedPreferences工具)
- StringUtils(字符串处理工具类)
- ULog(自定义Log输出)
- Url2Path(URL处理工具类)


## Notice ##

- Android Studio 2.0
- Use Okhttp3
- Use Gson
- Use Leakcanary

## Usage ##

**Using as a library project Including In Your Project**

## Detail ##
- Custom Application(自定义Application)
- ArrayTool(数组工具) <br/>
1.获取最大值 getMax(int[] arr);<br/>
2.获取最大值及索引值 getMax2(int[] arr);<br/>
3.数组的反转 revArray(int[] arr);<br/>
- CalendarTool(时间工具类)<br/>
1.String类型的日期时间转化为Date类型 getDateByFormat("2016-04-15 12:20:11","yyyy-MM-dd HH:mm:ss");<br/>
2.获取Date偏移之后的Date getDateByOffset(date,Calendar.DATE,1);<br/>
3.获取字符串日期时间的字符串(可偏移) getStringByOffset("2016-04-15 12:20:11","yyyy-MM-dd HH:mm:ss",Calendar.DATE,1);<br/>
4.Date类型转化为String类型(可偏移)  getStringByOffset(date,"yyyy-MM-dd HH:mm:ss",Calendar.DATE,1);<br/>
5.Date类型转化为String类型 getStringByFormat((date,"yyyy-MM-dd HH:mm:ss");<br/>
6.获取指定日期时间的字符串,用于导出想要的格式 getStringByFormat("2016-04-15 12:20:11","yyyy-MM-dd HH:mm:ss");<br/>
7.获取milliseconds表示的日期时间的字符串 getStringByFormat(1470816365,"yyyy-MM-dd HH:mm:ss");<br/>
8.获取表示当前日期时间的字符串 getCurrentDate("yyyy-MM-dd HH:mm:ss");<br/>
9.计算两个日期所差的天数 getOffectDay(1470816365,1470816415);<br/> or getOffectDay(startDate,endDate)
10.计算两个日期所差的小时数 getOffectHour(1470816365,1470816415);<br/>
11.计算两个日期所差的分钟数 getOffectMinutes(1470816365,1470816415);<br/>
12.获取本周一 getFirstDayOfWeek("2016-04-15 12:20:11");<br/>
13.获取本周日 getLastDayOfWeek("2016-04-15 12:20:11");<br/>
14.获取本周的某一天 getDayOfWeek("2016-04-15 12:20:11",Calendar.DATE);<br/>
15.获取本月第一天 getFirstDayOfMonth("2016-04-15 12:20:11");<br/>
16.获取本月最后一天 getLastDayOfMonth("2016-04-15 12:20:11");<br/>
17.获取表示当前日期的0点时间毫秒数 getFirstTimeOfDay();<br/>
18.获取表示当前日期24点时间毫秒数 getLastTimeOfDay();<br/>
19.判断是否是闰年 isLeapYear(2014);<br/>
20.根据时间返回格式化后的时间的描述 formatDateStr2Desc2("2016-04-15 12:20:11","yyyy-MM-dd HH:mm:ss");<br/>
21.以友好的方式显示时间  formatDateStr2Desc("2016-04-15 12:20:11","yyyy-MM-dd HH:mm:ss");<br/>
22.取指定日期为星期几 getWeekNumber("2016-04-15 12:20:11","yyyy-MM-dd HH:mm:ss");<br/>
23.根据给定的日期判断是否为上下午 getTimeQuantum("2016-04-15 12:20:11","yyyy-MM-dd HH:mm:ss");<br/>
24.根据给定的毫秒数算得时间的描述 getTimeDescription(1470816365);<br/>
25.将Calendar转成字符串 calendarConvertString(calendar,"yyyy-MM-dd HH:mm:ss");<br/>
26.将字符串转成Calendar stringConvertCalendar("2016-04-15 12:20:11","yyyy-MM-dd HH:mm:ss");<br/>
27.比较2个时间的大小 calendarCompare(calendar1,calendar2);<br/>
28.得到当前时间的随机数 getCurRandomDate();<br/>
29.某年某月有多少天 getDaysInMonth(2016,5);<br/>
30.某年某月的第一天是周几 getFirstWeekDayInMonth(2016,5);<br/>
31.得到2个时间戳之间相差的 天 时 分 秒 getDate(1470816365,1470816415);<br/>
32.阳历节日 calendarFestival(calendar);<br/>
33.阴历节日 lunarHoliday(calendar);<br/>
- CharacterParseUtils(汉字转拼音)<br/>
1.单字解析拼音 convert("中");<br/>
2.词组解析拼音 getSelling("美国");<br/>
- CheckUtils(数据格式检查)<br/>
1.检查手机号码合法性 checkMDN("18224411300",true);<br/> or isMobileNo("18224411300");<br/>
2.检测邮箱合法性  checkEmailValid("yz130@163.com");<br/>
3.检查是否是IPV4 isIPv4Address(12.12.10.26);<br/>
4.检查是否是IPV6 isIPv6StdAddress("9489:1572:28fa:1bda%28");<br/>
5.是否只是字母和数字 isNumberLetter("test");<br/>
6.是否只是数字 isNumber("test");<br/>
7.是否是中文 isChinese("test");<br/>
8.是否包含中文 isContainChinese("test");<br/>
9.判断是否为一个合法的url地址 isUrl("http://www.slantech.top");<br/>
- ChineseCalendarGB(农历工具)<br/>
1.传回农历 y年的总天数 yearDays(2016);<br/>
2.传回农历 y年闰月的天数 leapDays(2016);<br/>
3.传回农历 y年闰哪个月 1-12 , 没闰传回 0  leapMonth(2016);<br/>
4.传回农历 y年m月的总天数 monthDays(2016,1);<br/>
5.传入 月日的offset 传回干支, 0=甲子 cyclicalm(12);<br/>
6.通过天获取农历 getChinaDayString(23);<br/>
- DataCleanUtils(数据清除工具)<br/>
1.清除本应用内部缓存 cleanInternalCache(Activity);<br/>
2.清除本应用所有数据库 cleanDatabases(Activity);<br/>
3.清除本应用SharedPreference cleanSharedPreference(Activity);<br/>
4.按名字清除本应用数据库 cleanDatabaseByName(Activity,"test");<br/>
5.清除/data/data/com.xxx.xxx/files下的内容 cleanFiles(Activity);<br/>
6.清除外部cache下的内容 cleanExternalCache(Activity);<br/>
7.清除自定义路径下的文件，使用需小心，请不要误删。而且只支持目录下的文件删除 cleanCustomCache("/sd/ewe");<br/>
8.清除本应用所有的数据 cleanApplicationData(Activity,"/sd/ewe");<br/>
9.删除方法 这里只会删除某个文件夹下的文件 deleteFilesByDirectory(file);<br/>
10.获取文件夹下文件大小 getFolderSize(file);<br/>
11.删除指定目录下文件及目录 deleteFolderFile("/sd/ewe",true);<br/>
12.格式化单位 getFormatSize(1024);<br/>
13.获取文件夹下文件大小并格式化 getCacheSize(file);<br/>
- DisplayUtil(屏幕工具)<br/>
1.dip转px dip2px(Activity,100);<br/>
2.px转dip px2dip(Activity,100);<br/>
3.获取屏幕宽度和高度 getScreenMetrics(Activity);<br/>
4.获取屏幕长宽比 getScreenRate(Activity);<br/>
- Encode(编码工具)<br/>
1.转换为%E4%BD%A0形式 toUtf8String("中国");<br/>
2.将%E4%BD%A0转换为汉字 unescape("%E4%BD%A0");<br/>
- EquipInfoUtils(设备基本信息)<br/>
1.系统版本信息 getVersion();<br/>
2.CPU getCpuInfo();<br/>
3.获取CPU核心数  getNumCores();<br/>
4.主频 getMaxCpuFreq();<br/>
5.获取RAM空间大小  getTotalRAMMemory();<br/>
6.获取ROM空间 getRomMemroy();<br/>
7.获取SD空间  getSDCardMemory();<br/>
8.获取手机屏幕信息 getDisplayMetrics(Activity);<br/>
9.获取电池容量 getBatteryCapacity(Activity);<br/>
- FaceConversionUtil(表情转换工具)<br/>
- FileUtils(文件操作类)<br/>
1.通过文件的网络地址从SD卡中读取图片，如果SD中没有则自动下载并保存 getBitmapFromSD("/sd/ese","http://xx.png",ImageUtils.CUTIMG,100,100);<br/>
2.通过文件的本地地址从SD卡读取图片 getBitmapFromSD("/sd/ese/xx.png",100,100);<br/>
3.通过文件的本地地址从SD卡读取图片 getBitmapFromSD(file),getBitmapFromSD("/sd/ese/xx.png");<br/>
4.将图片的byte[]写入本地文件 getBitmapFromByte("/sd/ese",byte[],"test.png",ImageUtils.CUTIMG,100,100);<br/>
5.根据URL从互连网获取图片 getBitmapFromURL("http://xx.png",ImageUtils.CUTIMG,100,100);<br/>
6.获取src中的图片资源 getBitmapFromSrc("image/arrow.png");<br/>
7.获取Asset中的图片资源 getBitmapFromAsset(Activity,"test");<br/>
8.获取Asset中的图片资源 getDrawableFromAsset(Activity,"test");<br/>
9.下载网络文件到SD卡中.如果SD中存在同名文件将不再下载 downloadFile("http://xx.png","/sd/ese");<br/>
10.获取网络文件的大小 getContentLengthFromUrl("http://xx.png");<br/>
11.通过网络获取文件名 getRealFileNameFromUrl("http://xx.png");<br/>
12.通过网络获取真实文件名(xx.后缀)  getRealFileName(HttpURLConnection);<br/>
13.通过网络获取真实文件名(xx.后缀)  getRealFileName(HttpResponse);<br/>
14.获取文件名（不含后缀） getCacheFileNameFromUrl("http://xx.png");<br/>
15.外链模式和通过网络获取文件名（.后缀） getCacheFileNameFromUrl("http://xx.png",HttpResponse);<br/>
16.外链模式和通过网络获取文件名（.后缀） getCacheFileNameFromUrl("http://xx.png",HttpURLConnection);<br/>
17.获取文件后缀 getMIMEFromUrl("http://xx.png",HttpURLConnection);<br/>
18.获取文件后缀 getMIMEFromUrl("http://xx.png",HttpResponse);<br/>
19.从sd卡中的文件读取到byte[] getByteArrayFromSD("/sd/xx.txt");<br/>
20.将byte数组写入文件 writeByteArrayToSD("/sd/xx",byte[],true);<br/>
21.SD卡是否能用 isCanUseSD();<br/>
22.计算sdcard上的剩余空间 freeSpaceOnSD();<br/>
23.根据文件的最后修改时间进行排序
24.获取文件夹下的文件列表 直级 getFileByPath("/sd/xx");<br/>
25.删除指定文件夹下的所有文件 clearFile("/sd/xx");<br/>
26.读取Assets目录的文件内容 readAssetsByName(Activity,"test","UTF-8");<br/>
27.读取Assets目录的文件内容 readAssetsByName(Activity,"test");<br/>
28.读取Assets目录的文件内容 readAssetsByName(InputStream);<br/>
29.读取Raw目录的文件内容 readRawByName(Activity,R.raw.test,"UTF-8");<br/>
30.保存图片至本地并将图片目录显示到系统图库中 saveFile(Bitmap,"/sd/xx","test.png",Activity);<br/>
31.保存文件 至指定文件夹的本地中同时保存了2份一份系统图库一份指定目录 通知系统图库更新显示 saveFile2(Bitmap,"/sd/xx","test.png",Activity);<br/>
32.Drawable 转 Bitmap drawable2Bitmap(Drawable);<br/>
33.Bitmap对象转换Drawable对象 bitmap2Drawable(Bitmap);<br/>
34.Gets the extension of a file name, like ".png" or ".jpg" getExtension("/sd/xx.png");<br/>
35.是否图片 isPic("/sd/xx.png");<br/>
36.获取文件后缀名(png) getRealFileNameFromPath("/sd/xx.png");<br/>
37.【通过Okio方法】，通过路径得到文件内容 readContentFromPath(String path);<br/>
38.【通过Okio方法】，向指定文件写入文件内容 writeContentFromPath(String path, String content);<br/>
- HexUtils(进制转换工具)<br/>
1.将字符串编码成16进制数字,适用于所有字符（包括中文） encode(String str)
2.将16进制数字解码成字符串,适用于所有字符（包括中文） decode(String bytes)
3.把16进制字符串转换成字节数组 hexStringToByte(String hex)
4.把字节数组转换成16进制字符串 bytesToHexString(byte[] bArray)
5.BCD码转为10进制串(阿拉伯数据) bcd2Str(byte[] bytes)
6.10进制串转为BCD码 str2Bcd(String asc)
- HTMLUtil(HTML工具)<br/>
1.删除HTML中的标签 delHTMLTag("<html>");<br/>
2.替换标记以正常显示 replaceTag("<html>");<br/>
3.判断标记是否存在 hasSpecialChars("<html>");<br/>
4.过滤所有以"<"开头以">"结尾的标签 filterHtml("<html>");<br/>
5.过滤指定标签 fiterHtmlTag("<html>","html");<br/>
6.替换指定的标签 replaceHtmlTag("<html>test</html>","<html>","html","html","/html");<br/>
- IdcardValidator(身份证合法性校验)<br/>
- ImageCompressUtil(图片压缩工具类)<br/>
1.压缩单张图片方法 compressImage(Activity,"/sd/xx.png",new ProcessImgCallBack);<br/>
2.压缩图片集合方法 compressImageList(Activity,List<String>,new ProcessImgCallBack);<br/>
- ImageRotateUtil(图片旋转工具类)<br/>
1.读取图片的旋转的角度 getBitmapDegree("/sd/xx.png");<br/>
2.将图片按照某个角度进行旋转 rotateBitmapByDegree(Bitmap,180);<br/>
3.将图片按照指定的角度进行旋转 rotateBitmapByDegree("/sd/xx.png",180);<br/>
- ImageUtils(图片操作类)<br/>
1.直接获取互联网上的图片 getBitmap("http://xx.png",ImageUtils.CUTIMG,100,100);<br/>
2.获取原图 getBitmap(file);<br/>
3.缩放图片.压缩 scaleImg(file,100,100);<br/>
4.缩放图片,不压缩的缩放 scaleImg(Bitmap,100,100);<br/>
5.根据等比例缩放图片 scaleImg(Bitmap,0.4);<br/>
6.裁剪图片 cutImg(file,100,100);<br/>
7.裁剪图片 cutImg(Bitmap,100,100);<br/>
8.Bitmap对象转换TransitionDrawable对象 bitmapToTransitionDrawable(Bitmap);<br/>
9.Drawable对象转换TransitionDrawable对象 drawableToTransitionDrawable(Drawable);<br/>
10.将Bitmap转换为byte[] bitmap2Bytes(Bitmap,Bitmap.CompressFormat.JPEG,true);<br/>
11.获取Bitmap大小 getByteCount(Bitmap,Bitmap.CompressFormat.JPEG);<br/>
12.将byte[]转换为Bitmap bytes2Bimap(byte[]);<br/>
13.将ImageView转换为Bitmap imageView2Bitmap(ImageView);<br/>
14.将View转换为Drawable.需要最上层布局为Linearlayout view2Drawable(View);<br/>
15.将View转换为Bitmap.需要最上层布局为Linearlayout view2Bitmap(View);<br/>
16.将View转换为byte[] view2Bytes(View,Bitmap.CompressFormat.JPEG);<br/>
17.旋转Bitmap为一定的角度 rotateBitmap(Bitmap,180);<br/>
18.旋转Bitmap为一定的角度并四周暗化处理 rotateBitmapTranslate(Bitmap,180);<br/>
19.转换图片转换成圆形 toRoundBitmap(Bitmap);<br/>
20.转换图片转换成镜面效果的图片 toReflectionBitmap(Bitmap);<br/>
21.释放Bitmap对象 releaseBitmap(Bitmap);<br/>
22.释放Bitmap数组 releaseBitmapArray(Bitmap[]);<br/>
23.简单的图像的特征值，用于缩略图找原图比较好 getHashCode(Bitmap);<br/>
24.图像的特征值余弦相似度 getDCTHashCode(Bitmap);<br/>
25.图像的特征值颜色分布 将颜色分4个区，0,1,2,3 区组合共64组，计算每个像素点属于哪个区 getColorHistogram(Bitmap);<br/>
26.汉明距离(如果不相同的数据位不超过5，就说明两张图片很相似；如果大于10，就说明这是两张不同的图片) hammingDistance(源hashCode,与之比较的hashCode);<br/>
27.F dct transform fDctTransform(double[][] ablk);<br/>
28.读取图片的旋转的角度 getBitmapDegree("/sd/xx.png");<br/>
29.直接将照片旋转正常 roteBitmap("/sd/xx.png",Bitmap);<br/>
30.压缩 picCompression("/sd/xx.png");<br/>
31.压缩 picCompression("/sd/xx.png",100,100);<br/>
32.将 view 生成 图片 createViewBitmap(View);<br/>
- KeyBoardUtils(软键盘操作类)<br/>
1.打开软键盘 showSoftInput(EditText,Activity) or showSoftInput(Activity);<br/>
2.关闭软键盘 closeSoftInput(EditText,Activity) or closeSoftInput(Activity);<br/>
- MathUtils(数学工具类)<br/>
1.四舍五入保留几位小数 round(123.012,2);<br/>
2.字节数组转换成16进制串 byte2HexStr(byte[],2);<br/>
3.二进制转为十六进制 binaryToHex(00000011);<br/>
4.一维数组转为二维数组 arrayToMatrix(int[] m, int width, int height);<br/>
5.二维数组转为一维数组 matrixToArray(double[][] m);<br/>
6.int数组转换为double数组 intToDoubleArray(int[] input);<br/>
7.int二维数组转换为double二维数组 intToDoubleMatrix(int[][] input);<br/>
8.计算数组的平均值 average(int[] pixels);<br/>
9.计算数组的平均值 average(double[] pixels);<br/>
10.点在直线上 pointAtSLine(double x,double y,double x1,double y1,double x2,double y2);<br/>
11.点在线段上 pointAtELine(double x,double y,double x1,double y1,double x2,double y2);<br/>
12.两条直线相交 lineAtLine(double x1,double y1,double x2,double y2,double x3,double y3,double x4,double y4);<br/>
13.线段与线段相交 eLineAtELine(double x1,double y1,double x2,double y2,double x3,double y3,double x4,double y4);<br/>
14.线段直线相交 eLineAtLine(double x1,double y1,double x2,double y2,double x3,double y3,double x4,double y4);<br/>
15.点在矩形内 pointAtRect(double x,double y,double x1,double y1,double x2,double y2);<br/>
16.矩形在矩形内 rectAtRect(double x1,double y1,double x2,double y2,double x3,double y3,double x4,double y4);<br/>
17.圆心在矩形内 circleAtRect(double x,double y,double r,double x1,double y1,double x2,double y2);<br/>
18.获取两点间的距离 getDistance(double x1,double y1,double x2,double y2);<br/>
19.矩形碰撞检测 isRectCollision(float x1, float y1, float w1,float h1, float x2, float y2, float w2, float h2);<br/>
- NetUtils(网络状态工具)<br/>
1.检测网络是否可用 isNetworkConnected(Context context);<br/>
2.获取当前网络类型 getNetworkType(Context context);<br/>
- SettingUtils(系统设置工具类)<br/>
1.Gps是否打开 isGpsEnabled(Activity);<br/>
2.调用系统分享功能 分享文本 systemShareText(Activity,content,title);<br/>
3.调用系统分享功能 分享图片 systemShareImg(Activity,imgPath,title);<br/>
4.是否root isRootSystem();<br/>
5.是否root getRootAhth();<br/>
- SPUtils(SharedPreferences工具)<br/>
1.保存数据的方法 put(Context context, String key, Object object);<br/>
2.得到保存数据的方法 get(Context context, String key, Object defaultObject)<br/>
3.保存数据的方法 put(Context context, String fileName,String key, Object object)<br/>
4.得到保存数据的方法 get(Context context,String fileName, String key, Object defaultObject);<br/>
5.移除某个key值已经对应的值 remove(Context context, String key);<br/>
6.清除所有数据 clear(Context context);<br/>
7.查询某个key是否已经存在 contains(Context context, String key);<br/>
8.查询某个key是否已经存在 contains(Context context,String fileName, String key);<br/>
9.返回所有的键值对 getAll(Context context);<br/>
- StringUtils(字符串处理工具类)<br/>
1.将null转化为“” parseEmpty(String str)
2.判断一个字符串是否为null或空值 isEmpty(String str);<br/>
3.获取字符串中文字符的长度（每个中文算2个字符） chineseLength(String str);<br/>
4.获取字符串的长度（中文字符计2个） strLength(String str);<br/>
5.获取指定长度的字符所在位置 subStringLength(String str, int maxL);<br/>
6.从输入流中获得String inputStreamConvertToString(InputStream is);<br/>
7.不足2个字符的在前面补“0” strFormat2(String str);<br/>
8.截取字符串到指定字节长度 cutString(String str, int length);<br/>
9.标准化日期时间类型的数据，不足两位的补0 dateTimeFormat(String dateTime);<br/>
10.截取字符串到指定字节长度 cutString(String str, int length, String dot);<br/>
11.截取字符串从第一个指定字符 cutStringFromChar(String str1, String str2, int offset);<br/>
12.获取字节长度 strlen(String str, String charset);<br/>
13.获取大小的描述 getSizeDesc(long size);<br/>
14.获取大小的描述 getSizeDesc2(long length);<br/>
15.获取大小的描述 getSizeDesc3(long size)
16.转换成Mb单位 formatSizeMb(long length);<br/>
17.ip地址转换为10进制数 ip2int(String ip);<br/>
18.获得指定小数位的double getDoubleXS(String num, int scale);<br/>
19.复制文本 copyTxt(Context context, String txt);<br/>
20.米转化成公里 getGongLiFromMi(float a);<br/>
21.获取字符串中的数字 getIntFromString(String str);<br/>
- ULog(自定义Log输出)<br/>
- Url2Path(URL处理工具类)<br/>
1.通过uri 取得path getPath(Context context, Uri contentUri)；<br/>
2.Get the value of the data column for this Uri getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs);<br/>
3.Whether the Uri authority is ExternalStorageProvider isExternalStorageDocument(Uri uri);<br/>
4.Whether the Uri authority is DownloadsProvider isDownloadsDocument(Uri uri);<br/>
5.Whether the Uri authority is MediaProvider isMediaDocument(Uri uri);<br/>
6.Whether the Uri authority is Google Photos isGooglePhotosUri(Uri uri);<br/>

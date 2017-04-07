package top.slantech.yzlibrary.utils;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;

/**
 * 屏幕工具
 * 功能描述：
 * 1、dip转px dip2px(Activity,100);
 * 2、px转dip px2dip(Activity,100);
 * 3、获取屏幕宽度和高度 getScreenMetrics(Activity);
 * 4、获取屏幕长宽比 getScreenRate(Activity);
 *
 */
public class DisplayUtil {
	private static final String TAG = "DisplayUtil";
	/**
	 * dip转px
	 * @param context context
	 * @param dipValue dipValue
	 * @return int
	 */
	public static int dip2px(Context context, float dipValue){            
		final float scale = context.getResources().getDisplayMetrics().density;                 
		return (int)(dipValue * scale + 0.5f);         
	}     
	
	/**
	 * px转dip
	 * @param context context
	 * @param pxValue pxValue
	 * @return int
	 */
	public static int px2dip(Context context, float pxValue){                
		final float scale = context.getResources().getDisplayMetrics().density;                 
		return (int)(pxValue / scale + 0.5f);         
	} 
	
	/**
	 * 获取屏幕宽度和高度，单位为px
	 * @param context context
	 * @return Point
	 */
	public static Point getScreenMetrics(Context context){
		DisplayMetrics dm =context.getResources().getDisplayMetrics();
		int w_screen = dm.widthPixels;
		int h_screen = dm.heightPixels;
		return new Point(w_screen, h_screen);
	}
	
	/**
	 * 获取屏幕长宽比
	 * @param context context
	 * @return float
	 */
	public static float getScreenRate(Context context){
		Point P = getScreenMetrics(context);
		float H = P.y;
		float W = P.x;
		return (H/W);
	}
}
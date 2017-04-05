package top.slantech.yzlibrary.utils;

/**
 * 数组工具
 * 功能描述：
 * 1、获取最大值 getMax(int[] arr);
 * 2、获取最大值及索引值 getMax2(int[] arr);
 * 3、数组的反转 revArray(int[] arr);
 * Created by slantech on 2016/08/26 19:19
 */
public class ArrayTool {
    private ArrayTool() {
    }                        //私有构造方法,不让其他类创建本来对象,直接用类名.调用

    /**
     * 获取最大值
     * @param arr
     * @return
     */
    public static int getMax(int[] arr) {
        int max = arr[0];                                     //记录第一个元素
        int len = arr.length;
        for (int i = 1; i < len; i++) {                    //从第二个元素开始遍历
            if (max < arr[i]) {                                            //max与数组中其他的元素比较
                max = arr[i];                                            //记录主较大的
            }
        }
        return max;
    }

    /**
     * 获取最大值及索引值
     * @param arr
     * @return
     */
    public static int[] getMax2(int[] arr) {
        int[] rtn = new int[2];
        int max = arr[0];                                     //记录第一个元素
        int len = arr.length;
        for (int i = 1; i < len ; i++) {                    //从第二个元素开始遍历
            if (max < arr[i]) {                                            //max与数组中其他的元素比较
                max = arr[i];                                            //记录主较大的
                rtn[1] = i;
            }
        }
        rtn[0] =max;
        return rtn;
    }

    /**
     * 数组的遍历
     * @param arr
     */
    public static void print(int[] arr) {
        for (int temp:arr){
            System.out.print(temp + " ");
        }
    }

    /**
     * 数组的反转
     * @param arr
     */
    public static void revArray(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len / 2; i++) {
        /*
        arr[0]与arr[arr.length-1-0]交换
        arr[1]与arr[arr.length-1-1]交换
        arr[2]与arr[arr.length-1-2]交换
        */
            int temp = arr[i];
            arr[i] = arr[len - 1 - i];
            arr[len - 1 - i] = temp;
        }
    }
}

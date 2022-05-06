package com.hspedu.mhl.utils;
import java.util.Scanner;
/**
 * @author 18190
 * 输入的工具类
 */
public class Utility {
    private final static Scanner in = new Scanner(System.in);
    public static String readKeyBoard(int len, boolean flag){
        String ans;
        for (;;){
            ans = in.next();
            if(ans.length() <= len == flag){
                if (flag){
                    System.out.println("输入有误，请输入大于"+len + "位，请重新输入：");
                }else {
                    System.out.println("输入有误，请输入小于等于"+len + "位，请重新输入：");
                }
            }else {
                return ans;
            }
        }


    }

    public static String readString(int len){
        return readKeyBoard(len,false);
    }

    public static int readInt(){
        return readInt(2,false);
    }
    public static int readInt(int len){
        return readInt(len,false);
    }
    public static int readInt(int len,boolean flag){
        int n;
//        if(!flag){
//
//            System.out.printf("请输入一个长度小于等于%d位的整数:\n",len);
//        }else {
//            System.out.printf("请输入一个长度大于%d位的整数:\n",len);
//        }
        for (;;){
            String str = readKeyBoard(len,flag);
            try {
                n = Integer.parseInt(str);
                break;
            }catch (Exception e){
                System.out.print("输入数字有误，");
                if(!flag){
                    System.out.printf("请输入一个长度小于等于%d位的整数:\n",len);
                }else {
                    System.out.printf("请输入一个长度大于%d位的整数:\n",len);
                }
            }
        }
        return n;
    }
    public static char readConfirmSelection() {
        System.out.println("确认是否预定(Y/N): ");
        char c;
        for (;;) { //无限循环
            // 在这里，将接受到字符，转成大写字母
            // y=> Y n=>N
            String str = readKeyBoard(1, false).toUpperCase();
            c = str.charAt(0);
            if (c == 'Y' || c == 'N') {
                break;
            } else {
                System.out.print("选择错误，请重新输入: ");
            }
        }
        return c;
    }
}

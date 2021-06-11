package com.wolftech.utils;

import java.util.ArrayList;
import java.util.List;

public class ConvertNum {
    public final static String[] lowUnits = {"千", "百", "十"};
    public final static String[] lowNumArr = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};

    public String convert(String numStr) {

        // 结果集
        List<List<Integer>> resultList = new ArrayList();

        List<Integer> nums = new ArrayList();

        //numStr长度,有可能做长度校验
        int length = numStr.length();
        if (length > 12){
            return "数字长度超过限制，应在12位（含12位）以内！";
        }
        System.out.println("numStr长度：" + length);

        //循环次数
        int times = length / 4;
        System.out.println("循环次数：" + times);

        //第一次截取长度
        int mod = length % 4;
        String firstSubstring = null;
        String afterSubstring = null;
        if (mod > 0) {
            //闭区间
            firstSubstring = numStr.substring(0, mod);
            //剩余
            afterSubstring = numStr.substring(mod);

            //把第一次截取的字符串变成单个数字，并加入到List集合
            for (int i = 0; i < firstSubstring.length(); ++i) {
                int firstNum = Integer.parseInt(firstSubstring.charAt(i) + "");
                nums.add(firstNum);
            }
            //处理后的单个数字集合加入到结果集合中
            resultList.add(nums);
        }
        System.out.println("第一次截取：" + firstSubstring);
        System.out.println("剩余：" + afterSubstring);

        for (int i = 0; i < times; ++i) {
            nums = new ArrayList<>();
            // 写固定数值4，方便读数
            for (int j = 0; j < 4; ++j) {
                /*
                index = i * 4 + j,这个是二维转一维公式
                多加了一个MOD，是因为刚才可能会有截取字符串的情况发生
                 */
                int ind = i * 4 + j + mod;
                // 实际转换后的单个数字
                int singleNum = Integer.parseInt(numStr.charAt(ind) + "");

                System.out.println("实际转换后的单个数字：" + singleNum);
                nums.add(singleNum);

            }
            resultList.add(nums);
            //输出一个空行，该行只为打印美观，可注释
            System.out.println();
        }

        StringBuilder sb = new StringBuilder();
        //这里是重点。开始读数
        int resultSize = resultList.size();
        for (int i = 0; i < resultSize; ++i) {
            int numsSize = resultList.get(i).size();
            for (int j = 0; j < numsSize; ++j) {
                Integer number = resultList.get(i).get(j);

                // 当j为最后一位数字且数字为：0时，不用读
                if (j != numsSize - 1 || number != 0) {
                    // 把读好的数字，拼接到StringBuilder中
                    sb.append(lowNumArr[number]);
                } else {

                }

                //读千百十位，个位不用读
                if (j + 4 - numsSize <= numsSize && j < lowUnits.length) {
                    //读数加入千百十
                    sb.append(lowUnits[j + 4 - numsSize]);
                }
            }

            switch (resultSize - i) {
                case 3:
                    sb.append("亿");
                    break;
                case 2:
                    sb.append("万");
                    break;
                default:
                    sb.append("");
            }
        }


        return sb.toString();
    }

    public static void main(String[] args) {

        String testNum = "345678912032";

        String convert = new ConvertNum().convert(testNum);

        System.out.println(convert);

    }
}

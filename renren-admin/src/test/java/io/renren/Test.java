package io.renren;

import io.renren.common.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    private static final String GSTIME = "yyyy-MM-dd";

    public static void main(String[] args) {

        String t1 = "2018-12-12";

        String tmp = String.valueOf(DateUtils.stringToDate(t1, "yyyy-MM-dd").getTime()).substring(0, 10);

        System.out.println(tmp);
        Integer seconds =1546096500;
        long millions=new Long(seconds).longValue()*1000;
        Date date=new Date(millions);
        SimpleDateFormat format=new SimpleDateFormat(GSTIME);
        String nowDateString=format.format(date);
        System.out.println(nowDateString);
    }

    //时间戳转换成日期格式：方法二
    public static void getUnixTransferTime(){
        System.out.println("转换的日期是：");
        long nowtime=1545751020;//某个时间戳;
        Date date=new Date(nowtime*1000);
        SimpleDateFormat format=new SimpleDateFormat(GSTIME);
        String nowDateString=format.format(date);
        System.out.println(nowDateString);
    }
}

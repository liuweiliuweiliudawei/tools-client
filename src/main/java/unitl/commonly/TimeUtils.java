package unitl.commonly;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author levy
 * @description 时间工具
 * @date 2021年1月12日 10:55:16
 */
public class TimeUtils {

    /**
     * @return 获取当前17位的毫秒级时间
     */
    public static String getCurrMillTime(){
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(date);
    }

    /**
     *
     * @param date 日期
     * @param format 格式
     * @return  string转date
     */
    public static Date StrToDate(String date,String format) throws ParseException {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(format);
        return simpleDateFormat.parse(date);
    }

    /**
     *
     * @param date 日期
     * @param format 格式
     * @return date转string
     * @throws ParseException
     */
    public static String DateToStr(Date date,String format) throws ParseException {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }


    /**
     * @return 获取当前14位的秒级时间
     */
    public static String getCurrTime(){
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(date);
    }

    /**
     * @param minute 延后的分钟数
     * @return 当前时间后minute分钟的17位毫秒级时间
     */
    public static String getNextMillTimeByMinute(int minute){
        Calendar calendar= Calendar.getInstance();
        calendar.add(Calendar.MINUTE, minute);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(calendar.getTime());
    }

    /**
     * @param time yyyyMMddHHmmss时间字符串
     * @return yyyy-MM-dd HH:mm:ss字符串
     * @throws ParseException
     */
    public static String getDateString(String time) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        Date date=sdf.parse(time);
        SimpleDateFormat sdfNew=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdfNew.format(date);
    }

    /**
     * @param date
     * @return 由2017-11-22 09:20:12得到20171122092012
     * @throws ParseException
     */
    public static String getTimeByDateTime(String date) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date newDate=sdf.parse(date);
        SimpleDateFormat sdfNew=new SimpleDateFormat("yyyyMMddHHmmss");
        return sdfNew.format(newDate);
    }

    /**
     * @param startDate 20171230类似的8位字符串
     * @return 		  获得2017-12-30类似的日期
     * @throws ParseException
     */
    public static String getDateSringByDate(String startDate) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        Date newDate=sdf.parse(startDate);
        SimpleDateFormat sdfNew=new SimpleDateFormat("yyyy-MM-dd");
        return sdfNew.format(newDate);
    }

    /**
     * @param date yyyy-MM-dd日期时间
     * @return yyyyMMdd
     * @throws ParseException
     */
    public static String getNumberStringByDate(String date) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date newDate=sdf.parse(date);
        SimpleDateFormat sdfNew=new SimpleDateFormat("yyyyMMdd");
        return sdfNew.format(newDate);
    }

    /**
     * 获得databaseTime(时间的毫秒数)之后，validTimeH小时后的时间
     * @param databaseTime validTimeH
     * @return yyyyMMddHHmmssSSS
     * @throws ParseException
     */
    public static String getNextHours(String databaseTime,int validTimeH) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Date date=sdf.parse(databaseTime);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, validTimeH);
        return sdf.format(calendar.getTime());
    }

    /**
     * 获得databaseTime之后，codeValidM分钟后的时间
     * @param databaseTime validTimeH
     * @return yyyyMMddHHmmssSSS
     * @throws ParseException
     */
    public static String getNextMinutes(String databaseTime,int codeValidM) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Date date=sdf.parse(databaseTime);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, codeValidM);
        return sdf.format(calendar.getTime());
    }

    /**
     * 按格式返回当前时间串
     * @param format 时间格式
     * @return 时间串
     */
    public static String getCurrTimeStr(String format) {
        Date now=new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(now);
    }

    /**
     * 按要求计算时间后返回
     * @param time 传入的时间
     * @param format 传入的时间格式
     * @param field	时间改变的单位 举例：静态常量(数值)Calendar.DATE天 Calendar.HOUR_OF_DAY时  Calendar.MILLISECOND  Calendar.SECOND
     * @param i 时间改变的量  正数是增量  负数是减量即往前计算
     * @param resultFormat 计算后返回的时间格式
     * @return 时间串
     * @throws ParseException
     */
    public static String getAddSubTime(String time,String format,int field ,int i, String resultFormat) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = simpleDateFormat.parse(time);
        Calendar calender = Calendar.getInstance();
        calender.setTime(date);
        calender.add(field, i);
        SimpleDateFormat resultSdfFormat = new SimpleDateFormat(resultFormat);
        return resultSdfFormat.format(calender.getTime());
    }

    /**
     * 按要求计算时间后返回
     * @param time 传入的时间
     * @param format 传入的时间格式
     * @param i 改变的量  长整型的数字 单位是毫秒 举例：70*24*60*60*1000L 正数表示增加70天后的日期
     * @param resultFormat 计算后返回的时间格式
     * @return 时间串
     * @throws ParseException
     */
    public static String getAddSubTime(String time,String format,long i, String resultFormat) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = simpleDateFormat.parse(time);
        long newTime = date.getTime() + i;
        Date newDate = new Date(newTime);
        SimpleDateFormat resultSdfFormat = new SimpleDateFormat(resultFormat);
        return resultSdfFormat.format(newDate);
    }

    /**
     * 计算两个时间之差
     * @param time1 第一个时间 被减数
     * @param time1Format 第一个时间格式
     * @param time2 第二个时间  减数
     * @param time2Format 第二个时间格式
     * @param i 返回时间差的级别：1 返回毫秒  2返回秒 3返回分钟  4返回小时 5返回天 其他的也返回毫秒
     * @return 相减的毫秒级数
     * @throws ParseException
     */
    public static long getMillSecondeByTime(String time1, String time1Format, String time2, String time2Format,int i) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(time1Format);
        Date date1 = simpleDateFormat.parse(time1);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(time2Format);
        Date date2 = simpleDateFormat2.parse(time2);
        long ms = date1.getTime() - date2.getTime();
        switch (i) {
            case 1:
                return ms;
            case 2:
                return ms/1000;
            case 3:
                return ms/(1000*60);
            case 4:
                return ms/(1000*60*60);
            case 5:
                return ms/(1000*60*60*24);
        }
        return ms;
    }

}

package untilcalss;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by zhushimin on 2017/8/29.
 */
public class DateUntil {

  /**
   * 获取指定日期所在周，月，年的第一天和最后一天.
   *
   * @param dataStr 日期
   * @param state   周年月
   * @return String
   * @throws ParseException ex
   */
  public static String getFirstAndLastOfWeek(String dataStr, String state) throws ParseException {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    String data = format.format(Long.valueOf(dataStr));
    Date date = format.parse(data);
    cal.setTime(date);
    Date date1 = null;
    Date date2 = null;
    if ("week".equals(state)) {
      int day = 0;
      if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
        day = -6;
      } else {
        day = 2 - cal.get(Calendar.DAY_OF_WEEK);
      }
      cal.add(Calendar.DAY_OF_WEEK, day);
      // 所在周开始日期
      date1 = cal.getTime();
      cal.add(Calendar.DAY_OF_WEEK, 6);
      // 所在周结束日期
      date2 = cal.getTime();
    } else if ("month".equals(state)) {
      cal.add(Calendar.MONTH, 0);
      Date theDate = cal.getTime();
      // 上个月第一天
      GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
      gcLast.setTime(theDate);
      gcLast.set(Calendar.DAY_OF_MONTH, 1);
      date1 = gcLast.getTime();
      // 上个月最后一天
      cal.add(Calendar.MONTH, 1); // 加一个月
      cal.set(Calendar.DATE, 1); // 设置为该月第一天
      cal.add(Calendar.DATE, -1); // 再减一天即为上个月最后一天
      date2 = cal.getTime();
    } else if ("year".equals(state)) {
      int year = cal.get(Calendar.YEAR);
      cal.clear();
      cal.set(Calendar.YEAR, year);
      date1 = cal.getTime();
      cal.set(Calendar.YEAR, year + 1);
      cal.add(cal.DATE, -1);
      date2 = cal.getTime();
    }
    Long days = Long.valueOf(86399000);
    long date3 = date2.getTime() + days;
    return date1.getTime() + "-" + date3;
  }
}

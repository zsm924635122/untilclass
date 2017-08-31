package untilcalss;

import java.util.Calendar;

/**
 * 日历功能的工具类.
 * 今天 1，昨天 2 ，本周 3，上周 4，近7天 5，本月 6，
 * 上月 7，近30天 8, 本季度 9，上季度 10，今年 11，去年 12
 * Created by zhushimin on 2017/8/31.
 */
public class CalenderUtils {

  /**
   * 获取数据选取时间的范围.
   *
   * @param type 选取时间类型
   * @return 数组 第一个为开始时间，第二个为结束时间
   */
  public static long[] getRangeTime(int type) {
    Calendar calendar = Calendar.getInstance();
    long now = calendar.getTime().getTime();
    int quarter = getQuarter(calendar);
    long[] resultTime = new long[2];
    switch (type) {
      // 今天
      case 1:
        resultTime[0] = getStartTime(calendar);
        resultTime[1] = now;
        break;
      // 昨天
      case 2:
        calendar.set(Calendar.DAY_OF_MONTH,
            calendar.get(Calendar.DAY_OF_MONTH) - 1);
        resultTime[0] = getStartTime(calendar);
        resultTime[1] = getEndTime(calendar);
        break;
      // 本周
      case 3:
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        resultTime[0] = getStartTime(calendar);
        resultTime[1] = now;
        break;
      // 上周
      case 4:
        calendar.set(Calendar.WEEK_OF_YEAR,
            calendar.get(Calendar.WEEK_OF_YEAR) - 1);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        resultTime[0] = getStartTime(calendar);
        calendar.set(Calendar.DAY_OF_YEAR,
            calendar.get(Calendar.DAY_OF_YEAR) + 6);
        resultTime[1] = getEndTime(calendar);
        break;
      // 最近7天
      case 5:
        calendar.set(Calendar.DAY_OF_YEAR,
            calendar.get(Calendar.DAY_OF_YEAR) - 6);
        resultTime[0] = getStartTime(calendar);
        resultTime[1] = now;
        break;
      // 本月
      case 6:
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        resultTime[0] = getStartTime(calendar);
        resultTime[1] = now;
        break;
      // 上月
      case 7:
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        resultTime[0] = getStartTime(calendar);
        int max = calendar.getActualMaximum(Calendar.DATE);
        calendar.set(Calendar.DAY_OF_MONTH, max);
        resultTime[1] = getEndTime(calendar);
        break;
      // 近30天
      case 8:
        calendar.set(Calendar.DAY_OF_YEAR,
            calendar.get(Calendar.DAY_OF_YEAR) - 29);
        resultTime[0] = getStartTime(calendar);
        resultTime[1] = now;
        break;
      // 本季度
      case 9:
        calendar.set(Calendar.MONTH, quarter);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        resultTime[0] = getStartTime(calendar);
        resultTime[1] = now;
        break;
      // 上季度
      case 10:
        if (quarter == 0) {
          calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
          calendar.set(Calendar.MONTH, 9);
        } else {
          calendar.set(Calendar.MONTH, quarter - 3);
        }
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        resultTime[0] = getStartTime(calendar);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 2);
        int day = calendar.getActualMaximum(Calendar.DATE);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        resultTime[1] = getEndTime(calendar);
        break;
      // 今年
      case 11:
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        resultTime[0] = getStartTime(calendar);
        resultTime[1] = now;
        break;
      // 去年
      case 12:
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        resultTime[0] = getStartTime(calendar);
        int maxYear = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DAY_OF_YEAR, maxYear);
        resultTime[1] = getEndTime(calendar);
        break;
      default:
        break;
    }
    return resultTime;
  }

  /**
   * 获取日历0:0:0时间戳.
   */
  public static long getStartTime(Calendar calendar) {
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    return calendar.getTime().getTime();
  }

  /**
   * 获取日历某天的23:59:59时间戳.
   */
  public static long getEndTime(Calendar calendar) {
    calendar.set(Calendar.HOUR_OF_DAY, 23);
    calendar.set(Calendar.MINUTE, 59);
    calendar.set(Calendar.SECOND, 59);
    return calendar.getTime().getTime();
  }

  /**
   * 获取季度值.
   */
  public static int getQuarter(Calendar calendar) {
    int quarter = calendar.get(Calendar.MONTH);
    if (quarter <= 3) {
      quarter = 0;
    } else if (quarter <= 6) {
      quarter = 3;
    } else if (quarter <= 9) {
      quarter = 6;
    } else {
      quarter = 9;
    }
    return quarter;
  }
}

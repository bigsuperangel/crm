package com.crm.common.tools;

import hirondelle.date4j.DateTime;
import hirondelle.date4j.DateTime.DayOverflow;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public final class DateTimeProcessing {
	public static final int ABORT = 1;
	public static final int FIRST_DAY = 2;
	public static final int LAST_DAY = 3;
	public static final int SPILLOVER = 4;

	private DateTimeProcessing() {
		throw new UnsupportedOperationException("非法构造 DateTimeProcessing 对象");
	}

	public static String dateToString(Date paramDate, String paramString) {
		if (paramDate == null)
			throw new IllegalArgumentException("date 参数异常");
		if (paramString == null)
			throw new IllegalArgumentException("format 参数异常");
		SimpleDateFormat localSimpleDateFormat = null;
		try {
			localSimpleDateFormat = new SimpleDateFormat(paramString);
		} catch (IllegalArgumentException localIllegalArgumentException) {
			throw new IllegalArgumentException("format 参数异常");
		}
		return localSimpleDateFormat.format(paramDate);
	}

	public static String dateToDateTimeString(Date paramDate) {
		return dateToString(paramDate, "yyyy-MM-dd HH:mm:ss.SSS");
	}

	public static Date dateTimeStringToDate(String paramString) {
		return new Date(getMilliseconds(paramString));
	}

	public static Date todayToDate() {
		return dateTimeStringToDate(todayToString());
	}

	public static String todayToString() {
		return DateTime.today(TimeZone.getDefault()).format("YYYY-MM-DD");
	}

	public static Date nowToDate() {
		return new Date();
	}

	public static String nowToString() {
		return DateTime.now(TimeZone.getDefault()).format(
				"YYYY-MM-DD hh:mm:ss.fff");
	}

	public static long getMilliseconds(Date paramDate) {
		if (paramDate == null)
			throw new IllegalArgumentException("date 参数异常");
		return paramDate.getTime();
	}

	public static long getMilliseconds(String paramString) {
		if (paramString == null)
			throw new IllegalArgumentException("dateTime 参数异常");
		DateTime localDateTime = new DateTime(paramString);
		try {
			return localDateTime.getMilliseconds(TimeZone.getDefault());
		} catch (Exception localException) {
		}
		throw new IllegalArgumentException("dateTime 参数异常");
	}

	public static int getYear(Date paramDate) {
		return getYear(dateToDateTimeString(paramDate));
	}

	public static int getYear(String paramString) {
		if (paramString == null)
			throw new IllegalArgumentException("dateTime 参数异常");
		DateTime localDateTime = new DateTime(paramString);
		try {
			return localDateTime.getYear().intValue();
		} catch (Exception localException) {
		}
		throw new IllegalArgumentException("dateTime 参数异常");
	}

	public static int getMonth(Date paramDate) {
		return getMonth(dateToDateTimeString(paramDate));
	}

	public static int getMonth(String paramString) {
		if (paramString == null)
			throw new IllegalArgumentException("dateTime 参数异常");
		DateTime localDateTime = new DateTime(paramString);
		try {
			return localDateTime.getMonth().intValue();
		} catch (Exception localException) {
		}
		throw new IllegalArgumentException("dateTime 参数异常");
	}

	public static int getDay(Date paramDate) {
		return getDay(dateToDateTimeString(paramDate));
	}

	public static int getDay(String paramString) {
		if (paramString == null)
			throw new IllegalArgumentException("dateTime 参数异常");
		DateTime localDateTime = new DateTime(paramString);
		try {
			return localDateTime.getDay().intValue();
		} catch (Exception localException) {
		}
		throw new IllegalArgumentException("dateTime 参数异常");
	}

	public static int getHour(Date paramDate) {
		return getHour(dateToDateTimeString(paramDate));
	}

	public static int getHour(String paramString) {
		if (paramString == null)
			throw new IllegalArgumentException("dateTime 参数异常");
		DateTime localDateTime = new DateTime(paramString);
		try {
			return localDateTime.getHour().intValue();
		} catch (Exception localException) {
		}
		throw new IllegalArgumentException("dateTime 参数异常");
	}

	public static int getMinute(Date paramDate) {
		return getMinute(dateToDateTimeString(paramDate));
	}

	public static int getMinute(String paramString) {
		if (paramString == null)
			throw new IllegalArgumentException("dateTime 参数异常");
		DateTime localDateTime = new DateTime(paramString);
		try {
			return localDateTime.getMinute().intValue();
		} catch (Exception localException) {
		}
		throw new IllegalArgumentException("dateTime 参数异常");
	}

	public static int getSecond(Date paramDate) {
		return getSecond(dateToDateTimeString(paramDate));
	}

	public static int getSecond(String paramString) {
		if (paramString == null)
			throw new IllegalArgumentException("dateTime 参数异常");
		DateTime localDateTime = new DateTime(paramString);
		try {
			return localDateTime.getSecond().intValue();
		} catch (Exception localException) {
		}
		throw new IllegalArgumentException("dateTime 参数异常");
	}

	public static int getMillisecond(Date paramDate) {
		return getMillisecond(dateToDateTimeString(paramDate));
	}

	public static int getMillisecond(String paramString) {
		if (paramString == null)
			throw new IllegalArgumentException("dateTime 参数异常");
		DateTime localDateTime = new DateTime(paramString);
		try {
			return localDateTime.getNanoseconds().intValue() / 1000000;
		} catch (Exception localException) {
		}
		throw new IllegalArgumentException("dateTime 参数异常");
	}

	public static int getWeekDay(Date paramDate) {
		return getWeekDay(dateToDateTimeString(paramDate));
	}

	public static int getWeekDay(String paramString) {
		if (paramString == null)
			throw new IllegalArgumentException("dateTime 参数异常");
		DateTime localDateTime = new DateTime(paramString);
		try {
			return localDateTime.getWeekDay().intValue();
		} catch (Exception localException) {
		}
		throw new IllegalArgumentException("dateTime 参数异常");
	}

	public static int getNumDaysInMonth(Date paramDate) {
		return getNumDaysInMonth(dateToDateTimeString(paramDate));
	}

	public static int getNumDaysInMonth(String paramString) {
		if (paramString == null)
			throw new IllegalArgumentException("dateTime 参数异常");
		DateTime localDateTime = new DateTime(paramString);
		try {
			return localDateTime.getNumDaysInMonth();
		} catch (Exception localException) {
		}
		throw new IllegalArgumentException("dateTime 参数异常");
	}

	public static int getWeekOfMonth(Date paramDate) {
		if (paramDate == null)
			throw new IllegalArgumentException("date 参数异常");
		Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
		localCalendar.setTime(paramDate);
		return localCalendar.get(8);
	}

	public static int getWeekOfMonth(String paramString) {
		return getWeekOfMonth(dateTimeStringToDate(paramString));
	}

	public static int getWeekOfYear(Date paramDate) {
		if (paramDate == null)
			throw new IllegalArgumentException("date 参数异常");
		Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
		localCalendar.setTime(paramDate);
		return localCalendar.get(3);
	}

	public static int getWeekOfYear(String paramString) {
		return getWeekOfYear(dateTimeStringToDate(paramString));
	}

	public static int getDayOfYear(Date paramDate) {
		return getDayOfYear(dateToDateTimeString(paramDate));
	}

	public static int getDayOfYear(String paramString) {
		if (paramString == null)
			throw new IllegalArgumentException("dateTime 参数异常");
		DateTime localDateTime = new DateTime(paramString);
		try {
			return localDateTime.getDayOfYear().intValue();
		} catch (Exception localException) {
		}
		throw new IllegalArgumentException("dateTime 参数异常");
	}

	public static Date getStartOfDayToDate(Date paramDate) {
		String str = getStartOfDayToString(dateToDateTimeString(paramDate));
		return dateTimeStringToDate(str);
	}

	public static Date getStartOfDayToDate(String paramString) {
		String str = getStartOfDayToString(paramString);
		return dateTimeStringToDate(str);
	}

	public static String getStartOfDayToString(Date paramDate) {
		return getStartOfDayToString(dateToDateTimeString(paramDate));
	}

	public static String getStartOfDayToString(String paramString) {
		if (paramString == null)
			throw new IllegalArgumentException("dateTime 参数异常");
		DateTime localDateTime = new DateTime(paramString);
		try {
			return localDateTime.getStartOfDay().format(
					"YYYY-MM-DD hh:mm:ss.fff");
		} catch (Exception localException) {
		}
		throw new IllegalArgumentException("dateTime 参数异常");
	}

	public static Date getEndOfDayToDate(Date paramDate) {
		String str = getEndOfDayToString(dateToDateTimeString(paramDate));
		return dateTimeStringToDate(str);
	}

	public static Date getEndOfDayToDate(String paramString) {
		String str = getEndOfDayToString(paramString);
		return dateTimeStringToDate(str);
	}

	public static String getEndOfDayToString(Date paramDate) {
		return getEndOfDayToString(dateToDateTimeString(paramDate));
	}

	public static String getEndOfDayToString(String paramString) {
		if (paramString == null)
			throw new IllegalArgumentException("dateTime 参数异常");
		DateTime localDateTime = new DateTime(paramString);
		try {
			return localDateTime.getEndOfDay()
					.format("YYYY-MM-DD hh:mm:ss.fff");
		} catch (Exception localException) {
		}
		throw new IllegalArgumentException("dateTime 参数异常");
	}

	public static Date getStartOfMonthToDate(Date paramDate) {
		String str = getStartOfMonthToString(dateToDateTimeString(paramDate));
		return dateTimeStringToDate(str);
	}

	public static Date getStartOfMonthToDate(String paramString) {
		String str = getStartOfMonthToString(paramString);
		return dateTimeStringToDate(str);
	}

	public static String getStartOfMonthToString(Date paramDate) {
		return getStartOfMonthToString(dateToDateTimeString(paramDate));
	}

	public static String getStartOfMonthToString(String paramString) {
		if (paramString == null)
			throw new IllegalArgumentException("dateTime 参数异常");
		DateTime localDateTime = new DateTime(paramString);
		try {
			return localDateTime.getStartOfMonth().format(
					"YYYY-MM-DD hh:mm:ss.fff");
		} catch (Exception localException) {
		}
		throw new IllegalArgumentException("dateTime 参数异常");
	}

	public static Date getEndOfMonthToDate(Date paramDate) {
		String str = getEndOfMonthToString(dateToDateTimeString(paramDate));
		return dateTimeStringToDate(str);
	}

	public static Date getEndOfMonthToDate(String paramString) {
		String str = getEndOfMonthToString(paramString);
		return dateTimeStringToDate(str);
	}

	public static String getEndOfMonthToString(Date paramDate) {
		return getEndOfMonthToString(dateToDateTimeString(paramDate));
	}

	public static String getEndOfMonthToString(String paramString) {
		if (paramString == null)
			throw new IllegalArgumentException("dateTime 参数异常");
		DateTime localDateTime = new DateTime(paramString);
		try {
			return localDateTime.getEndOfMonth().format(
					"YYYY-MM-DD hh:mm:ss.fff");
		} catch (Exception localException) {
		}
		throw new IllegalArgumentException("dateTime 参数异常");
	}

	public static Date getStartOfYearToDate(Date paramDate) {
		String str = getStartOfYearToString(dateToDateTimeString(paramDate));
		return dateTimeStringToDate(str);
	}

	public static Date getStartOfYearToDate(String paramString) {
		String str = getStartOfYearToString(paramString);
		return dateTimeStringToDate(str);
	}

	public static String getStartOfYearToString(Date paramDate) {
		return getStartOfYearToString(dateToDateTimeString(paramDate));
	}

	public static String getStartOfYearToString(String paramString) {
		if (paramString == null)
			throw new IllegalArgumentException("dateTime 参数异常");
		DateTime localDateTime = new DateTime(paramString);
		try {
			if (!localDateTime.hasYearMonthDay())
				throw new IllegalArgumentException("dateTime 参数异常");
		} catch (IllegalArgumentException localIllegalArgumentException) {
			throw localIllegalArgumentException;
		} catch (Exception localException) {
			throw new IllegalArgumentException("dateTime 参数异常");
		}
		int i = getYear(paramString);
		return i + "-01-01 00:00:00.000";
	}

	public static Date getEndOfYearToDate(Date paramDate) {
		String str = getEndOfYearToString(dateToDateTimeString(paramDate));
		return dateTimeStringToDate(str);
	}

	public static Date getEndOfYearToDate(String paramString) {
		String str = getEndOfYearToString(paramString);
		return dateTimeStringToDate(str);
	}

	public static String getEndOfYearToString(Date paramDate) {
		return getEndOfYearToString(dateToDateTimeString(paramDate));
	}

	public static String getEndOfYearToString(String paramString) {
		if (paramString == null)
			throw new IllegalArgumentException("dateTime 参数异常");
		DateTime localDateTime = new DateTime(paramString);
		try {
			if (!localDateTime.hasYearMonthDay())
				throw new IllegalArgumentException("dateTime 参数异常");
		} catch (IllegalArgumentException localIllegalArgumentException) {
			throw localIllegalArgumentException;
		} catch (Exception localException) {
			throw new IllegalArgumentException("dateTime 参数异常");
		}
		int i = getYear(paramString);
		return i + "-12-31 23:59:59.999";
	}

	public static Date plusToDate(Date paramDate, int paramInt1, int paramInt2,
			int paramInt3, int paramInt4, int paramInt5, int paramInt6,
			int paramInt7, int paramInt8) throws Exception {
		String str = plusToString(dateToDateTimeString(paramDate), paramInt1,
				paramInt2, paramInt3, paramInt4, paramInt5, paramInt6,
				paramInt7, paramInt8);
		return dateTimeStringToDate(str);
	}

	public static Date plusToDate(String paramString, int paramInt1,
			int paramInt2, int paramInt3, int paramInt4, int paramInt5,
			int paramInt6, int paramInt7, int paramInt8) throws Exception {
		String str = plusToString(paramString, paramInt1, paramInt2, paramInt3,
				paramInt4, paramInt5, paramInt6, paramInt7, paramInt8);
		return dateTimeStringToDate(str);
	}

	public static String plusToString(Date paramDate, int paramInt1,
			int paramInt2, int paramInt3, int paramInt4, int paramInt5,
			int paramInt6, int paramInt7, int paramInt8) throws Exception {
		return plusToString(dateToDateTimeString(paramDate), paramInt1,
				paramInt2, paramInt3, paramInt4, paramInt5, paramInt6,
				paramInt7, paramInt8);
	}

	public static String plusToString(String paramString, int paramInt1,
			int paramInt2, int paramInt3, int paramInt4, int paramInt5,
			int paramInt6, int paramInt7, int paramInt8) throws Exception {
		if (paramString == null)
			throw new IllegalArgumentException("dateTime 参数异常");
		if ((paramInt1 < 0) || (paramInt1 > 9999))
			throw new IllegalArgumentException("year 参数异常");
		if ((paramInt2 < 0) || (paramInt2 > 9999))
			throw new IllegalArgumentException("month 参数异常");
		if ((paramInt3 < 0) || (paramInt3 > 9999))
			throw new IllegalArgumentException("day 参数异常");
		if ((paramInt4 < 0) || (paramInt4 > 9999))
			throw new IllegalArgumentException("hour 参数异常");
		if ((paramInt5 < 0) || (paramInt5 > 9999))
			throw new IllegalArgumentException("minute 参数异常");
		if ((paramInt6 < 0) || (paramInt6 > 9999))
			throw new IllegalArgumentException("second 参数异常");
		if ((paramInt7 < 0) || (paramInt7 > 999))
			throw new IllegalArgumentException("millisecond 参数异常");
		DateTime localDateTime = new DateTime(paramString);
		try {
			switch (paramInt8) {
			case 1:
				return localDateTime.plus(Integer.valueOf(paramInt1),
						Integer.valueOf(paramInt2), Integer.valueOf(paramInt3),
						Integer.valueOf(paramInt4), Integer.valueOf(paramInt5),
						Integer.valueOf(paramInt6),
						Integer.valueOf(paramInt7 * 1000000),
						DateTime.DayOverflow.Abort).format(
						"YYYY-MM-DD hh:mm:ss.fff");
			case 2:
				return localDateTime.plus(Integer.valueOf(paramInt1),
						Integer.valueOf(paramInt2), Integer.valueOf(paramInt3),
						Integer.valueOf(paramInt4), Integer.valueOf(paramInt5),
						Integer.valueOf(paramInt6),
						Integer.valueOf(paramInt7 * 1000000),
						DateTime.DayOverflow.FirstDay).format(
						"YYYY-MM-DD hh:mm:ss.fff");
			case 3:
				return localDateTime.plus(Integer.valueOf(paramInt1),
						Integer.valueOf(paramInt2), Integer.valueOf(paramInt3),
						Integer.valueOf(paramInt4), Integer.valueOf(paramInt5),
						Integer.valueOf(paramInt6),
						Integer.valueOf(paramInt7 * 1000000),
						DateTime.DayOverflow.LastDay).format(
						"YYYY-MM-DD hh:mm:ss.fff");
			case 4:
				return localDateTime.plus(Integer.valueOf(paramInt1),
						Integer.valueOf(paramInt2), Integer.valueOf(paramInt3),
						Integer.valueOf(paramInt4), Integer.valueOf(paramInt5),
						Integer.valueOf(paramInt6),
						Integer.valueOf(paramInt7 * 1000000),
						DateTime.DayOverflow.Spillover).format(
						"YYYY-MM-DD hh:mm:ss.fff");
			}
			throw new IllegalArgumentException("overflow 参数异常");
		} catch (IllegalArgumentException localIllegalArgumentException) {
			if (localIllegalArgumentException.getMessage().startsWith(
					"overflow 参数异常")) {
				throw localIllegalArgumentException;
			}
			throw new IllegalArgumentException("dateTime 参数异常");
		} catch (Exception localException) {
			StringBuilder localStringBuilder = new StringBuilder();
			localStringBuilder.append("时间相加操作失败（原因“").append(
					localException.toString().replace("\r", "").replace("\n",
							"").trim()).append("”）");
			throw new Exception(localStringBuilder.toString());
		}
	}

	public static Date minusToDate(Date paramDate, int paramInt1,
			int paramInt2, int paramInt3, int paramInt4, int paramInt5,
			int paramInt6, int paramInt7, int paramInt8) throws Exception {
		String str = minusToString(dateToDateTimeString(paramDate), paramInt1,
				paramInt2, paramInt3, paramInt4, paramInt5, paramInt6,
				paramInt7, paramInt8);
		return dateTimeStringToDate(str);
	}

	public static Date minusToDate(String paramString, int paramInt1,
			int paramInt2, int paramInt3, int paramInt4, int paramInt5,
			int paramInt6, int paramInt7, int paramInt8) throws Exception {
		String str = minusToString(paramString, paramInt1, paramInt2,
				paramInt3, paramInt4, paramInt5, paramInt6, paramInt7,
				paramInt8);
		return dateTimeStringToDate(str);
	}

	public static String minusToString(Date paramDate, int paramInt1,
			int paramInt2, int paramInt3, int paramInt4, int paramInt5,
			int paramInt6, int paramInt7, int paramInt8) throws Exception {
		return minusToString(dateToDateTimeString(paramDate), paramInt1,
				paramInt2, paramInt3, paramInt4, paramInt5, paramInt6,
				paramInt7, paramInt8);
	}

	public static String minusToString(String paramString, int paramInt1,
			int paramInt2, int paramInt3, int paramInt4, int paramInt5,
			int paramInt6, int paramInt7, int paramInt8) throws Exception {
		if (paramString == null)
			throw new IllegalArgumentException("dateTime 参数异常");
		if ((paramInt1 < 0) || (paramInt1 > 9999))
			throw new IllegalArgumentException("year 参数异常");
		if ((paramInt2 < 0) || (paramInt2 > 9999))
			throw new IllegalArgumentException("month 参数异常");
		if ((paramInt3 < 0) || (paramInt3 > 9999))
			throw new IllegalArgumentException("day 参数异常");
		if ((paramInt4 < 0) || (paramInt4 > 9999))
			throw new IllegalArgumentException("hour 参数异常");
		if ((paramInt5 < 0) || (paramInt5 > 9999))
			throw new IllegalArgumentException("minute 参数异常");
		if ((paramInt6 < 0) || (paramInt6 > 9999))
			throw new IllegalArgumentException("second 参数异常");
		if ((paramInt7 < 0) || (paramInt7 > 999))
			throw new IllegalArgumentException("millisecond 参数异常");
		DateTime localDateTime = new DateTime(paramString);
		try {
			switch (paramInt8) {
			case 1:
				return localDateTime.minus(Integer.valueOf(paramInt1),
						Integer.valueOf(paramInt2), Integer.valueOf(paramInt3),
						Integer.valueOf(paramInt4), Integer.valueOf(paramInt5),
						Integer.valueOf(paramInt6),
						Integer.valueOf(paramInt7 * 1000000),
						DateTime.DayOverflow.Abort).format(
						"YYYY-MM-DD hh:mm:ss.fff");
			case 2:
				return localDateTime.minus(Integer.valueOf(paramInt1),
						Integer.valueOf(paramInt2), Integer.valueOf(paramInt3),
						Integer.valueOf(paramInt4), Integer.valueOf(paramInt5),
						Integer.valueOf(paramInt6),
						Integer.valueOf(paramInt7 * 1000000),
						DateTime.DayOverflow.FirstDay).format(
						"YYYY-MM-DD hh:mm:ss.fff");
			case 3:
				return localDateTime.minus(Integer.valueOf(paramInt1),
						Integer.valueOf(paramInt2), Integer.valueOf(paramInt3),
						Integer.valueOf(paramInt4), Integer.valueOf(paramInt5),
						Integer.valueOf(paramInt6),
						Integer.valueOf(paramInt7 * 1000000),
						DateTime.DayOverflow.LastDay).format(
						"YYYY-MM-DD hh:mm:ss.fff");
			case 4:
				return localDateTime.minus(Integer.valueOf(paramInt1),
						Integer.valueOf(paramInt2), Integer.valueOf(paramInt3),
						Integer.valueOf(paramInt4), Integer.valueOf(paramInt5),
						Integer.valueOf(paramInt6),
						Integer.valueOf(paramInt7 * 1000000),
						DateTime.DayOverflow.Spillover).format(
						"YYYY-MM-DD hh:mm:ss.fff");
			}
			throw new IllegalArgumentException("overflow 参数异常");
		} catch (IllegalArgumentException localIllegalArgumentException) {
			if (localIllegalArgumentException.getMessage().startsWith(
					"overflow 参数异常")) {
				throw localIllegalArgumentException;
			}
			throw new IllegalArgumentException("dateTime 参数异常");
		} catch (Exception localException) {
			StringBuilder localStringBuilder = new StringBuilder();
			localStringBuilder.append("时间相减操作失败（原因“").append(
					localException.toString().replace("\r", "").replace("\n",
							"").trim()).append("”）");
			throw new Exception(localStringBuilder.toString());
		}
	}

	public static boolean isLeapYear(Date paramDate) {
		return isLeapYear(dateToDateTimeString(paramDate));
	}

	public static boolean isLeapYear(String paramString) {
		if (paramString == null)
			throw new IllegalArgumentException("dateTime 参数异常");
		DateTime localDateTime = new DateTime(paramString);
		try {
			return localDateTime.isLeapYear().booleanValue();
		} catch (Exception localException) {
		}
		throw new IllegalArgumentException("dateTime 参数异常");
	}

	public static boolean isInTheFuture(Date paramDate) {
		return isInTheFuture(dateToDateTimeString(paramDate));
	}

	public static boolean isInTheFuture(String paramString) {
		if (paramString == null)
			throw new IllegalArgumentException("dateTime 参数异常");
		DateTime localDateTime = new DateTime(paramString);
		try {
			return localDateTime.isInTheFuture(TimeZone.getDefault());
		} catch (Exception localException) {
		}
		throw new IllegalArgumentException("dateTime 参数异常");
	}

	public static boolean isInThePast(Date paramDate) {
		return isInThePast(dateToDateTimeString(paramDate));
	}

	public static boolean isInThePast(String paramString) {
		if (paramString == null)
			throw new IllegalArgumentException("dateTime 参数异常");
		DateTime localDateTime = new DateTime(paramString);
		try {
			return localDateTime.isInThePast(TimeZone.getDefault());
		} catch (Exception localException) {
		}
		throw new IllegalArgumentException("dateTime 参数异常");
	}

	public static boolean isSameDate(Date paramDate1, Date paramDate2) {
		if (paramDate1 == null)
			throw new IllegalArgumentException("date1 参数异常");
		if (paramDate2 == null)
			throw new IllegalArgumentException("date2 参数异常");
		return isSameDate(dateToDateTimeString(paramDate1),
				dateToDateTimeString(paramDate2));
	}

	public static boolean isSameDate(String paramString1, String paramString2) {
		if (paramString1 == null)
			throw new IllegalArgumentException("dateTime1 参数异常");
		if (paramString2 == null)
			throw new IllegalArgumentException("dateTime2 参数异常");
		DateTime localDateTime1 = new DateTime(paramString1);
		try {
			if (!localDateTime1.hasYearMonthDay())
				throw new IllegalArgumentException("dateTime1 参数异常");
		} catch (IllegalArgumentException localIllegalArgumentException1) {
			throw localIllegalArgumentException1;
		} catch (Exception localException1) {
			throw new IllegalArgumentException("dateTime1 参数异常");
		}
		DateTime localDateTime2 = new DateTime(paramString2);
		try {
			if (!localDateTime2.hasYearMonthDay())
				throw new IllegalArgumentException("dateTime2 参数异常");
		} catch (IllegalArgumentException localIllegalArgumentException2) {
			throw localIllegalArgumentException2;
		} catch (Exception localException2) {
			throw new IllegalArgumentException("dateTime2 参数异常");
		}
		return localDateTime1.isSameDayAs(localDateTime2);
	}

	public static int compare(Date paramDate1, Date paramDate2) {
		if (paramDate1 == null)
			throw new IllegalArgumentException("date1 参数异常");
		if (paramDate2 == null)
			throw new IllegalArgumentException("date2 参数异常");
		long l1 = paramDate1.getTime();
		long l2 = paramDate2.getTime();
		if (l1 > l2)
			return 1;
		if (l1 < l2) {
			return -1;
		}
		return 0;
	}

	public static int compare(String paramString1, String paramString2) {
		Date localDate1 = null;
		Date localDate2 = null;
		try {
			localDate1 = dateTimeStringToDate(paramString1);
		} catch (IllegalArgumentException localIllegalArgumentException1) {
			throw new IllegalArgumentException("dateTime1 参数异常");
		}
		try {
			localDate2 = dateTimeStringToDate(paramString2);
		} catch (IllegalArgumentException localIllegalArgumentException2) {
			throw new IllegalArgumentException("dateTime2 参数异常");
		}
		return compare(localDate1, localDate2);
	}

	public static long differenceMilliseconds(Date paramDate1, Date paramDate2) {
		if (paramDate1 == null)
			throw new IllegalArgumentException("date1 参数异常");
		if (paramDate2 == null)
			throw new IllegalArgumentException("date2 参数异常");
		return paramDate1.getTime() - paramDate2.getTime();
	}

	public static long differenceMilliseconds(String paramString1,
			String paramString2) {
		Date localDate1 = null;
		Date localDate2 = null;
		try {
			localDate1 = dateTimeStringToDate(paramString1);
		} catch (IllegalArgumentException localIllegalArgumentException1) {
			throw new IllegalArgumentException("dateTime1 参数异常");
		}
		try {
			localDate2 = dateTimeStringToDate(paramString2);
		} catch (IllegalArgumentException localIllegalArgumentException2) {
			throw new IllegalArgumentException("dateTime2 参数异常");
		}
		return localDate1.getTime() - localDate2.getTime();
	}
}
package com.gratex.tools.pp.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DateFormatter {

	public static final DateTimeFormatter PP_DATE_FORMAT = DateTimeFormatter.ofPattern("ddMMyyyy");
	public static final DateTimeFormatter SLOVAK_DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

	public static final int DAY_MONTH_FORMAT_SCALE = 2;

	/**
	 *
	 * @param ddmmyyyy
	 * @return
	 */
	public static LocalDate fromDDMMYYYY(String ddmmyyyy) {
		Objects.requireNonNull(ddmmyyyy, "Input can not be null");
		return LocalDate.parse(ddmmyyyy, PP_DATE_FORMAT);
	}

	/**
	 *
	 * @param slovakDateString
	 * @return
	 */
	public static LocalDate fromSlovakDateFormat(String slovakDateString) {
		Objects.requireNonNull(slovakDateString, "Input can not be null");
		return LocalDate.parse(slovakDateString, SLOVAK_DATE_FORMAT);
	}

	public static String toSlovakDateFormatString(LocalDate date) {
		return SLOVAK_DATE_FORMAT.format(date);
	}

	/**
	 * @param number
	 * @return
	 */
	public static String formatNumber(int number, int scale) {
		return String.format("%0" + scale + "d", number);
	}

}

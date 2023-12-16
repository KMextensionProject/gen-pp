package com.gratex.tools.pp.utils;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormattingUtility {

	public static final DateTimeFormatter PP_DATE_FORMAT = DateTimeFormatter.ofPattern("ddMMyyyy");
	public static final DateTimeFormatter SLOVAK_DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

	public static final int DAY_MONTH_FORMAT_SCALE = 2;

	private FormattingUtility() {
		throw new IllegalStateException("DateFormatter was not designed to be instantiated");
	}

	/**
	 *
	 * @param ddmmyyyy
	 * @return
	 */
	public static LocalDate fromDDMMYYYY(String ddmmyyyy) {
		requireNonNull(ddmmyyyy, "ddmmyyyy can not be null");
		return LocalDate.parse(ddmmyyyy, PP_DATE_FORMAT);
	}

	/**
	 *
	 * @param slovakDateString
	 * @return
	 */
	public static LocalDate fromSlovakDateFormat(String slovakDateString) {
		requireNonNull(slovakDateString, "slovakDateString can not be null");
		return LocalDate.parse(slovakDateString, SLOVAK_DATE_FORMAT);
	}

	public static String toSlovakDateFormatString(LocalDate date) {
		requireNonNull(date, "date can not be null");
		return SLOVAK_DATE_FORMAT.format(date);
	}

	/**
	 * @param number
	 * @return
	 */
	public static String formatNumber(int number, int scale) {
		if (scale == 0) {
			throw new IllegalArgumentException("scale must be > 0");
		}
		if (("" + number).replace("-", "").length() > scale) {
			throw new IllegalArgumentException("scale must be >= number digits length");
		}
		String formatterMark = "%0" + scale + "d";
		return String.format(formatterMark, number);
	}

}

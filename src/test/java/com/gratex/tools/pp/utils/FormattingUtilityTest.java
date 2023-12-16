package com.gratex.tools.pp.utils;

import static com.gratex.tools.pp.utils.FormattingUtility.formatNumber;
import static com.gratex.tools.pp.utils.FormattingUtility.fromDDMMYYYY;
import static com.gratex.tools.pp.utils.FormattingUtility.fromSlovakDateFormat;
import static com.gratex.tools.pp.utils.FormattingUtility.toSlovakDateFormatString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

class FormattingUtilityTest {

	@Test
	void fromDDMMYYYYTest() {
		LocalDate christmas = fromDDMMYYYY("24122023");
		assertEquals(24, christmas.getDayOfMonth());
		assertEquals(12, christmas.getMonthValue());
		assertEquals(2023, christmas.getYear());

		assertThrows(NullPointerException.class, () -> fromDDMMYYYY(null));
		assertThrows(DateTimeParseException.class, () -> fromDDMMYYYY("ddMMyyyy"));
		assertThrows(DateTimeParseException.class, () -> fromDDMMYYYY("20231224"));
	}

	@Test
	void fromSlovakDateFormatTest() {
		LocalDate date = fromSlovakDateFormat("24.12.2023");
		assertEquals(24, date.getDayOfMonth());
		assertEquals(12, date.getMonthValue());
		assertEquals(2023, date.getYear());

		assertThrows(DateTimeParseException.class, () -> fromSlovakDateFormat("2023.12.24"));
		assertThrows(NullPointerException.class, () -> fromSlovakDateFormat(null));
	}

	@Test
	void toSlovakDateFormatStringTest() {
		String slovakDate = toSlovakDateFormatString(LocalDate.of(2023, 12, 24));
		assertEquals("24.12.2023", slovakDate);

		assertThrows(NullPointerException.class, () -> toSlovakDateFormatString(null));
	}

	@Test
	void formatNumberTest() {
		assertEquals("00123", formatNumber(123, 5));
		assertEquals("00000", formatNumber(0, 5));
		assertThrows(IllegalArgumentException.class, () -> formatNumber(123456, 5));
		assertThrows(IllegalArgumentException.class, () -> formatNumber(-1, 0));
	}
}

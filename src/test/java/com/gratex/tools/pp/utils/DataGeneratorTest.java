package com.gratex.tools.pp.utils;

import static com.gratex.tools.pp.utils.DataGenerator.getRandomNumericString;
import static com.gratex.tools.pp.utils.DataGenerator.getRandomString;
import static com.gratex.tools.pp.utils.DataGenerator.appendSpacesToEnsureSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DataGeneratorTest {

	@Test
	void getRandomNumericStringTest() {
		assertThrows(IllegalArgumentException.class, () -> getRandomNumericString(0));
		assertThrows(IllegalArgumentException.class, () -> getRandomNumericString(-1));

		assertEquals(1, getRandomNumericString(1).length());
		assertEquals(1234, getRandomNumericString(1234).length());
	}

	@Test
	void getRandomStringTest() {
		assertEquals(12, getRandomString(12, 12).length());
		assertEquals(20, getRandomString(12, 20).length());
		assertEquals(01, getRandomString(00, 01).length());
		assertTrue(getRandomString(5, 10).substring(5).trim().isEmpty());

		// why would we use this method to generate empty string...
		assertThrows(IllegalArgumentException.class, () -> getRandomString(00, 00));
		assertThrows(IllegalArgumentException.class, () -> getRandomString(05, 04));
	}

	@Test
	void ensuringStringLengthTest() {
		String actual = appendSpacesToEnsureSize("Bratislava", 12);

		assertEquals(12, actual.length());		
		assertTrue(actual.contains("Bratislava"));
		assertTrue(actual.substring(10).trim().isEmpty());
		assertEquals(12, appendSpacesToEnsureSize("", 12).length());

		// hmm...do we need to handle such case?
//		assertEquals(12, appendSpacesToEnsureSize("\t\t\t\t\t\t", 12).length());

		assertThrows(IllegalArgumentException.class, () -> appendSpacesToEnsureSize("123", 2));
		assertThrows(NullPointerException.class, () -> appendSpacesToEnsureSize(null, 2));
	}

}

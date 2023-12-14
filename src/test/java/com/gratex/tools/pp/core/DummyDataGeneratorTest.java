package com.gratex.tools.pp.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.gratex.tools.main.DummyDataGenerator;

class DummyDataGeneratorTest {

	@Test
	void getRandomNumericStringTest() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> DummyDataGenerator.getRandomNumericString(0));
		Assertions.assertThrows(IllegalArgumentException.class, () -> DummyDataGenerator.getRandomNumericString(-1));

		Assertions.assertEquals(1, DummyDataGenerator.getRandomNumericString(1).length());
		Assertions.assertEquals(1234, DummyDataGenerator.getRandomNumericString(1234).length());
	}

}

package com.gratex.tools.pp.utils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.gratex.tools.pp.core.DataIntegrityViolation;

class CharSequenceIteratorTest {

	@Test
	void sequencerTest() {
		// nonNull
		assertThrows(NullPointerException.class, () -> new CharSequenceIterator(null));

		CharSequenceIterator sequencer = new CharSequenceIterator("onetwothree");
		assertDoesNotThrow(() -> sequencer.resetSequencer());
		assertEquals("onetwothree", sequencer.next(11));

		sequencer.resetSequencer();
		assertEquals("one", sequencer.next(3));
		assertEquals("two", sequencer.next(3));
		assertEquals("three", sequencer.next(5));

		// when read it all out, reset should be called - overflow is not supported
		assertFalse(sequencer.hasMore());
		assertThrows(DataIntegrityViolation.class, () -> sequencer.next(1));
		// same happens if you try to go backwards - no point of implementing this right now
		assertThrows(UnsupportedOperationException.class, () -> sequencer.next(-2));
	}
}

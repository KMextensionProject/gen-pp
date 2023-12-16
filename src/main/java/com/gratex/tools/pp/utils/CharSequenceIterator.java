package com.gratex.tools.pp.utils;

import static java.util.Objects.requireNonNull;

/**
 *
 * @author mkrajcovicux
 */
public class CharSequenceIterator {

	private String data;
	private int maxLength;

	private int start;
	private int end;

	/**
	 *
	 * @param source
	 */
	public CharSequenceIterator(String source) {
		requireNonNull(source);
		data = source;
		maxLength = data.length();
	}

	/**
	 *
	 * @param length
	 * @return
	 */
	public String next(int length) {
		if (length < 0) {
			throw new UnsupportedOperationException("Length must be in range [0-source::length]");
		}
		end += length;
		if (end > maxLength) {
			throw new UnsupportedOperationException("Reached end of source string");
		}
		String result = data.substring(start, end);
		start = end;
		return result;
	}

	/**
	 *
	 * @return
	 */
//	public boolean canRead(int length) {
//		
//	}

	/**
	 *
	 */
	public void resetSequencer() {
		start = 0;
		end = 0;
	}

}

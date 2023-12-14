package com.gratex.tools.main;

import java.security.SecureRandom;
import java.util.Random;

public class DummyDataGenerator {

	private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final Random NUMBER_GENERATOR = new SecureRandom();

	public static String getRandomNumericString(int length) {
		if (length < 1) {
			throw new IllegalArgumentException("length " + length + " is not valid, try 0-" + Integer.MAX_VALUE);
		}
		if (length == 1) {
			return "" + NUMBER_GENERATOR.nextInt(9);
		}
	    int number = NUMBER_GENERATOR.nextInt(99);
	    return String.format("%0" + length + "d", number);
	}

	public static String getRandomString(int filledContentLength, int totalSize) {
		if (totalSize < 1) {
			throw new IllegalArgumentException("total size of desired string must be positive");
		} else if (filledContentLength > totalSize) {
			throw new IllegalArgumentException("total size of desired string cannot be smaller than filled content");
		} else if (filledContentLength < 1) {
			return getSpaces(totalSize); 
		}
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < filledContentLength; i++) {
			result.append(ALPHABET.charAt(NUMBER_GENERATOR.nextInt(ALPHABET.length())));
		}
		return result + getSpaces(totalSize - filledContentLength);
	}

	public static String appendSpacesToMatchSize(String startingText, int totalSize) {
		if (startingText.length() > totalSize) {
			throw new IllegalArgumentException("Starting text is longer than totalSize (" 
					+ startingText.length() + "/" + totalSize + ")");
		}
		return startingText + getSpaces(totalSize - startingText.length());
	}

	private static String getSpaces(int howMany) {
		StringBuilder spaces = new StringBuilder();
		for (int i = 0; i < howMany; i++) {
			spaces.append(" ");
		}
		return spaces.toString();
	}
}

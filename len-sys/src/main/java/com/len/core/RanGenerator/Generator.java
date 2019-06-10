package com.len.core.RanGenerator;

import java.util.ArrayList;
import java.util.Random;

public class Generator {
	public int MinUpperCaseChars = 0;
	public int MinLowerCaseChars = 0;
	public int MinNumericChars = 16;
	public int MinSpecialChars = 0;
	public CharType FillRest = CharType.NUMERIC;

	public Generator() {
	}

	public Generator(int minUpperCaseChars, int minLowerCaseChars,
			int minNumericChars, int minSpecialChars, CharType fillRest) {
		this.MinUpperCaseChars = minUpperCaseChars;
		this.MinLowerCaseChars = minLowerCaseChars;
		this.MinNumericChars = minNumericChars;
		this.MinSpecialChars = minSpecialChars;
		this.FillRest = fillRest;
	}

	public String generateString() {
		int sum = this.MinUpperCaseChars + this.MinLowerCaseChars
				+ this.MinNumericChars + this.MinSpecialChars;
		int length = 16;
		if (length < sum)
			throw new IllegalArgumentException(
					"length parameter must be valid!");

		ArrayList<Character> chars = new ArrayList<Character>();

		if (this.MinUpperCaseChars > 0) {
			chars.addAll(getUpperCaseChars(this.MinUpperCaseChars));
		}

		if (this.MinLowerCaseChars > 0) {
			chars.addAll(getLowerCaseChars(this.MinLowerCaseChars));
		}

		if (this.MinNumericChars > 0) {
			chars.addAll(getNumericChars(this.MinNumericChars));
		}

		if (this.MinSpecialChars > 0) {
			chars.addAll(getSpecialChars(this.MinSpecialChars));
		}

		int restLength = length - chars.size();

		switch (this.FillRest) {
		case UPPERCASE:
			chars.addAll(getUpperCaseChars(restLength));
			break;
		case LOWERCASE:
			chars.addAll(getLowerCaseChars(restLength));
			break;
		case NUMERIC:
			chars.addAll(getNumericChars(restLength));
			break;
		case SPECIAL:
			chars.addAll(getSpecialChars(restLength));
			break;
		default:
			chars.addAll(getLowerCaseChars(restLength));
			break;
		}

		return generateStringFromList(chars);
	}

	private ArrayList<Character> getUpperCaseChars(int count) {
		ArrayList<Character> result = new ArrayList<Character>();

		Random random = new Random();

		for (int index = 0; index < count; index++) {
			result.add((char) ((random.nextInt(26) + 65)));
		}

		return result;
	}

	private ArrayList<Character> getLowerCaseChars(int count) {
		ArrayList<Character> result = new ArrayList<Character>();

		Random random = new Random();

		for (int index = 0; index < count; index++) {
			result.add((char) (random.nextInt(26) + 97));
		}

		return result;
	}

	private ArrayList<Character> getNumericChars(int count) {
		ArrayList<Character> result = new ArrayList<Character>();

		Random random = new Random();

		for (int index = 0; index < count; index++) {
			result.add(Character.forDigit(random.nextInt(10), 10));
		}

		return result;
	}

	private ArrayList<Character> getSpecialChars(int count) {
		ArrayList<Character> result = new ArrayList<Character>();

		Random random = new Random();

		for (int index = 0; index < count; index++) {
			result.add((char) (random.nextInt(4) + 35));
		}

		return result;
	}

	private String generateStringFromList(ArrayList<Character> chars) {
		String result = "";

		Random random = new Random();

		while (chars.size() > 0) {
			int randomIndex = random.nextInt(chars.size());
			result += chars.get(randomIndex);
			chars.remove(randomIndex);
		}

		return result;
	}
}

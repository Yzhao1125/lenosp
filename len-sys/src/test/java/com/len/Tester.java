package com.len;

import com.len.core.RanGenerator.CharType;
import com.len.core.RanGenerator.Generator;

public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Generator generator = new Generator();
	//	generator.MinLowerCaseChars = 2;
	//	generator.MinNumericChars = 1;
	//	generator.MinUpperCaseChars = 3;
	//	generator.MinSpecialChars = 1;
	//	generator.FillRest = CharType.NUMERIC;

		System.out.println("Random String: " + generator.generateString());
	}
}

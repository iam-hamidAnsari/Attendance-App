package com.main.AttendanceApp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		char[] arr = { 'a', 'b', 'c' };
		System.out.println(arrayconverter(arr));;

	}

	private static List<Character> arrayconverter(char[] arr) {
		ArrayList<Character> arr1 = new ArrayList<Character>();
		for (int i = 0; i < arr.length; i++) {
			arr1.add(arr[i]);

		}
		return arr1;   

	}

}

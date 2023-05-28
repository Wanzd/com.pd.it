package com.pd.it.base.util.math;

import static com.pd.it.base.util.StaticTool.asList;

import java.util.ArrayList;
import java.util.List;

public class Probability {
	/**
	 * ½×³Ë
	 * 
	 * @param index
	 * @return
	 */
	public static int factorial(int index) {
		int result = 1;
		for (int i = 1; i <= index; i++) {
			result *= i;
		}
		return result;
	}

	public static List<Integer> cSortCnt(int totalCnt, int chooseCnt) {
		List<Integer> preList = new ArrayList<>();
		for (int i = 0; i < totalCnt; i++) {
			preList.add(i);
		}
		List<Integer> rsList = new ArrayList<>();
		for (int i = 0; i < totalCnt; i++) {
			int index = chooseCnt % factorial(totalCnt - i) / factorial(totalCnt - i - 1);
			rsList.add(preList.remove(index));
		}
		return rsList;
	}
}

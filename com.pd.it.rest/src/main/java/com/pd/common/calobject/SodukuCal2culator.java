package com.pd.common.calobject;

import static com.pd.it.common.util.StaticTool.count;
import static com.pd.it.common.util.StaticTool.eq;
import static com.pd.it.common.util.StaticTool.formatStr;
import static com.pd.it.common.util.StaticTool.isEmpty;
import static com.pd.it.common.util.StaticTool.ne;
import static com.pd.it.common.util.StaticTool.row;
import static com.pd.it.common.util.StaticTool.sum;
import static com.pd.it.common.util.StaticTool.toJsonStr;

import java.util.ArrayList;
import java.util.List;

import com.pd.it.common.exception.BusinessException;
import com.pd.it.common.itf.IBuilder;

import lombok.AllArgsConstructor;
import lombok.Data;

public class SodukuCal2culator implements IBuilder<Integer[][][], Integer[][]> {

	private Processor process = new Processor();
	private Calculator cal = new Calculator();
	private int xSize = 0;
	private int ySize = 0;
	private Integer[][][] in;
	private Integer[][] rs;
	private int tmpMove = 0;
	private int move = 0;
	private int turnCount = 0;

	@Override
	public Integer[][] build(Integer[][][] inParam) throws BusinessException {
		in = inParam;
		if (in.length != 2) {
			System.out.println("入参第一维度大小应为2");
			return null;
		}
		xSize = in[0].length;
		ySize = in[1].length;
		rs = new Integer[ySize][xSize];
		process = new Processor();
		do {
			tmpMove = 0;
			turnCount++;
			System.out.println(formatStr("turnCount:%d", turnCount));
			process.process();
		} while (tmpMove > 0);

		System.out.println("no move ,finished");
		return rs;
	}

	@Data
	@AllArgsConstructor
	private class Processor {
		private void process() {
			/* 完全匹配处理 */unique();
			/* 逻辑匹配处理 */logic();
			/* 概率1处理 */rate();
			/* 单连续 */connect();
			/* 范围匹配处理 */range();
			/* 群组匹配处理 */group();
		}

		private void unique() {
			for (int i = 0, total = ySize; i < total; i++) {
				Integer[] arr = in[1][i];
				if (eq(xSize, sum(arr) + arr.length - 1)) {
					Integer[] uniqueRs = cal.calUniqueQueue(arr);
					List<MoveVO> list = new ArrayList<>();
					for (int j = 0, total2 = xSize; j < total2; j++) {
						if (rs[i][j] != null) {
							continue;
						}
						list.add(new MoveVO(i, j, uniqueRs[j]));
					}
					move("uniqueLine:" + i, list);
				}
			}
			for (int i = 0, total = xSize; i < total; i++) {
				Integer[] arr = in[0][i];
				if (eq(ySize, sum(arr) + arr.length - 1)) {
					Integer[] uniqueRs = cal.calUniqueQueue(arr);

					List<MoveVO> list = new ArrayList<>();
					for (int j = 0, total2 = ySize; j < total2; j++) {
						if (rs[j][i] != null) {
							continue;
						}
						list.add(new MoveVO(j, i, uniqueRs[j]));
					}
					move("uniqueRow:" + i, list);
				}
			}
		}

		private void logic() {
			/* 匹配隐性逻辑 */logicNegative();
			/* 匹配显性逻辑 */logicPositive();
		}

		private void move(String scene, List<MoveVO> list) {
			if (isEmpty(list)) {
				return;
			}
			boolean moveFlag = true;
			for (MoveVO eachVO : list) {
				if (rs[eachVO.x][eachVO.y] != null) {
					continue;
				}
				moveFlag = true;
				move++;
				tmpMove++;
				rs[eachVO.x][eachVO.y] = eachVO.value;
			}
			if (moveFlag) {
				String newStr = toJsonStr(rs);
				System.out.println(formatStr("scene:%s\tmove:%d,tmpMove:%d\n\t%s\n", scene, move, tmpMove, newStr));
			}
		}

		private void rate() {
			/* 每行检查逻辑0值 */
			for (int i = 0, total = ySize; i < total; i++) {
				if (in[1][i].length > 1 || in[1][i][0] <= xSize / 2) {
					continue;
				}
				int continueLen = in[1][i][0];
				List<MoveVO> list = new ArrayList<>();
				for (int j = xSize - continueLen, total2 = continueLen; j < total2; j++) {
					if (rs[i][j] != null) {
						continue;
					}
					list.add(new MoveVO(i, j, 1));
				}
				move("rateLine:" + i, list);
			}
			/* 每例检查逻辑0值 */
			for (int i = 0, total = xSize; i < total; i++) {
				if (in[0][i].length > 1 || in[0][i][0] <= ySize / 2) {
					continue;
				}
				int continueLen = in[0][i][0];
				List<MoveVO> list = new ArrayList<>();
				for (int j = ySize - continueLen, total2 = continueLen; j < total2; j++) {
					if (rs[j][i] != null) {
						continue;
					}
					list.add(new MoveVO(j, i, 1));
				}
				move("rateRow:" + i, list);
			}
		}

		private void connect() {
			/* 每行检查边界连续1值 */
			for (int i = 0, total = ySize; i < total; i++) {
				List<MoveVO> list = new ArrayList<>();
				if (eq(1, rs[i][0])) {
					int len = in[1][i][0];
					int start = 0;
					int end = len;
					for (int j = start, total2 = end; j < total2; j++) {
						if (rs[i][j] != null) {
							continue;
						}
						list.add(new MoveVO(i, j, 1));
					}
					if (len < xSize && rs[i][len] == null) {
						list.add(new MoveVO(i, len, 0));
					}
				}
				if (eq(1, rs[i][xSize - 1])) {
					int len = in[1][i][in[1][i].length - 1];
					int start = -1;
					int end = -1;
					start = xSize - len;
					end = xSize;
					for (int j = start, total2 = end; j < total2; j++) {
						if (rs[i][j] != null) {
							continue;
						}
						list.add(new MoveVO(i, j, 1));
					}
					if (start > 0 && rs[i][start - 1] == null) {
						list.add(new MoveVO(i, start - 1, 0));
					}
				}
				move("connectLine:" + i, list);
			}
			/* 每列检查边界连续1值 */
			for (int i = 0, total = xSize; i < total; i++) {
				List<MoveVO> list = new ArrayList<>();
				if (eq(1, rs[0][i])) {
					int len = in[0][i][0];
					int start = 0;
					int end = len;
					for (int j = start, total2 = end; j < total2; j++) {
						if (rs[j][i] != null) {
							continue;
						}
						list.add(new MoveVO(j, i, 1));
					}
					if (len < ySize && rs[len][i] == null) {
						list.add(new MoveVO(len, i, 0));
					}
				}
				if (eq(1, rs[ySize - 1][i])) {
					int len = in[0][i][in[0][i].length - 1];
					int start = -1;
					int end = -1;
					start = ySize - len;
					end = ySize;
					for (int j = start, total2 = end; j < total2; j++) {
						if (rs[j][i] != null) {
							continue;
						}
						list.add(new MoveVO(j, i, 1));
					}
					if (start > 0 && rs[start - 1][i] == null) {
						list.add(new MoveVO(start - 1, i, 0));
					}
				}
				move("connectLine:" + i, list);
			}
		}

		private void range() {
			
		}

		private void group() {
		}

		private void logicNegative() {
			/* 每行检查逻辑0值 */
			for (int i = 0, total = ySize; i < total; i++) {
				if (ne(sum(rs[i]), sum(in[1][i]))) {
					continue;
				}
				List<MoveVO> list = new ArrayList<>();
				for (int j = 0, total2 = xSize; j < total2; j++) {
					if (rs[i][j] != null) {
						continue;
					}
					list.add(new MoveVO(i, j, 0));
				}
				move("logicNegativeLine:" + i, list);
			}
			/* 每例检查逻辑0值 */
			for (int i = 0, total = xSize; i < total; i++) {
				if (ne(sum(row(rs, i)), sum(in[0][i]))) {
					continue;
				}
				List<MoveVO> list = new ArrayList<>();
				for (int j = 0, total2 = ySize; j < total2; j++) {
					if (rs[j][i] != null) {
						continue;
					}
					list.add(new MoveVO(j, i, 0));
				}
				move("logicNegativeRow:" + i, list);
			}
		}

		private void logicPositive() {
			/* 每行检查逻辑1值 */
			for (int i = 0, total = ySize; i < total; i++) {
				if (ne(count(0, rs[i]) + sum(in[1][i]), xSize)) {
					continue;
				}
				List<MoveVO> list = new ArrayList<>();
				for (int j = 0, total2 = xSize; j < total2; j++) {
					if (rs[i][j] != null) {
						continue;
					}
					list.add(new MoveVO(i, j, 1));
				}
				move("logicPositiveLine:" + i, list);
			}
			/* 每例检查逻辑1值 */
			for (int i = 0, total = xSize; i < total; i++) {
				if (ne(count(0, row(rs, i)) + sum(in[0][i]), ySize)) {
					continue;
				}
				List<MoveVO> list = new ArrayList<>();
				for (int j = 0, total2 = ySize; j < total2; j++) {
					if (rs[j][i] != null) {
						continue;
					}
					list.add(new MoveVO(j, i, 1));
				}
				move("logicPositiveRow:" + i, list);
			}
		}

	}

	private class Calculator {
		private Integer[] calUniqueQueue(Integer[] in) {
			List<Integer> list = new ArrayList<>();
			for (Integer eachValue : in) {
				for (int i = 0, total = eachValue.intValue(); i < total; i++) {
					list.add(1);
				}
				list.add(0);
			}
			list.remove(list.size() - 1);
			return list.toArray(new Integer[list.size()]);
		}
	}

	@Data
	@AllArgsConstructor
	private class MoveVO {
		private int x;
		private int y;
		private int value;
	}
}

package com.pd.it.game;

import static com.pd.it.base.util.StaticTool.asList;
import static com.pd.it.base.util.StaticTool.formatStr;
import static com.pd.it.base.util.StaticTool.valid;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import com.pd.it.base.itf.ICrack;
import com.pd.it.base.util.LenEqValid;
import com.pd.it.base.util.math.Probability;
import com.pd.it.game.vo.Point24VO;

@Named
public class Point24 implements ICrack<String, List<String>> {
	@Override
	public List<String> crack(String in) {
		List<String> rsList = new ArrayList<>();
		String[] numbers = in.split(",");
		valid(new LenEqValid(numbers, 4));
		List<Double> numberList = asList(numbers).stream().sorted().map(Double::valueOf).collect(toList());
		int total = Probability.factorial(4);
		for (int i = 0; i < total; i++) {
			List<Integer> cSortCnt = Probability.cSortCnt(4, i);
			// System.out.println(formatStr("index:%s", asStr(cSortCnt)));
			rsList.addAll(cal(numberList, cSortCnt, 24));
		}
		// System.out.println(formatStr("result:%s", asStr(rsList)));
		return rsList;
	}

	private List<String> cal(List<Double> numberList, List<Integer> cSortCnt, int target) {
		List<Point24VO> tmpList = new ArrayList<>();

		List<Double> opList = new ArrayList<>();
		for (Integer eachIndex : cSortCnt) {
			opList.add(numberList.get(eachIndex));
		}

		for (int i = 0; i < opList.size(); i++) {
			if (i == 0) {
				Point24VO vo = new Point24VO();
				vo.setValue(opList.get(i));
				vo.setDetail(opList.get(i).intValue() + "");
				tmpList.add(vo);
			} else {
				tmpList = cal(tmpList, opList.get(i));
			}
		}
		List<String> rsList = tmpList.stream().filter(vo -> vo.getValue() > 23.999999d && vo.getValue() < 24.000001d)
				.map(Point24VO::getDetail).collect(toList());

		return rsList;
	}

	private static List<Point24VO> cal(List<Point24VO> list, Double in) {
		List<Point24VO> rsList = new ArrayList<>();
		for (Point24VO eachVO : list) {
			Point24VO addVO = new Point24VO();
			addVO.setValue(eachVO.getValue() + in);
			addVO.setDetail(formatStr("%s+%s", eachVO.getDetail(), in.intValue() + ""));
			addVO.setLastOp("+");
			rsList.add(addVO);

			Point24VO delVO = new Point24VO();
			delVO.setValue(eachVO.getValue() - in);
			delVO.setDetail(formatStr("%s-%s", eachVO.getDetail(), in.intValue() + ""));
			delVO.setLastOp("-");
			rsList.add(delVO);

			Point24VO mulVO = new Point24VO();
			mulVO.setValue(eachVO.getValue() * in);
			if (eachVO.getLastOp().matches("[+|\\-]")) {
				mulVO.setDetail(formatStr("(%s)*%s", eachVO.getDetail(), in.intValue() + ""));
			} else {
				mulVO.setDetail(formatStr("%s*%s", eachVO.getDetail(), in.intValue() + ""));
			}
			mulVO.setLastOp("*");
			rsList.add(mulVO);

			Point24VO divVO = new Point24VO();
			divVO.setValue(eachVO.getValue() / in);
			if (eachVO.getLastOp().matches("[+|\\-]")) {
				divVO.setDetail(formatStr("(%s)/%s", eachVO.getDetail(), in.intValue() + ""));
			} else {
				divVO.setDetail(formatStr("%s/%s", eachVO.getDetail(), in.intValue() + ""));
			}
			divVO.setLastOp("/");
			rsList.add(divVO);
		}
		return rsList;
	}

}

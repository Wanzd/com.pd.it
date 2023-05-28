package com.pd.it.base.util.math;

import static com.pd.it.base.util.StaticTool.asStr;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ProbabilityTest {
	@Test
	public void testCSortCnt() {
		assertEquals("[0,1,2,3]", asStr(Probability.cSortCnt(4, 0)));
		assertEquals("[1,0,2,3]", asStr(Probability.cSortCnt(4, 6)));
	}
}

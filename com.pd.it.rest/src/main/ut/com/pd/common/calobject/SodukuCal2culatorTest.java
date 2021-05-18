package com.pd.common.calobject;

import static com.pd.it.common.util.StaticTool.toIntegerArray2;
import static com.pd.it.common.util.StaticTool.toIntegerArray3;
import static com.pd.it.common.util.StaticTool.toJsonStr;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.pd.it.common.exception.BusinessException;

public class SodukuCal2culatorTest {

	@Test
	public void testBuild() {
		Integer[][][] in = toIntegerArray3("2,1", "2,1");
		Integer[][] rs = toIntegerArray2("11,10");
		SodukuCal2culator cal = new SodukuCal2culator();
		try {
			assertEquals(toJsonStr(rs), toJsonStr(cal.build(in)));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testBuild3() {
		Integer[][][] in = toIntegerArray3("4,3,2,1", "4,3,2,1");
		Integer[][] rs = toIntegerArray2("1111,1110,1100,1000");
		SodukuCal2culator cal = new SodukuCal2culator();
		try {
			assertEquals(toJsonStr(rs), toJsonStr(cal.build(in)));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testBuild18() {
		Integer[][][] in = toIntegerArray3("21,11,14,14,11,21", "2,11,121,121,4,121");
		Integer[][] rs = toIntegerArray2("001100,010010,101101,101101,011110,101101");
		SodukuCal2culator cal = new SodukuCal2culator();
		try {
			assertEquals(toJsonStr(rs), toJsonStr(cal.build(in)));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testBuild32() {
		Integer[][][] in = toIntegerArray3("1,11,13,3,111,2", "2,111,11,2,2,4");
		Integer[][] rs = toIntegerArray2("111111,111111,111111,111111,111111,111111");
		SodukuCal2culator cal = new SodukuCal2culator();
		try {
			assertEquals(toJsonStr(rs), toJsonStr(cal.build(in)));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testBuild35() {
		Integer[][][] in = toIntegerArray3("2,12,22,111,11,2", "12,1,1,211,31,3");
		Integer[][] rs = toIntegerArray2("111111,111111,111111,111111,111111,111111");
		SodukuCal2culator cal = new SodukuCal2culator();
		try {
			assertEquals(toJsonStr(rs), toJsonStr(cal.build(in)));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
}

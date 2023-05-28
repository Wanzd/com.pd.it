package com.pd.it.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class Point24Test {

	@Test
	public void testCrack() {
		assertNotNull(Point24.crack("1,2,3,4"));
	}

}

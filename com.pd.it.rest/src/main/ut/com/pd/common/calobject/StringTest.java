package com.pd.common.calobject;

import org.junit.Test;

public class StringTest {

    @Test
    public void testReplaceAll() {
        String in = "CREATE TABLE `test_t` (\r\n" + "  `a` int DEFAULT NULL,\r\n" + "  `b` int DEFAULT NULL\r\n"
                + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci";
        String rs = in.replaceAll("COLLATE=utf8mb4_0900_ai_ci", "");
        System.out.println(rs);
    }
}

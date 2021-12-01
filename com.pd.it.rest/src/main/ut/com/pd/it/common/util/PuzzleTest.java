package com.pd.it.common.util;

import java.util.Scanner;

class PuzzleTest {

    private static final int WALL = -1;
    private static final int INIT = 0;
    private static final int ROAD = 1;

    private static int x = 0;
    private static int y = 0;

    // 统计AB个数
    private static void cal(int[][] puzzleIn, int[][] puzzleOut) {
        int countA = 0;
        int countB = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (puzzleIn[j][i] == INIT && puzzleOut[j][i] == ROAD) {
                    countA++;
                }
                if (puzzleIn[j][i] == ROAD && puzzleOut[j][i] == INIT) {
                    countB++;
                }
            }
        }
        System.out.println(countA + " " + countB);
    }

    // 正向处理
    private static int[][] calIn(int[][] puzzle) {
        int[][] result = new int[y][x];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                result[j][i] = puzzle[j][i];
            }
        }
        cal(result, 0, y - 1, "EN");
        return result;
    }

    // 反向处理
    private static int[][] calOut(int[][] puzzle) {
        int[][] result = new int[y][x];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                result[j][i] = puzzle[j][i];
            }
        }
        cal(result, x - 1, 0, "WS");
        return result;
    }

    // 计算单个格子
    private static void cal(int[][] puzzle, int tmpX, int tmpY, String direct) {
        if (tmpX < 0 || tmpX >= x || tmpY < 0 || tmpY >= y) {
            return;
        }
        if (puzzle[tmpY][tmpX] != INIT) {
            return;
        }
        puzzle[tmpY][tmpX] = ROAD;
        int nextX = -1;
        int nextY = -1;
        if (direct.contains("E")) {
            cal(puzzle, x + 1, y, direct);
        }
        if (direct.contains("W")) {
            cal(puzzle, x - 1, y, direct);
        }
        if (direct.contains("N")) {
            cal(puzzle, x, y - 1, direct);
        }
        if (direct.contains("E")) {
            cal(puzzle, x, y + 1, direct);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        x = sc.nextInt();
        y = sc.nextInt();
        int[][] puzzle = new int[y][x];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                puzzle[j][i] = INIT;
            }
        }
        int wallCount = sc.nextInt();
        for (int i = 0; i < wallCount; i++) {
            int tmpX = sc.nextInt();
            int tmpY = sc.nextInt();
            if (tmpX < 0 || tmpX >= x || tmpY < 0 || tmpY >= y) {
                continue;
            }
            puzzle[tmpY][tmpX] = WALL;
        }

        int[][] puzzleIn = calIn(puzzle);
        int[][] puzzleOut = calOut(puzzle);
        cal(puzzleIn, puzzleOut);
    }

}

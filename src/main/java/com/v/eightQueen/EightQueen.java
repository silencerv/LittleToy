package com.v.eightQueen;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by v on 2016/10/3.
 */
public class EightQueen {

    private int success = 0;

    private int cols = 8;

    private int rows = 8;

    private int queenCount = 8;

    private boolean[][] chessBoard = new boolean[rows][cols];

    /*
    检查行是否安全
    Return
    is safe return true
    is not sage return false
     */
    public boolean isSafeRow(int row) {
        for (int i = 0; i < cols; i++) {
            if (chessBoard[row][i])
                return false;
        }
        return true;
    }

    /*
    检查列是否安全
     */
    public boolean isSafeCol(int col) {
        for (int i = 0; i < rows; i++) {
            if (chessBoard[i][col])
                return false;
        }
        return true;
    }

    /**
     * 检查对角线是否安全
     *
     * @param row 当前行坐标
     * @param col 当前列坐标
     * @return is safe return true  else return false
     */
    public boolean isSafeDiagonal(int row, int col) {
        //左上
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (chessBoard[i][j])
                return false;
        }
        //右下
        for (int i = row, j = col; i < rows && j < cols ; i++, j++) {
            if (chessBoard[i][j])
                return false;
        }
        return true;
    }

    /**
     * 计算反对角线
     *
     * @param row
     * @param col
     * @return
     */
    public boolean isSafeReverseDiagonal(int row, int col) {
        //左下
        for (int i = row, j = col; i < rows && j >= 0; i++, j--) {
            if (chessBoard[i][j])
                return false;
        }
        //右上
        for (int i = row, j = col; i >=0  && j < cols ; i--, j++) {
            if (chessBoard[i][j])
                return false;
        }
        return true;
    }

    @Test
    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf("%3s", chessBoard[i][j] ? "X" : "O");
            }
            System.out.println();
        }
        System.out.println("-------------------------------------");
    }

    public void clearRow(int row) {
        for (int i = 0; i < cols; i++) {
            chessBoard[row][i] = false;
        }
    }

    @Test
    public void testReverseDiagonal() {
        int row = 0;
        int col = 0;
        System.out.printf("row:%2d  col:%2d\n", row, col);
        for (int i = 1; i < rows; i++) {
            System.out.printf("row:%2d  col:%2d\n", (i + row) % 8, (8 + col - i) % 8);
        }
    }


    public void calculateEightQueen(int queen) {

        //皇后已经放置完成，结束递归
        if (queen == 0) {
            //打印棋盘
            print();
//            System.exit(0);
            System.out.printf("成功数量%2d\n",++success);
            return;
        }

        int row = queenCount - queen;//当前要放置的行数
        for (int i = 0; i < cols; i++) {
            clearRow(row);
            //循环列
            if (isSafeCol(i)) {//列安全
                if (isSafeDiagonal(row, i)) {//对角线安全
                    if (isSafeReverseDiagonal(row, i)) {//反对角线安全
                        //放置皇后
                        chessBoard[row][i] = true;
                        calculateEightQueen(queen - 1);
                    }
                }
            }
        }
        clearRow(row);
    }

    @Test
    public void testEightQueen() {
        calculateEightQueen(8);
    }
}

/*
	HW4:         Huffman Coding
	Author:      Weston Smith
	Class:       CS 316
	Date:        12/4/2020
 */

import java.util.Scanner;

public class LCS {
    // Declarations
    enum arrow {NORTH, WEST, NORTHWEST};

    /**
     * This class is for storing information related to one cell in an LCS table
     */
    private class Cell {
        arrow direction;
        int num;

        /**
         * No-Arg Constructor
         */
        public Cell() {
            direction = arrow.NORTH;
            num = 0;
        }

        /**
         * Constructor
         * @param direction
         * @param num
         */
        public Cell(arrow direction, int num) {
            this.direction = direction;
            this.num = num;
        }
    }

    /**
     * No-Arg Constructors
     */
    public LCS () {
        // Input
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the first word");
        String word1 = scan.next().toLowerCase();

        System.out.println("Please enter the second word");
        String word2 = scan.next().toLowerCase();


        // Create table
        int numRows = word1.length()+1;
        int numColumns  = word2.length()+1;

        Cell[][] array = new Cell[numRows][numColumns];

        // First row and first column set to 0
        for (int j = 0; j < numRows; j++) {
            array[j][0] = new Cell();
        }
        for (int k = 0; k < numColumns; k++) {
            array[0][k] = new Cell();
        }

        // Fill in the rest of the table
        for (int j = 1; j < numRows; j++) {
            for (int k = 1; k < numColumns; k++) {
                if (word1.charAt(j-1) == word2.charAt(k-1)){
                    array[j][k] = new Cell(arrow.NORTHWEST, array[j-1][k-1].num+1);
                } else if (array[j][k-1].num > array[j-1][k].num) {
                    array[j][k] = new Cell(arrow.WEST, array[j][k-1].num);
                } else {
                    array[j][k] = new Cell(arrow.NORTH, array[j-1][k].num);
                }
            }
        }

        // Find LCS
        StringBuilder lcs = new StringBuilder();
        int j = numRows-1;
        int k = numColumns-1;
        while (j > 0 && k > 0) {
            switch (array[j][k].direction){
                case NORTHWEST:
                    lcs.append(word1.charAt(j-1));
                    j--;
                    k--;
                    break;
                case NORTH:
                    j--;
                    break;
                case WEST:
                    k--;
                    break;
            }
        }

        // Output
        System.out.println("\nLCS: "+lcs.reverse().toString());
    }

    public static void main(String[] args) {
        LCS a = new LCS();
    }
}

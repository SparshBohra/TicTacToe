/*
 *  Sparsh Bohra
 *  16/11/2019
 *  TicTacToe
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    static ArrayList<Integer> plaPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String[] args) {

        //game board
        char[][] playBoard = {  {' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '}   };

        //prints game board
        printBoard(playBoard);

        while(true) {
            Scanner input = new Scanner (System.in);
            System.out.print("Enter position (1-9): ");

            //plaPos for player and cpuPos for cpu
            int plaPos = input.nextInt();
            while(plaPositions.contains(plaPos) || cpuPositions.contains(plaPositions)) {
                System.out.println("Position taken. Choose another one.");
                plaPos = input.nextInt();
            }

            //method to place piece
            placeItem(playBoard, plaPos, "player");

            //check winner
            String result = findWinner();
            if(result.length() > 0) {
                System.out.println(result);
                break;
            }

            //cpu plays are Random
            Random rd = new Random();
            int cpuPos = rd.nextInt(9) + 1;
            while(plaPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
                cpuPos = rd.nextInt(9) + 1;
            }
            placeItem(playBoard, cpuPos, "cpu");

            //print final game board
            printBoard(playBoard);

            //check winner
            result = findWinner();
            if(result.length() > 0) {
                System.out.println(result);
                break;
            }
        }
    }

    //method to print game board
    public static void printBoard(char[][] x) {
        for (char[] row: x){
            for (char col: row) {
                System.out.print(col);
            }
            System.out.println();
        }
    }

    //method to place piece
    public static void placeItem(char[][] playBoard, int pos, String user) {
        //default case
        char piece = ' ';

        //two players
        if (user.equals("player")) {
            piece = 'X';
            plaPositions.add(pos);
        }
        else if (user.equals("cpu")) {
            piece = 'O';
            cpuPositions.add(pos);
        }

        switch(pos){
            case 1:
                playBoard[0][0] = piece;
                break;
            case 2:
                playBoard[0][2] = piece;
                break;
            case 3:
                playBoard[0][4] = piece;
                break;
            case 4:
                playBoard[2][0] = piece;
                break;
            case 5:
                playBoard[2][2] = piece;
                break;
            case 6:
                playBoard[2][4] = piece;
                break;
            case 7:
                playBoard[4][0] = piece;
                break;
            case 8:
                playBoard[4][2] = piece;
                break;
            case 9:
                playBoard[4][4] = piece;
                break;
            default:
                break;
        }
    }

    //method to check winner
    public static String findWinner() {
        //rows
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);

        //columns
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);

        //diagonals
        List diag1 = Arrays.asList(1, 5, 9);
        List diag2 = Arrays.asList(7, 5, 3);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(diag1);
        winning.add(diag2);

        for (List l : winning) {
            if (plaPositions.containsAll(l)) {
                return "Congrats! you won :)";
            }
            else if (cpuPositions.containsAll(l)) {
                return "CPU wins! try again :(";
            }
            else if (plaPositions.size() + cpuPositions.size() == 9) {
                return "That's a tie lmao :D";
            }
        }

        return "";
    }
}

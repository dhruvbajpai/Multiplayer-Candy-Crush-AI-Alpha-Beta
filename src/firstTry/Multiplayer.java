package firstTry;

import java.io.*;
import java.util.ArrayList;

import static firstTry.GamePlay.showBoard;

public class Multiplayer {

    public static String INPUT_FILE_NAME = "C:\\Users\\Dhruv Bajpai\\Dropbox\\USC Data\\CSCI 561 - Foundations of Artificial Intelligence\\Homework\\HomeWork 2 - CandyCrush Type\\src\\firstTry\\input.txt";
    public static int boardSize = 0;
    public static int numberOfFruits = 0;
    public static double timeLeft = 0;
    public static char[][] board;
    public static char[][] copyboard;
    public final static int plyValue = 2;
    public static int xScore = 0;
    public static int yScore = 0;
    public static boolean isXscore;
    public static void main(String[] args) throws IOException {
        // GAME PLAYING MANUAL TESTING
        FileReader fileReader = new FileReader(INPUT_FILE_NAME);
        BufferedReader br = new BufferedReader(fileReader);
        boardSize = Integer.parseInt(br.readLine());
        numberOfFruits = Integer.parseInt(br.readLine());
        timeLeft = Double.parseDouble(br.readLine());
        board = new char[boardSize][boardSize];
        copyboard = new char[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            String[] line = br.readLine().split("");
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = line[j].charAt(0);
                copyboard[i][j] = line[j].charAt(0);
            }
        }
        System.out.println("Showing Board");
        showBoard(board);

        System.out.println("Enter y if you play first, n if computer plays first");
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String ans = consoleReader.readLine();
        if(ans.equals("y")||ans.equals("Y"))
        {
            System.out.println("Enter the location of the fruit you wanna pop");
            String loc = consoleReader.readLine();
            int col  =  (int)loc.charAt(0)-65;
            int row = Character.getNumericValue(loc.charAt(1)-1);
            System.out.println("Row: "+ row + " Col: "+ col);



        }
        else if (ans.equals("n")||ans.equals("N"))
        {

        }

//        isXscore=true;
//        GamePlay gamePlay = new GamePlay(boardSize, numberOfFruits, timeLeft, board);
//        char[][] board = gamePlay.getSolutionForBoard(isXscore);
//        System.out.println("The solution board is: ");
//        showBoard(board);
//        System.out.println();


    }
    public static void showBoard(char board[][]) {

        System.out.print("  ");
        for(int k=0;k<boardSize;k++)
            System.out.print(((char)(65+k)));
        System.out.println();
        for (int i = 0; i < boardSize; i++) {
            System.out.print(""+(i+1)+" ");
            for (int j = 0; j < boardSize; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
    public void recursiveProp(ArrayList<GamePlay.Point> arr, char[][] board, int x, int y, char ele) {

        //mark this place as visited on the board
        board[x][y] = '*';

        int newX;
        int newY;
        // GOING UP IN THE SAME COLUMN
        newX = x - 1;
        newY = y;
        if (isWithinBoardLimits(newX, newY))// not to be considered if x,y out of the board
        {
            if (board[newX][newY] == ele) {
                //add these new coordinates to the arraylist
                arr.add(new GamePlay.Point(newX, newY));
                recursiveProp(arr, board, newX, newY, ele);
            }
        }

        // GOING DOWN IN THE SAME COLUMN
        newX = x + 1;
        newY = y;
        if (isWithinBoardLimits(newX, newY))// not to be considered if x,y out of the board
        {
            if (board[newX][newY] == ele) {
                //add these new coordinates to the arraylist
                arr.add(new GamePlay.Point(newX, newY));
                recursiveProp(arr, board, newX, newY, ele);
            }
        }

        // GOING LEFT IN THE SAME ROW
        newX = x;
        newY = y - 1;
        if (isWithinBoardLimits(newX, newY))// not to be considered if x,y out of the board
        {
            if (board[newX][newY] == ele) {
                //add these new coordinates to the arraylist
                arr.add(new GamePlay.Point(newX, newY));
                recursiveProp(arr, board, newX, newY, ele);
            }
        }

        // GOING RIGHT IN THE SAME ROW
        newX = x;
        newY = y + 1;
        if (isWithinBoardLimits(newX, newY))// not to be considered if x,y out of the board
        {
            if (board[newX][newY] == ele) {
                //add these new coordinates to the arraylist
                arr.add(new GamePlay.Point(newX, newY));
                recursiveProp(arr, board, newX, newY, ele);
            }
        }


    }
    public boolean isWithinBoardLimits(int x, int y) {
        if (x < boardSize && x >= 0 && y < boardSize && y >= 0)
            return true;
        else
            return false;
    }
}

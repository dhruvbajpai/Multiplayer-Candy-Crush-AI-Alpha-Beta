package firstTry;

import com.sun.org.apache.bcel.internal.generic.RET;

import java.io.*;

import static firstTry.GamePlay.*;

public class PlayingScript {


    public static int scoreA = 0;
    public static int scoreB = 0;
    public static int timeA = 300000;
    public static int timeB = 300000;
    public static String INPUT_FILE_NAME = "C:\\Users\\Dhruv Bajpai\\Dropbox\\USC Data\\CSCI 561 - Foundations of Artificial Intelligence\\Homework\\HomeWork 2 - CandyCrush Type\\src\\firstTry\\input.txt";
    public static String OUTPUT_FILE_NAME = "C:\\Users\\Dhruv Bajpai\\Dropbox\\USC Data\\CSCI 561 - Foundations of Artificial Intelligence\\Homework\\HomeWork 2 - CandyCrush Type\\src\\firstTry\\ouput.txt";
    public static int boardSize = 0;
    public static int lastScoreAdded = 1;
    public static int Acounter = 0;
    public static int Bcounter = 0;
    public static boolean isAturn = false;
    public static int Aply = 3;
    public static int Bply = 3;
    public static boolean wasAtrue;
    public static char[][] board;
    public static char[][] copyboard;
    public static int numberOfFruits =0;
    public static double timeLeft=0;
    public static int gameTimeCounter =0;
    public static int numberOfGames =4 ;
    public static void main(String[] args) throws IOException {
//        for(int q=0;q<numberOfGames;q++) {
//            numberOfGames++;
            {
                //to read boardSize
                FileReader fileReader = new FileReader(INPUT_FILE_NAME);
                BufferedReader br = new BufferedReader(fileReader);
                boardSize = Integer.parseInt(br.readLine());
                numberOfFruits = Integer.parseInt(br.readLine());
                timeLeft = Double.parseDouble(br.readLine());
                copyboard = new char[boardSize][boardSize];
                for (int i = 0; i < boardSize; i++) {
                    String[] line = br.readLine().split("");
                    for (int j = 0; j < boardSize; j++) {
//                    board[i][j] = line[j].charAt(0);
                        copyboard[i][j] = line[j].charAt(0);
                    }
                }
                board = new char[boardSize][boardSize];
                br.close();
                wasAtrue = isAturn;// to note who started the game
            }
            while (timeA > 0 && timeB > 0 && !isBoardEmpty()) {
                gameTimeCounter++;
                if (gameTimeCounter % 2 == 0) {
                    System.out.println("A time left: " + timeA);
                    System.out.println("B time left: " + timeB);
                }
                if (isAturn) {
                    System.out.println("\n-----------------------------------");
                    System.out.println("A played move number: " + ++Acounter);
                    System.out.println("-----------------------------------");
                    long beforeTimeA = System.currentTimeMillis();
//                GamePlay.plyValue=Aply;
//                GamePlay.main(new String[]{"a"});
                    submission.homework.plyValue = Aply;
                    submission.homework.main(new String[]{"a"});
                    long afterTimeA = System.currentTimeMillis();
                    //decrerment A time
                    timeA -= (afterTimeA - beforeTimeA);
                    //read output of A from output and prepare input for B.
                    FileReader fileReader = new FileReader(OUTPUT_FILE_NAME);
                    BufferedReader reader = new BufferedReader(fileReader);
                    String useless = reader.readLine();//move played
                    int scoreToAdd = Integer.parseInt(reader.readLine()); // A's score int this move
                    if (scoreToAdd == 0)
                        lastScoreAdded = 0;
                    scoreA += scoreToAdd;
                    //Read matrix to be dumped in INPUT.txt
//                char[][] board = new char[boardSize][boardSize];
                    for (int i = 0; i < boardSize; i++) {
                        String[] line = reader.readLine().split("");
                        for (int j = 0; j < boardSize; j++) {
                            board[i][j] = line[j].charAt(0);
                        }
                    }
                    isAturn = !isAturn;// toggle boolean for other player
                    //write to Input for B to play


                    FileWriter fileWriter = new FileWriter(INPUT_FILE_NAME);
                    BufferedWriter br = new BufferedWriter(fileWriter);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(boardSize);
                    stringBuilder.append("\n");
                    stringBuilder.append(3); //random fruit value
                    stringBuilder.append("\n");
                    stringBuilder.append(String.valueOf((double) (timeB / 1000))); // B's time left
                    stringBuilder.append("\n");
                    for (int i = 0; i < boardSize; i++) {
                        for (int j = 0; j < boardSize; j++) {
                            stringBuilder.append(board[i][j]);
                        }
                        stringBuilder.append("\n");
                    }
                    br.write(stringBuilder.toString());

                    br.close();
                    System.out.println("-----------------------------------\n");

                } else {
                    System.out.println("\n-----------------------------------");
                    System.out.println("B played move number: " + ++Bcounter);
                    System.out.println("-----------------------------------");
                    // B's program Run
                    long beforeTimeB = System.currentTimeMillis();
                GamePlay.plyValue = Bply;
                GamePlay.main(new String[]{"a"}); // agent to play
//                homework.MAX_DEPTH = Bply;
//                homework.main(new String[]{"a"});
//                    submission.homework.plyValue = Bply;
//                    submission.homework.main(new String[]{"a"});
                    long afterTimeB = System.currentTimeMillis();
                    //decrerment A time
                    timeB -= (afterTimeB - beforeTimeB);
                    //read output of B from output and prepare input for A.
                    FileReader fileReader = new FileReader(OUTPUT_FILE_NAME);
                    BufferedReader reader = new BufferedReader(fileReader);
                    String useless = reader.readLine();//move played
                    int scoreToAdd = Integer.parseInt(reader.readLine()); // B's score in this move
                    if (scoreToAdd == 0)
                        lastScoreAdded = 0;
                    scoreB += scoreToAdd;
                    //Read matrix to be dumped in Input.txt
//                char[][] board = new char[boardSize][boardSize];
                    for (int i = 0; i < boardSize; i++) {
                        String[] line = reader.readLine().split("");
                        for (int j = 0; j < boardSize; j++) {
                            board[i][j] = line[j].charAt(0);
                        }
                    }
                    isAturn = !isAturn;// toggle boolean for other player
                    //write to Input for A to play


                    FileWriter fileWriter = new FileWriter(INPUT_FILE_NAME);
                    BufferedWriter br = new BufferedWriter(fileWriter);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(boardSize);
                    stringBuilder.append("\n");
                    stringBuilder.append(3); //random fruit value
                    stringBuilder.append("\n");
                    stringBuilder.append(String.valueOf((double) (timeA / 1000))); // B's time left
                    stringBuilder.append("\n");
                    for (int i = 0; i < boardSize; i++) {
                        for (int j = 0; j < boardSize; j++) {
                            stringBuilder.append(board[i][j]);
                        }
                        stringBuilder.append("\n");
                    }
                    br.write(stringBuilder.toString());
//                System.out.println("B played move counter: "+ ++Bcounter);
                    br.close();
                    System.out.println("-----------------------------------\n");
                }
            }

            System.out.println("A final scorer = " + scoreA);
            System.out.println("B final scorer = " + scoreB);
            System.out.println((wasAtrue ? "A" : "B") + " PLAYED FIRST");
            System.out.println("THE WINNER IS ============>>> " + (whoWon()));
            System.out.println("A moves: " + Acounter);
            System.out.println("B moves: " + Bcounter);
            System.out.println("A time left :" + timeA + " ===>> " + ((double) timeA / 1000) + " seconds");
            System.out.println("B time left :" + timeB + " ===>> " + ((double) timeB / 1000) + " seconds");
            System.out.println("TOTAL TIME TAKEN: "+ ((300-((double) timeA / 1000))+(300-((double) timeB / 1000))));
            System.out.println("A ply depth was: " + Aply);
            System.out.println("B ply depth was: " + Bply);
            System.out.println("Is Board EMPTY: " + isBoardEmpty());
            writeOriginalBoardToFile();
        }
//    }
    public static int writeOutput() throws IOException {
        FileWriter fw = new FileWriter("outputs.txt",true);
        BufferedWriter bw = new BufferedWriter(fw);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Game Number : "+ numberOfGames);
        stringBuilder.append("\n");
        stringBuilder.append((wasAtrue ? "A" : "B") + " PLAYED FIRST");
        stringBuilder.append("\n");

        return 1;

    }
    public static void writeOriginalBoardToFile() throws IOException {
        FileWriter fw = new FileWriter(INPUT_FILE_NAME);
        BufferedWriter bufferedWriter = new BufferedWriter(fw);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(boardSize);
        stringBuilder.append("\n");
        stringBuilder.append(numberOfFruits);
        stringBuilder.append("\n");
        stringBuilder.append(timeLeft);
        stringBuilder.append("\n");
        for(int i=0;i<boardSize;i++)
        {
            for(int j=0;j<boardSize;j++)
            {
                stringBuilder.append(copyboard[i][j]);
            }
            stringBuilder.append("\n");
        }
        bufferedWriter.write(stringBuilder.toString());
        bufferedWriter.close();
    }
    public static boolean isBoardEmpty()
    {
        for(int i=0;i<boardSize;i++)
        {
            for(int j=0;j<boardSize;j++)
            {
                if(board[i][j]!='*')
                    return false;
            }
        }
        return true;
    }

    public static String whoWon()
    {
        if(isBoardEmpty())// BOARD OVER == POINTS DECIDE WINNER
        {
            return (scoreA>=scoreB)?"A":"B";
        }
        // SOMEONE LOST BECAUSE OF TIME 0
        return ((timeA>timeB)?"A":"B");
    }

}

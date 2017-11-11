package firstTry;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class RandomMatrixGenerator {

    public static String INPUT_FILE_NAME = "C:\\Users\\Dhruv Bajpai\\Dropbox\\USC Data\\CSCI 561 - Foundations of Artificial Intelligence\\Homework\\HomeWork 2 - CandyCrush Type\\src\\firstTry\\input.txt";
//    public static String OUTPUT_FILE_NAME = "C:\\Users\\Dhruv Bajpai\\Dropbox\\USC Data\\CSCI 561 - Foundations of Artificial Intelligence\\Homework\\HomeWork 2 - CandyCrush Type\\src\\firstTry\\ouput.txt";
    public static int matrixSize = 15;
    public static int fruitRange=9;
    public static void main(String[] args) throws IOException {

        /*long be = System.currentTimeMillis();
        for(int k=0;k<20000000;k++) {
            char[][] arr = new char[26][26];
        }
        long af = System.currentTimeMillis();*/
//        System.out.println((af-be)/1000+" seconds");
        FileWriter fw = new FileWriter(INPUT_FILE_NAME);

        BufferedWriter br = new BufferedWriter(fw);
        Random r= new Random();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(matrixSize);
        stringBuilder.append("\n");
        stringBuilder.append(fruitRange);
        stringBuilder.append("\n");
        stringBuilder.append("2");//random time
        stringBuilder.append("\n");
        for(int i=0;i<matrixSize;i++)
        {
            for(int j=0;j<matrixSize;j++)
            {
                stringBuilder.append(r.nextInt(fruitRange));
            }
            stringBuilder.append("\n");
        }
        br.write(stringBuilder.toString());
        br.close();

    }
}

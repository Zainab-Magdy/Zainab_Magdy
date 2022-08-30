/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package Projects.gui.yellow_team.activities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**

 @author EMAM
 */
class savedData {

          public static final String PREV_GAME_FILE = "src\\Projects\\gui\\yellow_team\\documents\\SavedGame.txt";
          public static final String HELP = "src\\Projects\\gui\\yellow_team\\documents\\help.txt";
          public static final String SETTING = "src\\Projects\\gui\\yellow_team\\documents\\setting.txt";
          public static final String INFO = "src\\Projects\\gui\\yellow_team\\documents\\members.txt";


          public static int[][] readPrevGame() throws FileNotFoundException , IOException {
                    /** **************************************
                     ///////////////////////////////////////////
                     TODO: Read The data from the file
                     with the status for each number
                     Is the number was written by user or by the game
                     ///////////////////////////////////////////
                     *************************************** */
                    BufferedReader br = new BufferedReader(new FileReader(new File(PREV_GAME_FILE)));
                    String line = br.readLine().replace(" " , "");
                    if ( line.isEmpty() ) {
                              return null;
                    }
                    String[] nums = line.split(",");
                    int[][] numbers = new int[9][9];
                    for ( int i = 0 ; i < nums.length ; i++ ) {
                              numbers[i / 9][i % 9] = Integer.parseInt(nums[i]);
                    }
                    return numbers;
          }


          static void readHelp() throws FileNotFoundException , IOException {
                    BufferedReader br = new BufferedReader(new FileReader(new File(HELP)));
                    String line;
                    String lines = "";
                    while ( (line = br.readLine()) != null ) {
                              lines += line + "\n";
                    }
                    JOptionPane.showMessageDialog(null , lines);
          }


          static void readInfo() throws FileNotFoundException , IOException {
                    BufferedReader br = new BufferedReader(new FileReader(new File(INFO)));
                    String line;
                    String lines = "";
                    while ( (line = br.readLine()) != null ) {
                              lines += line + "\n";
                    }
                    JOptionPane.showMessageDialog(null , lines);
          }


          static void readSetting() throws FileNotFoundException , IOException {
                    BufferedReader br = new BufferedReader(new FileReader(new File(SETTING)));
                    String line;
                    String lines = "";
                    while ( (line = br.readLine()) != null ) {
                              lines += line + "\n";
                    }
                    JOptionPane.showMessageDialog(null , lines);
          }


          static void writePrevGame(int[][] board) throws FileNotFoundException , IOException {
                    /** **************************************
                     ///////////////////////////////////////////
                     TODO: Write The data to the file
                     with the status for each number
                     Is the number was written by user or by the game
                     ///////////////////////////////////////////
                     *************************************** */
                    try ( BufferedWriter br = new BufferedWriter(new FileWriter(new File(PREV_GAME_FILE))) ) {
                              String line = "";
                              for ( int[] board1 : board ) {
                                        for ( int j = 0 ; j < board1.length ; j++ ) {
                                                  line += board1[j] + ",";
                                        }
                              }
                              br.write(line);
                    }
          }
        

}

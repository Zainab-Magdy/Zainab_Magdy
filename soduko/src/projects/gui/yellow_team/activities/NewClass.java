/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package Projects.gui.yellow_team.activities;

/**

 @author EMAM
 */
public class NewClass {

          public static void main(String[] args) {
                    Sudoku s = new Sudoku(projects.gui.yellow_team.activities.Level.EASY);
                    print(s.getUnSolvedBoard());
                    System.out.println("---");
                    print(s.getSolvedBoard());
                    System.out.println("---");
                    System.out.println(s.getList().size());
                    for ( int[][] ele : s.getList() ) {
                              print(ele);
                              System.out.println("---");
                    }
//                    SamuraiSudoku s = new SamuraiSudoku(100);
//                    for ( int[][] ele : s.getUnSolvedBoard() ) {
//                              print(ele);
//                              System.out.println("---");
//                    }
//                    System.out.println("---");
//
//                    for ( int[][] ele : s.getSolvedBoard() ) {
//                              print(ele);
//                              System.out.println("---");
//                    }
          }


          public static void print(int[][] board) {
                    for ( int[] raw : board ) {
                              for ( int ele : raw ) {
                                        System.out.print(ele + " ");
                              }
                              System.out.println("");
                    }
          }

}

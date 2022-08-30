/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package Projects.gui.yellow_team.activities;

import static Projects.gui.yellow_team.activities.NewClass.print;
import static Projects.gui.yellow_team.activities.Sudoku.isValid;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**

 @author mohas
 */
public class SamuraiSudoku {

          private Sudoku[] unSolvedBoard;
          private Sudoku[] solvedBoard;

          public static final int LEFT_UP = 0;
          public static final int RIGHT_UP = 1;
          public static final int CENTER = 2;
          public static final int LEFT_DOWN = 3;
          public static final int RIGHT_DOWN = 4;


          public SamuraiSudoku(int n) {
                    unSolvedBoard = generate(n);
                    System.out.println("generated done");
                    solvedBoard = solve(unSolvedBoard);
                    System.out.println("solve done");
          }


          public int[][][] getSolvedBoard() {
                    return new int[][][] {
                              solvedBoard[LEFT_UP].getSolvedBoard() ,
                              solvedBoard[RIGHT_UP].getSolvedBoard() ,
                              solvedBoard[CENTER].getSolvedBoard() ,
                              solvedBoard[LEFT_DOWN].getSolvedBoard() ,
                              solvedBoard[RIGHT_DOWN].getSolvedBoard() };
          }


          public int[][][] getUnSolvedBoard() {
                    return new int[][][] {
                              unSolvedBoard[LEFT_UP].getUnSolvedBoard() ,
                              unSolvedBoard[RIGHT_UP].getUnSolvedBoard() ,
                              unSolvedBoard[CENTER].getUnSolvedBoard() ,
                              unSolvedBoard[LEFT_DOWN].getUnSolvedBoard() ,
                              unSolvedBoard[RIGHT_DOWN].getUnSolvedBoard(), };
          }


          private Sudoku[] generate(int n) {
                    Sudoku[] s = new Sudoku[5];

                    Sudoku centerSudoku = new Sudoku(n / 4);
                    s[CENTER] = centerSudoku;
                    int[][] centerBoard = centerSudoku.getUnSolvedBoard();
                    System.out.println("generateCenter done");

                    new Thread(() -> {
//                    generate left-up side with the common box
                              int[][] num = new int[9][9];
                              for ( int i = 0 ; i < 3 ; i++ ) {
                                        for ( int j = 0 ; j < 3 ; j++ ) {
                                                  num[i + 6][j + 6] = centerBoard[i][j];
                                        }
                              }
                              int[][] leftUp = generateLeftUp(num , n / 5);
                              s[LEFT_UP] = new Sudoku(leftUp);
                              System.out.println("generateLeftUp done");
                    }).start();

                    new Thread(() -> {
//                    generate right-up side with the common box
                              int[][] num = new int[9][9];
                              for ( int i = 0 ; i < 3 ; i++ ) {
                                        for ( int j = 0 ; j < 3 ; j++ ) {
                                                  num[i + 6][j] = centerBoard[i][j + 6];
                                        }
                              }
                              int[][] rightUp = generateRightUp(num , n / 5);
                              s[RIGHT_UP] = new Sudoku(rightUp);
                              System.out.println("generateRightUp done");
                    }).start();

                    new Thread(() -> {
//                    generate left-down side with the common box
                              int[][] num = new int[9][9];
                              for ( int i = 0 ; i < 3 ; i++ ) {
                                        for ( int j = 0 ; j < 3 ; j++ ) {
                                                  num[i][j + 6] = centerBoard[i + 6][j];
                                        }
                              }
                              int[][] leftDown = generateLeftDown(num , n / 5);
                              s[LEFT_DOWN] = new Sudoku(leftDown);
                              System.out.println("generateLeftDown done");
                    }).start();
                    new Thread(() -> {

//                    generate right-down side with the common box
                              int[][] num = new int[9][9];
                              for ( int i = 0 ; i < 3 ; i++ ) {
                                        for ( int j = 0 ; j < 3 ; j++ ) {
                                                  num[i][j] = centerBoard[i + 6][j + 6];
                                        }
                              }
                              int[][] rightDown = generateRightDown(num , n / 5);
                              s[RIGHT_DOWN] = new Sudoku(rightDown);
                              System.out.println("generateRightDown done");
                    }).start();

                    while ( Thread.activeCount()>1 );
                    return s;
          }


          private int[][] generateSide(int bord[][] , int num , int i1 , int i2 , int j1 , int j2) {
                    if ( num <= 0 ) {
                              return bord;
                    }
                    int i, j, n, b[][], cobyb[][];
                    do {
                              b = copy(bord);
                              for ( int k = 0 ; k < num ; k++ ) {
                                        i = (int) (Math.random() * 9);
                                        j = (int) (Math.random() * 9);
                                        if ( i >= i1 && i <= i2 && j >= j1 && j <= j2 ) {
                                                  continue;
                                        }
                                        if ( b[i][j] > 0 && b[i][j] < 10 ) {
                                                  k--;
                                                  continue;
                                        }
                                        n = (int) (Math.random() * 9) + 1;
                                        if ( isValid(b , i , j , n) ) {
                                                  b[i][j] = n;
                                        } else {
                                                  k--;
                                        }
                              }
                              cobyb = copy(b);
                    } while ( !Sudoku.solve(cobyb , 0 , 0) );
                    return b;
          }


          private int[][] generateLeftUp(int bord[][] , int num) {
                    return generateSide(bord , num , 6 , 8 , 6 , 8);
          }


          private int[][] generateLeftDown(int bord[][] , int num) {
                    return generateSide(bord , num , 0 , 2 , 6 , 8);
          }


          private int[][] generateRightUp(int bord[][] , int num) {
                    return generateSide(bord , num , 6 , 8 , 0 , 2);
          }


          private int[][] generateRightDown(int bord[][] , int num) {
                    return generateSide(bord , num , 0 , 2 , 0 , 2);
          }


          private static int[][] copy(int bord[][]) {
                    int s[][] = new int[9][9];
                    for ( int i = 0 ; i < bord.length ; i++ ) {
                              System.arraycopy(bord[i] , 0 , s[i] , 0 , bord[i].length);
                    }
                    return s;
          }


          private Sudoku[] solve(Sudoku[] sudokus) {
                    Set<int[][]> luSet = sudokus[LEFT_UP].getList();
                    Set<int[][]> ruSet = sudokus[RIGHT_UP].getList();
                    Set<int[][]> cSet = sudokus[CENTER].getList();
                    Set<int[][]> ldSet = sudokus[LEFT_DOWN].getList();
                    Set<int[][]> rdSet = sudokus[RIGHT_DOWN].getList();
                    // get the common between all solutions 
                    Set<int[][]> solutions1 = getCommon(luSet , cSet , 6 , 6 , 0 , 0);
                    Set<int[][]> solutions2 = getCommon(ruSet , solutions1 , 6 , 0 , 0 , 6);
                    Set<int[][]> solutions3 = getCommon(ldSet , solutions2 , 0 , 6 , 6 , 0);
                    Set<int[][]> finalCenterSolutions = getCommon(rdSet , solutions3 , 0 , 0 , 6 , 6);

                    for ( int[][] ele : finalCenterSolutions ) {
                              print(ele);
                              System.out.println("---");
                    }

                    Sudoku[] s = new Sudoku[5];
//                    s[LEFT_UP] = leftUpSudoku;
//                    s[RIGHT_UP] = rightUpSudoku;
//                    s[CENTER] = centerSudoku;
//                    s[LEFT_DOWN] = leftDownSudoku;
//                    s[RIGHT_DOWN] = rightDownSudoku;
                    return s;
          }


          private boolean isequal(int[][] center , int[][] side , int rSide , int cSide , int rCenter , int cCenter) {
                    int c1 = cCenter;
                    int c2 = cSide;
                    for ( int i = 0 ; i < 3 ; i++ ) {
                              cCenter = c1;
                              cSide = c2;
                              for ( int j = 0 ; j < 3 ; j++ ) {
                                        if ( center[rCenter][cCenter] != side[rSide][cSide] ) {
                                                  return false;
                                        }
                                        cCenter++;
                                        cSide++;
                              }
                              rCenter++;
                              rSide++;
                    }
                    return true;
          }


          private Set<int[][]> getCommon(Set<int[][]> side , Set<int[][]> center , int rSide , int cSide , int rCenter , int cCenter) {
                    Set<int[][]> commonSet = new HashSet<>();
                    for ( Iterator<int[][]> i = center.iterator() ; i.hasNext() ; ) {
                              int[][] c = i.next();
                              for ( Iterator<int[][]> j = side.iterator() ; j.hasNext() ; ) {
                                        int[][] s = j.next();
                                        if ( isequal(c , s , rSide , cSide , rCenter , cCenter) ) {
                                                  commonSet.add(c);
                                                  break;
                                        }
                              }
                    }
                    return commonSet;
          }

//          private static int[][] generateCenter(int bord[][] , int num) {
//                    if ( num <= 0 ) {
//                              return bord;
//                    }
//                    int i, j, n, b[][], cobyb[][];
//                    do {
//                              b = copy(bord);
//                              for ( int k = 0 ; k < num ; k++ ) {
//                                        i = (int) (Math.random() * 9);
//                                        j = (int) (Math.random() * 9);
//                                        if ( i >= 3 && i <= 5 || j >= 3 && j <= 5 ) {
//                                                  if ( b[i][j] > 0 && b[i][j] < 10 ) {
//                                                            k--;
//                                                            continue;
//                                                  }
//                                                  n = (int) (Math.random() * 9) + 1;
//                                                  if ( isValid(b , i , j , n) ) {
//                                                            b[i][j] = n;
//                                                  } else {
//                                                            k--;
//                                                  }
//                                        } else {
//                                                  k--;
//                                                  continue;
//                                        }
//                              }
//                              cobyb = copy(b);
//                    } while ( solve(cobyb , 0 , 0) );
//
//                    return bord;
//          }
}

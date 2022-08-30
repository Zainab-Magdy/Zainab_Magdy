/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package Projects.gui.yellow_team.activities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.input.*;
import javax.swing.JOptionPane;

/**

 @author EMAM
 */
public class mainStageController implements Initializable {

          @FXML
          private Group newGame;


          @FXML
          void exit(MouseEvent event) {
                    MainStage.stage.close();
          }


          @FXML
          void help(MouseEvent event) throws FileNotFoundException {
                    try {
                              savedData.readHelp();
                    } catch ( IOException ex ) {
                              Logger.getLogger(mainStageController.class.getName()).log(Level.SEVERE , null , ex);
                    }
          }


          @FXML
          void openNewGame(MouseEvent event) throws IOException {
                    openGame(new Sudoku(projects.gui.yellow_team.activities.Level.EASY , false));
          }


          @FXML
          void openPrevGame(MouseEvent event) throws IOException {
                    /** **************************************
                     ///////////////////////////////////////////
                     TODO: Read The data from the file
                     with the status for each number
                     Is the number was written by user or by the game
                     ///////////////////////////////////////////
                     *************************************** */

                    int[][] board = savedData.readPrevGame();
                    if ( board == null ) {
                              JOptionPane.showMessageDialog(null , "There is no previous game");
                    } else {
                              openGame(new Sudoku(board));
                    }
          }


          @FXML
          void openSettings(MouseEvent event) throws FileNotFoundException , IOException {
                    savedData.readSetting();
          }


          @FXML
          void showInfo(MouseEvent event) throws FileNotFoundException , IOException {
                    savedData.readInfo();
          }


          @Override
          public void initialize(URL url , ResourceBundle rb) {
          }


          private void openGame(Sudoku s) throws IOException {
                    sudoukoController.sudoku = s;
                    Parent root = FXMLLoader.load(getClass().getResource("..\\designs\\sudouko.fxml"));
                    MainStage.stage.setScene(new Scene(root));
          }

}

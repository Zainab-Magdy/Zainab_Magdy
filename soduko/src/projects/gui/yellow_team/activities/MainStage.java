/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package Projects.gui.yellow_team.activities;

import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;

/**

 @author EMAM
 */
public class MainStage extends Application {

          static Stage stage;


          @Override
          public void start(Stage s) throws Exception {
                    Parent root = FXMLLoader.load(getClass().getResource("..\\designs\\mainStage.fxml"));
                    Scene scene = new Scene(root);
                    stage = new  Stage();
                    stage.setScene(scene);
                    stage.show();
          }
          public static void main(String[] args) {
                    launch(args);
          }

}

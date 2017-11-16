/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektkrizickruzic;

import DAL.IRepo;
import DAL.RepoFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author bfidr
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    TextField igrac1;
    @FXML
    TextField igrac2;
    @FXML
    Button exit;

    IRepo repo = RepoFactory.getRepo();

    private void closeButtonAction() {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void pokretanjeSaveaneIgre(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLIgra.fxml"));
        Parent root = loader.load();

        FXMLIgraController igraController = loader.getController();
        
        igraController.setIgra(repo.LoadGame());

        Scene igraViewScene = new Scene(root);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(igraViewScene);
        window.show();
    }

    @FXML
    private void pokretanjeIgre(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLIgra.fxml"));
        Parent root = loader.load();

        FXMLIgraController igraController = loader.getController();
        
        igraController.setName(igrac1.getText(), igrac2.getText());

        Scene igraViewScene = new Scene(root);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(igraViewScene);
        window.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}

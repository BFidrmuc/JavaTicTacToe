/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektkrizickruzic;

import BL.Reflection;
import static BL.Reflection.IspisReflectiona;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author bfidr
 */
public class ProjektKrizicKruzic extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        StringBuilder reflectionProjekta = new StringBuilder();
       
        String[] naziviKlasa = new String[]{"BL.IgracaPloca", "BL.Oznaka", "BL.Polje", "BL.Reflection", "DAL.IRepo", "DAL.RepoDat", "DAL.RepoFactory", "projektkrizickruzic.FXMLDocumentController", "projektkrizickruzic.FXMLIgraController", "projektkrizickruzic.ProjektKrizicKruzic"};
        for (String s : naziviKlasa) {
            reflectionProjekta.append(IspisReflectiona(s));
        }
        try {
            PrintWriter out = new PrintWriter("popisRefleksija.txt");
            out.print(reflectionProjekta.toString());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProjektKrizicKruzic.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        launch(args);
    }

}

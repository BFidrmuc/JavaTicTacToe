/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektkrizickruzic;

import BL.IgracaPloca;
import BL.Oznaka;
import BL.Polje;
import DAL.IRepo;
import DAL.RepoFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bfidr
 */
public class FXMLIgraController implements Initializable {

    /**
     * Initializes the controller class.
     */
    int counter = 0;
    Polje[][] igracaPloca = new Polje[3][3];

    IRepo repo = RepoFactory.getRepo();

    @FXML
    Label player1;
    @FXML
    Label player2;
    @FXML
    Button exit;
    @FXML
    Label polje00;
    @FXML
    Label polje10;
    @FXML
    Label polje20;
    @FXML
    Label polje01;
    @FXML
    Label polje11;
    @FXML
    Label polje21;
    @FXML
    Label polje02;
    @FXML
    Label polje12;
    @FXML
    Label polje22;

    public void setName(String igrac1, String igrac2) {
        player1.setText(igrac1);
        player2.setText(igrac2);

    }

    @FXML
    public void povratakNaMeni(ActionEvent event) throws IOException {
        Object caller = event.getTarget();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root = loader.load();

        Scene igraViewScene = new Scene(root);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(igraViewScene);
        window.show();

    }

    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
        System.exit(0);
    }

    @FXML
    private void onLabelAction(MouseEvent event) {

        Label label = (Label) event.getSource();
        if (counter == 0 || counter % 2 == 0) {
            Thread playerOne = new Thread(new Runnable() {
                @Override
                public void run() {
                    provjeraOznake(label);
                }

            });
            playerOne.start();
        } else {
            Thread playerTwo = new Thread(new Runnable() {
                @Override
                public void run() {
                    provjeraOznake(label);
                }
            });

            playerTwo.start();
        }

    }

    private synchronized void provjeraOznake(Label label) {
        if (label.getText().isEmpty()) {
            if (counter == 0 || counter % 2 == 0) {
                krajnjiPotez(label, "X");
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } else {
                krajnjiPotez(label, "O");
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } else {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Alert alert = new Alert(AlertType.INFORMATION,
                            "Polje puno");
                    alert.showAndWait();
                }
            });

        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

            }
        });

    }

    private synchronized void krajnjiPotez(Label label, String x) {

        Polje polje = parsirajLokaciju(label.getId());
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                label.setText(x);

                if (x.equals("X")) {
                    polje.setOznaka(Oznaka.X);

                } else {
                    polje.setOznaka(Oznaka.O);

                }

                igracaPloca[polje.getX()][polje.getY()] = polje;
                gameOver();
                counter++;
            }
        });

        notifyAll();
    }

    @FXML
    private void spremiIgru(ActionEvent event) throws IOException {
        IgracaPloca plocaZaSave = new IgracaPloca(igracaPloca, player1.getText(), player2.getText(), counter);
        repo.SaveGame(plocaZaSave);
        Alert alert = new Alert(AlertType.INFORMATION,
                "Igra spremljena");
        alert.showAndWait();
        closeButtonAction();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Polje p = new Polje(Oznaka.Y);
                igracaPloca[i][j] = p;
            }
        }

    }

    private Polje parsirajLokaciju(String text) {

        int X = Character.getNumericValue(text.charAt(5));
        int Y = Character.getNumericValue(text.charAt(6));
        Polje polje = new Polje(X, Y);
        return polje;
    }

    private void gameOver() {
        for (int i = 0; i < 3; i++) {
            //Linija
            if ((igracaPloca[0][i].getOznaka().equals(igracaPloca[1][i].getOznaka()) && igracaPloca[0][i].getOznaka().equals(igracaPloca[2][i].getOznaka()))
                    && !(igracaPloca[0][i].getOznaka().equals(Oznaka.Y))) {

                if (igracaPloca[0][i].getOznaka() == Oznaka.X) {
                    Alert alert = new Alert(AlertType.INFORMATION,
                            "Game over, igrac " + player1.getText() + " je dobio.");
                    alert.showAndWait();
                    closeButtonAction();
                    return;
                } else {
                    Alert alert = new Alert(AlertType.INFORMATION,
                            "Game over, igrac " + player2.getText() + " je dobio.");
                    alert.showAndWait();
                    closeButtonAction();
                    return;

                }

            }
            //Stupac
            if ((igracaPloca[i][0].getOznaka().equals(igracaPloca[i][1].getOznaka()) && igracaPloca[i][0].getOznaka().equals(igracaPloca[i][2].getOznaka()))
                    && !(igracaPloca[i][0].getOznaka().equals(Oznaka.Y))) {
                if (igracaPloca[i][0].getOznaka() == Oznaka.X) {
                    Alert alert = new Alert(AlertType.INFORMATION,
                            "Game over, igrac " + player1.getText() + " je dobio.");
                    alert.showAndWait();
                    closeButtonAction();
                    return;
                } else {
                    Alert alert = new Alert(AlertType.INFORMATION,
                            "Game over, igrac " + player2.getText() + " je dobio.");
                    alert.showAndWait();
                    closeButtonAction();
                    return;

                }
            }

        }
        //okomica
        if ((igracaPloca[0][0].getOznaka().equals(igracaPloca[1][1].getOznaka()) && igracaPloca[0][0].getOznaka().equals(igracaPloca[2][2].getOznaka()))
                && !(igracaPloca[0][0].getOznaka().equals(Oznaka.Y))) {
            if (igracaPloca[0][0].getOznaka() == Oznaka.X) {
                Alert alert = new Alert(AlertType.INFORMATION,
                        "Game over, igrac " + player1.getText() + " je dobio.");
                alert.showAndWait();
                closeButtonAction();
                return;
            } else {
                Alert alert = new Alert(AlertType.INFORMATION,
                        "Game over, igrac " + player2.getText() + " je dobio.");
                alert.showAndWait();
                closeButtonAction();
                return;

            }
        }
        if (igracaPloca[0][2].getOznaka().equals(igracaPloca[1][1].getOznaka()) && igracaPloca[0][2].getOznaka().equals(igracaPloca[2][0].getOznaka())
                && !(igracaPloca[0][2].getOznaka().equals(Oznaka.Y))) {
            if (igracaPloca[0][0].getOznaka() == Oznaka.X) {
                Alert alert = new Alert(AlertType.INFORMATION,
                        "Game over, igrac " + player1.getText() + " je dobio.");
                alert.showAndWait();
                closeButtonAction();
                return;
            } else {
                Alert alert = new Alert(AlertType.INFORMATION,
                        "Game over, igrac " + player2.getText() + " je dobio.");
                alert.showAndWait();
                closeButtonAction();
                return;

            }
        }
        boolean draw = false;
        int brojacY = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (igracaPloca[i][j].getOznaka() == Oznaka.Y) {
                    brojacY++;
                }
            }
        }
        if (brojacY == 0) {
            Alert alert = new Alert(AlertType.INFORMATION,
                    "DRAW ");
            alert.showAndWait();
            closeButtonAction();
            return;
        }
    }

    void setIgra(IgracaPloca LoadGame) {
        player1.setText(LoadGame.getIgracPrvi());
        player2.setText(LoadGame.getIgracDrugi());
        counter = LoadGame.getCounter();
        igracaPloca = LoadGame.getIgracaploca();
        iscrtajPlocu();
    }

    private void iscrtajPlocu() {
        for (Polje[] u : igracaPloca) {
            for (Polje p : u) {
                if (p.getOznaka().equals(Oznaka.X)) {
                    postaviOznakuNaPlocu(p, "X");
                }
                if (p.getOznaka().equals(Oznaka.O)) {
                    postaviOznakuNaPlocu(p, "O");

                }
            }
        }
    }

    private void postaviOznakuNaPlocu(Polje p, String c) {
        StringBuilder sb = new StringBuilder(String.valueOf("polje"));
        sb.append(p.getX());
        sb.append(p.getY());
        //naziv polja na kojem je oznaka
        String polje = sb.toString();

        //System.out.printf("Na mjestu (%d,%d)", p.getX(), p.getY());
        for (Label l : napuniLabele()) {
            if (l.getId().equals(polje)) {
                l.setText(c);
            }
        }

    }

    private Label[] napuniLabele() {
        Label[] poljeLabel = new Label[]{polje00, polje01, polje02, polje10, polje11, polje12, polje20, polje21, polje22};
        return poljeLabel;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siscon.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 *
 * @author joseluiz
 */
public class PausaPdvController implements Initializable {

    @FXML
    public void handleSair() {
        System.exit(0);
    }

    @FXML
    public void handleAbrirCaixa() {
        Stage stage = new Stage();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}

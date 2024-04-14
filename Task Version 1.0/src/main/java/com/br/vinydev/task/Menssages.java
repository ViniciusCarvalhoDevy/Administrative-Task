package com.br.vinydev.task;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Menssages {

    public static void showMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

        // Carrega a imagem
        Image icon = new Image("/styles/Images/loco_fundo_branco_icone.png");

        // Define o ícone do Stage
        stage.getIcons().add(icon);



        alert.showAndWait();
        }
}
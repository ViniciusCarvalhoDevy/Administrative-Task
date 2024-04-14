package com.br.vinydev.task;

import com.vinydev.admin_task.services.Files_all;
import java.lang.System.Logger;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.w3c.dom.Element;
public class PrimaryController {
    private List<Element> datas = new ArrayList<>();
    private String mySheet;
    private int vezes = 0;
    private int vezes_two = 0;
    @FXML
    private Button uplodXmlBotton;
    @FXML
    private Button saveInSheet;
    @FXML
    private Button chooseSheet;
    @FXML
    private CheckBox refProd; 
    @FXML
    private CheckBox nameProd; 
    @FXML
    private CheckBox ncmProd; 
    @FXML
    private CheckBox valueProd; 
    @FXML
    private CheckBox cfopProd; 
    @FXML
    private CheckBox origProd; 
    @FXML
    private CheckBox infoProd; 
    @FXML
    private CheckBox unitBuy;
    @FXML
    private CheckBox supplier;
    @FXML
    private Button reload;
    @FXML
    private CheckBox AllCheck;

    

    
    @FXML
    private void uploadButton(ActionEvent event) throws IOException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
        new FileChooser.ExtensionFilter("XML Files","*.xml")
        );
        Stage stage = (Stage) uplodXmlBotton.getScene().getWindow();
        
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(stage);
    
        if (selectedFiles != null){
            uplodXmlBotton.setStyle("-fx-background-color:#2263A1;-fx-text-fill:#fff;");
            for (File file : selectedFiles) {
                try{
                this.datas.add(Files_all.readXmls(file));
                }catch(RuntimeException e){
                    Menssages.showMessage("Erro!", "Erro em Carregar os Arquivos em Xml!");
                }
                if(this.vezes <1){
                Menssages.showMessage("Sucesso!", "XML Carregado Com Sucesso!");
                vezes++;
                }
                
                
                
            }
                
        }else{
            uplodXmlBotton.setStyle("-fx-background-color:#fff;-fx-text-fill:#000;");
            if(this.vezes_two <1){
                Menssages.showMessage("Aviso!", "XML Não Carregado!");
            } 
        }
    }
    
    @FXML
    private void ChooseSheet() throws IOException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
        new FileChooser.ExtensionFilter("Excel Files", "*.xls", "*.xlsx")
        );
        Stage stage = (Stage) chooseSheet.getScene().getWindow();
        
        File selectedFile = fileChooser.showOpenDialog(stage);
    
        if (selectedFile != null){
               chooseSheet.setStyle("-fx-background-color:#2263A1;-fx-text-fill:#fff;");
               this.mySheet = selectedFile.getPath();
               Menssages.showMessage("Sucesso!", "Planilha Carregada Com Sucesso!");
               
               
        }else{
            chooseSheet.setStyle("-fx-background-color:#fff;-fx-text-fill:#000;");
            Menssages.showMessage("Aviso!", "Planilha Não Carregada!");
        }
    }
    @FXML
    private void allCheck() throws IOException {
        if(AllCheck.isSelected()){
           refProd.setSelected(true);
           nameProd.setSelected(true);
           origProd.setSelected(true);
           cfopProd.setSelected(true);
           unitBuy.setSelected(true);
           valueProd.setSelected(true);
           infoProd.setSelected(true);
           ncmProd.setSelected(true);
           supplier.setSelected(true);
        }else{
           AllCheck.setSelected(false);
           refProd.setSelected(false);
           nameProd.setSelected(false);
           origProd.setSelected(false);
           cfopProd.setSelected(false);
           unitBuy.setSelected(false);
           valueProd.setSelected(false);
           infoProd.setSelected(false);
           ncmProd.setSelected(false);
           supplier.setSelected(false);
        }
    }
    @FXML
    private void extractAndSave(ActionEvent event) throws IOException{
        ArrayList<Boolean> list = new ArrayList<Boolean>();
        list.add(0, refProd.isSelected());
        list.add(1, nameProd.isSelected());
        list.add(2, origProd.isSelected());
        list.add(3, cfopProd.isSelected());
        list.add(4, unitBuy.isSelected());
        list.add(5, valueProd.isSelected());
        list.add(6, ncmProd.isSelected());
        list.add(7, supplier.isSelected());
        list.add(8, infoProd.isSelected());
        try{
        Boolean results = Files_all.witreSheet(this.datas,list,this.mySheet);
        if(results){
            Menssages.showMessage("Sucesso!", "Dados Trasferidos Com Sucesso!");
            this.vezes = 0;
            this.vezes_two=0;
        }
        }catch(RuntimeException e){
            Menssages.showMessage("Erro!", "Não foi possivel Transferir Os dados para a Planilha! Causa:" + e.getCause());
        }
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}

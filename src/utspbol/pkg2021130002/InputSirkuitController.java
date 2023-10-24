/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utspbol.pkg2021130002;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Vincent C.S
 */
public class InputSirkuitController implements Initializable {
    
    boolean editdata=false;
    
    @FXML
    private Button btnsimpan;
    @FXML
    private Button btnbatal;
    @FXML
    private Button btnkeluar;
    @FXML
    private TextField txtkode;
    @FXML
    private TextField txtlokasi;
    @FXML
    private TextField txtnama;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void execute(SirkuitModel d){
        if(!d.getKodesirkuit().isEmpty()){
          editdata=true;
          txtkode.setText(d.getKodesirkuit());
          txtlokasi.setText(d.getLokasi());
          txtnama.setText(d.getNamasirkuit());                    
          txtkode.setEditable(false);          
          txtnama.requestFocus();         
        }
    }
    
    @FXML
    private void simpanklik(ActionEvent event) {
        SirkuitModel n = new SirkuitModel();        
        n.setKodesirkuit(txtkode.getText());
        n.setLokasi(txtlokasi.getText());
        n.setNamasirkuit(txtnama.getText());           
        FXMLDocumentController.dtsirkuit.setSirkuitModel(n);
        if(editdata){
            if(FXMLDocumentController.dtpmb.update()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
               a.showAndWait();   
               txtkode.setEditable(true);        
               batalklik(event);                
            } else {               
                Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
                a.showAndWait(); 
            }
         }else if(FXMLDocumentController.dtsirkuit.validasi(n.getKodesirkuit())<=0){
            if(FXMLDocumentController.dtsirkuit.insert()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil disimpan",ButtonType.OK);
               a.showAndWait();            
               batalklik(event);
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
               a.showAndWait();            
            }
        }else{Alert a=new Alert(Alert.AlertType.ERROR,"Data sudah ada",ButtonType.OK);
            a.showAndWait();
            txtkode.requestFocus();
        } 
    }
    
    @FXML
    private void batalklik(ActionEvent event) {
        txtkode.setText("");
        txtlokasi.setText("");
        txtnama.setText("");
        txtkode.requestFocus();
    }
    
    @FXML
    private void keluarklik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }
    
}

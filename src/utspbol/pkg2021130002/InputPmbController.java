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
public class InputPmbController implements Initializable {
    
    boolean editdata=false;
    
    @FXML
    private Button btnsimpan;
    @FXML
    private Button btnbatal;
    @FXML
    private Button btnkeluar;
    @FXML
    private TextField txtid;
    @FXML
    private TextField txtnama;
    @FXML
    private TextField txttim;
    @FXML
    private TextField txtmotor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    public void execute(PmbModel d){
        if(!d.getIdpembalap().isEmpty()){
          editdata=true;
          txtid.setText(d.getIdpembalap());
          txtnama.setText(d.getNamapembalap());          
          txttim.setText(d.getTim());
          txtmotor.setText(d.getMotor());          
          txtid.setEditable(false);          
          txtnama.requestFocus();         
        }
    }

    @FXML
    private void simpanklik(ActionEvent event) {
        PmbModel n = new PmbModel();        
        n.setIdpembalap(txtid.getText());
        n.setNamapembalap(txtnama.getText());     
        n.setTim(txttim.getText());
        n.setMotor(txtmotor.getText());       
        FXMLDocumentController.dtpmb.setPmbModel(n);
        if(editdata){
            if(FXMLDocumentController.dtpmb.update()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
               a.showAndWait();   
               txtid.setEditable(true);        
               batalklik(event);                
            } else {               
                Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
                a.showAndWait(); 
            }
         }else if(FXMLDocumentController.dtpmb.validasi(n.getIdpembalap())<=0){
            if(FXMLDocumentController.dtpmb.insert()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil disimpan",ButtonType.OK);
               a.showAndWait();            
               batalklik(event);
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
               a.showAndWait();            
            }
        }else{Alert a=new Alert(Alert.AlertType.ERROR,"Data sudah ada",ButtonType.OK);
            a.showAndWait();
            txtid.requestFocus();
        } 
    }

    @FXML
    private void batalklik(ActionEvent event) {
        txtid.setText("");
        txtnama.setText("");
        txttim.setText("");
        txtmotor.setText("");
        txtid.requestFocus();
    }

    @FXML
    private void keluarklik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }
    
}

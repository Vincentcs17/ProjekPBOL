/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utspbol.pkg2021130002;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Vincent C.S
 */
public class DisplaySirkuitController implements Initializable {
    @FXML
    private TableView<SirkuitModel> tbvsirkuit;
    @FXML
    private Button btnclose;
    @FXML
    private Button btntambah;
    @FXML
    private Button btnawal;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button btnedit;
    @FXML
    private Button btnsesudah;
    @FXML
    private Button btnakhir;
    @FXML
    private Button btnkurang;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
    }
    public void showdata(){
        ObservableList<SirkuitModel> data=FXMLDocumentController.dtsirkuit.Load();
        if(data!=null){            
            tbvsirkuit.getColumns().clear();            
            tbvsirkuit.getItems().clear();

            TableColumn col=new TableColumn("kodesirkuit");
            col.setCellValueFactory(new PropertyValueFactory<SirkuitModel, String>("kodesirkuit"));
            tbvsirkuit.getColumns().addAll(col);
            col=new TableColumn("lokasi");
            col.setCellValueFactory(new PropertyValueFactory<SirkuitModel, String>("lokasi"));
            tbvsirkuit.getColumns().addAll(col);
            col=new TableColumn("namasirkuit");
            col.setCellValueFactory(new PropertyValueFactory<SirkuitModel, String>("namasirkuit"));
            tbvsirkuit.getColumns().addAll(col);
            tbvsirkuit.setItems(data);
            
    }else {  Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvsirkuit.getScene().getWindow().hide();
        }                
    }

    @FXML
    private void closeklik(ActionEvent event) {
        btnclose.getScene().getWindow().hide();
    }

    @FXML
    private void tambahklik(ActionEvent event) {
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputSirkuit.fxml"));    
        Parent root = (Parent)loader.load();        
        Scene scene = new Scene(root);        
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);        
        stg.setIconified(false);        
        stg.setScene(scene);
        stg.showAndWait();
        } catch (IOException e){   
            e.printStackTrace();   }
        showdata();        
        awalklik(event);
    }

    @FXML
    private void awalklik(ActionEvent event) {
        tbvsirkuit.getSelectionModel().selectFirst();
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
        tbvsirkuit.getSelectionModel().selectPrevious();
    }

    @FXML
    private void editklik(ActionEvent event) {
        SirkuitModel s= new SirkuitModel();
        s=tbvsirkuit.getSelectionModel().getSelectedItem();
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputPmb.fxml"));    
        Parent root = (Parent)loader.load();
        InputSirkuitController isidt=(InputSirkuitController)loader.getController();
        isidt.execute(s);                
        Scene scene = new Scene(root);
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);
        stg.setIconified(false);
        stg.setScene(scene);
        stg.showAndWait();
        } catch (IOException e){   e.printStackTrace();   }
        showdata();  
        awalklik(event);
    }

    @FXML
    private void sesudahklik(ActionEvent event) {
        tbvsirkuit.getSelectionModel().selectNext();
    }

    @FXML
    private void akhirklik(ActionEvent event) {
        tbvsirkuit.getSelectionModel().selectLast();
    }

    @FXML
    private void kurangklik(ActionEvent event) {
        SirkuitModel s= new SirkuitModel();       
        s=tbvsirkuit.getSelectionModel().getSelectedItem();
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?",ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
           if(FXMLDocumentController.dtpmb.delete(s.getKodesirkuit())){
               Alert b=new Alert(Alert.AlertType.INFORMATION,"Data berhasil dihapus", ButtonType.OK);
               b.showAndWait();
           } else {
               Alert b=new Alert(Alert.AlertType.ERROR,"Data gagal dihapus", ButtonType.OK);
               b.showAndWait();               
           }    
           showdata();           
           awalklik(event);       
        }
    }
    
}

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
public class DisplayPembalapController implements Initializable {
    @FXML
    private Button btntambah;
    @FXML
    private Button btnedit;
    @FXML
    private Button btnkurang;
    @FXML
    private Button btnclose;
    @FXML
    private TableView<PmbModel> tbvpembalap;
    @FXML
    private Button btnawal;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button btnsesudah;
    @FXML
    private Button btnakhir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
    }
    
    public void showdata(){
        ObservableList<PmbModel> data=FXMLDocumentController.dtpmb.Load();
        if(data!=null){            
            tbvpembalap.getColumns().clear();            
            tbvpembalap.getItems().clear();

            TableColumn col=new TableColumn("idpembalap");
            col.setCellValueFactory(new PropertyValueFactory<PmbModel, String>("idpembalap"));
            tbvpembalap.getColumns().addAll(col);
            col=new TableColumn("namapembalap");
            col.setCellValueFactory(new PropertyValueFactory<PmbModel, String>("namapembalap"));
            tbvpembalap.getColumns().addAll(col);
            col=new TableColumn("tim");
            col.setCellValueFactory(new PropertyValueFactory<PmbModel, String>("tim"));
            tbvpembalap.getColumns().addAll(col);
            col=new TableColumn("motor");
            col.setCellValueFactory(new PropertyValueFactory<PmbModel, String>("motor"));
            tbvpembalap.getColumns().addAll(col);
            tbvpembalap.setItems(data);
            
    }else {  Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvpembalap.getScene().getWindow().hide();
        }                
    }

    @FXML
    private void tambahklik(ActionEvent event) {
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputPmb.fxml"));    
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
    private void editklik(ActionEvent event) {
        PmbModel s= new PmbModel();
        s=tbvpembalap.getSelectionModel().getSelectedItem();
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputPmb.fxml"));    
        Parent root = (Parent)loader.load();
        InputPmbController isidt=(InputPmbController)loader.getController();
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
    private void kurangklik(ActionEvent event) {
        PmbModel s= new PmbModel();       
        s=tbvpembalap.getSelectionModel().getSelectedItem();
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?",ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
           if(FXMLDocumentController.dtpmb.delete(s.getIdpembalap())){
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

    @FXML
    private void closeklik(ActionEvent event) {
        btnclose.getScene().getWindow().hide();
    }

    @FXML
    private void awalklik(ActionEvent event) {
        tbvpembalap.getSelectionModel().selectFirst();
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
        tbvpembalap.getSelectionModel().selectPrevious();
    }

    @FXML
    private void sesudahklik(ActionEvent event) {
        tbvpembalap.getSelectionModel().selectNext();
    }

    @FXML
    private void akhirklik(ActionEvent event) {
        tbvpembalap.getSelectionModel().selectLast();
    }
    
}

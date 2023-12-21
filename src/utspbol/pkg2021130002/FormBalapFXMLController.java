/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utspbol.pkg2021130002;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Vincent C.S
 */
public class FormBalapFXMLController implements Initializable {
    @FXML
    private TextField txtkodesirkuit;
    @FXML
    private TableView<Balap> tbvbalap;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdatabalap();
        tbvbalap.getSelectionModel().selectFirst();
    }
    
      public void showdatabalap(){
        ObservableList<Balap> data=FXMLDocumentController.dtbalap.Load();
        if(data!=null){            
            tbvbalap.getColumns().clear();            
            tbvbalap.getItems().clear();

            TableColumn col=new TableColumn("kodesirkuit");
            col.setCellValueFactory(new PropertyValueFactory<Balap, String>("kodesirkuit"));
            tbvbalap.getColumns().addAll(col);
            col=new TableColumn("idpembalap");
            col.setCellValueFactory(new PropertyValueFactory<Balap, String>("idpembalap"));
            tbvbalap.getColumns().addAll(col);
            col=new TableColumn("tanggal_balap");
            col.setCellValueFactory(new PropertyValueFactory<Balap, String>("tanggal_balap"));
            tbvbalap.getColumns().addAll(col);
            col=new TableColumn("lap");
            col.setCellValueFactory(new PropertyValueFactory<Balap, String>("lap"));
            tbvbalap.getColumns().addAll(col);
            
            
            tbvbalap.setItems(data);
            
    }else {  Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvbalap.getScene().getWindow().hide();
        }                
    }

    @FXML
    private void tbvbalapklik(MouseEvent event) {
        txtkodesirkuit.setText(tbvbalap.getSelectionModel().getSelectedItem().getKodesirkuit());
    }
    
}

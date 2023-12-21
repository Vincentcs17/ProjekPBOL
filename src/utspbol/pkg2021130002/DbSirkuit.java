/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utspbol.pkg2021130002;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Vincent C.S
 */
public class DbSirkuit {
    private SirkuitModel dt = new SirkuitModel();

    public SirkuitModel getSirkuitModel() {
        return (dt);
    }

    public void setSirkuitModel(SirkuitModel s) {
        dt = s;
    }

    public ObservableList<SirkuitModel> Load() {
        try {
            ObservableList<SirkuitModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select kodesirkuit, lokasi, namasirkuit from sirkuit");

            int i = 1;
            while (rs.next()) {
                SirkuitModel s = new SirkuitModel();
                s.setKodesirkuit(rs.getString("KodeSirkuit"));
                s.setLokasi(rs.getString("Lokasi"));
                s.setNamasirkuit(rs.getString("NamaSirkuit"));
               
                tableData.add(s);
                i++;
            }
            con.tutupKoneksi();
            return tableData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean insert() {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {       
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into sirkuit (kodesirkuit, loaksi, namasirkuit) values (?,?,?)");
            con.preparedStatement.setString(1, getSirkuitModel().getKodesirkuit());           
            con.preparedStatement.setString(2, getSirkuitModel().getLokasi());
            con.preparedStatement.setString(3, getSirkuitModel().getNamasirkuit());                  
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();            
            berhasil = false;
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }    
    }
    
    public boolean delete(String nomor) {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {            
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from siirkuit where kodesirkuit  = ? ");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.executeUpdate();            
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }
    }
    
    public boolean update() {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {            
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("update sirkuit set namasirkuit = ?, loaksi = ? where  kodesirkuit = ? ");
            con.preparedStatement.setString(1, getSirkuitModel().getNamasirkuit());
            con.preparedStatement.setString(2, getSirkuitModel().getLokasi());
            con.preparedStatement.setString(4, getSirkuitModel().getKodesirkuit());
            con.preparedStatement.executeUpdate();            
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();            
            berhasil = false;
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }    
    }
    
    public int validasi (String nomor){
        int val = 0;
        try{
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from sirkuit where kodesirkuit = '" + nomor + "'");
            while (rs.next()){
                val = rs.getInt("jml");
            }
            con.tutupKoneksi();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return val;
    }
}

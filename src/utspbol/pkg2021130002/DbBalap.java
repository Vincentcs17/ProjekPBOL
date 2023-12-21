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
public class DbBalap {
    private Balap dt = new Balap();
    
    public Balap getBalap(){
        return(dt);
    }
    
    public void setBalap(Balap s){
        dt=s;
    }
    
    public ObservableList<Balap> Load() {
        try {
            ObservableList<Balap> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select kodesirkuit, idpembalap, tanggal_balap, lap_balap from balap");

            int i = 1;
            while (rs.next()) {
                Balap b = new Balap();
                b.setKodesirkuit(rs.getString("KodeSirkuit"));
                b.setIdpembalap(rs.getString("IdPembalap"));
                b.setTanggal_balap(rs.getDate("TangggalBalap"));
                b.setLap(rs.getInt("Lap"));
               
                tableData.add(b);
                i++;
            }
            con.tutupKoneksi();
            return tableData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean delete(String nomor) {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {            
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from balap where kodesirkuit  = ? ");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update balap set idpembalap = ?, tanggal_balap = ?, lap = ?  where  kodesirkuit = ? ");
            con.preparedStatement.setString(1, getBalap().getIdpembalap());
            con.preparedStatement.setDate(2, getBalap().getTanggal_balap());
            con.preparedStatement.setInt(3, getBalap().getLap());
            con.preparedStatement.setString(4, getBalap().getKodesirkuit());
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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from balap where kodesirkuit = '" + nomor + "'");
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

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
public class DbPembalap {
    private PmbModel dt = new PmbModel();

    public PmbModel getPmbModel() {
        return (dt);
    }

    public void setPmbModel(PmbModel s) {
        dt = s;
    }

    public ObservableList<PmbModel> Load() {
        try {
            ObservableList<PmbModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select idpembalap, namapembalap, tim, motor from pembalap");

            int i = 1;
            while (rs.next()) {
                PmbModel p = new PmbModel();
                p.setIdpembalap(rs.getString("IdPembalap"));
                p.setNamapembalap(rs.getString("NamaPembalap"));
                p.setTim(rs.getString("Tim"));
                p.setMotor(rs.getString("Motor"));
               
                tableData.add(p);
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into pembalap (idpembalap,namapembalap, tim, motor) values (?,?,?,?)");
            con.preparedStatement.setString(1, getPmbModel().getIdpembalap());           
            con.preparedStatement.setString(2, getPmbModel().getNamapembalap());
            con.preparedStatement.setString(3, getPmbModel().getTim());           
            con.preparedStatement.setString(4, getPmbModel().getMotor());        
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from pembalap where idpembalap  = ? ");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update pembalap set namapembalap = ?, tim = ?, motor = ?  where  idpembalap = ? ");
            con.preparedStatement.setString(1, getPmbModel().getNamapembalap());
            con.preparedStatement.setString(2, getPmbModel().getTim());
            con.preparedStatement.setString(3, getPmbModel().getMotor());
            con.preparedStatement.setString(4, getPmbModel().getIdpembalap());
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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from pembalap where idpembalap = '" + nomor + "'");
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

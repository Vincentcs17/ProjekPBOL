/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utspbol.pkg2021130002;

import java.sql.Date;

/**
 *
 * @author Vincent C.S
 */
public class Balap {
    private String kodesirkuit, idpembalap;
    private int lap_balap;

    public int getLap_balap() {
        return lap_balap;
    }

    public void setLap_balap(int lap_balap) {
        this.lap_balap = lap_balap;
    }
    private Date tanggal_balap;

    public String getKodesirkuit() {
        return kodesirkuit;
    }

    public void setKodesirkuit(String kodesirkuit) {
        this.kodesirkuit = kodesirkuit;
    }

    public String getIdpembalap() {
        return idpembalap;
    }

    public void setIdpembalap(String idpembalap) {
        this.idpembalap = idpembalap;
    }

    public Date getTanggal_balap() {
        return tanggal_balap;
    }

    public void setTanggal_balap(Date tanggal_balap) {
        this.tanggal_balap = tanggal_balap;
    }
    
    
    
}
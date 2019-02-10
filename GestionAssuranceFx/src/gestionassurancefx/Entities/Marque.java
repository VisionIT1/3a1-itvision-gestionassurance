/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionassurancefx.Entities;

/**
 *
 * @author Psico
 */
public class Marque {
    private int id_marque;
    private String lib_mrq;

    public Marque() {
    }

    public Marque(int id_marque, String lib_mrq) {
        this.id_marque = id_marque;
        this.lib_mrq = lib_mrq;
    }
    

    public Marque(String lib_mrq) {
        this.lib_mrq = lib_mrq;
    }

    public String getLib_mrq() {
        return lib_mrq;
    }

    public void setLib_mrq(String lib_mrq) {
        this.lib_mrq = lib_mrq;
    }

    public int getId_marque() {
        return id_marque;
    }

    public void setId_marque(int id_marque) {
        this.id_marque = id_marque;
    }

    @Override
    public String toString() {
        return "Marque{" + "id_marque=" + id_marque + ", lib_mrq=" + lib_mrq + '}';
    }

  
    
}

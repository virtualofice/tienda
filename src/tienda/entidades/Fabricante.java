/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.entidades;

/**
 *
 * @author chachoadmin
 */
public class Fabricante {
    
    
    private int codigo;
    
    private String nombreF;
    
    private String apellidoF;

    public Fabricante() {
    }

    public Fabricante(int codigo, String nombreF) {
        this.codigo = codigo;
        this.nombreF = nombreF;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreF() {
        return nombreF;
    }

    public void setNombreF(String nombreF) {
        this.nombreF = nombreF;
    }

    @Override
    public String toString() {
        return "Fabricante{" + "codigo=" + codigo + ", nombreF=" + nombreF + '}';
    }

    
    
}

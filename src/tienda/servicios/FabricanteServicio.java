/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.servicios;

import java.sql.SQLException;
import java.util.Scanner;
import tienda.entidades.Fabricante;
import tienda.excepcion.MiExcepcion;
import tienda.persistencia.FabricanteDAO;

/**
 *
 * @author chachoadmin
 */
public class FabricanteServicio {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    private FabricanteDAO fabricanteDAO;

    public FabricanteServicio() {
        
        fabricanteDAO = new FabricanteDAO();
    }

    public FabricanteServicio(FabricanteDAO fabricanteDAO) {
        this.fabricanteDAO = fabricanteDAO;
    }

    public Scanner getLeer() {
        return leer;
    }

    public void setLeer(Scanner leer) {
        this.leer = leer;
    }

    public FabricanteDAO getFabricanteDAO() {
        return fabricanteDAO;
    }

    public void setFabricanteDAO(FabricanteDAO fabricanteDAO) {
        this.fabricanteDAO = fabricanteDAO;
    }

    @Override
    public String toString() {
        return "FabricanteServicio{" + "leer=" + leer + ", fabricanteDAO=" + fabricanteDAO + '}';
    }

    public void ingresarFabricante() throws MiExcepcion, SQLException {

        try {

            System.out.println("Ingrese nombre del fabricante");
            String nombre = leer.next();

            Fabricante nuevoFabricante = new Fabricante(fabricanteDAO.idUltimoFabricante(), nombre);

            fabricanteDAO.crearFabricante(nuevoFabricante);

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

    }

}

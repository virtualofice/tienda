/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.persistencia;

import java.sql.SQLException;
import tienda.entidades.Fabricante;
import tienda.excepcion.MiExcepcion;

/**
 *
 * @author chachoadmin
 */
public class FabricanteDAO extends DAO {

    public int idUltimoFabricante() throws MiExcepcion, SQLException {
        int ultimoIdFabricante = 0;

        String sql = "select codigo from fabricante f order by f.codigo desc limit 1;";

        consultarBase(sql);

        while (resultado.next()) {

            ultimoIdFabricante = resultado.getInt(1) + 1;

        }

        return ultimoIdFabricante;
    }

    public void crearFabricante(Fabricante fabricante) throws MiExcepcion {

        try {

            if (fabricante == null) {
                System.out.println("No hay fabricante para insertar");
            } else {

                String sql = "INSERT INTO Fabricante (codigo, nombre) "
                        + "VALUES('"
                        + fabricante.getCodigo()
                        + "', '"
                        + fabricante.getNombreF()
                        + "');";

                // SE MUESTRA LA CADENA RESULTANTE
                System.out.println(sql);
                System.out.println();

                insertarModificarEliminar(sql);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new MiExcepcion("ERROR AL GUARDAR fabricante");

        }

    }

}

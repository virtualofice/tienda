/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.persistencia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tienda.entidades.Producto;
import tienda.excepcion.MiExcepcion;

/**
 *
 * @author chachoadmin
 */
public class ProductoDAO extends DAO {

    public List<Producto> obtenerProductos() throws Exception {
        try {

            String sql = "select * from producto;";

            consultarBase(sql);

            List<Producto> productos = new ArrayList<>();
            Producto producto = null;
            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombreP(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigoFabricante(resultado.getInt(4));

                productos.add(producto);
            }
            desconectarBase();
            return productos;
        } catch (Exception e) {

            throw new Exception("Error de sistema");
        } finally {
            desconectarBase();
        }

    }

    public List<Producto> obtenerProductoNomYPrecio() throws Exception {

        try {

            String sql = "SELECT nombre, precio FROM Producto p;";

            consultarBase(sql);

            List<Producto> listaNYP = new ArrayList<>();
            Producto producto = null;

            while (resultado.next()) {
                producto = new Producto();
                producto.setNombreP(resultado.getString(1));
                producto.setPrecio(resultado.getInt(2));

                listaNYP.add(producto);
            }
            desconectarBase();

            return listaNYP;

        } catch (Exception e) {

            throw new Exception("Error de sistema");
        } finally {
            desconectarBase();
        }

    }

    public List<Producto> productoRango120202() throws MiExcepcion, SQLException, Exception {

        try {

            String sql = "SELECT * FROM Producto p WHERE p.precio >= 120 and p.precio <= 202 order by p.precio asc;";

            consultarBase(sql);
            List<Producto> rango120202 = new ArrayList<>();
            Producto prod = null;

            while (resultado.next()) {
                prod = new Producto();
                prod.setCodigo(resultado.getInt(1));
                prod.setNombreP(resultado.getString(2));
                prod.setPrecio(resultado.getDouble(3));
                prod.setCodigoFabricante(resultado.getInt(4));

                rango120202.add(prod);
            }
            desconectarBase();
            return rango120202;

        } catch (Exception e) {

            throw new Exception("Error de sistema");
        } finally {

            desconectarBase();
        }
    }

    public List<Producto> portatiles() throws MiExcepcion, SQLException, Exception {

        try {

            List<Producto> listaPort = new ArrayList();
            Producto produc = null;

            String sql = "SELECT * FROM Producto p WHERE p.nombre like '%portatil%';";

            consultarBase(sql);

            while (resultado.next()) {
                produc = new Producto();
                produc.setCodigo(resultado.getInt(1));
                produc.setNombreP(resultado.getString(2));
                produc.setPrecio(resultado.getDouble(3));
                produc.setCodigoFabricante(resultado.getInt(4));

                listaPort.add(produc);

            }

            return listaPort;

        } catch (Exception e) {

            throw new Exception("Error de sistema");

        } finally {

            desconectarBase();
        }
    }

    public Producto masBarato() throws MiExcepcion, Exception {

        try {
            Producto masBarato = null;

            String sql = "SELECT * FROM producto p  ORDER BY p.precio asc limit 1;";

            consultarBase(sql);

            masBarato = new Producto();

            while (resultado.next()) {
                masBarato.setCodigo(resultado.getInt(1));
                masBarato.setNombreP(resultado.getString(2));
                masBarato.setPrecio(resultado.getDouble(3));
                masBarato.setCodigoFabricante(resultado.getInt(4));
            }

            return masBarato;

        } catch (Exception e) {

            throw new Exception("Error de sistema");
        } finally {

            desconectarBase();
        }

    }

    public void crearProducto(Producto p) throws MiExcepcion, ClassNotFoundException, Exception {

        try {

            if (p == null) {
                throw new MiExcepcion("producto INVÁLIDO");
            }

            String sql = "INSERT INTO Producto( codigo, nombre, precio, codigo_fabricante) "
                    + "VALUES('"
                    + p.getCodigo()
                    + "', '"
                    + p.getNombreP()
                    + "', '"
                    + p.getPrecio()
                    + "', '"
                    + p.getCodigoFabricante()
                    + "');";

            // SE MUESTRA LA CADENA RESULTANTE
            System.out.println(sql);
            System.out.println();

            insertarModificarEliminar(sql);

        } catch (Exception e) {

            System.out.println(e.getMessage());
            throw new MiExcepcion("ERROR AL GUARDAR producto");

        }
    }

    public int ultimoId() throws MiExcepcion, SQLException {

        int ultimo = 0;

        String sql = "select codigo from producto order by codigo desc limit 1 ;";

        consultarBase(sql);

        while (resultado.next()) {
            ultimo = resultado.getInt(1) + 1;
        }

        return ultimo;
    }

    public Producto buscarProductoXId(int id) throws MiExcepcion, SQLException {

        Producto p = null;

        String sql = "Select * from Producto p where p.codigo = " + id + ";";

        consultarBase(sql);

        while (resultado.next()) {

            p = new Producto(resultado.getInt(1), resultado.getString(2), resultado.getDouble(3), resultado.getInt(4));
            System.out.println("Producto ");
            System.out.println(p.toString());
        }

        return p;

    }

    public void editarProducto(Producto prod, String nombre, Double precio, int cFabricante) throws ClassNotFoundException, Exception {

        if (prod == null) {

            System.out.println("No hay producto para editar");
        } else {

            prod.setNombreP(nombre);
            prod.setPrecio(precio);
            prod.setCodigoFabricante(cFabricante);

            String sql = "UPDATE Producto SET nombre = '"
                    + prod.getNombreP()
                    + "', "
                    + "precio = "
                    + prod.getPrecio()
                    + ", codigo_fabricante = "
                    + prod.getCodigoFabricante()
                    + " WHERE codigo = "
                    + prod.getCodigo()
                    + ";";

            // SE MUESTRA LA CADENA RESULTANTE
            System.out.println(sql);
            System.out.println();

            insertarModificarEliminar(sql);

            System.out.println("Producto editado");
            System.out.println(prod.toString());
            System.out.println("");

        }

    }

}

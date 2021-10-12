/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.servicios;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import tienda.entidades.Producto;
import tienda.excepcion.MiExcepcion;
import tienda.persistencia.ProductoDAO;

/**
 *
 * @author chachoadmin
 */
public class ProductoServicio {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    private ProductoDAO productoDAO;

    public ProductoServicio() {

        productoDAO = new ProductoDAO();
    }

    public ProductoDAO getDao() {
        return productoDAO;
    }

    public void setDao(ProductoDAO dao) {
        this.productoDAO = dao;
    }

    @Override
    public String toString() {
        return "ProductoServicio{" + "dao=" + productoDAO + '}';
    }

    public void imprimirProductos() throws MiExcepcion {
        try {
            List<Producto> productos = productoDAO.obtenerProductos();

            if (productos.isEmpty()) {
                throw new MiExcepcion("NO EXISTEN productos");
            } else {
                System.out.println("*** LISTA DE productos ***");
                System.out.printf("%-15s%-35s%-20s%-15s\n", "Codigo", "NOMBRE", "precio", "cod_Fabricante");

                for (Producto producto : productos) {
                    System.out.printf("%-15s%-35s%-20s%-15s\n", producto.getCodigo(), producto.getNombreP(), producto.getPrecio(), producto.getCodigoFabricante());
                }

                System.out.println();
            }
        } catch (MiExcepcion e) {
            // e.printStackTrace();
            throw e;
        } catch (Exception e) {
            // e.printStackTrace();
            throw new MiExcepcion("ERROR DE SISTEMA " + e.toString());
        }
    }

    public void obtenerProductoNomYPrecio() throws Exception {

        List<Producto> listaNP = productoDAO.obtenerProductos();

        try {

            if (listaNP.isEmpty()) {
                throw new Exception("No hay productos con esas caracteristicas");
            } else {

                System.out.println("*** LISTA DE productos - Nombre y Precio***");
                System.out.printf("%-35s%-20s\n", "NOMBRE", "precio");

                for (Producto producto : listaNP) {

                    System.out.printf("%-35s%-20s\n", producto.getNombreP(), producto.getPrecio());

                }
                System.out.println("");

            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void obtenerProducto120202() throws SQLException, Exception {

        List<Producto> listaProd = productoDAO.productoRango120202();

        try {

            if (listaProd.isEmpty()) {

                System.out.println("No hay productos para mostrar");
            } else {

                System.out.println("*** LISTA DE productos ***");
                System.out.printf("%-15s%-35s%-20s%-15s\n", "Codigo", "NOMBRE", "precio", "cod_Fabricante");

                for (Producto producto : listaProd) {
                    System.out.printf("%-15s%-35s%-20s%-15s\n", producto.getCodigo(), producto.getNombreP(), producto.getPrecio(), producto.getCodigoFabricante());
                }

                System.out.println();

            }
        } catch (Exception e) {

            System.out.println("Error ....");
        }

    }

    public void portatiles() throws SQLException, Exception { /// verifica que no esta nula la lista y muestra el listado

        List<Producto> listaPortatiles = productoDAO.portatiles();

        try {

            if (listaPortatiles.isEmpty()) {
                System.out.println("No hay portatiles disponibles");
            } else {

                System.out.println("*** LISTA DE Portatiles ***");
                System.out.printf("%-15s%-35s%-20s%-15s\n", "Codigo", "NOMBRE", "precio", "cod_Fabricante");

                for (Producto aux : listaPortatiles) {
                    System.out.printf("%-15s%-35s%-20s%-15s\n", aux.getCodigo(), aux.getNombreP(), aux.getPrecio(), aux.getCodigoFabricante());

                }
                System.out.println(" ");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void masBarato() throws Exception {

        Producto masBarato = productoDAO.masBarato();

        if (masBarato == null) {
            System.out.println("No hay productos");
        }
        try {

            System.out.println("*** Producto màs barato ***");
            System.out.printf("%-15s%-35s%-20s%-15s\n", "Codigo", "NOMBRE", "precio", "cod_Fabricante");
            System.out.printf("%-15s%-35s%-20s%-15s\n", masBarato.getCodigo(), masBarato.getNombreP(), masBarato.getPrecio(), masBarato.getCodigoFabricante());

        } catch (Exception e) {
            throw e;
        }
    }

    public void ingresarNuevoProducto() throws MiExcepcion, Exception {

        try {

            System.out.println("Ingrese el nombre del producto");
            String nombre = leer.next();

            System.out.println("Ingrese precio del producto");
            Double precio = leer.nextDouble();

            System.out.println("Ingrese codigo del fabricante");
            int codFabricante = leer.nextInt();

            Producto nuevo = new Producto(productoDAO.ultimoId(), nombre, precio, codFabricante);

            productoDAO.crearProducto(nuevo);

        } catch (MiExcepcion e) {
            System.out.println(e.getMessage());
        }

    }

    public void editarProducto() throws MiExcepcion, SQLException, Exception {

        imprimirProductos();

        System.out.println("Ingrese el id del producto que desea modificar ");
        int idEdit = leer.nextInt();

        Producto prodAEditar = productoDAO.buscarProductoXId(idEdit);
        
        System.out.println("ingrese nuevo nombre");
        String nombre = leer.next();
        
        System.out.println("ingrese nuevo precio");
        Double precio = leer.nextDouble();
        
        System.out.println("ingrese nuevo codigo Fabricante");
        int codFab = leer.nextInt();
        
        productoDAO.editarProducto(prodAEditar, nombre, precio, codFab);
        
        imprimirProductos();
        
    }
}

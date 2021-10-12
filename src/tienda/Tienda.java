/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda;

import java.util.InputMismatchException;
import java.util.Scanner;
import tienda.servicios.FabricanteServicio;
import tienda.servicios.ProductoServicio;

public class Tienda {

    public static void main(String[] args) throws Exception {

        Scanner leer = new Scanner(System.in).useDelimiter("\n");

        ProductoServicio ps = new ProductoServicio();
        FabricanteServicio fs = new FabricanteServicio();

        int option = 0;
        int error = 0;

        do {
            System.out.println("---------Menu---------");
            System.out.println("1- Lista el nombre de todos los productos que hay en la tabla producto. ");
            System.out.println("2- Lista los nombres y los precios de todos los productos de la tabla producto.");
            System.out.println("3- Listar aquellos productos que su precio esté entre 120 y 202.");
            System.out.println("4- Buscar y listar todos los Portátiles de la tabla producto.");
            System.out.println("5- Listar el nombre y el precio del producto más barato.");
            System.out.println("6- Ingresar un producto a la base de datos.");
            System.out.println("7- Ingresar un fabricante a la base de datos");
            System.out.println("8- Editar un producto con datos a elección.");
            System.out.println("0-  Salir");
            do {
                error = 0;
                System.out.println("Ingrese una opciòn");
                try {
                    option = leer.nextInt();
                } catch (InputMismatchException imp) {
                    System.out.println("Debe ingresar una opcion valida");
                    error = error + 1;
                    leer.nextLine();
                }
            } while (error > 0 || option < 0 || option > 8);

            switch (option) {

                case 1:
                    ps.imprimirProductos();
                    break;
                case 2:
                    ps.obtenerProductoNomYPrecio();
                    break;

                case 3:
                    ps.obtenerProducto120202();
                    break;
                case 4:
                    ps.portatiles();
                    break;
                case 5:
                    ps.masBarato();
                    break;
                case 6:
                    ps.ingresarNuevoProducto();
                    break;
                case 7:
                    fs.ingresarFabricante();
                    break;
                case 8:
                    ps.editarProducto();
                    break;
                case 0:

                    break;

            }

        } while (option != 0);

    }

}

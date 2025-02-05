package FrikiHouse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import Gestion.Clientes;
import Gestion.Detalle_Pedido;
import Gestion.Detalle_Venta;
import Gestion.Garantia;
import Gestion.Pedidos;
import Gestion.Productos;
import Gestion.Proveedores;
import Gestion.Series;
import Gestion.Usuarios;
import Gestion.Ventas;

public class Main {

	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try (Connection c = ConexionBBDD.getConnection();
				Statement s = c.createStatement()) {
			/*
			 * CREACION DE TABLAS
			 
			Series.crearTabla();
			Productos.crearTabla();
			Proveedores.crearTabla();
			Pedidos.crearTabla();
			Clientes.crearTabla();
			Ventas.crearTabla();
			Detalle_Pedido.crearTabla();
			Detalle_Venta.crearTabla();
			Usuarios.crearTabla();
			Garantia.crearTabla();
			
			*/
			
			/*
			 * MENU
			 */
			System.out.println("¡Bienvenido a la FrikiHouse!\n");
			// System.out.println("\n1. Administrador\n2. Empleado\n3. Salir\nElige una opción: ");
			System.out.println("\n1. Hacer pedido a proveedor\n2. Revisar stock\n3. Historial de pedidos\n4. Gestionar ventas\n5. Historial de ventas\n6. Estadísticas\n7. Salir\n\nElige una opción: ");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		
	}

}

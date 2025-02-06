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
			
			
			Usuarios.mostrarValor("1");
			
			/*
			 * MENU
			 */
			int op;
			System.out.println("¡Bienvenido a la FrikiHouse!\n");
			do {
				System.out.println("\n===== SELECCIONAR USUARIO =====");
				System.out.print("\n1. Administrador\n2. Empleado\n3. Salir\n\nElige una opción: ");
				op = sc.nextInt();
				switch (op) {
				case 1:
					System.out.print("\nIntroduce la contraseña: ");
					int password = sc.nextInt();
					if (password == 1234) {
						int admin;
						String usuario;
						String contraseña;
						System.out.println("\nBienvenido, administrador. Aquí puede gestionar la BBDD.");
						System.out.print("1. Crear tabla\n2. Eliminar tabla\n3. Crear usuario\n4. Cambiar contraseña del usuario\n5. Salir\n\nElige una opción: ");
						admin = sc.nextInt();
						do {
							switch (admin) {
							case 1:
								
								break;
							case 2:
								
								break;
							case 3:
								Usuarios.Rol rol;
								System.out.println("Usuario: ");
								usuario = sc.next();
								System.out.println("Contraseña: ");
								contraseña = sc.next();
								System.out.println("Rol (admin/empleado): ");
							    String rolInput = sc.next().toUpperCase();
							    rol = Usuarios.Rol.valueOf(rolInput);
							    //Usuarios.insertar(usuario, contraseña, rol);
								break;
							case 4:
								System.out.println("¿Qué usuario quieres cambiar?: ");
								usuario = sc.next();
								System.out.println("Introduce la nueva contraseña: ");
								contraseña = sc.next();
								//Usuarios.actualizarValor(usuario, contraseña);
								break;
							case 5:
								// salir y +volver al menú anterior
								break;
							default:
								
								break;
							}
						} while (admin != 5);
						
					} else {
						System.err.println("Acceso denegado.");
					}
					break;
				case 2:
					int gestion;
					System.out.println("\n===== GESTOR DE FRIKIHOUSE =====");
					System.out.print("\n1. Hacer pedido a proveedor\n2. Revisar stock\n3. Historial de pedidos\n4. Gestionar ventas\n5. Historial de ventas\n6. Estadísticas\n7. Salir\n\nElige una opción: ");
					gestion = sc.nextInt();
					do {
						switch (gestion) {
						case 1:
							String proveedor;
							System.out.print("Proveedor: ");
							proveedor = sc.next();
							break;
						case 2:
							// Productos.mostrar();
							break;
						case 3:
							
							break;
						case 4:
							
							break;
						case 5:
							
							break;
						case 6:
							
							break;
						case 7:
							
							break;
						default:
							System.err.println("Opción no válida.");
							break;
						}
					} while (gestion != 7);
					break;
				case 3:
					
					break;
				default:
					System.err.println("Opción no válida.");
					break;
				}
			} while (op != 3);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		
	}

}

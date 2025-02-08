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

/**
 * Clase Main.
 */
public class Main {

	/**
	 * Método main(). Encargado de hacer un menú de usuario para ejecutar todas las operaciones que indique el usuario,
	 * utilizando los métodos de las clases que pertenecen al paquete Gestion.
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try (Connection c = ConexionBBDD.getConnection();
				Statement s = c.createStatement()) {
			/*Series.insertarValor("Dragon Ball");
			Series.insertarValor("Dragon Ball Z");
			Series.insertarValor("Dragon Ball Daima");
			Series.insertarValor("Dragon Ball Super");
			Series.insertarValor("Dragon Ball GT");
			Proveedores.insertarValor("amazon", "uwu");
			Proveedores.insertarValor("puchi", "uwu");
			Productos.insertarValor("1", "goku", "bandai", "resina", "40", "4");
			Productos.insertarValor("2", "freezer", "puchi", "resina", "50", "2");
			Clientes.insertarValor("50570284T", "Cris", ":3");
			 */
			
			int op;
			System.out.println("¡Bienvenido a la FrikiHouse!\n");
			
			do {
				System.out.println("\n===== SELECCIONAR USUARIO =====");
				System.out.print("\n1. Administrador\n2. Empleado\n3. Salir\n\nElige una opción: ");
				op = sc.nextInt();
				
				// MENÚ DE INICIO
				
				switch (op) {
				// Administrador
				case 1:
					System.out.print("\nIntroduce la contraseña: ");
					int password = sc.nextInt();
					if (password == 1234) {
						int admin;
						String usuario;
						String contraseña;
						System.out.println("\nBienvenido, administrador. Aquí puede gestionar la BBDD.");
						
						// MENÚ DE ADMINISTRADOR
						
						do {
							System.out.print("\n1. Crear tablas\n2. Eliminar tablas\n3. Crear usuario\n4. Cambiar contraseña del usuario\n5. Salir\n\nElige una opción: ");
							admin = sc.nextInt();
							switch (admin) {
							// Crear tablas
							case 1:
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
								System.out.println("Tablas creadas correctamente.");
								break;
							// Eliminar tablas
							case 2:
								Garantia.eliminarTabla();
								Usuarios.eliminarTabla();
								Detalle_Venta.eliminarTabla();
								Detalle_Pedido.eliminarTabla();
								Ventas.eliminarTabla();
								Clientes.eliminarTabla();
								Pedidos.eliminarTabla();
								Proveedores.eliminarTabla();
								Productos.eliminarTabla();
								Series.eliminarTabla();
								System.out.println("Tablas eliminadas correctamente.");
								break;
							// Crear usuario
							case 3:
								Usuarios.Rol rol;
								System.out.println("Usuario: ");
								usuario = sc.next();
								System.out.println("Contraseña: ");
								contraseña = sc.next();
								System.out.println("Rol (admin/empleado): ");
							    String rolInput = sc.next().toUpperCase();
							    rol = Usuarios.Rol.valueOf(rolInput);
							    Usuarios.insertar(usuario, contraseña, rol);
								break;
							// Cambiar contraseña de un usuario
							case 4:
								System.out.println("¿Qué usuario quieres cambiar?: ");
								usuario = sc.next();
								System.out.println("Introduce la nueva contraseña: ");
								contraseña = sc.next();
								Usuarios.actualizarValor(usuario, contraseña);
								break;
							// Salir
							case 5:
								System.out.println("Saliendo del menú de administrador...");
								break;
							default:
								System.out.println("Opción no válida.");
								break;
							}
						} while (admin != 5);
						
					} else {
						System.err.println("Acceso denegado.");
					}
					break;
				// Empleado
				case 2:
					int gestion;
					System.out.println("\n===== GESTOR DE FRIKIHOUSE =====");
					
					// MENÚ DE EMPLEADO
					
					do {
						System.out.print("\n1. Hacer pedido a proveedor\n2. Revisar stock\n3. Historial de pedidos\n4. Hacer venta\n5. Historial de ventas\n6. Estadísticas\n7. Salir\n\nElige una opción: ");
						gestion = sc.nextInt();
						switch (gestion) {
						// Hacer pedido a proveedor
						case 1:
	                        System.out.print("ID del proveedor: ");
	                        int idProveedor = sc.nextInt();
	                        System.out.print("ID del producto: ");
	                        int idProducto = sc.nextInt();
	                        System.out.print("Cantidad: ");
	                        int cantidad = sc.nextInt();
	                        String idPedido = Pedidos.insertarValor(String.valueOf(idProveedor), "2024-01-01", String.valueOf(cantidad));
	                        Productos.actualizarValor(String.valueOf(idProducto), 2, String.valueOf(cantidad), true);
	                        Detalle_Pedido.insertarValor(idPedido, String.valueOf(idProducto), String.valueOf(cantidad));
	                        System.out.println("Pedido registrado correctamente.");
	                        break;
	                    // Revisar stock
						case 2:
							Productos.mostrarTabla();
							break;
						// Historial de pedidos
						case 3:
	                        System.out.println("Mostrando historial de pedidos...");
	                        String queryPedidos = "SELECT dp.id_detalle_pedido, p.id_pedido, pr.nombre AS producto, dp.cantidad "
	                                + "FROM detalle_pedido dp "
	                                + "JOIN pedidos p ON dp.id_pedido = p.id_pedido "
	                                + "JOIN productos pr ON dp.id_producto = pr.id_producto";
	                        try (Statement stmt = c.createStatement();
	                             var rs = stmt.executeQuery(queryPedidos)) {
	                            while (rs.next()) {
	                                System.out.println("ID Detalle Pedido: " + rs.getInt("id_detalle_pedido"));
	                                System.out.println("ID Pedido: " + rs.getInt("id_pedido"));
	                                System.out.println("Producto: " + rs.getString("producto"));
	                                System.out.println("Cantidad: " + rs.getInt("cantidad"));
	                                System.out.println("-----------------------------\n");
	                            }
	                        }
	                        break;
						// Hacer venta a cliente
						case 4:
	                        System.out.print("ID del cliente: ");
	                        String dni = sc.next();
	                        System.out.print("ID del producto: ");
	                        int idVentaProducto = sc.nextInt();
	                        System.out.print("Cantidad: ");
	                        int cantidadVenta = sc.nextInt();

	                        // Obtener el precio del producto
	                        double precioProducto = 0;
	                        String queryPrecio = "SELECT precio FROM productos WHERE id_producto = " + idVentaProducto;
	                        try (Statement stmt = c.createStatement();
	                             ResultSet rs = stmt.executeQuery(queryPrecio)) {
	                            if (rs.next()) {
	                                precioProducto = rs.getDouble("precio");
	                            }
	                        }
	                        double subtotalVenta = precioProducto * cantidadVenta;

	                        String idVenta = Ventas.insertarValor(dni, "2024-01-01", String.valueOf(subtotalVenta));
	                        Productos.actualizarValor(String.valueOf(idVentaProducto), 2, String.valueOf(cantidadVenta), false);
	                        Detalle_Venta.insertarValor(idVenta, String.valueOf(idVentaProducto), String.valueOf(cantidadVenta), String.valueOf(subtotalVenta));
	                        Garantia.insertarValor(idVenta, "2024-01-01", "2025-01-01");
	                        System.out.println("Venta registrada correctamente con garantía y stock actualizado.");
	                        break;
	                    // Historial de ventas
						case 5:
	                        System.out.println("Mostrando historial de ventas...");
	                        String queryVentas = "SELECT dv.id_detalle_venta, v.id_venta, c.nombre AS cliente, g.fecha_inicio, g.fecha_fin, dv.cantidad, dv.subtotal "
	                                + "FROM detalle_venta dv "
	                                + "JOIN ventas v ON dv.id_venta = v.id_venta "
	                                + "JOIN garantia g ON g.id_venta = v.id_venta "
	                                + "JOIN clientes c ON v.dni = c.dni";
	                        try (Statement stmt = c.createStatement();
	                             var rs = stmt.executeQuery(queryVentas)) {
	                            while (rs.next()) {
	                                System.out.println("ID Detalle Venta: " + rs.getInt("id_detalle_venta"));
	                                System.out.println("ID Venta: " + rs.getInt("id_venta"));
	                                System.out.println("Cliente: " + rs.getString("cliente"));
	                                System.out.println("Garantía Inicio: " + rs.getDate("fecha_inicio"));
	                                System.out.println("Garantía Fin: " + rs.getDate("fecha_fin"));
	                                System.out.println("Cantidad: " + rs.getInt("cantidad"));
	                                System.out.println("Subtotal: " + rs.getDouble("subtotal"));
	                                System.out.println("-----------------------------");
	                            }
	                        }
	                        break;
						// Estadísticas
						case 6:
	                        System.out.println("Mostrando estadísticas...");
	                        String queryStats = "SELECT "
	                                + "COUNT(*) AS total_productos, "
	                                + "MAX(precio) AS producto_mas_caro, "
	                                + "MIN(precio) AS producto_mas_barato, "
	                                + "SUM(precio * stock) AS valor_total_inventario "
	                                + "FROM productos";
	                        try (Statement stmt = c.createStatement();
	                             var rs = stmt.executeQuery(queryStats)) {
	                            if (rs.next()) {
	                                System.out.println("Total de productos: " + rs.getInt("total_productos"));
	                                System.out.println("Producto más caro: " + rs.getDouble("producto_mas_caro"));
	                                System.out.println("Producto más barato: " + rs.getDouble("producto_mas_barato"));
	                                System.out.println("Valor total del inventario: " + rs.getDouble("valor_total_inventario"));
	                            }
	                        }
	                        break;
						// Salir
						case 7:
							System.out.println("Saliendo del gestor de FrikiHouse...");
							break;
						default:
							System.err.println("Opción no válida.");
							break;
						}
					} while (gestion != 7);
					break;
				// Salir
				case 3:
					System.out.println("Saliendo de FrikiHouse...");
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

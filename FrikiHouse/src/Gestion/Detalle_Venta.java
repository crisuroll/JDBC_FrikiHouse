package Gestion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import FrikiHouse.ConexionBBDD;

/**
 * Clase Detalle_Venta. Contiene todos los métodos para hacer operaciones CRUD en dicha tabla.
 */
public class Detalle_Venta {
    
	/**
	 * Método crearTabla(). Crea la tabla en la BBDD.
	 */
    public static void crearTabla() {
        String query = "CREATE TABLE IF NOT EXISTS detalle_venta ("
                + "id_detalle_venta INT NOT NULL AUTO_INCREMENT, "
                + "id_venta INT NOT NULL, "
                + "id_producto INT NOT NULL, "
                + "cantidad INT NOT NULL, "
                + "subtotal DECIMAL(10,2) NOT NULL, "
                + "PRIMARY KEY (id_detalle_venta), "
                + "CONSTRAINT fk_detalleventa_ventas FOREIGN KEY (id_venta) REFERENCES Ventas(id_venta), "
                + "CONSTRAINT fk_detalleventa_productos FOREIGN KEY (id_producto) REFERENCES Productos(id_producto) "
                + ");";
        
        try (Connection c = ConexionBBDD.getConnection();
                Statement s = c.createStatement()) {
            s.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
    
    /**
	 * Método eliminarTabla(). Elimina la tabla de la BBDD.
	 */
    public static void eliminarTabla() {
        String query = "DROP TABLE IF EXISTS detalle_venta";
        try (Connection c = ConexionBBDD.getConnection();
                Statement s = c.createStatement()) {
            s.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
    
    /**
	 * Método mostrarTabla(). Muestra la tabla completa.
	 */
    public static void mostrarTabla() {
        String query = "SELECT * FROM detalle_venta";
        try {
            Connection c = ConexionBBDD.getConnection();
            Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = s.executeQuery(query);
            
            while (rs.next()) {
                System.out.println("ID Detalle Venta: " + rs.getString("id_detalle_venta"));
                System.out.println("ID Venta: " + rs.getString("id_venta"));
                System.out.println("ID Producto: " + rs.getString("id_producto"));
                System.out.println("Cantidad: " + rs.getString("cantidad"));
                System.out.println("Subtotal: " + rs.getString("subtotal"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
    
    /**
	 * Método insertarValor(). Inserta una nueva fila a la tabla.
	 * @param id_venta
	 * @param id_producto
	 * @param cantidad
	 * @param subtotal
	 */
    public static void insertarValor(String id_venta, String id_producto, String cantidad, String subtotal) {
        String query = "INSERT INTO detalle_venta (id_venta, id_producto, cantidad, subtotal) VALUES (" 
                + id_venta + ", " + id_producto + ", " + cantidad + ", " + subtotal + ")";
        try {
            Connection c = ConexionBBDD.getConnection();
            Statement s = c.createStatement();
            s.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

}

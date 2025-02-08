package Gestion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import FrikiHouse.ConexionBBDD;

/**
 * Clase Detalle_Pedido. Contiene todos los métodos para hacer operaciones CRUD en dicha tabla.
 */
public class Detalle_Pedido {
    
	/**
	 * Método crearTabla(). Crea la tabla en la BBDD.
	 */
    public static void crearTabla() {
        String query = "CREATE TABLE IF NOT EXISTS detalle_pedido ("
                + "id_detalle_pedido INT NOT NULL AUTO_INCREMENT, "
                + "id_pedido INT NOT NULL, "
                + "id_producto INT NOT NULL, "
                + "cantidad INT NOT NULL, "
                + "PRIMARY KEY (id_detalle_pedido), "
                + "CONSTRAINT fk_detallepedido_pedidos FOREIGN KEY (id_pedido) REFERENCES Pedidos(id_pedido), "
                + "CONSTRAINT fk_detallepedido_productos FOREIGN KEY (id_producto) REFERENCES Productos(id_producto) "
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
        String query = "DROP TABLE IF EXISTS detalle_pedido";
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
        String query = "SELECT * FROM detalle_pedido";
        try {
            Connection c = ConexionBBDD.getConnection();
            Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = s.executeQuery(query);
            
            while (rs.next()) {
                System.out.println("ID Detalle Pedido: " + rs.getString("id_detalle_pedido"));
                System.out.println("ID Pedido: " + rs.getString("id_pedido"));
                System.out.println("ID Producto: " + rs.getString("id_producto"));
                System.out.println("Cantidad: " + rs.getString("cantidad"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
    
    /**
	 * Método insertarValor(). Inserta una nueva fila a la tabla.
	 * @param id_pedido
	 * @param id_producto
	 * @param cantidad
	 */
    public static void insertarValor(String id_pedido, String id_producto, String cantidad) {
        String query = "INSERT INTO detalle_pedido (id_pedido, id_producto, cantidad) VALUES (" 
                + id_pedido + ", " + id_producto + ", " + cantidad + ")";
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
    
    /**
     * Metodo actualizarValor(). Actualiza el parametro indicado al valor indicado.
     * @param id_detalle_pedido
     * @param n
     * @param valor
     */
    public static void actualizarValor(String id_detalle_pedido, int n, String valor) {
        String campo = "";
        if (n == 1) {
            campo = "cantidad";
        } else if (n == 2) {
            campo = "subtotal";
        } else {
            System.out.println("Campo no encontrado");
            return;
        }
        
        String query = "UPDATE detalle_pedido SET " + campo + " = " + valor + " WHERE id_detalle_pedido = " + id_detalle_pedido;
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
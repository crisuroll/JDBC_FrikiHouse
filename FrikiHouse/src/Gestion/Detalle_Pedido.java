package Gestion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import FrikiHouse.ConexionBBDD;

public class Detalle_Pedido {
    
    public static void crearTabla() {
        String query = "CREATE TABLE IF NOT EXISTS detalle_pedido ("
                + "id_detalle_pedido INT NOT NULL AUTO_INCREMENT, "
                + "id_pedido INT NOT NULL, "
                + "id_producto INT NOT NULL, "
                + "cantidad INT NOT NULL, "
                + "subtotal DECIMAL(10,2) NOT NULL, "
                + "PRIMARY KEY (id_detalle_pedido), "
                + "CONSTRAINT fk_detallepedido_pedidos FOREIGN KEY (id_pedido) REFERENCES Pedidos(id_pedido), "
                + "CONSTRAINT fk_detallepedido_productos FOREIGN KEY (id_producto) REFERENCES Productos(id_producto) "
                + ");";
        
        try (Connection c = ConexionBBDD.getConnection();
                Statement s = c.createStatement()) {
            s.execute(query);
            System.out.println("Tabla creada correctamente.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
    
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
                System.out.println("Subtotal: " + rs.getString("subtotal"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
    
    public static void insertarValor(String id_pedido, String id_producto, String cantidad, String subtotal) {
        String query = "INSERT INTO detalle_pedido (id_pedido, id_producto, cantidad, subtotal) VALUES (" 
                + id_pedido + ", " + id_producto + ", " + cantidad + ", " + subtotal + ")";
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
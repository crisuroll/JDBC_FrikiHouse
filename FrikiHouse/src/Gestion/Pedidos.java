package Gestion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import FrikiHouse.ConexionBBDD;

public class Pedidos {
    
    public static void crearTabla() {
        String query = "CREATE TABLE IF NOT EXISTS pedidos ("
                + "id_pedido INT NOT NULL AUTO_INCREMENT, "
                + "id_proveedor INT NOT NULL, "
                + "fecha_pedido DATE NOT NULL, "
                + "cantidad INT NOT NULL, "
                + "subtotal DECIMAL(10,2) NOT NULL, "
                + "PRIMARY KEY (id_pedido), "
                + "CONSTRAINT fk_pedidos_proveedores FOREIGN KEY (id_proveedor) REFERENCES Proveedor(id_proveedor)"
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
        String query = "DROP TABLE IF EXISTS pedidos";
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
        String query = "SELECT * FROM pedidos";
        try {
            Connection c = ConexionBBDD.getConnection();
            Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = s.executeQuery(query);
            
            while (rs.next()) {
                System.out.println("ID Pedido: " + rs.getString("id_pedido"));
                System.out.println("ID Proveedor: " + rs.getString("id_proveedor"));
                System.out.println("Fecha Pedido: " + rs.getString("fecha_pedido"));
                System.out.println("Cantidad: " + rs.getString("cantidad"));
                System.out.println("Subtotal: " + rs.getString("subtotal"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
    
    public static void insertarValor(String id_proveedor, String fecha_pedido, String cantidad, String subtotal) {
        String query = "INSERT INTO pedidos (id_proveedor, fecha_pedido, cantidad, subtotal) VALUES (" 
                + id_proveedor + ", '" + fecha_pedido + "', " + cantidad + ", " + subtotal + ")";
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
    
    public static void actualizarValor(String id_pedido, int n, String valor) {
        String campo = "";
        if (n == 1) {
            campo = "fecha_pedido";
        } else if (n == 2) {
            campo = "cantidad";
        } else if (n == 3) {
            campo = "subtotal";
        } else {
            System.out.println("Campo no encontrado");
            return;
        }
        
        String query = "UPDATE pedidos SET " + campo + " = '" + valor + "' WHERE id_pedido = " + id_pedido;
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
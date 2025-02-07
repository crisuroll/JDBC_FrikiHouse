package Gestion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import FrikiHouse.ConexionBBDD;

public class Ventas {
    
    public static void crearTabla() {
        String query = "CREATE TABLE IF NOT EXISTS ventas ("
                + "id_venta INT NOT NULL AUTO_INCREMENT, "
                + "dni VARCHAR(9) NOT NULL, "
                + "fecha_venta DATE NOT NULL, "
                + "subtotal DECIMAL(10,2) NOT NULL, "
                + "PRIMARY KEY (id_venta), "
                + "CONSTRAINT fk_ventas_clientes FOREIGN KEY (dni) REFERENCES clientes(dni)"
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
        String query = "DROP TABLE IF EXISTS ventas";
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
        String query = "SELECT * FROM ventas";
        try {
            Connection c = ConexionBBDD.getConnection();
            Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = s.executeQuery(query);
            
            while (rs.next()) {
                System.out.println("ID Venta: " + rs.getString("id_venta"));
                System.out.println("DNI: " + rs.getString("dni"));
                System.out.println("Fecha Venta: " + rs.getString("fecha_venta"));
                System.out.println("Subtotal: " + rs.getString("subtotal"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
    
    public static void mostrarValor(String id_venta) {
        String query = "SELECT * FROM ventas WHERE id_venta = " + id_venta;
        try {
            Connection c = ConexionBBDD.getConnection();
            Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = s.executeQuery(query);
            
            if (rs.next()) {
                System.out.println("ID Venta: " + rs.getString("id_venta"));
                System.out.println("DNI: " + rs.getString("dni"));
                System.out.println("Fecha Venta: " + rs.getString("fecha_venta"));
                System.out.println("Subtotal: " + rs.getString("subtotal"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
    
    public static void insertarValor(String dni, String fecha_venta, String subtotal) {
        String query = "INSERT INTO ventas (dni, fecha_venta, subtotal) VALUES ('" + dni + "', '" + fecha_venta + "', " + subtotal + ")";
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

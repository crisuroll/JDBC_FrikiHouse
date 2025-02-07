package Gestion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import FrikiHouse.ConexionBBDD;

public class Proveedores {
    
    public static void crearTabla() {
        String query = "CREATE TABLE IF NOT EXISTS proveedor ("
                + "id_proveedor INT NOT NULL AUTO_INCREMENT, "
                + "nombre VARCHAR(70) NOT NULL, "
                + "contacto VARCHAR(100) NOT NULL, "
                + "PRIMARY KEY (id_proveedor) "
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
    
    public static void eliminarTabla() {
        String query = "DROP TABLE IF EXISTS proveedor";
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
        String query = "SELECT * FROM proveedor";
        try {
            Connection c = ConexionBBDD.getConnection();
            Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = s.executeQuery(query);
            
            while (rs.next()) {
                System.out.println("ID: " + rs.getString("id_proveedor"));
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Contacto: " + rs.getString("contacto"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
    
    public static void insertarValor(String nombre, String contacto) {
        String query = "INSERT INTO proveedor (nombre, contacto) VALUES ('" + nombre + "', '" + contacto + "')";
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
    
    public static void eliminarValores(String id_proveedor) {
        String deleteQuery = "DELETE FROM proveedor WHERE id_proveedor = " + id_proveedor;
        String resetAutoIncrementQuery = "ALTER TABLE proveedor AUTO_INCREMENT = 1"; 
        try (Connection c = ConexionBBDD.getConnection();
             Statement s = c.createStatement()) {
            s.executeUpdate(deleteQuery);
            s.executeUpdate(resetAutoIncrementQuery);

        } catch (SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
    
    public static void actualizarValor(String id_proveedor, int n, String valor) {
        String campo = "";
        if (n == 1) {
            campo = "nombre";
        } else if (n == 2) {
            campo = "contacto";
        } else {
            System.out.println("Campo no encontrado");
            return;
        }
        
        String query = "UPDATE proveedor SET " + campo + " = '" + valor + "' WHERE id_proveedor = " + id_proveedor;
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

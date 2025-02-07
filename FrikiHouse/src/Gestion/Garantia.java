package Gestion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import FrikiHouse.ConexionBBDD;

public class Garantia {
    
    public static void crearTabla() {
        String query = "CREATE TABLE IF NOT EXISTS garantia ("
                + "id_garantia INT NOT NULL AUTO_INCREMENT, "
                + "id_venta INT NOT NULL, "
                + "fecha_inicio DATE NOT NULL, "
                + "fecha_fin DATE NOT NULL, "
                + "PRIMARY KEY (id_garantia), "
                + "CONSTRAINT fk_garantia_ventas FOREIGN KEY (id_venta) REFERENCES Ventas(id_venta)"
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
        String query = "DROP TABLE IF EXISTS garantia";
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
        String query = "SELECT * FROM garantia";
        try {
            Connection c = ConexionBBDD.getConnection();
            Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = s.executeQuery(query);
            
            while (rs.next()) {
                System.out.println("ID Garantía: " + rs.getString("id_garantia"));
                System.out.println("ID Venta: " + rs.getString("id_venta"));
                System.out.println("Fecha Inicio: " + rs.getString("fecha_inicio"));
                System.out.println("Fecha Fin: " + rs.getString("fecha_fin"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
    
    public static void mostrarValor(String id_garantia) {
        String query = "SELECT * FROM garantia WHERE id_garantia = " + id_garantia;
        try {
            Connection c = ConexionBBDD.getConnection();
            Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = s.executeQuery(query);
            
            if (rs.next()) {
                System.out.println("ID Garantía: " + rs.getString("id_garantia"));
                System.out.println("ID Venta: " + rs.getString("id_venta"));
                System.out.println("Fecha Inicio: " + rs.getString("fecha_inicio"));
                System.out.println("Fecha Fin: " + rs.getString("fecha_fin"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
    
    public static void insertarValor(String id_venta, String fecha_inicio, String fecha_fin) {
        String query = "INSERT INTO garantia (id_venta, fecha_inicio, fecha_fin) VALUES "
                + "(" + id_venta + ", '" + fecha_inicio + "', '" + fecha_fin + "')";
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

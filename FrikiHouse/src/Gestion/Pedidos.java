package Gestion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import FrikiHouse.ConexionBBDD;

/**
 * Clase Pedidos. Contiene todos los métodos para hacer operaciones CRUD en dicha tabla.
 */
public class Pedidos {
    
	/**
	 * Método crearTabla(). Crea la tabla en la BBDD.
	 */
    public static void crearTabla() {
        String query = "CREATE TABLE IF NOT EXISTS pedidos ("
                + "id_pedido INT NOT NULL AUTO_INCREMENT, "
                + "id_proveedor INT NOT NULL, "
                + "fecha_pedido DATE NOT NULL, "
                + "cantidad INT NOT NULL, "
                + "PRIMARY KEY (id_pedido), "
                + "CONSTRAINT fk_pedidos_proveedores FOREIGN KEY (id_proveedor) REFERENCES Proveedor(id_proveedor)"
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
    
    /**
	 * Método mostrarTabla(). Muestra la tabla completa.
	 */
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
                System.out.println("-----------------------------\n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
    
    /**
	 * Método insertarValor(). Inserta una nueva fila a la tabla.
	 * @param id_proveedor
	 * @param fecha_pedido
	 * @param cantidad
	 * @return idPedido
	 */
    public static String insertarValor(String id_proveedor, String fecha_pedido, String cantidad) {
    	String idPedido = "";
        String query = "INSERT INTO pedidos (id_proveedor, fecha_pedido, cantidad) VALUES (" 
                + id_proveedor + ", '" + fecha_pedido + "', " + cantidad + ")";
        try {
            Connection c = ConexionBBDD.getConnection();
            Statement s = c.createStatement();
            s.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            
            // Obtener el idPedido generado
            ResultSet rs = s.getGeneratedKeys();
            if (rs.next()) {
                idPedido = rs.getString(1); // Obtener la primera columna (idPedido)
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return idPedido;
    }
    
    /**
     * Metodo actualizarValor(). Actualiza el parametro dado al valor dado.
     * @param id_pedido
     * @param n
     * @param valor
     */
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
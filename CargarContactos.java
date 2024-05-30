package agenda;

import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class CargarContactos {
    public static void cargarContactos(Connection conexion, DefaultTableModel modelo) {
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM contactos");
            while (rs.next()) {
                modelo.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("telefono"),
                        rs.getString("direccion")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

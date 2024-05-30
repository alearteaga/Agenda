package agenda;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AgregarContacto {
    public static void agregarContacto(Connection conexion, JTextField txtNombre, JTextField txtApellidos, JTextField txtTelefono, JTextField txtDireccion, DefaultTableModel modelo) {
        try {
            String query = "INSERT INTO contactos (nombre, apellidos, telefono, direccion) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conexion.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, txtNombre.getText());
            ps.setString(2, txtApellidos.getText());
            ps.setString(3, txtTelefono.getText());
            ps.setString(4, txtDireccion.getText());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                modelo.addRow(new Object[]{rs.getInt(1), txtNombre.getText(), txtApellidos.getText(), txtTelefono.getText(), txtDireccion.getText()});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

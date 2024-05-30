package agenda;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ModificarContacto {
    public static void modificarContacto(Connection conexion, JTextField txtNombre, JTextField txtApellidos, JTextField txtTelefono, JTextField txtDireccion, JTable tabla, DefaultTableModel modelo) {
        int selectedRow = tabla.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un contacto para modificar.");
            return;
        }
        try {
            String query = "UPDATE contactos SET nombre = ?, apellidos = ?, telefono = ?, direccion = ? WHERE id = ?";
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setString(1, txtNombre.getText());
            ps.setString(2, txtApellidos.getText());
            ps.setString(3, txtTelefono.getText());
            ps.setString(4, txtDireccion.getText());
            ps.setInt(5, (int) modelo.getValueAt(selectedRow, 0));
            ps.executeUpdate();
            modelo.setValueAt(txtNombre.getText(), selectedRow, 1);
            modelo.setValueAt(txtApellidos.getText(), selectedRow, 2);
            modelo.setValueAt(txtTelefono.getText(), selectedRow, 3);
            modelo.setValueAt(txtDireccion.getText(), selectedRow, 4);
        } catch (SQLException e) {
        }
    }
}

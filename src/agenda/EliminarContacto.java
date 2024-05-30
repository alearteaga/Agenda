package agenda;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EliminarContacto {
    public static void eliminarContacto(Connection conexion, JTable tabla, DefaultTableModel modelo) {
        int selectedRow = tabla.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un contacto para eliminar.");
            return;
        }
        try {
            String query = "DELETE FROM contactos WHERE id = ?";
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, (int) modelo.getValueAt(selectedRow, 0));
            ps.executeUpdate();
            modelo.removeRow(selectedRow);
        } catch (SQLException e) {
        }
    }
}

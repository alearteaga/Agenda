package agenda;

import javax.swing.*;

public class VerContacto {
    public static void verContacto(JTextField txtNombre, JTextField txtApellidos, JTextField txtTelefono, JTextField txtDireccion, JTable tabla) {
        int selectedRow = tabla.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un contacto para ver.");
            return;
        }
        txtNombre.setText((String) tabla.getValueAt(selectedRow, 1));
        txtApellidos.setText((String) tabla.getValueAt(selectedRow, 2));
        txtTelefono.setText((String) tabla.getValueAt(selectedRow, 3));
        txtDireccion.setText((String) tabla.getValueAt(selectedRow, 4));
    }
}

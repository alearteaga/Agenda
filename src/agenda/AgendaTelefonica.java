package agenda;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AgendaTelefonica {
    private DefaultTableModel modelo;
    private JTable tabla;
    private JTextField txtNombre, txtApellidos, txtTelefono, txtDireccion;

    public AgendaTelefonica() {
        JFrame frame = new JFrame("Agenda Telefónica");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        panel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);

        panel.add(new JLabel("Apellidos:"));
        txtApellidos = new JTextField();
        panel.add(txtApellidos);

        panel.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        panel.add(txtTelefono);

        panel.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        panel.add(txtDireccion);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(e -> AgregarContacto.agregarContacto(getConnection(), txtNombre, txtApellidos, txtTelefono, txtDireccion, modelo));
        panel.add(btnAgregar);

        JButton btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(e -> ModificarContacto.modificarContacto(getConnection(), txtNombre, txtApellidos, txtTelefono, txtDireccion, tabla, modelo));
        panel.add(btnModificar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> EliminarContacto.eliminarContacto(getConnection(), tabla, modelo));
        panel.add(btnEliminar);

        JButton btnVer = new JButton("Ver");
        btnVer.addActionListener(e -> VerContacto.verContacto(txtNombre, txtApellidos, txtTelefono, txtDireccion, tabla));
        panel.add(btnVer);

        frame.add(panel, BorderLayout.NORTH);

        modelo = new DefaultTableModel(new String[]{"ID", "Nombre", "Apellidos", "Teléfono", "Dirección"}, 0);
        tabla = new JTable(modelo);
        frame.add(new JScrollPane(tabla), BorderLayout.CENTER);

        frame.setVisible(true);

        CargarContactos.cargarContactos(getConnection(), modelo);
    }

    private Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/Agenda", "root", "My7Pass@Word_9_8A_zE");
        } catch (SQLException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AgendaTelefonica());
    }
}

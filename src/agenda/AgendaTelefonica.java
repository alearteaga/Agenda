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
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Padding

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField(20);
        panel.add(txtNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Apellidos:"), gbc);
        gbc.gridx = 1;
        txtApellidos = new JTextField(20);
        panel.add(txtApellidos, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Teléfono:"), gbc);
        gbc.gridx = 1;
        txtTelefono = new JTextField(20);
        panel.add(txtTelefono, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Dirección:"), gbc);
        gbc.gridx = 1;
        txtDireccion = new JTextField(20);
        panel.add(txtDireccion, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(e -> AgregarContacto.agregarContacto(getConnection(), txtNombre, txtApellidos, txtTelefono, txtDireccion, modelo));
        buttonPanel.add(btnAgregar);

        JButton btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(e -> ModificarContacto.modificarContacto(getConnection(), txtNombre, txtApellidos, txtTelefono, txtDireccion, tabla, modelo));
        buttonPanel.add(btnModificar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> EliminarContacto.eliminarContacto(getConnection(), tabla, modelo));
        buttonPanel.add(btnEliminar);

        JButton btnVer = new JButton("Ver");
        btnVer.addActionListener(e -> VerContacto.verContacto(txtNombre, txtApellidos, txtTelefono, txtDireccion, tabla));
        buttonPanel.add(btnVer);

        panel.add(buttonPanel, gbc);

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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;
import conexion.SQLConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import clases.Clientes;
import java.util.LinkedList;
import estructuras.HashClientes;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.sql.ResultSet;

/**
 *
 * @author ASUS
 */
public class Cliente extends javax.swing.JPanel {
    private LinkedList<Clientes> listaClientes;
    private HashClientes tablaClientes;

    /**
     * Creates new form Proveedores
     */
    public Cliente() {
        initComponents();
        
        tablaClientes = new HashClientes();
        tablaClientes.cargarClientesDesdeBD();
        mostrarClientesEnTabla();
        
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarCliente(evt);
            }
            
        });

        // Agregar un listener de selección a la tabla para manejar la fila seleccionada
        jTable2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
                manejarSeleccion(evt);
            }
        });
        
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarCliente();
            }
        });
        
        jButton4.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton4ActionPerformed(evt);
           }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
             public void mousePressed(java.awt.event.MouseEvent evt) {
            limpiarCampos();
         
            }
        });
    }
    private void manejarSeleccion(ListSelectionEvent evt) {
    int selectedRow = jTable2.getSelectedRow();
    
    if (selectedRow >= 0) {
        String dniSeleccionado = jTable2.getValueAt(selectedRow, 0).toString();
        String nombre = jTable2.getValueAt(selectedRow, 1).toString();
        String apellido = jTable2.getValueAt(selectedRow, 2).toString();
        String sexo = jTable2.getValueAt(selectedRow, 3).toString();
        String telefono = jTable2.getValueAt(selectedRow, 4).toString();
        String ciudad = jTable2.getValueAt(selectedRow, 5).toString();
        String email = jTable2.getValueAt(selectedRow, 6).toString();
        
        jTextField1.setText(dniSeleccionado);
        jTextField2.setText(nombre);
        jTextField3.setText(apellido);
        jComboBox1.setSelectedItem(sexo);
        jTextField4.setText(telefono);
        jTextField5.setText(ciudad);
        jTextField6.setText(email);

        jTextField1.setEnabled(false);
    }
}
public void mostrarClientesEnTabla() {
    SQLConexion conexion = SQLConexion.getConexion();
    Connection conn = conexion.conectar();

    String sql = "SELECT dni, nombre, apellido, sexo, telefono, ciudad, email FROM clientes";
    DefaultTableModel modelo = (DefaultTableModel) jTable2.getModel(); // Obtener el modelo de la tabla

    modelo.setRowCount(0);
    jTable2.setDefaultEditor(Object.class, null);

    try {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        java.sql.ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            String dni = rs.getString("dni");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String sexo = rs.getString("sexo");
            String telefono = rs.getString("telefono");
            String ciudad = rs.getString("ciudad");
            String email = rs.getString("email");

            modelo.addRow(new Object[]{dni, nombre, apellido, sexo, telefono, ciudad, email});
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

public void agregarCliente(Clientes cliente) {
    if (cliente.getDni().length() != 9) {
        JOptionPane.showMessageDialog(null, "El DNI debe tener exactamente 9 caracteres.");
        return; 
    }

    if (cliente.getNombre().isEmpty() || cliente.getApellido().isEmpty() || cliente.getTelefono().isEmpty() ||
        cliente.getCiudad().isEmpty() || cliente.getEmail().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
        return; 
    }

    if (dniYaExiste(cliente.getDni())) {
        JOptionPane.showMessageDialog(null, "Ya existe un cliente con ese DNI.");
        return; 
    }

    tablaClientes.agregarCliente(cliente);
    tablaClientes.imprimirClientes();

    SQLConexion conexion = SQLConexion.getConexion();
    Connection conn = conexion.conectar();

    String sql = "INSERT INTO clientes (dni, nombre, apellido, sexo, telefono, ciudad, email) VALUES (?, ?, ?, ?, ?, ?, ?)";

    try {
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, cliente.getDni());
        pstmt.setString(2, cliente.getNombre());
        pstmt.setString(3, cliente.getApellido());
        pstmt.setString(4, cliente.getSexo());
        pstmt.setString(5, cliente.getTelefono());
        pstmt.setString(6, cliente.getCiudad());
        pstmt.setString(7, cliente.getEmail());

        int filasAfectadas = pstmt.executeUpdate();

        if (filasAfectadas > 0) {
            JOptionPane.showMessageDialog(null, "Cliente agregado exitosamente.");
            mostrarClientesEnTabla();
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo agregar el cliente.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al agregar el cliente.");
    } finally {
        try {
            // Cerramos la conexión
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

private boolean dniYaExiste(String dni) {
    SQLConexion conexion = SQLConexion.getConexion();
    Connection conn = conexion.conectar();
    String sql = "SELECT 1 FROM clientes WHERE dni = ?";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, dni);
        ResultSet rs = pstmt.executeQuery();

        return rs.next();
    } catch (SQLException e) {
        e.printStackTrace();
        return false; 
    } finally {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
private void eliminarCliente(java.awt.event.ActionEvent evt) {
    int selectedRow = jTable2.getSelectedRow();
    
    if (selectedRow >= 0) {
        String dniCliente = jTable2.getValueAt(selectedRow, 0).toString();
        
        SQLConexion conexion = SQLConexion.getConexion();
        Connection conn = conexion.conectar();
        
        String sql = "DELETE FROM clientes WHERE dni = ?";
        
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dniCliente);
            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Cliente eliminado exitosamente.");
                
                tablaClientes.eliminarCliente(dniCliente);

                mostrarClientesEnTabla();
            } else {
                System.out.println("No se pudo eliminar el cliente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    } else {
        System.out.println("Por favor seleccione un cliente.");
    }
}
     

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(650, 420));

        jLabel1.setText("DNI:");

        jLabel2.setText("Nombre");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Apellido");

        jLabel4.setText("Sexo");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Masculino", "Femenino", "No Binario" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Telefono");

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jLabel6.setText("Ciudad");

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jLabel7.setText("Email");

        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "dni", "nombre", "Apellido", "Sexo", "Telefono", "Ciudad", "Email"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jButton2.setText("Eliminar");

        jButton3.setText("Añadir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Actualizar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Buscar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton1.setText("Consultar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextField3))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextField5))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel7))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField6)
                                        .addComponent(jTextField4)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jButton3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jButton4)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addGap(55, 55, 55))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton4)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5)
                            .addComponent(jButton2))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Registro", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String dni = jTextField1.getText();
        String nombre = jTextField2.getText();
        String apellido = jTextField3.getText();
        String sexo = (String) jComboBox1.getSelectedItem();
        String telefono = jTextField4.getText();
        String ciudad = jTextField5.getText();
        String email = jTextField6.getText();

        // Creamos un objeto Cliente con los datos obtenidos
        Clientes cliente = new Clientes(dni, nombre, apellido, sexo, telefono, ciudad, email);

        // Llamamos al método para agregar el cliente
        agregarCliente(cliente);

        // Limpiar los campos después de agregar el cliente
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jComboBox1.setSelectedIndex(0);
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        buscarCliente(); 
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
         String dni = jTextField1.getText().trim();
    String nombre = jTextField2.getText().trim();
    String apellido = jTextField3.getText().trim();
    String sexo = (String) jComboBox1.getSelectedItem();
    String telefono = jTextField4.getText().trim();
    String ciudad = jTextField5.getText().trim();
    String email = jTextField6.getText().trim();

    if (dni.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || sexo.equals("Seleccionar") ||
        telefono.isEmpty() || ciudad.isEmpty() || email.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    SQLConexion conexion = SQLConexion.getConexion();
    Connection conn = conexion.conectar();
    String sql = "UPDATE clientes SET nombre = ?, apellido = ?, sexo = ?, telefono = ?, ciudad = ?, email = ? WHERE dni = ?";

    try {
        PreparedStatement pstmt = conn.prepareStatement(sql);

       
        pstmt.setString(1, nombre);
        pstmt.setString(2, apellido);
        pstmt.setString(3, sexo);
        pstmt.setString(4, telefono);
        pstmt.setString(5, ciudad);
        pstmt.setString(6, email);
        pstmt.setString(7, dni);

        int filasAfectadas = pstmt.executeUpdate();

        if (filasAfectadas > 0) {
            JOptionPane.showMessageDialog(this, "Cliente actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            mostrarClientesEnTabla();

            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");
            jComboBox1.setSelectedIndex(0);
            jTextField4.setText("");
            jTextField5.setText("");
            jTextField6.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo actualizar el cliente.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed
    private void buscarCliente() {
    String dni = jTextField1.getText().trim();  
    String nombre = jTextField2.getText().trim(); 
    String apellido = jTextField3.getText().trim();  

    DefaultTableModel modelo = (DefaultTableModel) jTable2.getModel();
    modelo.setRowCount(0);  

    Clientes clienteEncontrado = tablaClientes.buscarCliente(dni, nombre, apellido);

    if (clienteEncontrado != null) {
        modelo.addRow(new Object[]{
            clienteEncontrado.getDni(),
            clienteEncontrado.getNombre(),
            clienteEncontrado.getApellido(),
            clienteEncontrado.getSexo(),
            clienteEncontrado.getTelefono(),
            clienteEncontrado.getCiudad(),
            clienteEncontrado.getEmail()
        });
        JOptionPane.showMessageDialog(this, "Clienteencontrado", "Resultado de búsqueda", JOptionPane.INFORMATION_MESSAGE);
        mostrarClientesEnTabla();

    } else {
        // Si no se encontró el cliente, mostramos un mensaje
        JOptionPane.showMessageDialog(this, "Cliente no encontrado", "Resultado de búsqueda", JOptionPane.INFORMATION_MESSAGE);
        mostrarClientesEnTabla();
    }
}

private void limpiarCampos() {
    jTextField1.setText("");
    jTextField2.setText("");
    jTextField3.setText("");
    jComboBox1.setSelectedIndex(0);
    jTextField4.setText("");
    jTextField5.setText("");
    jTextField6.setText("");

    jTextField1.setEnabled(true);
    jTable2.clearSelection();
    
    jTable2.clearSelection();
}

 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}

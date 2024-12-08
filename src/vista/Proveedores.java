/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

import clases.Nodo;
import clases.listaEnlazada;
import clases.modelo.proveedorDao;
import clases.proveedor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Proveedores extends javax.swing.JPanel {

    /**
     * cracion de objetos
     */
    proveedor pro = new proveedor();
    proveedorDao proDao = new proveedorDao();
    
    DefaultTableModel modelo = new DefaultTableModel();
    
    public Proveedores() {
        initComponents();
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelCompleto = new javax.swing.JTabbedPane();
        PanelRegistroPro = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCodigoPro = new javax.swing.JTextField();
        txtNombrePro = new javax.swing.JTextField();
        txtUbicacionPro = new javax.swing.JTextField();
        txtTelefonoPro = new javax.swing.JTextField();
        txtCorreoPro = new javax.swing.JTextField();
        btnAgregarPro = new javax.swing.JButton();
        btnNuevoPro = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        PanelGestionPro = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaProveedor = new javax.swing.JTable();
        btnActualizarPro = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        btnBuscarPro = new javax.swing.JButton();
        jTextField6 = new javax.swing.JTextField();
        btnEliminarPro = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(650, 420));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Registro de Proveedores");

        jLabel2.setText("Codigo : ");

        jLabel3.setText("Nombre : ");

        jLabel4.setText("Ubicacion : ");

        jLabel5.setText("Telefono : ");

        jLabel6.setText("Correo : ");

        txtNombrePro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreProActionPerformed(evt);
            }
        });

        btnAgregarPro.setText("Agregar");
        btnAgregarPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProActionPerformed(evt);
            }
        });

        btnNuevoPro.setText("Lista");
        btnNuevoPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProActionPerformed(evt);
            }
        });

        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo.png"))); // NOI18N
        jLabel9.setText("jLabel9");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 246, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout PanelRegistroProLayout = new javax.swing.GroupLayout(PanelRegistroPro);
        PanelRegistroPro.setLayout(PanelRegistroProLayout);
        PanelRegistroProLayout.setHorizontalGroup(
            PanelRegistroProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelRegistroProLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(PanelRegistroProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelRegistroProLayout.createSequentialGroup()
                        .addGroup(PanelRegistroProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(PanelRegistroProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCorreoPro, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigoPro, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PanelRegistroProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(PanelRegistroProLayout.createSequentialGroup()
                                    .addComponent(txtTelefonoPro, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(121, 121, 121))
                                .addGroup(PanelRegistroProLayout.createSequentialGroup()
                                    .addGroup(PanelRegistroProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtNombrePro, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtUbicacionPro, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(PanelRegistroProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnNuevoPro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnAgregarPro, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))))))
                    .addGroup(PanelRegistroProLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(121, 121, 121)))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        PanelRegistroProLayout.setVerticalGroup(
            PanelRegistroProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelRegistroProLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(PanelRegistroProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelRegistroProLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel1)
                        .addGap(39, 39, 39)
                        .addGroup(PanelRegistroProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtCodigoPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(PanelRegistroProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelRegistroProLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(PanelRegistroProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtNombrePro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAgregarPro))
                                .addGap(18, 18, 18)
                                .addGroup(PanelRegistroProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtUbicacionPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelRegistroProLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNuevoPro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(PanelRegistroProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtTelefonoPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(PanelRegistroProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtCorreoPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        PanelCompleto.addTab("Registro", PanelRegistroPro);

        TablaProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod. proveedor", "Nombre", "Ubicacion", "Telefono", "Correo"
            }
        ));
        jScrollPane1.setViewportView(TablaProveedor);

        btnActualizarPro.setText("Actualizar");
        btnActualizarPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarProActionPerformed(evt);
            }
        });

        jLabel7.setText("Gestion de proveedores");

        jLabel8.setText("Seleccion :  ");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-seleccion-", "Codigo", "Nombre", "Telefono" }));

        btnBuscarPro.setText("Buscar");

        btnEliminarPro.setText("Eliminar ");
        btnEliminarPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProActionPerformed(evt);
            }
        });

        jButton1.setText("Regresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelGestionProLayout = new javax.swing.GroupLayout(PanelGestionPro);
        PanelGestionPro.setLayout(PanelGestionProLayout);
        PanelGestionProLayout.setHorizontalGroup(
            PanelGestionProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelGestionProLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(PanelGestionProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelGestionProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField6, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(PanelGestionProLayout.createSequentialGroup()
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel7))
                    .addGroup(PanelGestionProLayout.createSequentialGroup()
                        .addComponent(btnBuscarPro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnActualizarPro))
                    .addGroup(PanelGestionProLayout.createSequentialGroup()
                        .addComponent(btnEliminarPro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)))
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addGap(23, 23, 23))
        );
        PanelGestionProLayout.setVerticalGroup(
            PanelGestionProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelGestionProLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel7)
                .addGroup(PanelGestionProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelGestionProLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(PanelGestionProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(PanelGestionProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBuscarPro)
                            .addComponent(btnActualizarPro))
                        .addGap(37, 37, 37)
                        .addGroup(PanelGestionProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEliminarPro)
                            .addComponent(jButton1))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelGestionProLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52))))
        );

        PanelCompleto.addTab("Lista", PanelGestionPro);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelCompleto)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelCompleto)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreProActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreProActionPerformed
    
   
    public void listarProveedor(){
        listaEnlazada listaenlazada = (listaEnlazada) proDao.listarProveedor();
        modelo = (DefaultTableModel) TablaProveedor.getModel();
        modelo.setRowCount(0);
        
        Nodo actual = listaenlazada.getCabeza();
        while (actual != null) {
            if(actual.info != null){
                Object[] ob = new Object[5];
            ob[0] = actual.info.getId_proveedor();
            ob[1] = actual.info.getNombre();
            ob[2] = actual.info.getUbicacion();
            ob[3] = actual.info.getTelefono();
            ob[4] = actual.info.getCorreo();
            modelo.addRow(ob);  
                
            }
        actual = actual.siguiente; // Avanza al siguiente nodo
        }
        
        TablaProveedor.setModel(modelo);
    }
    
    //Metodo para limpiar
    public void limpiarTabla(){
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }
    
    /**Boton para agregar los proveedores (Daniel)
     */
    private void btnAgregarProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProActionPerformed
        if (!"".equals(txtCodigoPro.getText()) || 
            !"".equals(txtNombrePro.getText()) ||
            !"".equals(txtUbicacionPro.getText()) ||
            !"".equals(txtTelefonoPro.getText()) ||
            !"".equals(txtCorreoPro.getText())   ){
           
            pro.setId_proveedor(Integer.parseInt(txtCodigoPro.getText()));
            pro.setNombre(txtNombrePro.getText());
            pro.setUbicacion(txtUbicacionPro.getText());
            pro.setTelefono(Integer.parseInt(txtTelefonoPro.getText()));
            pro.setCorreo(txtCorreoPro.getText());
            
            limpiarTabla();
            limpiarProveedor();
            listarProveedor();
            
            boolean registroPro = proDao.registrarProveedor(pro);
            JOptionPane.showMessageDialog(null, "Proveedor registrado");
            
        }else{
            JOptionPane.showMessageDialog(null,"Rellene los campos");
        }
    }//GEN-LAST:event_btnAgregarProActionPerformed

    //boton eliminar proveedor
    private void btnEliminarProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProActionPerformed
        // Verificar si hay una fila seleccionada
        int filaSeleccionada = TablaProveedor.getSelectedRow();
        if (filaSeleccionada >= 0) {
        // Obtener el ID del proveedor desde la tabla
        int idProveedor = (int) TablaProveedor.getValueAt(filaSeleccionada, 0); // Asegúrate de que el ID esté en la columna 0
        
        // Confirmar la eliminación
        int confirmacion = JOptionPane.showConfirmDialog(null, 
                "¿Está seguro de eliminar este proveedor?");
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            // Eliminar el proveedor de la lista enlazada
            boolean eliminado = proDao.eliminarProveedor(idProveedor);
            
            if (eliminado) {
                // Eliminar la fila de la tabla
                DefaultTableModel modelo = (DefaultTableModel) TablaProveedor.getModel();
                modelo.removeRow(filaSeleccionada);
                JOptionPane.showMessageDialog(this, "Proveedor eliminado.");
            } else {
                
            }
        }
    } else {
        // Si no hay una fila seleccionada
        JOptionPane.showMessageDialog(this, "seleccione campo para eliminar");
    }
        listarProveedor();
    }//GEN-LAST:event_btnEliminarProActionPerformed
    
    //boton que me lleve a la tabla donde aparece todo 
    private void btnNuevoProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProActionPerformed
        
        PanelCompleto.setSelectedIndex(1);  
    }//GEN-LAST:event_btnNuevoProActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       //boton para que regrese
       PanelCompleto.setSelectedIndex(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnActualizarProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarProActionPerformed
        // BOTON PARA ACTUALIZAR LA TABLA 
        limpiarTabla();
        listarProveedor();
    }//GEN-LAST:event_btnActualizarProActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane PanelCompleto;
    private javax.swing.JPanel PanelGestionPro;
    private javax.swing.JPanel PanelRegistroPro;
    private javax.swing.JTable TablaProveedor;
    private javax.swing.JButton btnActualizarPro;
    private javax.swing.JButton btnAgregarPro;
    private javax.swing.JButton btnBuscarPro;
    private javax.swing.JButton btnEliminarPro;
    private javax.swing.JButton btnNuevoPro;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField txtCodigoPro;
    private javax.swing.JTextField txtCorreoPro;
    private javax.swing.JTextField txtNombrePro;
    private javax.swing.JTextField txtTelefonoPro;
    private javax.swing.JTextField txtUbicacionPro;
    // End of variables declaration//GEN-END:variables
    
    private void limpiarProveedor() {
        txtCodigoPro.setText("");
        txtNombrePro.setText("");
        txtUbicacionPro.setText("");
        txtTelefonoPro.setText("");
        txtCorreoPro.setText("");
    }
}

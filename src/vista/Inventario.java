/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

import clases.Productos;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class Inventario extends javax.swing.JPanel {

    List<Productos> lista = new ArrayList<>();
    
    public Inventario() {
        initComponents();
        crear();
        cargar();
        deshabilitar();
    }

    void crear (){
        lista.add(new Productos(1, "Martillo", 25));
        lista.add(new Productos(2, "Taladro", 50));
        botones.add(rbtnIngreso);
        botones.add(rbtnSalida);
    }
    void cargar() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"Código", "Producto", "Stock"}, 0);
        lista.forEach(p -> model.addRow(new Object[]{p.getId(), p.getNombre(), p.getStock()}));
        Tabla.setModel(model);
    }
    void limpiar(){
        txtCantidad.setText("");
        txtCodigo.setText("");
        txtID.setText("");
    }
    void deshabilitar(){
        List<JTextField> textFields = Arrays.asList(txtID, txtCantidad, txtCodigo);
        textFields.forEach(field -> field.setEnabled(false));
        btnEliminar.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnEditar.setEnabled(false);
        rbtnIngreso.setEnabled(false);
        rbtnSalida.setEnabled(false);
        cbxMotivo.setEnabled(false);
        Fecha.setEnabled(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botones = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        rbtnIngreso = new javax.swing.JRadioButton();
        rbtnSalida = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        txtID = new javax.swing.JTextField();
        cbxMotivo = new javax.swing.JComboBox<>();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        Fecha = new com.toedter.calendar.JDateChooser();

        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Producto", "Cantidad"
            }
        ));
        jScrollPane1.setViewportView(Tabla);

        rbtnIngreso.setText("Ingreso");

        rbtnSalida.setText("Salida");

        jLabel1.setText("Motivo");

        jLabel2.setText("Codigo");

        jLabel3.setText("Cantidad");

        jLabel4.setText("IDMovimiento");

        jLabel5.setText("Fecha");

        cbxMotivo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Seleccione--", "Venta", "Compra", "Devolucion", "Donacion" }));

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1)
                            .addComponent(rbtnIngreso))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbtnSalida)
                            .addComponent(cbxMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                                .addComponent(txtCantidad, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtCodigo, javax.swing.GroupLayout.Alignment.LEADING))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevo)
                    .addComponent(btnEditar)
                    .addComponent(btnGuardar)
                    .addComponent(btnEliminar))
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnIngreso)
                    .addComponent(rbtnSalida)
                    .addComponent(btnNuevo))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(cbxMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditar))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGuardar))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addComponent(jLabel5))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(btnEliminar)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(118, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        List<JTextField> textFields = Arrays.asList(txtID, txtCantidad, txtCodigo);
        textFields.forEach(field -> field.setEnabled(true));
        //btnEliminar.setEnabled(true);
        //btnGuardar.setEnabled(true);
        btnEditar.setEnabled(true);
        rbtnIngreso.setEnabled(true);
        rbtnSalida.setEnabled(true);
        cbxMotivo.setEnabled(true);
        Fecha.setEnabled(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        try {
        
            if (!rbtnIngreso.isSelected() && !rbtnSalida.isSelected()) {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona un motivo: Ingreso o Salida.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int index = Tabla.getSelectedRow();
            if (index < 0) {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona un producto en la tabla para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
            btnEliminar.setEnabled(rbtnSalida.isSelected());
            btnGuardar.setEnabled(rbtnIngreso.isSelected()|| rbtnSalida.isSelected());
            int id = (int) Tabla.getValueAt(index, 0);
            Productos producto = lista.stream()
                                     .filter(p -> p.getId() == id)
                                     .findFirst()
                                     .orElse(null);
            if (producto != null) {
                txtCodigo.setText(String.valueOf(producto.getId()));
                //txtCantidad.setText(String.valueOf(producto.getStock()));
                txtCodigo.setEditable(false);
            } else {
                JOptionPane.showMessageDialog(this, "El producto seleccionado no se encuentra en la lista.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (ClassCastException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener los datos de la tabla. Verifica que los valores sean correctos.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } 
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
        
            int id = Integer.parseInt(txtCodigo.getText());
            Productos producto = lista.stream()
                                     .filter(p -> p.getId() == id)
                                     .findFirst()
                                     .orElse(null);

            if (producto == null) {
                JOptionPane.showMessageDialog(this, "El producto con el código especificado no existe.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int cantidad = Integer.parseInt(txtCantidad.getText());
            if (rbtnIngreso.isSelected()) {
                producto.setStock(producto.getStock() + cantidad); 
            } else if (rbtnSalida.isSelected()) {
                if (producto.getStock() < cantidad) {
                    JOptionPane.showMessageDialog(this, "No hay suficiente stock para realizar la salida.", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                producto.setStock(producto.getStock() - cantidad);
            }
            JOptionPane.showMessageDialog(this, "Producto actualizado correctamente.", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
            cargar();
            limpiar();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El valor ingresado no es válido. Por favor, verifica el código y la cantidad.", "Error", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ha ocurrido un error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser Fecha;
    private javax.swing.JTable Tabla;
    private javax.swing.ButtonGroup botones;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> cbxMotivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbtnIngreso;
    private javax.swing.JRadioButton rbtnSalida;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtID;
    // End of variables declaration//GEN-END:variables
}

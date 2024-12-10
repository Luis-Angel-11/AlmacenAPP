/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

import clases.Cola;
import clases.Inventarios;
import clases.Nodo1;
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
    List<Inventarios> original = new ArrayList<>();
    Cola<Inventarios> movimiento = new Cola<>(100);
    public Inventario() {
        initComponents();
        crear();
        cargar();
        deshabilitar();
        cargarMovimientos();
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
    
    void cargarMovimientos(){
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Producto", "Cantidad", "Motivo"}, 0);
        Nodo1<Inventarios> actual = movimiento.getInicio();
        while (actual != null) {
            Inventarios inventario = actual.getClase();
            model.addRow(new Object[]{
                inventario.getId(),
                inventario.getProducto(),
                inventario.getCantidad(),
                inventario.getMotivo()
            });
            actual = actual.getSiguiente(); // Pasar al siguiente nodo
        }
        TablaMovimientos.setModel(model);
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
        //Fecha.setEnabled(false);
    }
    void ordenamientoshell(){
        List<Inventarios> listaMovimientos = new ArrayList<>();
        Nodo1<Inventarios> actual = movimiento.getInicio();
        while (actual != null) {
            listaMovimientos.add(actual.getClase());
            actual = actual.getSiguiente();
        }
        int n = listaMovimientos.size();
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                Inventarios temp = listaMovimientos.get(i);
                int j;
                for (j = i; j >= gap && listaMovimientos.get(j - gap).getId() > temp.getId(); j -= gap) {
                    listaMovimientos.set(j, listaMovimientos.get(j - gap));
                }
                listaMovimientos.set(j, temp);
            }
        }
        // Vaciar la cola y volver a llenarla en orden
        while (!movimiento.estaVacia()) {
            movimiento.eliminar();
        }
        for (Inventarios inventario : listaMovimientos) {
            movimiento.agregar(inventario, inventario.getId());
        }
        cargarMovimientos();
    }
    void restaurar(){
        // Vaciar la cola actual
        while (!movimiento.estaVacia()) {
            movimiento.eliminar();
        }
        for (Inventarios inventario : original) {
            movimiento.agregar(inventario, inventario.getId());
        }
        cargarMovimientos();
    }
    void eliminarCola() {
        if (!movimiento.estaVacia()) {
            Inventarios eliminado = this.movimiento.eliminar();
            JOptionPane.showMessageDialog(this, "Movimiento eliminado: " + eliminado.getProducto(), "Información", JOptionPane.INFORMATION_MESSAGE);

            // Actualizar la tabla de movimientos
            cargarMovimientos();
        } else {
            JOptionPane.showMessageDialog(this, "El historial está vacío. No hay movimientos para eliminar.", "Información", JOptionPane.WARNING_MESSAGE);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botones = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        rbtnSalida = new javax.swing.JRadioButton();
        cbxMotivo = new javax.swing.JComboBox<>();
        txtCodigo = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        txtID = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        rbtnIngreso = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaMovimientos = new javax.swing.JTable();
        txtBusqueda = new javax.swing.JTextField();
        cbxTipo = new javax.swing.JComboBox<>();
        btnFiltrar = new javax.swing.JButton();
        btnOrdenar = new javax.swing.JButton();
        btnEliminarC = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblMovimientos = new javax.swing.JLabel();
        lblIngreso = new javax.swing.JLabel();
        lblSalida = new javax.swing.JLabel();

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        rbtnSalida.setText("Salida");

        cbxMotivo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Seleccione--", "Venta", "Compra", "Devolucion", "Donacion" }));

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jLabel4.setText("IDMovimiento");

        jLabel3.setText("Cantidad");

        jLabel2.setText("Codigo");

        jLabel1.setText("Motivo");

        rbtnIngreso.setText("Ingreso");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1)
                    .addComponent(rbtnIngreso))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtnSalida)
                    .addComponent(cbxMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtID, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevo)
                    .addComponent(btnEditar)
                    .addComponent(btnGuardar)
                    .addComponent(btnEliminar))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rbtnIngreso)
                        .addGap(45, 45, 45)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rbtnSalida)
                        .addGap(42, 42, 42)
                        .addComponent(cbxMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(btnNuevo)
                        .addGap(42, 42, 42)
                        .addComponent(btnEditar)
                        .addGap(55, 55, 55)
                        .addComponent(btnGuardar)
                        .addGap(28, 28, 28)
                        .addComponent(btnEliminar)))
                .addGap(73, 73, 73))
        );

        jTabbedPane1.addTab("Lista", jPanel1);

        TablaMovimientos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Producto", "Cantidad", "Motivo"
            }
        ));
        jScrollPane2.setViewportView(TablaMovimientos);

        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyReleased(evt);
            }
        });

        cbxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Seleccione--", "Ingreso", "Salida", "Todos" }));

        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        btnOrdenar.setText("Ordenar");
        btnOrdenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenarActionPerformed(evt);
            }
        });

        btnEliminarC.setText("Eliminar");
        btnEliminarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCActionPerformed(evt);
            }
        });

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        jLabel6.setText("Movimientos:");

        jLabel7.setText("Ingreso");

        jLabel8.setText("Salida:");

        jLabel9.setText("Historial de movimientos");

        lblMovimientos.setText("0");

        lblIngreso.setText("0");

        lblSalida.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(12, 12, 12)
                                .addComponent(lblMovimientos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblIngreso)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblSalida)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                                .addComponent(btnOrdenar)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminarC)
                                .addGap(18, 18, 18)
                                .addComponent(btnActualizar)
                                .addGap(21, 21, 21)))
                        .addGap(52, 52, 52))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnFiltrar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(239, 239, 239)
                .addComponent(jLabel9)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFiltrar))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnOrdenar)
                            .addComponent(btnEliminarC)
                            .addComponent(btnActualizar)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(lblMovimientos)
                            .addComponent(lblIngreso)
                            .addComponent(lblSalida))))
                .addGap(37, 37, 37))
        );

        jTabbedPane1.addTab("Movimientos", jPanel2);

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

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        List<JTextField> textFields = Arrays.asList(txtID, txtCantidad, txtCodigo);
        textFields.forEach(field -> field.setEnabled(true));
        //btnEliminar.setEnabled(true);
        //btnGuardar.setEnabled(true);
        btnEditar.setEnabled(true);
        rbtnIngreso.setEnabled(true);
        rbtnSalida.setEnabled(true);
        //cbxMotivo.setEnabled(true);
        //Fecha.setEnabled(true);
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
            String motivo = rbtnIngreso.isSelected() ? "Ingreso" :"Salida" ;
            if (rbtnIngreso.isSelected()) {
                producto.setStock(producto.getStock() + cantidad); 
            } else if (rbtnSalida.isSelected()) {
                if (producto.getStock() < cantidad) {
                    JOptionPane.showMessageDialog(this, "No hay suficiente stock para realizar la salida.", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                producto.setStock(producto.getStock() - cantidad);
            }
            int idM = Integer.parseInt(txtID.getText());
            Inventarios inventario = new Inventarios(idM, producto.getNombre() , cantidad, motivo);
            movimiento.agregar(inventario,idM);
            original.add(new Inventarios(idM, producto.getNombre(), cantidad, motivo));
            JOptionPane.showMessageDialog(this, "Producto actualizado correctamente.", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
            cargar();
            limpiar();
            cargarMovimientos();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El valor ingresado no es válido. Por favor, verifica el código y la cantidad.", "Error", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ha ocurrido un error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        try {
            String filtro = cbxTipo.getSelectedItem().toString(); // Obtener el motivo seleccionado del ComboBox
            DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Producto", "Cantidad", "Motivo"}, 0);

            Nodo1<Inventarios> actual = movimiento.getInicio(); // Obtener el nodo inicial de la cola

            while (actual != null) {
                Inventarios inventario = actual.getClase(); // Obtener el objeto Inventario del nodo
                if (inventario.getMotivo().equalsIgnoreCase(filtro) || filtro.equalsIgnoreCase("Todos")) {
                    model.addRow(new Object[]{
                        inventario.getId(),
                        inventario.getProducto(),
                        inventario.getCantidad(),
                        inventario.getMotivo()
                    });
                }
                actual = actual.getSiguiente(); // Pasar al siguiente nodo
            }

            TablaMovimientos.setModel(model); // Actualizar la tabla con los datos filtrados

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al filtrar los movimientos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void btnOrdenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenarActionPerformed
        try {
            ordenamientoshell();
            JOptionPane.showMessageDialog(null, "Movimientos ordenadoas por ID");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar los movimientos");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnOrdenarActionPerformed

    private void btnEliminarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCActionPerformed
        eliminarCola();
    }//GEN-LAST:event_btnEliminarCActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        restaurar();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void txtBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyReleased
        try {
            String textoBusqueda = txtBusqueda.getText().trim(); // Obtener el texto ingresado
            DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Producto", "Cantidad", "Motivo"}, 0);

            if (!textoBusqueda.isEmpty()) {
                boolean esNumero = textoBusqueda.matches("\\d+"); // Verificar si el texto es un número (ID)

                if (esNumero) {
                    // Buscar por ID
                    int idBuscado = Integer.parseInt(textoBusqueda);
                    Inventarios resultado = movimiento.buscarPorId(idBuscado);

                    if (resultado != null) {
                        model.addRow(new Object[]{
                            resultado.getId(),
                            resultado.getProducto(),
                            resultado.getCantidad(),
                            resultado.getMotivo()
                        });
                    } else {
                        JOptionPane.showMessageDialog(this, "No se encontró ningún movimiento con el ID ingresado.", "Información", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    // Buscar por nombre parcial
                    List<Inventarios> resultados = movimiento.buscarPorNombre(textoBusqueda);

                    if (!resultados.isEmpty()) {
                        for (Inventarios resultado : resultados) {
                            model.addRow(new Object[]{
                                resultado.getId(),
                                resultado.getProducto(),
                                resultado.getCantidad(),
                                resultado.getMotivo()
                            });
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "No se encontraron movimientos que coincidan con el nombre ingresado.", "Información", JOptionPane.WARNING_MESSAGE);
                    }
                }

                // Actualizar la tabla con los resultados
                TablaMovimientos.setModel(model);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al realizar la búsqueda: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtBusquedaKeyReleased

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabla;
    private javax.swing.JTable TablaMovimientos;
    private javax.swing.ButtonGroup botones;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminarC;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnOrdenar;
    private javax.swing.JComboBox<String> cbxMotivo;
    private javax.swing.JComboBox<String> cbxTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblIngreso;
    private javax.swing.JLabel lblMovimientos;
    private javax.swing.JLabel lblSalida;
    private javax.swing.JRadioButton rbtnIngreso;
    private javax.swing.JRadioButton rbtnSalida;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtID;
    // End of variables declaration//GEN-END:variables
}

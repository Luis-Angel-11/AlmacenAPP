package vista;

import clases.modelo.productoBD;
import clases.producto;
import clases.proveedor;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class ProductoV extends javax.swing.JPanel {

    /**
     * Creates new form ProductoV
     */
    public ProductoV() {
        initComponents();
        cargarProveedores();
        configurarEventos();
    }
    private void cargarProductosEnTabla(List<producto> listaProductos) {
    String[] columnNames = {"ID", "Nombre", "Precio", "Cantidad", "Categoría", "Estado", "Proveedor"};
    javax.swing.table.DefaultTableModel modeloTabla = new javax.swing.table.DefaultTableModel(columnNames, 0);

    for (producto p : listaProductos) {
        Object[] fila = {
            p.getId(),
            p.getNombre(),
            p.getPrecio(),
            p.getCantidad(),
            p.getCategoria(),
            p.getEstado(),
            p.getProveedor().getNombre()
        };
        modeloTabla.addRow(fila);
    }

    jTable1.setModel(modeloTabla);
    }
    private void configurarEventos() {
    jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
        public void stateChanged(javax.swing.event.ChangeEvent evt) {
            if (jTabbedPane1.getSelectedIndex() == 1) { // Índice 1 corresponde a la pestaña "Lista"
                productoBD productoBD = new productoBD();
                List<producto> listaProductos = productoBD.listarProductos(); // Obtén la lista desde la base de datos
                cargarProductosEnTabla(listaProductos); // Pasa la lista como argumento

            }
        }
    });
    }

    private void limpiarCampos() {
    TextID.setText("");
    TextNombre.setText("");
    TextPrecio.setText("");
    TextCantidad.setText("");
    TextCategoria.setText("");
    jComboEstado.setSelectedIndex(0); // Selecciona el primer elemento del ComboBox
    jComboProveedor.setSelectedIndex(0); // Selecciona el primer elemento del ComboBox
    }
    
    private void eliminarProductoPorNombre() {
    String nombre = jTextBuscar.getText().trim(); // Obtener el nombre del producto desde jTextBuscar
    // Verificar si el campo está vacío
    if (nombre.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, ingrese el nombre del producto a eliminar.");
        return;
    }
    // Crear instancia de productoBD y llamar al método eliminarProducto
    productoBD productoBD = new productoBD();
    boolean exito = productoBD.eliminarProducto(nombre); // Llamar al método de eliminarProducto pasando el nombre

    if (exito) {
        JOptionPane.showMessageDialog(this, "Producto eliminado correctamente.");
        cargarProductosEnTabla(); // Recargar la lista de productos
    } else {
        JOptionPane.showMessageDialog(this, "No se encontró un producto con ese nombre.");
    }
    }
    
    private void cargarProveedores() {
    productoBD productoBD = new productoBD();
    List<proveedor> proveedores = productoBD.listarProveedores();

    DefaultComboBoxModel<proveedor> modelo = new DefaultComboBoxModel<>();
    for (proveedor p : proveedores) {
        modelo.addElement(p); // Agrega el objeto proveedor
    }
    jComboProveedor.setModel(modelo);

    if (modelo.getSize() == 0) {
        JOptionPane.showMessageDialog(this, "No hay proveedores disponibles.");
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        NombreP = new javax.swing.JLabel();
        PrecioP = new javax.swing.JLabel();
        CantidadP = new javax.swing.JLabel();
        ProveedorP = new javax.swing.JLabel();
        CategoriaP = new javax.swing.JLabel();
        EstadoP = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jComboEstado = new javax.swing.JComboBox<>(new String[] { "Activo", "Inactivo", "Discontinuado" });
        TextCategoria = new javax.swing.JTextField();
        jComboProveedor = new javax.swing.JComboBox<>();
        TextCantidad = new javax.swing.JTextField();
        TextPrecio = new javax.swing.JTextField();
        TextNombre = new javax.swing.JTextField();
        TextID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jTextBuscar = new javax.swing.JTextField();
        jButtonBuscar = new javax.swing.JButton();
        jButtonOrdenar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Registro de productos");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 190, -1));

        jLabel2.setText("ID del producto:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 120, -1));

        NombreP.setText("Nombre del producto:");
        jPanel1.add(NombreP, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        PrecioP.setText("Precio del producto:");
        jPanel1.add(PrecioP, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));

        CantidadP.setText("Cantidad del producto:");
        jPanel1.add(CantidadP, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, -1));

        ProveedorP.setText("Proveedor del producto:");
        jPanel1.add(ProveedorP, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 129, -1));

        CategoriaP.setText("Categoria del producto:");
        jPanel1.add(CategoriaP, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 126, 20));

        EstadoP.setText("Estado del producto:");
        jPanel1.add(EstadoP, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 110, -1));

        jToggleButton1.setText("Agregar");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jToggleButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 350, 110, -1));
        jPanel1.add(jComboEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 310, 110, -1));

        TextCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextCategoriaActionPerformed(evt);
            }
        });
        jPanel1.add(TextCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, 110, -1));

        jComboProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboProveedorActionPerformed(evt);
            }
        });
        jPanel1.add(jComboProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 110, -1));
        jPanel1.add(TextCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 110, -1));

        TextPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextPrecioActionPerformed(evt);
            }
        });
        jPanel1.add(TextPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 90, -1));

        TextNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextNombreActionPerformed(evt);
            }
        });
        jPanel1.add(TextNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 110, -1));

        TextID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextIDActionPerformed(evt);
            }
        });
        jPanel1.add(TextID, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 110, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ImgProductos.jpg"))); // NOI18N
        jLabel3.setText("jLabel3");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, 300, 300));

        jLabel5.setText("S/.");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 20, 20));

        jTabbedPane1.addTab("Registro", jPanel1);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"ID", "Nombre", "Precio", "Cantidad", "Categoría", "Estado", "Proveedor"}
        )
    );
    jScrollPane1.setViewportView(jTable1);

    jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 610, 240));

    jLabel4.setText("Buscar por nombre:");
    jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 120, 20));

    jTextBuscar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jTextBuscarActionPerformed(evt);
        }
    });
    jPanel2.add(jTextBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 250, -1));

    jButtonBuscar.setText("Buscar");
    jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButtonBuscarActionPerformed(evt);
        }
    });
    jPanel2.add(jButtonBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 40, 90, -1));

    jButtonOrdenar.setText("Ordenar");
    jButtonOrdenar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButtonOrdenarActionPerformed(evt);
        }
    });
    jPanel2.add(jButtonOrdenar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, 130, -1));

    jButtonEliminar.setText("Eliminar");
    jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButtonEliminarActionPerformed(evt);
        }
    });
    jPanel2.add(jButtonEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 340, 130, -1));

    jTabbedPane1.addTab("Lista", jPanel2);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jTabbedPane1)
            .addGap(31, 31, 31))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
            .addContainerGap())
    );
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        if (TextID.getText().isEmpty() || TextNombre.getText().isEmpty() || TextPrecio.getText().isEmpty() || 
        TextCantidad.getText().isEmpty() || TextCategoria.getText().isEmpty() || 
        jComboProveedor.getSelectedItem() == null || jComboEstado.getSelectedItem() == null) {
        JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
        return; // Salir del método si hay campos vacíos
    }

    try {
        // Crear una instancia de la clase producto
        int id = Integer.parseInt(TextID.getText());
        String nombre = TextNombre.getText();
        double precio = Double.parseDouble(TextPrecio.getText());
        int cantidad = Integer.parseInt(TextCantidad.getText());
        String categoria = TextCategoria.getText();
        String estado = jComboEstado.getSelectedItem().toString();
        // Asumiendo que tienes un proveedor preseleccionado desde un JComboBox
        proveedor proveedorSeleccionado = (proveedor) jComboProveedor.getSelectedItem();
        // Verificar que el proveedor seleccionado no sea null
        if (proveedorSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un proveedor.");
            return; // Salir si no se ha seleccionado proveedor
        }

        // Crear un producto con los datos
        producto nuevoProducto = new producto(id, nombre, precio, cantidad, proveedorSeleccionado, categoria, estado);
        // Llamar al método para agregar el producto a la base de datos
        productoBD productoBD = new productoBD();
        boolean exito = productoBD.agregarProducto(nuevoProducto);

        if (exito) {
            JOptionPane.showMessageDialog(this, "Producto agregado correctamente.");
            limpiarCampos(); // Limpia los campos después de insertar

            // Reutilizar la instancia productoBD para listar productos
            List<producto> listaProductos = productoBD.listarProductos();
            cargarProductosEnTabla(listaProductos);
        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar el producto.");
        }
        } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Error en los datos numéricos: " + e.getMessage());
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void TextCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextCategoriaActionPerformed

    private void jComboProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboProveedorActionPerformed

    private void TextPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextPrecioActionPerformed

    private void TextNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextNombreActionPerformed

    private void TextIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextIDActionPerformed

    private void jTextBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextBuscarActionPerformed

    private void jButtonOrdenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOrdenarActionPerformed
        productoBD productoBD = new productoBD();
        List<producto> productosOrdenados = productoBD.ordenarProductosPorPrecio();
        cargarProductosEnTabla(productosOrdenados);
    }//GEN-LAST:event_jButtonOrdenarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        // TODO add your handling code here:
        String nombre = jTextBuscar.getText().trim();

    // Verificar que el campo no esté vacío
    if (nombre.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, ingrese el nombre del producto a eliminar.");
        return; // Salir si el campo está vacío
    }

    productoBD productoBD = new productoBD();
    boolean exito = productoBD.eliminarProducto(nombre);

    if (exito) {
        JOptionPane.showMessageDialog(this, "Producto eliminado correctamente.");
        cargarProductosEnTabla(); // Recargar la tabla después de eliminar el producto
    } else {
        JOptionPane.showMessageDialog(this, "No se encontró un producto con ese nombre.");
        }
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
    String nombre = jTextBuscar.getText().trim();

    if (nombre.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, ingrese el nombre del producto a buscar.");
        return; // Salir si el campo está vacío
    }

    productoBD productoBD = new productoBD();

    producto prod = productoBD.buscarProductoPorNombre(nombre);

    if (prod != null) {
        List<producto> listaProductos = List.of(prod); 
        cargarProductosEnTabla(listaProductos);
    } else {
        JOptionPane.showMessageDialog(this, "No se encontró un producto con ese nombre.");
        }
    }//GEN-LAST:event_jButtonBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CantidadP;
    private javax.swing.JLabel CategoriaP;
    private javax.swing.JLabel EstadoP;
    private javax.swing.JLabel NombreP;
    private javax.swing.JLabel PrecioP;
    private javax.swing.JLabel ProveedorP;
    private javax.swing.JTextField TextCantidad;
    private javax.swing.JTextField TextCategoria;
    private javax.swing.JTextField TextID;
    private javax.swing.JTextField TextNombre;
    private javax.swing.JTextField TextPrecio;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonOrdenar;
    private javax.swing.JComboBox<String> jComboEstado;
    private javax.swing.JComboBox<proveedor> jComboProveedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextBuscar;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables

    private void cargarProductosEnTabla() {
    // Obtener la lista de productos desde la base de datos
    productoBD productoBD = new productoBD();
    List<producto> listaProductos = productoBD.listarProductos(); // Obtener productos desde la base de datos

    // Definir las columnas para la tabla
    String[] columnNames = {"ID", "Nombre", "Precio", "Cantidad", "Categoría", "Estado", "Proveedor"};
    javax.swing.table.DefaultTableModel modeloTabla = new javax.swing.table.DefaultTableModel(columnNames, 0);

    // Iterar sobre la lista de productos y agregar las filas a la tabla
    for (producto p : listaProductos) {
        Object[] fila = {
            p.getId(),
            p.getNombre(),
            p.getPrecio(),
            p.getCantidad(),
            p.getCategoria(),
            p.getEstado(),
            p.getProveedor().getNombre() // Asumiendo que 'getProveedor().getNombre()' es válido
        };
        modeloTabla.addRow(fila);
    }

    // Asignar el modelo a la tabla
    jTable1.setModel(modeloTabla);
    }
}
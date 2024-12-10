package estructuras;

import clases.producto;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ProductoTableModel extends AbstractTableModel {
    private List<producto> listaProductos;
    private String[] columnas = {"ID", "Nombre", "Precio", "Cantidad", "Categoría", "Estado", "Proveedor"};

    public ProductoTableModel(List<producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    @Override
    public int getRowCount() {
        return listaProductos.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        producto prod = listaProductos.get(rowIndex);
        switch (columnIndex) {
            case 0: return prod.getId();
            case 1: return prod.getNombre();
            case 2: return prod.getPrecio();
            case 3: return prod.getCantidad();
            case 4: return prod.getCategoria();
            case 5: return prod.getEstado();
            case 6: return prod.getProveedor().getNombre(); // Nombre del proveedor
            default: return null;
        }
    }
}
package estructuras;

import clases.Orden;
import clases.modelo.NodoOrden;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArbolOrdenes {
    private NodoOrden raiz;

    public ArbolOrdenes() {
        this.raiz = null;
    }

    public void insertar(Orden orden) {
        raiz = insertarRec(raiz, orden);
    }

    private NodoOrden insertarRec(NodoOrden raiz, Orden orden) {
        if (raiz == null) {
            return new NodoOrden(orden);
        }

        if (orden.getIdOrden() < raiz.getOrden().getIdOrden()) {
            raiz.setIzquierdo(insertarRec(raiz.getIzquierdo(), orden));
        } else if (orden.getIdOrden() > raiz.getOrden().getIdOrden()) {
            raiz.setDerecho(insertarRec(raiz.getDerecho(), orden));
        } else {
            System.out.println("Orden con ID " + orden.getIdOrden() + " ya existe. Inserción ignorada.");
        }

        return raiz;
    }

    public Orden buscarPorId(int idOrden) {
        return buscarRec(raiz, idOrden);
    }

    private Orden buscarRec(NodoOrden raiz, int idOrden) {
        if (raiz == null) {
            return null;
        }

        if (raiz.getOrden().getIdOrden() == idOrden) {
            return raiz.getOrden();
        } else if (idOrden < raiz.getOrden().getIdOrden()) {
            return buscarRec(raiz.getIzquierdo(), idOrden);
        } else {
            return buscarRec(raiz.getDerecho(), idOrden);
        }
    }

    public List<Orden> recorridoInOrden() {
        List<Orden> listaOrdenes = new ArrayList<>();
        recorridoInOrdenRec(raiz, listaOrdenes);
        return listaOrdenes;
    }

    private void recorridoInOrdenRec(NodoOrden raiz, List<Orden> listaOrdenes) {
        if (raiz != null) {
            recorridoInOrdenRec(raiz.getIzquierdo(), listaOrdenes);
            listaOrdenes.add(raiz.getOrden());
            recorridoInOrdenRec(raiz.getDerecho(), listaOrdenes);
        }
    }

    public void eliminar(int idOrden) {
        raiz = eliminarRec(raiz, idOrden);
    }

    private NodoOrden eliminarRec(NodoOrden raiz, int idOrden) {
        if (raiz == null) {
            return raiz;
        }

        if (idOrden < raiz.getOrden().getIdOrden()) {
            raiz.setIzquierdo(eliminarRec(raiz.getIzquierdo(), idOrden));
        } else if (idOrden > raiz.getOrden().getIdOrden()) {
            raiz.setDerecho(eliminarRec(raiz.getDerecho(), idOrden));
        } else {
            if (raiz.getIzquierdo() == null) {
                return raiz.getDerecho();
            } else if (raiz.getDerecho() == null) {
                return raiz.getIzquierdo();
            }

            Orden sucesor = minValor(raiz.getDerecho());
            raiz.setOrden(sucesor);
            raiz.setDerecho(eliminarRec(raiz.getDerecho(), sucesor.getIdOrden()));
        }

        return raiz;
    }

    private Orden minValor(NodoOrden raiz) {
        Orden minOrden = raiz.getOrden();
        while (raiz.getIzquierdo() != null) {
            minOrden = raiz.getIzquierdo().getOrden();
            raiz = raiz.getIzquierdo();
        }
        return minOrden;
    }

    public List<Orden> buscarPorCliente(String dniCliente) {
        List<Orden> resultados = new ArrayList<>();
        buscarPorClienteRec(raiz, dniCliente, resultados);
        return resultados;
    }

    private void buscarPorClienteRec(NodoOrden raiz, String dniCliente, List<Orden> resultados) {
        if (raiz != null) {
            buscarPorClienteRec(raiz.getIzquierdo(), dniCliente, resultados);
            if (raiz.getOrden().getDniCliente().equalsIgnoreCase(dniCliente)) {
                resultados.add(raiz.getOrden());
            }
            buscarPorClienteRec(raiz.getDerecho(), dniCliente, resultados);
        }
    }

    public static void main(String[] args) {
        ArbolOrdenes arbol = new ArbolOrdenes();
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        System.out.println("=== Gestión de Órdenes ===");

        while (!salir) {
            mostrarMenu();
            String opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1":
                    arbol.agregarOrden(scanner);
                    break;
                case "2":
                    arbol.buscarOrdenPorId(scanner);
                    break;
                case "3":
                    arbol.buscarOrdenesPorDni(scanner);
                    break;
                case "4":
                    arbol.listarOrdenes();
                    break;
                case "5":
                    arbol.eliminarOrden(scanner);
                    break;
                case "6":
                    salir = true;
                    System.out.println("Saliendo de la aplicación. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, intente nuevamente.\n");
            }
        }

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\nSeleccione una opción:");
        System.out.println("1. Agregar Orden");
        System.out.println("2. Buscar Orden por ID");
        System.out.println("3. Buscar Órdenes por DNI de Cliente");
        System.out.println("4. Listar Todas las Órdenes (In-Orden)");
        System.out.println("5. Eliminar Orden por ID");
        System.out.println("6. Salir");
        System.out.print("Opción: ");
    }

    private void agregarOrden(Scanner scanner) {
        try {
            System.out.print("Ingrese el ID de la Orden (número entero): ");
            int idOrden = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Ingrese la Fecha de la Orden (YYYY-MM-DD): ");
            String fechaStr = scanner.nextLine().trim();
            LocalDate fecha = LocalDate.parse(fechaStr);

            System.out.print("Ingrese el DNI del Cliente: ");
            String dniCliente = scanner.nextLine().trim();

            System.out.print("Ingrese la Cantidad: ");
            int cantidad = Integer.parseInt(scanner.nextLine().trim());

            Orden orden = new Orden(idOrden, fecha, dniCliente, cantidad);
            insertar(orden);
            System.out.println("Orden agregada exitosamente.\n");
        } catch (NumberFormatException e) {
            System.out.println("Error: El ID de la Orden y la Cantidad deben ser números enteros.\n");
        } catch (DateTimeParseException e) {
            System.out.println("Error: La Fecha debe tener el formato YYYY-MM-DD.\n");
        } catch (Exception e) {
            System.out.println("Error al agregar la Orden: " + e.getMessage() + "\n");
        }
    }

    private void buscarOrdenPorId(Scanner scanner) {
        try {
            System.out.print("Ingrese el ID de la Orden a buscar: ");
            int idOrden = Integer.parseInt(scanner.nextLine().trim());

            Orden orden = buscarPorId(idOrden);
            if (orden != null) {
                System.out.println("Orden encontrada:");
                System.out.println(orden + "\n");
            } else {
                System.out.println("No se encontró una Orden con el ID " + idOrden + ".\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El ID de la Orden debe ser un número entero.\n");
        } catch (Exception e) {
            System.out.println("Error al buscar la Orden: " + e.getMessage() + "\n");
        }
    }

    private void buscarOrdenesPorDni(Scanner scanner) {
        try {
            System.out.print("Ingrese el DNI del Cliente para buscar sus Órdenes: ");
            String dniCliente = scanner.nextLine().trim();

            List<Orden> ordenes = buscarPorCliente(dniCliente);
            if (!ordenes.isEmpty()) {
                System.out.println("Órdenes encontradas para el DNI " + dniCliente + ":");
                for (Orden orden : ordenes) {
                    System.out.println(orden);
                }
                System.out.println();
            } else {
                System.out.println("No se encontraron Órdenes para el DNI " + dniCliente + ".\n");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar las Órdenes: " + e.getMessage() + "\n");
        }
    }

    private void listarOrdenes() {
        List<Orden> ordenes = recorridoInOrden();
        if (!ordenes.isEmpty()) {
            System.out.println("Listado de Todas las Órdenes (In-Orden):");
            for (Orden orden : ordenes) {
                System.out.println(orden);
            }
            System.out.println();
        } else {
            System.out.println("No hay Órdenes registradas.\n");
        }
    }

    private void eliminarOrden(Scanner scanner) {
        try {
            System.out.print("Ingrese el ID de la Orden a eliminar: ");
            int idOrden = Integer.parseInt(scanner.nextLine().trim());

            Orden orden = buscarPorId(idOrden);
            if (orden != null) {
                eliminar(idOrden);
                System.out.println("Orden con ID " + idOrden + " eliminada exitosamente.\n");
            } else {
                System.out.println("No se encontró una Orden con el ID " + idOrden + ".\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El ID de la Orden debe ser un número entero.\n");
        } catch (Exception e) {
            System.out.println("Error al eliminar la Orden: " + e.getMessage() + "\n");
        }
    }
}

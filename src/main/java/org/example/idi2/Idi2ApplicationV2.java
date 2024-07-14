//package org.example.idi2;
//import org.example.idi2.modelo.entidad.*;
//import org.example.idi2.modelo.service.*;
//import org.example.idi2.modelo.util.Estado;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
//
//import java.util.*;
//
//import org.example.idi2.extra.*;
//@SpringBootApplication
//@EnableNeo4jRepositories("org.example.idi2.modelo.repository")
//public class Idi2ApplicationV2 implements CommandLineRunner{
//
//    public static void main(String[] args) {
//        SpringApplication.run(Idi2ApplicationV2.class, args);
//    }
//
//    @Autowired
//    ServiceProducto productos;
//    @Autowired
//    ServiceCarrito carritos;
//    @Autowired
//    ServiceCuenta cuentas;
//    @Autowired
//    ServicePedido pedidos;
//    @Autowired
//    ServiceFactura facturas;
//
//    @Override
//    public void run(String... args) throws Exception {
//        Estado estado = new Estado();
//        Scanner scanner = new Scanner(System.in);
//
//
//
//        System.out.println("Bienvenido a nuestro E-Commerce");
//
//        while (true) {
//            Extra.imprimirMenu(estado.isSesionIniciada());
//            System.out.print("Seleccione una opción: ");
//            int seleccion = -1;
//            boolean validInput = false;
//            while (!validInput) {
//                try {
//                    seleccion = Integer.parseInt(scanner.nextLine());
//                    validInput = true; // Exit loop if parsing is successful
//                } catch (NumberFormatException e) {
//                    System.out.println("Por favor, ingrese un número válido.");
//                }
//            }
//
//            switch (seleccion) {
//
//                case 1: // Iniciar sesion - Cerrar sesion
//                      if (!estado.isSesionIniciada()) {
//                          inicioDeSesion(scanner, estado);
//                      } else {
//                          estado.cerrarSesion();
//                      }
//                      break;
//
//                case 2: // Registrarse y ver perfil, agregar, modificar y eliminar producto y pagar pedido
//                    if (!estado.isSesionIniciada()) {
//                        registro(scanner);
//                    } else {
//                        miPerfil(estado, scanner);
//                    }
//                    break;
//
//                case 3: // Imprimir Catalogo y agregar producto
//                    imprimirCatalogo();
//                    agregarProductoAlCarrito(scanner, estado);
//                    break;
//
//                case 4: // Ver carrito, concretar pedido, modificar y eliminar un producto
//                    verCarrito(estado, scanner);
//                    break;
//
//                case 5:
//                    System.out.println("Saliendo del programa");
//                    return;
//
//
//                default:
//                    System.out.println("Opción no válida. Por favor, seleccione una opción del 1 al 5.");
//                    break;
//
//            }
//        }
//    }
//
//
//    // -------------------------Inicio de sesion------------------------------//
//    public void inicioDeSesion(Scanner scanner, Estado estado) {
//        String password;
//        String username;
//        Cuenta c;
//        System.out.print("Usuario: ");
//        username = scanner.nextLine();
//        System.out.print("Contraseña: ");
//        password = scanner.nextLine();
//        c = iniciarSesion(username, password);
//        System.out.println();
//        if (Objects.isNull(c)) {
//            System.out.println("Usuario y/o contraseña incorrectas");
//        } else {
//            System.out.println("¡Sesion iniciada!");
//            estado.setSesionIniciada(true);
//            estado.setUsername(username);
//            estado.setEmail(c.getEmail());
//            Carrito carritoRecuperado = carritos.obtenerCarrito(username);
//            estado.getCarrito().setId(carritoRecuperado.getId());
//            estado.getCarrito().setUsernameId(carritoRecuperado.getUsernameId());
//            estado.getCarrito().setObjetos(carritoRecuperado.getObjetos());
//        }
//    }
//
//    public Cuenta iniciarSesion(String user, String password) {
//        Cuenta c = cuentas.getCuentaByUser(user, password);
//        if (Objects.isNull(c)) {
//            return null;
//        } else {
//            return c;
//        }
//    }
//
//
//    // -------------------------Registro------------------------------//
//    private void registro(Scanner s) {
//        Cuenta c = new Cuenta();
//        System.out.print("Nombre: ");
//        c.setName(s.nextLine());
//        System.out.print("Documento: ");
//        c.setDocumento(Integer.parseInt(s.nextLine()));
//        System.out.print("Email: ");
//        c.setEmail(s.nextLine());
//        System.out.print("Username: ");
//        c.setUsername(s.nextLine());
//        System.out.print("Contraseña: ");
//        c.setPassword(s.nextLine());
//        System.out.print("Direccion: ");
//        c.setDireccion(s.nextLine());
//        try { // Mejorar este metodo
//            if (!Objects.isNull(cuentas.getCuentaByUsername(c.getUsername()))) {
//                System.out.println("Username ya utilizado, debe ser otro");
//            } else {
//                cuentas.addCuenta(c);
//                Carrito carrito = new Carrito(c.getUsername());
//                carritos.guardarCarrito(carrito);
//                System.out.println("¡Registro exitoso!");
//            }
//        } catch (Exception e) {
//            System.out.println("Error al tratar de registrarse " + e);
//        }
//    }
//
//    // -------------------------Ver catalogo------------------------------//
//    private void imprimirCatalogo() {
//        List<Producto> catalogo = productos.obtenerTodos();
//        for (Producto p : catalogo) {
//            System.out.println("ID: " + p.getId() +"\n"
//                    + "Nombre: " + p.getNombre() + "\n"
//                    + "Descripcion: " + p.getDescripcion() + "\n"
//                    + "$" + p.getPrecio());
//            System.out.println("------------------");
//        }
//    }
//
//    // -------------------------Ver catalogo - Agregar producto al carrito------------------------------//
//    private void agregarProductoAlCarrito(Scanner scanner, Estado estado) {
//        if (estado.isSesionIniciada()) {
//            Long seleccion; Producto producto; int cantidad;
//            System.out.println("Seleccionar producto a agregar (-1 para salir): ");
//            seleccion = Long.parseLong(scanner.nextLine());
//            System.out.println("Cantidad del producto: "); // Validar que la cantidad no sea negativa
//            cantidad = Integer.parseInt(scanner.nextLine());
//            producto = productos.obtenerPorId(seleccion);
//            //Validar que tenga stock suficiente y que se disminuya ese stock
//            carritos.actualizar(estado.getCarrito().getId(), producto.getId(), cantidad);
//        }
//    }
//
//
//    // -------------------------Ver carrito------------------------------//
//    public void verCarrito(Estado estado, Scanner scanner) {
//        if (!estado.isSesionIniciada()) {
//            System.out.println("Debe iniciar sesion para ver el carrito");
//        } else {
//            estado.setCarrito(carritos.obtenerCarrito(estado.getUsername()));
//            if (estado.getCarrito().getObjetos().isEmpty()) {
//                System.out.println("El carrito está vacío");
//            } else {
//                System.out.println("Imprimiendo carrito: ");
//                for (ParProductoCantidad p : estado.getCarrito().getObjetos()) {
//                    System.out.println("ID: " + p.getProducto().getId() + "\n" +
//                            "Producto: " + p.getProducto().getNombre() + "\n" +
//                            "Cantidad: " + p.getCantidad() + "\n" +
//                            "$" + p.getProducto().getPrecio()
//                    );
//                }
//                submenuCarrito(estado, scanner);
//            }
//        }
//    }
//
//    // -------------------------Ver carrito - Submenu------------------------------//
//    public void submenuCarrito(Estado estado, Scanner scanner) {
//        int  agregarEliminar, cantidad; Long productoId;
//        while (true) {
//            System.out.println("Acciones: ");
//
//            System.out.println("1- Eliminar producto" + "\n" +
//                    "2- Modificar cantidad de producto" + "\n" +
//                    "3- Concretar compra" + "\n" +
//                    "4- Volver");
//
//            System.out.println("Seleccione una opción: ");
//
//            int decision = -1;
//            boolean validInput = false;
//            while (!validInput) {
//                try {
//                    decision = Integer.parseInt(scanner.nextLine());
//                    validInput = true; // Exit loop if parsing is successful
//                } catch (NumberFormatException e) {
//                    System.out.println("Por favor, ingrese un número válido.");
//                }
//            }
//            switch (decision) {
//                case 1:
//                    //EliminarProducto
//                    System.out.println("Indicar el id del producto que desea eliminar: ");
//                    productoId = Long.parseLong(scanner.nextLine()); // Verificar que el id del producto exista
//                    carritos.borrarProductoDelCarrito(estado.getCarrito().getId(), productoId);
//                    break;
//                case 2:
//                    //ModificarcantidadDeProducto
//                    System.out.println("1- Agregar\n2- Eliminar");
//                    agregarEliminar = Integer.parseInt(scanner.nextLine());
//                    System.out.println("Indicar el id del producto: ");
//                    productoId = Long.parseLong(scanner.nextLine());
//                    System.out.println("Cantidad: ");
//                    cantidad = Integer.parseInt(scanner.nextLine());
//                    if (agregarEliminar == 1) { // Verificar que haya stock disponible
//                        carritos.actualizar(estado.getCarrito().getId(), productoId, cantidad);
//                        System.out.println("Carrito actualizado");
//                    } else {
//                        Long finalProductoId = productoId;
//                        ParProductoCantidad productoEncontrado = carritos.obtenerCarrito(estado.getUsername()).getObjetos()
//                                .stream().filter(producto -> producto.getProducto().getId().equals(finalProductoId))
//                                .findFirst().orElse(null);
//                        if (Objects.isNull(productoEncontrado)) {
//                            System.out.println("El producto no existe");
//                        } else {
//                            if (productoEncontrado.getCantidad() <= cantidad) {
//                                carritos.borrarProductoDelCarrito(estado.getCarrito().getId(), finalProductoId);
//                            } else {
//                                carritos.actualizar(estado.getCarrito().getId(), finalProductoId, (cantidad*-1));
//                            }
//                            System.out.println("Carrito actualizado");
//                        }
//                    }
//                    break;
//                case 3:
//                    //ConcretarCompra
//                    concretarCompra(estado);
//                    break;
//                case 4:
//                    return;
//                default:
//                    System.out.println("Opción no válida. Por favor, seleccione una opción del 1 al 4.");
//                    break;
//            }
//
//        }
//    }
//
//    // -------------------------Concretar compra------------------------------//
//    public void concretarCompra(Estado estado) {
//        Carrito carrito;
//        try {
//            carrito = carritos.obtenerCarrito(estado.getUsername());
//        } catch (Exception e) {
//            System.out.println(e);
//            return;
//        }
//        if (carrito.getObjetos().isEmpty()) {
//            System.out.println("El carrito está vacío");
//        } else {
//            Pedido pedido = new Pedido(estado.getUsername());
//            ProductoParaPedido ppp;
//            for (ParProductoCantidad p : carrito.getObjetos()) {
//                ppp = new ProductoParaPedido(p.getProducto().getId(), p.getProducto().getNombre(), p.getProducto().getPrecio(), p.getCantidad());
//                pedido.getProductos().add(ppp);
//            }
//            try {
//                pedidos.guardarPedido(pedido);
//                carritos.eliminarTodosLosProductosDelCarrito(estado.getCarrito().getId());
//            } catch (Exception e) {
//                System.out.println("No se pudo guardar el pedido" + e);
//            }
//        }
//    }
//
//    // -------------------------Submenu mi perfil------------------------------//
//    public void miPerfil(Estado estado, Scanner scanner) {
//        System.out.println("Mi perfil: ");
//
//        while (true) {
//            System.out.println(
//                    // "1- Agregar producto al catalogo" + "\n" +
//                    // "2- Eliminar producto del catalogo" + "\n" +
//                    // "3- Modificar producto del catalogo" + "\n" +
//                    "1- Ver pedidos" + "\n" +
//                    "2- Pagar pedidos" + "\n" +
//                    "3- Agregar metodo de pago" + "\n" + //
//                    "4- Volver atras"
//            );
//            System.out.print("Seleccionar opcion: ");
//            int eleccion = -1;
//            boolean validInput = false;
//            while (!validInput) {
//                try {
//                    eleccion = Integer.parseInt(scanner.nextLine());
//                    validInput = true; // Exit loop if parsing is successful
//                } catch (NumberFormatException e) {
//                    System.out.println("Por favor, ingrese un número válido.");
//                }
//            }
//            switch (eleccion) {
//                case 1:
//                    verPedidos(estado);
//                    break;
//                case 2:
//                    pagarPedido(scanner, estado);
//                    break;
//                case 3:
//                    agregarMetodoDePago(scanner, estado);
//                    break;
//                case 4:
//                    return;
//                default:
//                    System.out.println("Opción no válida. Por favor, seleccione una opción del 1 al 4.");
//                    break;
//
//            }
//        }
//
//    }
//
//    // ------------------------- Submenu mi perfil - Ver pedidos ------------------------------ //
//    public void verPedidos(Estado estado) {
//        List<Pedido> listaDePedidos = pedidos.obtenerPedidosPorUsername(estado.getUsername());
//        if (listaDePedidos.isEmpty()) {
//            System.out.println("Tus pedidos estan vacios");
//        } else {
//            for (Pedido p : listaDePedidos) {
//                System.out.println("Pedido: " + p.getPedidoId());
//                for (ProductoParaPedido o : p.getProductos()) {
//                    System.out.println(o.getNombreProducto() +
//                            " | Cantidad: " + o.getCantProducto() +
//                            " | Precio total: " + o.getPrecio()*o.getCantProducto());
//                }
//            }
//        }
//    }
//
//    // ------------------------- Submenu mi perfil - Pagar pedido ------------------------------ //
//    public void pagarPedido(Scanner scanner, Estado estado) {
//
//        // Verificar que tenga metodos de pago asociados antes de pagar
//
//        List<Pedido> pedidosRecuperados = pedidos.obtenerPedidosPorUsername(estado.getUsername());
//        if (pedidosRecuperados.isEmpty()) {
//            System.out.println("No tenes pedidos realizados");
//        } else if (Extra.pedidosTodosPagos(pedidosRecuperados)) {
//            System.out.println("No tenes pedidos pendientes de pagar");
//        } else {
//            Extra.impresionDePedidos(pedidosRecuperados);
//            List<Pedido> pedidosAPagar = new ArrayList<>();
//            int idPedidoSeleccionado = 0;
//            while (true) {
//                System.out.println("Introducir nro de pedido que desea pagar (-1 para finalizar): ");
//                idPedidoSeleccionado = Integer.parseInt(scanner.nextLine()); // Validar la entrada del dato
//                if (idPedidoSeleccionado == -1) { // Finaliza la eleccion
//                    break;
//                }
//                pedidosAPagar.add(pedidosRecuperados.get(idPedidoSeleccionado - 1));
//            }
//            // Verificar que la lista de pedidos a pagar no esté vacía
//            List<MetodoDePago> metodosDePago = cuentas.getCuentaByUsername(estado.getUsername()).getMetodoDePago();
//            Extra.impresionDeMetodosDePago(metodosDePago);
//            System.out.print("Elegir el metodo de pago con el que desea efectuar la compra (-1 para cancelar): ");
//            int idMetodoSeleccionado = Integer.parseInt(scanner.nextLine());
//            if (idMetodoSeleccionado == -1) {
//                System.out.println("Compra cancelada, no se efectuó ningún pago");
//            } else {
//                Factura facturaNueva = new Factura(pedidosAPagar, estado.getUsername(), metodosDePago.get(idMetodoSeleccionado - 1));
//                System.out.println("Factura generada");
//                Extra.impresionDeFactura(facturaNueva);
//                Extra.cambiarEstadoDePedidos(pedidosRecuperados, pedidosAPagar);
//                // Persistir esos cambios
//                pedidos.eliminarPedidos(pedidosAPagar);
//                // Persistir la nueva factura
//                facturas.guardarFactura(facturaNueva);
//            }
//        }
//    }
//
//    // ------------------------- Submenu mi perfil - Agregar metodo de pago ------------------------------ //
//    public void agregarMetodoDePago(Scanner scanner, Estado estado) {
//        MetodoDePago metodoDePago = new MetodoDePago();
//        System.out.print("Tipo de pago: ");
//        metodoDePago.setTipoDePago(scanner.nextLine());
//        System.out.print("Numero de tarjeta: ");
//        metodoDePago.setNroTarjeta(Integer.parseInt(scanner.nextLine()));
//        System.out.println("Nombre identificatorio: ");
//        metodoDePago.setNombreAsociado(scanner.nextLine());
//
//        try {
//              Cuenta cuenta = cuentas.getCuentaByUsername(estado.getUsername());
//              boolean flag = false;
//              if (!Objects.isNull(cuenta.getMetodoDePago())) {
//                  for (MetodoDePago m : cuenta.getMetodoDePago()) {
//                      if (m.getTipoDePago() == metodoDePago.getTipoDePago() ||
//                              m.getNombreAsociado() == metodoDePago.getNombreAsociado() ||
//                              m.getNroTarjeta() == metodoDePago.getNroTarjeta()) {
//                          System.out.println("Metodo de pago ya registrado");
//                          flag = true;
//                      }
//                  }
//              } else {
//                  cuenta.setMetodoDePago(new ArrayList<>());
//              }
//              if (!flag) {
//                  cuenta.getMetodoDePago().add(metodoDePago);
//                  cuentas.addCuenta(cuenta);
//                  System.out.println("Metodo de pago registrado correctamente");
//              }
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
//    }
//}

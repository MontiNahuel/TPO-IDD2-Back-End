//package org.example.idi2;
//
//import org.example.idi2.modelo.entidad.Carrito;
//import org.example.idi2.modelo.entidad.Cuenta;
//import org.example.idi2.modelo.entidad.Producto;
//import org.example.idi2.modelo.service.ServiceCarrito;
//import org.example.idi2.modelo.service.ServiceCuenta;
//import org.example.idi2.modelo.service.ServiceProducto;
//import org.example.idi2.modelo.test.TestGuardado;
//import org.example.idi2.modelo.util.Estado;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
//
//import java.util.List;
//import java.util.Objects;
//import java.util.Scanner;
//
//@SpringBootApplication
//@EnableNeo4jRepositories("org.example.idi2.modelo.repository")
//public class Idi2Application implements CommandLineRunner {
//
//    @Autowired
//    private ServiceCuenta serviceCuenta;
//    @Autowired
//    private ServiceProducto serviceProducto;
//    @Autowired
//    private ServiceCarrito serviceCarrito;
//    public static void main(String[] args) {
//        SpringApplication.run(Idi2Application.class, args);
//    }
//
//
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        System.out.println("Aplicación iniciada!");
//        Scanner s = new Scanner(System.in);
//        int seleccion;
//        Estado e = new Estado();
//        Carrito carrito = new Carrito();
//
//
//        System.out.println("Bienvenido a nuestro E-Commerce");
//        while (true) {
//            System.out.println("Menu:");
//            if (!e.isSesionIniciada())
//                System.out.println("1- Iniciar Sesion \n2- Registrarse \n3- Ver Catalogo \n4- Ver Carrito \n5- Salir");
//            else
//                System.out.println("1- Cerrar Sesion \n2- Perfil \n3- Ver Catalogo \n4- Ver Carrito \n5- Salir");
//
//            System.out.print("Seleccione una opción: ");
//            seleccion = Integer.parseInt(s.nextLine());
//            if (seleccion == 5) {
//                break;
//            } else if (seleccion == 3) {
//                List<Producto> productos = serviceProducto.obtenerTodos(); imprimirCatalogo(productos);
//                if (e.isSesionIniciada()) {
//                    Long seleccionProducto; Producto producto;
//                    int cantidadProducto;
//                    System.out.print("Producto que desea agregar (-1 para salir): ");
//                    seleccionProducto = Long.parseLong(s.nextLine());
//                    System.out.print("Cantidad: ");
//                    cantidadProducto = Integer.parseInt(s.nextLine());
//                    producto = serviceProducto.obtenerPorId(seleccionProducto);
//                    if (Objects.isNull(producto)) {
//                        System.out.println("El producto solicitado no existe");
//                    } else {
//                        //carrito.agregarElemento(producto, cantidadProducto);
//                        serviceCarrito.actualizar(carrito.getId(), producto.getId(), cantidadProducto);
//                        carrito = obtenerCarrito(e.getUsername());
//                    }
//                }
//
//            } else if (seleccion == 1) {
//                if (!e.isSesionIniciada()) // Iniciar Sesion
//                    inicioDeSesion(s, e, carrito);
//                else                        // Cerrar Sesion
//                    if (cerrarSesion(e)) {
//                        serviceCarrito.guardarCarrito(carrito);
//                        System.out.println("Sesion cerrada");
//                    }
//                    else {
//                        System.out.println("Error al cerrar la sesion");
//                    }
//
//            } else if (seleccion == 2) {
//                if (!e.isSesionIniciada()) { // Si la sesion no esta iniciada va a registro
//                    registro(s);
//                }
//
//            } else if (seleccion == 4) {
//
//                if (!e.isSesionIniciada()) {
//                    System.out.println("Debes iniciar sesion para ver tu carrito");
//                } else {
//                    System.out.println(carrito);
//                }
//            }
//            System.out.println();
//        }
//    }
//
//    public Carrito obtenerCarrito(String username) {
//        return serviceCarrito.obtenerCarrito(username);
//    }
//
//    private void imprimirCatalogo(List<Producto> catalogo) {
//        for (Producto p : catalogo) {
//            System.out.println("ID: " + p.getId() +"\n"
//                    + "Nombre: " + p.getNombre() + "\n"
//                    + "Descripcion: " + p.getDescrpicion() + "\n"
//                    + "$" + p.getPrecio());
//            System.out.println("------------------");
//        }
//    }
//
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
//            if (!Objects.isNull(serviceCuenta.getCuentaByUsername(c.getUsername()))) {
//                System.out.println("Username ya utilizado, debe ser otro");
//            } else {
//                serviceCuenta.addCuenta(c);
//                Carrito carrito = new Carrito(c.getUsername());
//                serviceCarrito.guardarCarrito(carrito);
//                System.out.println("¡Registro exitoso!");
//            }
//        } catch (Exception e) {
//            System.out.println("Error al tratar de registrarse " + e);
//        }
//    }
//
//    private void inicioDeSesion(Scanner scanner, Estado estado, Carrito carrito) {
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
//            Carrito carritoRecuperado = serviceCarrito.obtenerCarrito(username);
//            carrito.setId(carritoRecuperado.getId());
//            carrito.setUsernameId(carritoRecuperado.getUsernameId());
//            carrito.setObjetos(carritoRecuperado.getObjetos());
//        }
//    }
//
//    public Cuenta iniciarSesion(String user, String password) {
//        Cuenta c = serviceCuenta.getCuentaByUser(user, password);
//        if (Objects.isNull(c)) {
//            return null;
//        } else {
//            return c;
//        }
//    }
//
//    public boolean cerrarSesion(Estado e) {
//        e.cerrarSesion();
//        return !e.isSesionIniciada();
//    }
//}

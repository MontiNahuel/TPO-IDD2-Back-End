package org.example.idi2.extra;

import org.example.idi2.modelo.entidad.*;

import java.util.List;

public class Extra {

    //---------------------------Impresion de menú------------------------------//
    public static void imprimirMenu(boolean sesionIniciada) {
        System.out.println("Menu:");
        if (!sesionIniciada)
            System.out.println("1- Iniciar Sesion \n2- Registrarse \n3- Ver Catalogo \n4- Ver Carrito \n5- Salir");
        else
            System.out.println("1- Cerrar Sesion \n2- Perfil \n3- Ver Catalogo \n4- Ver Carrito \n5- Salir");
    }

    //---------------------------Verificar si los pedidos estan todos pagos------------------------------//
    public static boolean pedidosTodosPagos(List<Pedido> pedidos) {
        boolean retorno = true; int puntero = 0;
        while (retorno && puntero < pedidos.size()) {
            if (!pedidos.get(puntero).isPago()) {
                retorno = false;
            } else {
                puntero++;
            }
        }
        return retorno;
    }

    //---------------------------Impresion de pedidos cambiando su id por numeros correlativos------------------------------//
    public static void impresionDePedidos(List<Pedido> pedidos) {
        int cont = 1;
        for (Pedido p : pedidos) {
            if (!p.isPago()) {
                System.out.println("ID: " + cont);
                for (ProductoParaPedido o : p.getProductos()) {
                    System.out.println(o.getNombreProducto() +
                            " | Cantidad: " + o.getCantProducto() +
                            " | Precio total: " + o.getPrecio()*o.getCantProducto());
                }
                System.out.println("Estado: " + p.isPago());
                System.out.println("Total del pedido: " + p.obtenerTotal());
            }
            cont++;
        }
    }

    //--------------------------- Impresion de metodos de pago ------------------------------//
    public static void impresionDeMetodosDePago(List<MetodoDePago> metodos) {
        int n = 1;
        for (MetodoDePago m : metodos) {
            System.out.println(
                    "Metodo: " + n + "\n Nombre: " + m.getNombreAsociado() + "\n Tipo de pago: " + m.getTipoDePago()
            );
            n++;
        }
    }

    //--------------------------- Impresion de nueva factura ------------------------------//
    public static void impresionDeFactura(Factura factura) {
        System.out.println("A nombre de: " + factura.getUsername());
        System.out.println("Fecha de emisión: " + factura.getFechaEmision());
        System.out.println("Pedido/s pago/s: ");
        impresionDePedidos(factura.getIdPedido());
    }

    //--------------------------- Cambiar estado de pedidos no pagos a pagos ------------------------------//
    public static void cambiarEstadoDePedidos(List<Pedido> pedidosTotales, List<Pedido> pedidosPagos) {
        pedidosTotales.removeAll(pedidosPagos);
    }
}

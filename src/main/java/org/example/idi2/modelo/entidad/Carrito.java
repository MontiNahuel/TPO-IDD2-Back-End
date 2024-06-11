package org.example.idi2.modelo.entidad;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

@Node
public class Carrito {
    @Id
    @GeneratedValue
    private Long id;
    private String usernameId;
    @Relationship(type = "tiene")
    private List<ParProductoCantidad> objetos;

    public Carrito(Long id, String usernameId, List<ParProductoCantidad> objetos) {
        this.id = id;
        this.usernameId = usernameId;
        this.objetos = objetos;
    }

    public Carrito(String usernameId) {
        this.usernameId = usernameId;
        this.objetos = new ArrayList<>();
    }

    public Carrito() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsernameId() {
        return usernameId;
    }

    public void setUsernameId(String usernameId) {
        this.usernameId = usernameId;
    }

    public List<ParProductoCantidad> getObjetos() {
        return objetos;
    }

    public void setObjetos(List<ParProductoCantidad> objetos) {
        this.objetos = objetos;
    }

    public void agregarElemento(Producto p, int cantidad) {
        boolean flag = false; int puntero = 0;
        while (puntero < objetos.size() && !flag) {
            if (objetos.get(puntero).getProducto().getId().equals(p.getId())) {
                objetos.get(puntero).setCantidad( objetos.get(puntero).getCantidad() + cantidad );
                flag = true;
            } else {
                puntero++;
            }
        }
        if (!flag) {
            ParProductoCantidad pc1 = new ParProductoCantidad(p, cantidad);
            objetos.add(pc1);
        }
    }

    @Override
    public String toString() {
        return "Carrito{" +
                "id=" + id +
                ", usernameId='" + usernameId + '\'' +
                ", objetos=" + objetos +
                '}';
    }
}

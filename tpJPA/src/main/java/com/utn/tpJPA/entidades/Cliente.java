package com.utn.tpJPA.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cliente")
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    //RELACIONES
@OneToMany(cascade = CascadeType.ALL , orphanRemoval = true , fetch = FetchType.EAGER)
@JoinColumn(name = "cliente_id")
@Builder.Default
private List<Domicilio> domicilios = new ArrayList<>();
//1 cliente puede tener muchos domicilios
    public void agregarDomicilio(Domicilio dom1){
        domicilios.add(dom1);
    }

    public void mostrarDomicilios() {
        System.out.println("Domicilios de " + nombre + " " + apellido + ":");
        for (Domicilio domicilio : domicilios) {
            System.out.println("Calle: " + domicilio.getCalle() + ", Número: " + domicilio.getNro());
        }
    }

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name="cliente_id")
    @Builder.Default
    private List<Pedido> pedidos =new ArrayList<>();
//1 cliente puede realizar muchos pedidos

public void agregarPedidos(Pedido ped){
    pedidos.add(ped);
}

    public void mostrarPedidos() {
        System.out.println("Pedidos de " + nombre + " " + apellido + ":");
        for (Pedido pedido : pedidos) {
            System.out.println("Fecha: " + pedido.getFecha() + ", Total: " + pedido.getTotalP());
            int counter=0;
            for (DetallePedido detallePedido : pedido.getDetallePedidos()){
                counter +=1;
                System.out.println("Producto: "+counter+"  "+ detallePedido.getProducto().getDenominacion()+"  cantidad"+detallePedido.getCantidad() +"   subtotal"+detallePedido.getSubtotal());
            }
        }
    }



}

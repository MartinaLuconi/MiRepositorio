package com.utn.tpJPA.entidades;

import com.utn.tpJPA.enumeraciones.Estado;
import com.utn.tpJPA.enumeraciones.TipoEnvio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pedido extends BaseEntidades {
    private Estado estado;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private TipoEnvio tipoEnvio;
    private double totalP;

@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
@JoinColumn(name = "pedido_id")
@Builder.Default
private List<DetallePedido> detallePedidos= new ArrayList<>();
//1 pedido tiene muchos detalles pedidos
public void agregarDetallePedido(DetallePedido dp){
    detallePedidos.add(dp);
}
//1 pedido corresponde a 1 y solo 1 factura
@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
@JoinColumn(name = "factura_id")
private Factura factura;


}

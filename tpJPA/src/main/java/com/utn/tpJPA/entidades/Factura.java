package com.utn.tpJPA.entidades;

import com.utn.tpJPA.enumeraciones.FormaPago;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Factura extends BaseEntidades {
    public int numero;
    public Date fecha;
    public double descuento;
    public FormaPago formaPago ;
    public int total;

}

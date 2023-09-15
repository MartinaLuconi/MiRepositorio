package com.utn.tpJPA.repositorios;

import com.utn.tpJPA.entidades.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido,Long> {

}

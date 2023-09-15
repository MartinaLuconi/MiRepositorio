package com.utn.tpJPA.repositorios;

import com.utn.tpJPA.entidades.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DPRepositorio extends JpaRepository<DetallePedido,Long> {
}

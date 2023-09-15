package com.utn.tpJPA.repositorios;

import com.utn.tpJPA.entidades.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepositorio extends JpaRepository<Factura,Long>{


}

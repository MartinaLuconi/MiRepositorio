package com.utn.tpJPA.repositorios;

import com.utn.tpJPA.entidades.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomicilioRepositorio extends JpaRepository<Domicilio,Long> {
}

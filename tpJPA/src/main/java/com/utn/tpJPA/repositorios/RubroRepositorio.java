package com.utn.tpJPA.repositorios;

import com.utn.tpJPA.entidades.Rubro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RubroRepositorio extends JpaRepository <Rubro,Long>{
}

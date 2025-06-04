package com.example.Reform.repositories;

import com.example.Reform.entities.Empresa;
import com.example.Reform.entities.EmpresaMaterial;
import com.example.Reform.entities.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmpresaMaterialRepository extends JpaRepository<EmpresaMaterial, Long> {
    List<EmpresaMaterial> findByEmpresa(Empresa empresa);
    List<EmpresaMaterial> findByMaterial(Material material);
}

package com.example.Reform.services;

import com.example.Reform.entities.Empresa;
import com.example.Reform.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Empresa> findAllEmpresas(){
        return empresaRepository.findAll();
    }

    public Optional<Empresa> findEmpresaById(Long id){
        return empresaRepository.findById(id);
    }

    public Empresa saveEmpresa(Empresa empresa){
        Optional<Empresa> empresaExistente = empresaRepository.findByLogin(empresa.getLogin());
        if (empresaExistente.isPresent()) {
            throw new RuntimeException("Login já está em uso.");
        }

        empresa.setSenha(passwordEncoder.encode(empresa.getSenha()));

        return empresaRepository.save(empresa);
    }
    public Empresa updateEmpresa(Long id, Empresa empresa){
        Optional<Empresa> empresaFind = empresaRepository.findById(id);

        if(empresaFind.isPresent()){
            Empresa empresaUpdate = empresaFind.get();
            empresaUpdate.setNome(empresa.getNome());
            empresaUpdate.setEmail(empresa.getEmail());
            empresaUpdate.setCnpj(empresa.getCnpj());
            empresaUpdate.setLogin(empresa.getLogin());
            empresaUpdate.setSenha(empresa.getSenha());

            return empresaRepository.save(empresaUpdate);

        }else {
            throw new RuntimeException("Empresa não encontrada com ID:"+id);
        }
    }

    public void deleteEmpresaById(Long id){
        empresaRepository.deleteById(id);
    }

    public Optional<Empresa> findEmpresaByLogin(String login){
        return empresaRepository.findByLogin(login);
    }


}

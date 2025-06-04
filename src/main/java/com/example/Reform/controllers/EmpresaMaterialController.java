package com.example.Reform.controllers;

import com.example.Reform.entities.Empresa;
import com.example.Reform.entities.EmpresaMaterial;
import com.example.Reform.services.EmpresaMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/empresamateriais")
@CrossOrigin(origins = "http://localhost:5173")
public class EmpresaMaterialController {

    @Autowired
    private EmpresaMaterialService empresaMaterialService;

    @GetMapping
    public ResponseEntity<List<EmpresaMaterial>> getAllEmpresaMaterials(){
        List<EmpresaMaterial> empresaMaterialList = empresaMaterialService.findAllEmpresaMaterial();

        if(empresaMaterialList.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(empresaMaterialList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaMaterial> getEmpresaMaterialById(@PathVariable Long id){
        return empresaMaterialService.findAllEmpresaMaterialById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/empresa/{id}")
    public ResponseEntity<List<EmpresaMaterial>> getEmpresaMaterialByIdEmpresa(@PathVariable Long id){
        List<EmpresaMaterial> empresaMaterialList = empresaMaterialService.findMaterialsByEmpresaId(id);
        if(empresaMaterialList.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(empresaMaterialList);
    }
    @GetMapping("/material/{id}")
    public ResponseEntity<List<EmpresaMaterial>> getEmpresaMaterialByIdMaterial(@PathVariable Long id){
        List<EmpresaMaterial> empresaMaterialList = empresaMaterialService.findMaterialsByMaterialId(id);
        if(empresaMaterialList.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(empresaMaterialList);
    }

    @PostMapping
    public  ResponseEntity<EmpresaMaterial> createEmpresaMaterial(@RequestBody EmpresaMaterial empresaMaterial){
        EmpresaMaterial empresaMaterialCreated = empresaMaterialService.saveEmpresaMaterial(empresaMaterial);

        return ResponseEntity.status(HttpStatus.CREATED).body(empresaMaterialCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaMaterial> updateEmpresaMaterial(@PathVariable Long id, @RequestBody EmpresaMaterial empresaMaterial){
        try{
            EmpresaMaterial empresaMaterialUpdate = empresaMaterialService.updateEmpresaMaterial(id, empresaMaterial);
            return ResponseEntity.ok(empresaMaterialUpdate);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpresaMaterial(@PathVariable Long id){
        empresaMaterialService.deleteEmpresaMaterialById(id);
        return ResponseEntity.noContent().build();
    }

}

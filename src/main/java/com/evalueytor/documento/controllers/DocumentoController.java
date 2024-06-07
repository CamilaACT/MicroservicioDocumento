package com.evalueytor.documento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Optional;
import com.evalueytor.documento.models.Documento;
import com.evalueytor.documento.repositories.DocumentoRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/documento")
public class DocumentoController {

    @Autowired
    DocumentoRepository documnetoRepository;

    // Listar todo
    @GetMapping("/findall")
    public List<Documento> list() {
        return documnetoRepository.findAll();
    }

    // Listar por Id
    @GetMapping("/findbyid/{id}")
    public ResponseEntity<Documento> obtenerEstadoPorId(@PathVariable Long id) {
        Optional<Documento> estadoOpcion = documnetoRepository.findById(id);
        return estadoOpcion.map(premio -> new ResponseEntity<>(premio, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

   // Crear nuevo documento
   @PostMapping("/save")
   public ResponseEntity<Documento> crearPremio(@RequestBody Documento nuevoPremio) {
       Documento premioGuardado = documnetoRepository.save(nuevoPremio);
       return new ResponseEntity<>(premioGuardado, HttpStatus.CREATED);
   }

    @PutMapping("/updatebyid/{id}")
    public ResponseEntity<Documento> actualizarEmopresa(@PathVariable Long id, @RequestBody Documento estadoActual) {
        Optional<Documento> estadoOptional = documnetoRepository.findById(id);
        return estadoOptional.map(estado -> {
            estado.setId(id);
            estado.setFormato(estadoActual.getFormato());
            Documento estadoActualGuardado = documnetoRepository.save(estado);
            return new ResponseEntity<>(estadoActualGuardado, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    
    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<Void> eliminarEmpresa(@PathVariable Long id) {
        Optional<Documento> estadoOptional = documnetoRepository.findById(id);
        if (estadoOptional.isPresent()) {
            documnetoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }









}

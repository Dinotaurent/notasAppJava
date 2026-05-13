package com.dinotaurent.notas_app.controller;


import com.dinotaurent.notas_app.model.Nota;
import com.dinotaurent.notas_app.service.NotaPageResponse;
import com.dinotaurent.notas_app.service.NotaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/${api.version}/notas")
@RequiredArgsConstructor
public class NotaController {

    private final NotaService service;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size){

        NotaPageResponse result = service.getAll(page, size);

        return ResponseEntity.ok(Map.of(
                "message","Notas obtenidas",
                "data", result.notas(),
                "total",result.total(),
                "pages", result.pages(),
                "error",false
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable String id){
        Nota nota = service.getById(id);

        return ResponseEntity.ok(Map.of(
                "message","Nota obtenida",
                "data", nota,
                "error",false
        ));
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody Nota nota){
        Nota created = service.create(nota);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message","Nota creada",
                "data", created,
                "error",false
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(
            @PathVariable String id,
            @Valid @RequestBody Nota nota) {
        Nota updated = service.update(id, nota);

        return ResponseEntity.ok(Map.of(
                "message", "Nota actualizada",
                "data", updated,
                "error",false
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable String id){
        service.delete(id);

        return ResponseEntity.ok(Map.of(
                "message", "Nota eliminada",
                "data", false
        ));
    }
}

package com.dinotaurent.notas_app.service;

import com.dinotaurent.notas_app.exception.NotaNotFoundException;
import com.dinotaurent.notas_app.model.Nota;
import com.dinotaurent.notas_app.repository.NotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotaService {

    private final NotaRepository repository;

    public NotaPageResponse getAll(int page, int size){
        Page<Nota> result = repository.findAll(PageRequest.of(page - 1,size));

        return new NotaPageResponse(
                result.getContent(),
                result.getTotalElements(),
                result.getTotalPages()
        );
    }

    public Nota getById(String id){
        return repository.findById(id).orElseThrow(() -> new NotaNotFoundException(id));
    }

    public Nota create(Nota nota){
        return repository.save(nota);
    }

    public Nota update(String id,Nota nota){
        Nota existing = getById(id);
        existing.setTitulo(nota.getTitulo());
        existing.setContenido(nota.getContenido());

        return repository.save(existing);
    }

    public void delete(String id){
        getById(id);
        repository.deleteById(id);
    }
}

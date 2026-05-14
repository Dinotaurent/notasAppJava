package com.dinotaurent.notas_app.repository;


import com.dinotaurent.notas_app.model.Nota;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaRepository extends MongoRepository<Nota, String> {
}

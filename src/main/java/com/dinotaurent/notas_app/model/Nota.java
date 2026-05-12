package com.dinotaurent.notas_app.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Builder
@Document(collection = "notas")
public class Nota {

    @Id
    private String id;

    @NotBlank(message = "El titulo es obligatorio")
    @Size(min = 3, max = 20, message = "El titulo debe tener entre 3 y 20 caracteres")
    private String titulo;

    @NotBlank(message = "El contenido es obligatorio")
    @Size(min = 6,max = 60, message = "El contenido de tener entre 6 y 60 caracteres")
    private String contenido;
}

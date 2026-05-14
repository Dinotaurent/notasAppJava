package com.dinotaurent.notas_app.exception;

public class NotaNotFoundException extends RuntimeException{

    public NotaNotFoundException(String id){
        super("Nota no encontrada con el id:" + id);
    }
}

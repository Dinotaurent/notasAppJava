package com.dinotaurent.notas_app.service;

import com.dinotaurent.notas_app.model.Nota;

import java.util.List;

public record NotaPageResponse(List<Nota> notas, long total, int pages) {
}

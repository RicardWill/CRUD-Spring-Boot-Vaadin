package com.example.application.Controlador;

import com.example.application.Persistencia.Entidad.Entrada;
import com.example.application.Persistencia.Repositorio.RepositorioEntrada;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ControlEntradas {

    private final RepositorioEntrada repositorioEntrada;


    public ControlEntradas(RepositorioEntrada repositorioEntrada) {

        this.repositorioEntrada = repositorioEntrada;
    }

    public List<Entrada> findAllEntradas(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return repositorioEntrada.findAll();
        } else {
            return repositorioEntrada.search(stringFilter);
        }
    }

    public long countEntradas() {
        return repositorioEntrada.count();
    }

    public void deleteEntrada(Entrada entrada) {
        repositorioEntrada.delete(entrada);
    }

    public void saveEntrada(Entrada entrada) {
        if (entrada == null) {
            System.err.println("Entrada es null you sure you have connected your form to the application?");
            return;
        }
        repositorioEntrada.save(entrada);
    }


}

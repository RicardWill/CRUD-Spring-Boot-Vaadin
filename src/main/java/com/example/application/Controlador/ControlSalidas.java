package com.example.application.Controlador;

import com.example.application.Persistencia.Entidad.Salida;
import com.example.application.Persistencia.Repositorio.RepositorioSalida;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ControlSalidas {

    private final RepositorioSalida repositorioSalida;


    public ControlSalidas(RepositorioSalida repositorioSalida) {
        this.repositorioSalida = repositorioSalida;
    }

    public List<Salida> findAllSalidas(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return repositorioSalida.findAll();
        } else {
            return repositorioSalida.search(stringFilter);
        }
    }

    public long countSalidas() {

        return repositorioSalida.count();
    }

    public void deleteSalida(Salida salida) {

        repositorioSalida.delete(salida);
    }

    public void saveSalida(Salida salida) {
        if (salida == null) {
            System.err.println("Salida es null you sure you have connected your form to the application?");
            return;
        }
        repositorioSalida.save(salida);
    }

}

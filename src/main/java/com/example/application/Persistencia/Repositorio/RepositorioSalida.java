package com.example.application.Persistencia.Repositorio;

import com.example.application.Persistencia.Entidad.Salida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepositorioSalida extends JpaRepository<Salida, Integer> {
    //Filtrar para el campo de busqueda (por id de empleado)
    @Query("select c from Salida c " +
            "where lower(c.claveEmpleado) like lower(concat('%', :searchTerm, '%')) ")
    List<Salida> search(@Param("searchTerm") String searchTerm);
}

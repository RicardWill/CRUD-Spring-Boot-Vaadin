package com.example.application.Persistencia.Entidad;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;


@Entity

@Getter
@Setter
public class Entrada extends Entidad_Abstracta{

    @NotEmpty
    private String claveEmpleado = "";

    @NotEmpty
    private String fechaEntrada;
    //private LocalDate fechaEntrada;

    @NotEmpty
    private String horaEntrada;
    //private LocalTime horaEntrada;

    @NotEmpty
    private String identificadorKiosko = "";


}

package com.example.application.Persistencia.Entidad;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Salida extends Entidad_Abstracta{

    @NotEmpty
    private String claveEmpleado = "";

    @NotEmpty
    private String fechaSalida;
    //private LocalDate fechaEntrada;

    @NotEmpty
    private String horaSalida;
    //private LocalTime horaEntrada;

    @NotEmpty
    private String identificadorKiosko = "";


}

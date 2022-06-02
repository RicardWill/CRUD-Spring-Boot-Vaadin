package com.example.application.Persistencia.Entidad;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public class Entidad_Abstracta {

    @Id
    @GeneratedValue
    private Integer id;

    @Override
    public int hashCode() {
        if (id != null) {
            return id.hashCode();
        }
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Entidad_Abstracta)) {
            //Nulo
            return false;
        }
        Entidad_Abstracta other = (Entidad_Abstracta) obj;

        if (id != null) {
            return id.equals(other.id);
        }
        return super.equals(other);
    }

}

package com.mariangel.administracion_tarea.Model;

import com.mariangel.administracion_tarea.Model.Tour;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-06-04T01:58:16", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Itinerario.class)
public class Itinerario_ { 

    public static volatile SingularAttribute<Itinerario, String> intLugar;
    public static volatile SingularAttribute<Itinerario, Long> intId;
    public static volatile SingularAttribute<Itinerario, Short> intDuracion;
    public static volatile SingularAttribute<Itinerario, Tour> intCodigotour;
    public static volatile SingularAttribute<Itinerario, String> intActividades;

}
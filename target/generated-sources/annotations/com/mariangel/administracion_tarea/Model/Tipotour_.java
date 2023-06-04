package com.mariangel.administracion_tarea.Model;

import com.mariangel.administracion_tarea.Model.Tour;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-06-03T18:03:55", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Tipotour.class)
public class Tipotour_ { 

    public static volatile SingularAttribute<Tipotour, String> ttPais;
    public static volatile SingularAttribute<Tipotour, Long> ttCodigo;
    public static volatile SingularAttribute<Tipotour, String> ttTipo;
    public static volatile CollectionAttribute<Tipotour, Tour> tourCollection;
    public static volatile SingularAttribute<Tipotour, String> ttNombretour;

}
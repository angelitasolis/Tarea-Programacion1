package com.mariangel.administracion_tarea.Model;

import com.mariangel.administracion_tarea.Model.Tour;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-06-03T18:03:55", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Empresa.class)
public class Empresa_ { 

    public static volatile SingularAttribute<Empresa, Long> emCalificacion;
    public static volatile SingularAttribute<Empresa, Long> emTelefono;
    public static volatile SingularAttribute<Empresa, String> emNombre;
    public static volatile SingularAttribute<Empresa, Date> emFechafundacion;
    public static volatile CollectionAttribute<Empresa, Tour> tourCollection;
    public static volatile SingularAttribute<Empresa, String> emCorreo;
    public static volatile SingularAttribute<Empresa, String> emCedulajuridica;

}
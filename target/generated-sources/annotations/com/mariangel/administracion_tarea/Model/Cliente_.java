package com.mariangel.administracion_tarea.Model;

import com.mariangel.administracion_tarea.Model.Reserva;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-06-07T02:46:50", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile SingularAttribute<Cliente, String> cltPapellido;
    public static volatile SingularAttribute<Cliente, Date> cltFechanac;
    public static volatile SingularAttribute<Cliente, String> cltNombre;
    public static volatile SingularAttribute<Cliente, String> cltCedula;
    public static volatile SingularAttribute<Cliente, String> cltSapellido;
    public static volatile CollectionAttribute<Cliente, Reserva> reservaCollection;
    public static volatile SingularAttribute<Cliente, Long> cltTelefono;
    public static volatile SingularAttribute<Cliente, String> cltCorreo;

}
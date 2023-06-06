package com.mariangel.administracion_tarea.Model;

import com.mariangel.administracion_tarea.Model.Cliente;
import com.mariangel.administracion_tarea.Model.Tour;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-06-05T20:19:38", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Reserva.class)
public class Reserva_ { 

    public static volatile SingularAttribute<Reserva, Long> rsTrscosto;
    public static volatile SingularAttribute<Reserva, Long> rsMontoabonado;
    public static volatile SingularAttribute<Reserva, Tour> rsCodigotour;
    public static volatile SingularAttribute<Reserva, Long> rsId;
    public static volatile SingularAttribute<Reserva, Date> rsFechareserva;
    public static volatile SingularAttribute<Reserva, Cliente> rsCedulacliente;

}
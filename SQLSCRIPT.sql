prompt PL/SQL Developer Export Tables for user MSOLIS@UNA
prompt Created by Administrador on sábado, 3 de junio de 2023
set feedback off
set define off

prompt Creating TBL_CLIENTE...
create table TBL_CLIENTE
(
  clt_cedula    VARCHAR2(30) not null,
  clt_nombre    VARCHAR2(30) not null,
  clt_papellido VARCHAR2(30) not null,
  clt_sapellido VARCHAR2(30) not null,
  clt_telefono  NUMBER(15) not null,
  clt_correo    VARCHAR2(100) not null,
  clt_fechanac  DATE not null
)
tablespace UNA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table TBL_CLIENTE
  add constraint PK_CLIENTE primary key (CLT_CEDULA)
  using index 
  tablespace UNA
  pctfree 10
  initrans 2
  maxtrans 255;

prompt Creating TBL_EMPRESA...
create table TBL_EMPRESA
(
  em_nombre         VARCHAR2(30) not null,
  em_cedulajuridica VARCHAR2(30) not null,
  em_telefono       NUMBER(15) not null,
  em_correo         VARCHAR2(100) not null,
  em_fechafundacion DATE not null,
  em_calificacion   NUMBER(1)
)
tablespace UNA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table TBL_EMPRESA
  add constraint PK_EMPRESA primary key (EM_CEDULAJURIDICA)
  using index 
  tablespace UNA
  pctfree 10
  initrans 2
  maxtrans 255;
alter table TBL_EMPRESA
  add constraint CHECK_CALIFICACION
  check (EM_CALIFICACION >=1 And  EM_CALIFICACION<= 5);

prompt Creating TBL_TIPOTOUR...
create table TBL_TIPOTOUR
(
  tt_codigo     NUMBER(5) not null,
  tt_tipo       VARCHAR2(15) not null,
  tt_nombretour VARCHAR2(30) not null,
  tt_pais       VARCHAR2(30) not null
)
tablespace UNA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table TBL_TIPOTOUR
  add constraint PK_TIPOTOURS primary key (TT_CODIGO)
  using index 
  tablespace UNA
  pctfree 10
  initrans 2
  maxtrans 255;

prompt Creating TBL_TOURS...
create table TBL_TOURS
(
  trs_nombre           VARCHAR2(30) not null,
  trs_empresacedjur    VARCHAR2(30) not null,
  trs_fechasalida      DATE not null,
  trs_fechallegada     DATE not null,
  trs_costotour        NUMBER(7) not null,
  trs_cantidadclientes NUMBER(5) not null,
  trs_codigotour       VARCHAR2(30) not null,
  trs_horasalida       NUMBER(2) not null,
  trs_horallegada      NUMBER(2) not null,
  trs_tipotourcodigo   NUMBER(5) not null
)
tablespace UNA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table TBL_TOURS
  add constraint PK_TOURS primary key (TRS_CODIGOTOUR)
  using index 
  tablespace UNA
  pctfree 10
  initrans 2
  maxtrans 255;
alter table TBL_TOURS
  add constraint FK_TRSEM foreign key (TRS_EMPRESACEDJUR)
  references TBL_EMPRESA (EM_CEDULAJURIDICA);
alter table TBL_TOURS
  add constraint FK_TRSTT foreign key (TRS_TIPOTOURCODIGO)
  references TBL_TIPOTOUR (TT_CODIGO);
alter table TBL_TOURS
  add constraint CHECK_HORALLEGADA
  check (TRS_HORALLEGADA >=1 And  TRS_HORALLEGADA<= 24);
alter table TBL_TOURS
  add constraint CHECK_HORASALIDA
  check (TRS_HORASALIDA >=1 And TRS_HORASALIDA <= 24);

prompt Creating TBL_ITINERARIO...
create table TBL_ITINERARIO
(
  int_id          NUMBER(5) not null,
  int_codigotour  VARCHAR2(30) not null,
  int_lugar       VARCHAR2(30) not null,
  int_duracion    NUMBER(2),
  int_actividades VARCHAR2(150)
)
tablespace UNA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table TBL_ITINERARIO
  add constraint PK_ITINERARIO primary key (INT_ID)
  using index 
  tablespace UNA
  pctfree 10
  initrans 2
  maxtrans 255;
alter table TBL_ITINERARIO
  add constraint FK_TRSINT foreign key (INT_CODIGOTOUR)
  references TBL_TOURS (TRS_CODIGOTOUR);

prompt Creating TBL_RESERVAS...
create table TBL_RESERVAS
(
  rs_id            NUMBER(5) not null,
  rs_cedulacliente VARCHAR2(30) not null,
  rs_codigotour    VARCHAR2(30) not null,
  rs_fechareserva  DATE not null,
  rs_montoabonado  NUMBER(7) not null,
  rs_trscosto      NUMBER(7) not null
)
tablespace UNA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table TBL_RESERVAS
  add constraint PK_RESERVAS primary key (RS_ID)
  using index 
  tablespace UNA
  pctfree 10
  initrans 2
  maxtrans 255;
alter table TBL_RESERVAS
  add constraint FK_CLTRS foreign key (RS_CEDULACLIENTE)
  references TBL_CLIENTE (CLT_CEDULA);
alter table TBL_RESERVAS
  add constraint FK_TRSRS foreign key (RS_CODIGOTOUR)
  references TBL_TOURS (TRS_CODIGOTOUR);

prompt Disabling triggers for TBL_CLIENTE...
alter table TBL_CLIENTE disable all triggers;
prompt Disabling triggers for TBL_EMPRESA...
alter table TBL_EMPRESA disable all triggers;
prompt Disabling triggers for TBL_TIPOTOUR...
alter table TBL_TIPOTOUR disable all triggers;
prompt Disabling triggers for TBL_TOURS...
alter table TBL_TOURS disable all triggers;
prompt Disabling triggers for TBL_ITINERARIO...
alter table TBL_ITINERARIO disable all triggers;
prompt Disabling triggers for TBL_RESERVAS...
alter table TBL_RESERVAS disable all triggers;
prompt Disabling foreign key constraints for TBL_TOURS...
alter table TBL_TOURS disable constraint FK_TRSEM;
alter table TBL_TOURS disable constraint FK_TRSTT;
prompt Disabling foreign key constraints for TBL_ITINERARIO...
alter table TBL_ITINERARIO disable constraint FK_TRSINT;
prompt Disabling foreign key constraints for TBL_RESERVAS...
alter table TBL_RESERVAS disable constraint FK_CLTRS;
alter table TBL_RESERVAS disable constraint FK_TRSRS;
prompt Deleting TBL_RESERVAS...
delete from TBL_RESERVAS;
commit;
prompt Deleting TBL_ITINERARIO...
delete from TBL_ITINERARIO;
commit;
prompt Deleting TBL_TOURS...
delete from TBL_TOURS;
commit;
prompt Deleting TBL_TIPOTOUR...
delete from TBL_TIPOTOUR;
commit;
prompt Deleting TBL_EMPRESA...
delete from TBL_EMPRESA;
commit;
prompt Deleting TBL_CLIENTE...
delete from TBL_CLIENTE;
commit;
prompt Loading TBL_CLIENTE...
prompt Table is empty
prompt Loading TBL_EMPRESA...
prompt Table is empty
prompt Loading TBL_TIPOTOUR...
prompt Table is empty
prompt Loading TBL_TOURS...
prompt Table is empty
prompt Loading TBL_ITINERARIO...
prompt Table is empty
prompt Loading TBL_RESERVAS...
prompt Table is empty
prompt Enabling foreign key constraints for TBL_TOURS...
alter table TBL_TOURS enable constraint FK_TRSEM;
alter table TBL_TOURS enable constraint FK_TRSTT;
prompt Enabling foreign key constraints for TBL_ITINERARIO...
alter table TBL_ITINERARIO enable constraint FK_TRSINT;
prompt Enabling foreign key constraints for TBL_RESERVAS...
alter table TBL_RESERVAS enable constraint FK_CLTRS;
alter table TBL_RESERVAS enable constraint FK_TRSRS;
prompt Enabling triggers for TBL_CLIENTE...
alter table TBL_CLIENTE enable all triggers;
prompt Enabling triggers for TBL_EMPRESA...
alter table TBL_EMPRESA enable all triggers;
prompt Enabling triggers for TBL_TIPOTOUR...
alter table TBL_TIPOTOUR enable all triggers;
prompt Enabling triggers for TBL_TOURS...
alter table TBL_TOURS enable all triggers;
prompt Enabling triggers for TBL_ITINERARIO...
alter table TBL_ITINERARIO enable all triggers;
prompt Enabling triggers for TBL_RESERVAS...
alter table TBL_RESERVAS enable all triggers;

set feedback on
set define on
prompt Done

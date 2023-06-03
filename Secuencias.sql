prompt PL/SQL Developer Export User Objects for user MSOLIS@UNA
prompt Created by Administrador on miércoles, 31 de mayo de 2023
set define off
spool Secuencias.log

prompt
prompt Creating sequence SEC_ITINERARIO
prompt ================================
prompt
create sequence MSOLIS.SEC_ITINERARIO
minvalue 1
maxvalue 10000
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEC_RESERVAS
prompt ==============================
prompt
create sequence MSOLIS.SEC_RESERVAS
minvalue 1
maxvalue 10000
start with 1
increment by 1
cache 20;


prompt Done
spool off
set define on

#drop database app_losjardines;
create database app_losjardines;

use app_losjardines;

create table Cliente(
Dni_Cli char(8) primary key,
Nomb_Cli varchar(20) not null,
Ape_Cli varchar(20) not null,
Correo_Cli varchar(30) unique not null,
Contra_Cli varchar(20) not null,
Cel_Cli varchar(15) unique not null
);

insert into cliente values
('00000000', 'Nombre', 'Apellido', 'correo@email.com', '000', '111111111'),
('72673554', 'Milhos', 'Sihuay', 'mi@g.com', '123', '997653086' ),
('70829460', 'Luiggi', 'Rebatta', 'lu@g.com', '123', '969599087' ),
('12345677', 'Marcelo', 'Yabar', 'ma@g.com', '123', '37373732' );


create procedure sp_ListarCLI()#--------
select * from Cliente;

create procedure sp_InsertarCLI(#--------
Dni char(8) ,
Nombre varchar(20),
Apellido varchar(20) ,
Correo varchar(30) ,
Contrasena varchar(20),
Celular varchar(10) 
)
insert into Cliente values(Dni,Nombre,Apellido,Correo,Contrasena,Celular);

create procedure sp_EliminarCLI(#---------------
Dni char(8))
delete from Cliente where Dni_Cli=Dni;

create procedure sp_ConsultarCLI(
Correo varchar(30),
Pass varchar(20))
select * from Cliente where Correo_Cli = Correo and Contra_Cli = Pass;

create procedure sp_EditarPassCLI(#-----------------------
Dni char(8) ,
Contra varchar(20))
update Cliente set Contra_Cli=Contra where Dni_Cli=Dni;


create procedure sp_ConsultarDniCLI(#-------------------------
Dni char(8))
select * from Cliente where Dni_Cli=Dni;

create procedure sp_ConsultarCorreoCLI(#-------------------------
Correo char(20))
select * from Cliente where Correo_Cli=Correo;

#-------------------------ADMIN--------
create table Admin(
Dni_Adm char(8) primary key,
Nomb_Adm varchar(20) not null,
Ape_Adm varchar(20) not null,
Correo_Adm varchar(30) unique not null,
Contra_Adm varchar(20) not null,
Cel_Admin varchar(15) unique not null
);

insert into admin values
('72673554', 'Milhos', 'Sihuay', 'mi_adm@g.com', '123', '997653086' ),
('70829460', 'Luiggi', 'Rebatta', 'lu_adm@g.com', '123', '969599087' ),
('12345677', 'Marcelo', 'Yabar', 'ma_adm@g.com', '123', '37373732' );

create procedure sp_ConsultarADM(
Correo varchar(30),
Pass varchar(20))
select * from Admin where Correo_Adm = Correo and Contra_Adm = Pass;

create procedure sp_ConsultarDniADM(#-------------------------
Dni char(9))
select * from Admin where Dni_Adm=Dni;

create procedure sp_ConsultarCorreoADM(#-------------------------
Correo char(20))
select * from Admin where Correo_Adm=Correo;


#----------------tabla reserva------

create table RESERVA(
DIA_rsv int primary key,
HORA3 boolean not null,
HORA5 boolean not null,
HORA7 boolean not null,
DNI_H3 char(8) not null,
DNI_H5 char(8) not null,
DNI_H7 char(8) not null,
foreign key(DNI_H3) references Cliente(dni_cli)
on update cascade on delete cascade,
foreign key(DNI_H5) references Cliente(dni_cli)
on update cascade on delete cascade,
foreign key(DNI_H7) references Cliente(dni_cli)
on update cascade on delete cascade
);

insert into reserva values
(1,false,false,false,'00000000','00000000','00000000'),
(2,false,false,false,'00000000','00000000','00000000'),
(3,false,false,false,'00000000','00000000','00000000'),
(4,false,false,false,'00000000','00000000','00000000'),
(5,false,false,false,'00000000','00000000','00000000'),
(6,false,false,false,'00000000','00000000','00000000');

#insert into reserva values (day(curdate()),false,false,false);

create procedure sp_ListarRESERVA()#--------
select * from reserva;

create procedure sp_ReservarH3(#-----------------------EDIT
dia int,
hora boolean,
dni char(8))
update Reserva set hora3=hora, dni_h3=dni where dia_rsv=dia;

create procedure sp_ReservarH5(#-----------------------EDIT
dia int,
hora boolean,
dni char(8))
update Reserva set hora5=hora,dni_h5=dni where dia_rsv=dia;

create procedure sp_ReservarH7(#-----------------------EDIT
dia int,
hora boolean,
dni char(8))
update Reserva set hora7=hora, dni_h7=dni where dia_rsv=dia;


create procedure sp_ConsultarRsvCLI(#-------------------------
Dni char(8))
select * from Reserva where Dni_h3=Dni or dni_h5=Dni or dni_h7=dni;

call sp_ConsultarRsvCLI('72673554');
select * from Reserva;




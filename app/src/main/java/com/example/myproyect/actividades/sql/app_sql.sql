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
#drop table cliente;
insert into cliente values
('72673554', 'Milhos', 'Sihuay', 'mi@g.com', '123', '997653086' ),
('70829460', 'Luiggi', 'Rebatta', 'lu@g.com', '123', '969599087' ),
('12345677', 'Marcelo', 'Yabar', 'ma@g.com', '123', '37373732' );

#select * from cliente;

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

select * from admin;

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








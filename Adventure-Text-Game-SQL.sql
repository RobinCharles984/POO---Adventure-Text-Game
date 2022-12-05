drop database if exists integracao;
create database integracao;

#RELACAO 1 PRA 1 UM USUARIO "TEM ACESSO A" UM PLAYER
#RELACAO N PRA 1 USUARIO "NO" SERVIDOR
#RELACAO N PRA N PLAYERS "AMIGOS DE" PLAYERS

use integracao;

#Criacao
create table usuario (
    id_usuario int not null auto_increment primary key,
    nome_usuario varchar(45),
    senha_usuario varchar(45),
    id_servidor int not null
);

create table player(
    id int not null auto_increment primary key,
    playerName varchar(45) not null,
    playerWeapon varchar(45) not null,
    maxHP int not null,
    playerHP int not null,
    silverRing int not null,
    oldPingent int not null
);

create table servidor (
    id int not null auto_increment primary key,
    nUsuarios int not null
);

CREATE TABLE seguidor_de (
	id_seguido int not null primary key,
    id_seguidor int not null
);


#Feito para criar pelo menos um servidor, começando com 0 usuários
insert into servidor (id, nUsuarios) values (1,0);

#Visualizar as tabelas
select * from player;
select * from usuario;
select * from servidor;
select * from seguidor_de;

#Adicionando um usuário
insert into usuario(nome_usuario, senha_usuario, id_servidor) values ('Juliano', 1234, 1);

#Atualizando a senha desse usuário (caso ele tenha sido o primeiro adicionado)
update usuario set senha_usuario = 5678 where id_usuario = 1;

#Apagando esse usuário
delete from usuario where id_usuario = 4;
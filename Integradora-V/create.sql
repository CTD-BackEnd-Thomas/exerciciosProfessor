CREATE TABLE IF NOT EXISTS endereco (
id int auto_increment primary key,
rua varchar(32),
numero varchar(12),
cidade varchar(32),
bairro varchar(32));

CREATE TABLE IF NOT EXISTS paciente (
id int auto_increment primary key,
nome varchar(16),
sobrenome varchar(48),
rg varchar(10),
data_cadastro TIMESTAMP WITHOUT TIME ZONE,
endereco_id int);

create table curso (
	id serial primary key,
	nome varchar (60) not null,
    sigla varchar (10) not null,
    data_fundacao date not null,
    email_coordenacao varchar(40),
    telefone_coordenacao varchar(15)
);

create table aluno (
	id serial primary key,
	nome varchar (45) not null,
	matricula varchar(15) not null,
	data_nascimento date not null,
    possui_necessidade_especifica boolean not null default false,
    descricao_necessidade_especifica varchar(100) not null,
    id_curso integer not null,
    foreign key (id_curso) references curso(id)
);

INSERT INTO curso (nome, sigla, data_fundacao, email_coordenacao, telefone_coordenacao) VALUES 
('Tecnologia em Sistemas para Internet', 'TSI', '2010-01-30', 'tsi@ifpb.edu.br', '83990901234'),
('Tecnologia em Análise e Desenvolvimento de Sistemas', 'ADS', '2011-03-20', 'ads@ifpb.edu.br', '83995807534'),
('Bacharelado em Engenharia Civil', 'BEC', '2012-07-01', 'bec@ifpb.edu.br', '83994712587');

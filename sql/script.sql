drop schema public cascade;
create schema public;

create table cliente (
	id_cliente serial primary key,
	nome varchar(100) not null,
	cpf varchar(11) not null unique,
	telefone varchar(20)
);

create table forma_pagamento (
		id_pagamento serial primary key,
		tipo varchar (30) not null unique
);

insert into forma_pagamento (tipo) values
('Pix'),
('Boleto'),
('Cartão de crédito'),
('Cartão de débito');

create table genero (
	id_genero serial primary key,
	nome varchar(50) not null unique
);

create table desenvolvedora (
	id_desenvolvedora serial primary key,
	nome varchar (50) not null unique
);

create table fornecedor (
    id_fornecedor   serial primary key,
    nome            varchar(100) not null,
    cnpj            varchar(14)  not null unique,
    telefone        varchar(20),
    email           varchar(100),
    endereco_cidade varchar(60),
    endereco_rua    varchar(100),
    endereco_numero varchar(10)
);

create table jogo (
	id_jogo serial primary key,
	titulo varchar(100) not null unique,
	preco_venda decimal(10, 2) not null,
	preco_aluguel decimal(10, 2) not null,
	lancamento date not null,
	classificacao_indicativa varchar(30) not null
);

create table compra (
	id_compra serial primary key,
	data_venda date not null,
	valor_total decimal(10, 2) not null,
	status varchar(30) not null,
	id_cliente int not null references cliente(id_cliente),
	id_pagamento int not null references forma_pagamento(id_pagamento)
);

create table aluguel (
	id_aluguel serial primary key,
	data_aluguel date not null,
	data_devolucao date,
	valor_total decimal(10, 2) not null,
	status varchar(30) not null,
	id_cliente int not null references cliente(id_cliente),
	id_pagamento int not null references forma_pagamento(id_pagamento)
);

create table compra_jogo (
    id_compra int not null references compra(id_compra),
    id_jogo   int not null references jogo(id_jogo),
    primary key (id_compra, id_jogo)
);

create table aluguel_jogo (
    id_aluguel int not null references aluguel(id_aluguel),
    id_jogo    int not null references jogo(id_jogo),
    primary key (id_aluguel, id_jogo)
);

create table jogo_genero (
    id_jogo   int not null references jogo(id_jogo),
    id_genero int not null references genero(id_genero),
    primary key (id_jogo, id_genero)
);

create table jogo_desenvolvedora (
    id_jogo           int not null references jogo(id_jogo),
    id_desenvolvedora int not null references desenvolvedora(id_desenvolvedora),
    primary key (id_jogo, id_desenvolvedora)
);

create table jogo_fornecedor (
    id_jogo       int not null references jogo(id_jogo),
    id_fornecedor int not null references fornecedor(id_fornecedor),
    primary key (id_jogo, id_fornecedor)
);
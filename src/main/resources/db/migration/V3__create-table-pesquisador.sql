create table pesquisador(

    id bigint not null auto_increment,
    idXML varchar(100) not null,
    NOME varchar(100) not null unique,
    STATUS tinyint,
    idISTITUTO bigint not null,

    primary key (id),
    FOREIGN KEY(idISTITUTO) REFERENCES instituto(id)
);
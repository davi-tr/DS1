create table instituto(

    id bigint not null auto_increment,
    NOME varchar(100) not null,
    ACRONIMO varchar(10) not null unique,
    STATUS tinyint,

    primary key (id)
);

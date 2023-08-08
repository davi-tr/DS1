create table instituto(

    id bigint not null auto_increment,
    NOME varchar(100) not null,
    ACRONITMO varchar(10) not null unique,
   
    primary key (id)
);
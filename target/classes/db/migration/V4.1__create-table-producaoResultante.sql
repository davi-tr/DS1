CREATE TABLE producaoResultante (
    id BIGINT NOT NULL AUTO_INCREMENT,
    idProducao BIGINT not null,
    idPesquisador BIGINT not null,
    FOREIGN KEY (idProducao) REFERENCES producao (id),
    FOREIGN KEY (idPesquisador) REFERENCES pesquisador(id),
    PRIMARY KEY (id,idProducao,idPesquisador)
);
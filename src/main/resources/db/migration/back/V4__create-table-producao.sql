CREATE TABLE producao (
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL,
    ano VARCHAR(100) NOT NULL,
    tipo VARCHAR(100) NOT NULL,
    pesquisadorListado bigint NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (pesquisadorListado) REFERENCES pesquisador (id)
);
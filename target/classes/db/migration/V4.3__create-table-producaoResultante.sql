CREATE TABLE producaoResultante (
id BIGINT NOT NULL AUTO_INCREMENT,
idProducao BIGINT NOT NULL,
idPesquisador BIGINT NOT NULL,
id_AutorComplementar BIGINT NULL, -- ou qualquer outro valor padrão
FOREIGN KEY (id_AutorComplementar) REFERENCES autor_complementar(id),
FOREIGN KEY (idProducao) REFERENCES producao(id),
FOREIGN KEY (idPesquisador) REFERENCES pesquisador(id),
PRIMARY KEY (id,idProducao,idPesquisador)
);

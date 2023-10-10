ALTER TABLE DSaula.producao DROP FOREIGN KEY producao_ibfk_1;
ALTER TABLE DSaula.producao DROP COLUMN pesquisadorListado;

RENAME TABLE DSaula.producaoResultante TO DSaula.producao_resultante;
ALTER TABLE DSaula.producao_resultante CHANGE idProducao id_Producao bigint NOT NULL;
ALTER TABLE DSaula.producao_resultante CHANGE idPesquisador id_Pesquisador bigint NOT NULL;

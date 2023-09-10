package br.com.femass.ProjetoDS1.domain.producao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProducaoRepository extends JpaRepository<Producao, Long> {

    List<Producao> findAllByid_pesquisadorAndStatusTrue(Long idPesquisador);
}

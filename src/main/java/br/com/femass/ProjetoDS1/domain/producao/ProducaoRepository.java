package br.com.femass.ProjetoDS1.domain.producao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProducaoRepository extends JpaRepository<Producao, Long> {



    List<Producao> findAllByPesquisadorIdAndStatusTrue(Long idPesquisador);
}

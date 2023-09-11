package br.com.femass.ProjetoDS1.domain.producao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProducaoRepository extends JpaRepository<Producao, Long> {



    Page<Producao> findAllByPesquisadorIdAndStatusTrue(Long idPesquisador, Pageable paginacao);

    Producao getReferenceByTitulo(String tituloDoArtigo);

    List<Producao> findAllByPesquisadorIdAndTituloAndStatusTrue (Long id, String titulo);

    Page<Producao> findAllByStatusTrue(Pageable paginacao);
}

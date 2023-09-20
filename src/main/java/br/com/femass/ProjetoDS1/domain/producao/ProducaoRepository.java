package br.com.femass.ProjetoDS1.domain.producao;

import br.com.femass.ProjetoDS1.domain.pesquisador.Pesquisador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProducaoRepository extends JpaRepository<Producao, Long> {



    @Query("SELECT p FROM Producao p JOIN p.pesquisador pesq WHERE pesq.id = :idPesquisador AND p.status = true")
    Page<Producao> findAllByPesquisadorIdAndStatusTrue(Long idPesquisador, Pageable paginacao);

    Producao getReferenceByTitulo(String tituloDoArtigo);

    List<Producao> findAllByPesquisadorIdAndTituloAndStatusTrue (Long id, String titulo);

    Page<Producao> findAllByStatusTrue(Pageable paginacao);

    @Query("SELECT p FROM Producao p WHERE (SELECT COUNT(pes) FROM p.pesquisador pes) > 1")
    Page<Producao> encontrarProducaoComMaisDeUmPesquisador(Pageable paginacao);

    List<Producao> findAllByPesquisadorIdAndStatusTrue(Long id);
}

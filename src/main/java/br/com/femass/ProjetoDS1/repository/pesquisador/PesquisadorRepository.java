package br.com.femass.ProjetoDS1.repository.pesquisador;

import br.com.femass.ProjetoDS1.domain.autor.Autor;
import br.com.femass.ProjetoDS1.domain.pesquisador.Pesquisador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PesquisadorRepository extends JpaRepository<Pesquisador, Long> {

    @Query("SELECT p FROM Pesquisador p LEFT JOIN FETCH p.instituto WHERE p.status = true")
    Page<Pesquisador> findAllByStatusTrue(Pageable paginacao);

    Pesquisador getReferenceByIdAndStatusTrue(Long id);

    Pesquisador findAllByIdXMLAndStatusTrue(String idXML);

    Page<Pesquisador> findAllByInstitutoIdAndStatusTrue(Long institutoId, Pageable paginacao);

    @Query("SELECT p FROM Pesquisador p where p.instituto.id = :institutoId")
    List<Autor> findAllByInstitutoIdAndStatusTrueList(@Param("institutoId") Long institutoId);
    Pesquisador getReferenceByidXMLAndStatusFalse(String s);

    List<Pesquisador> findAllByInstitutoIdAndStatusTrue(Long id);

    Pesquisador getReferenceByidXMLAndStatusTrue(String idinstituto);

    List<Pesquisador> findAllByIdXMLAndIdAndStatusTrue(String s, Long id);

    Pesquisador getReferenceByNome(String nome);


    Page<Pesquisador> findAllByNomeContainingAndStatusTrue(String nome, Pageable paginacao);

    Page<Pesquisador> findAllByIdXMLContainingAndStatusTrue(String nome, Pageable paginacao);
}

package br.com.femass.ProjetoDS1.domain.pesquisador;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PesquisadorRepository extends JpaRepository<Pesquisador, Long> {

    @Query("SELECT p FROM Pesquisador p LEFT JOIN FETCH p.instituto WHERE p.status = true")
    Page<Pesquisador> findAllByStatusTrue(Pageable paginacao);

    Pesquisador getReferenceByIdAndStatusTrue(Long id);

    Pesquisador findAllByIdXMLAndStatusTrue(String idXML);

    Page<Pesquisador> findAllByInstitutoIdAndStatusTrue(Long institutoId, Pageable paginacao);


    Pesquisador getReferenceByidXMLAndStatusFalse(String s);
}

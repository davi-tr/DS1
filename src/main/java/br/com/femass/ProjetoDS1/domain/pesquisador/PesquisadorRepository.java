package br.com.femass.ProjetoDS1.domain.pesquisador;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PesquisadorRepository extends JpaRepository<Pesquisador, Long> {

    Page<Pesquisador> findAllByStatusTrue(Pageable paginacao);

    Pesquisador getReferenceByIdAndStatusTrue(Long id);

    Pesquisador findAllByIdXMLAndStatusTrue(String idXML);

    @Query("""
    select p
    from Pesquisador p
    where
    p.instituto = :id
         """)
    Page<Pesquisador> findAllByidISTITUTOAndStatusTrue(Long id, Pageable paginacao);

    Page<Pesquisador> findAllByInstitutoIdAndStatusTrue(Long institutoId, Pageable paginacao);
}

package br.com.femass.ProjetoDS1.domain.instituto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstitutoRepository extends JpaRepository<Instituto, Long> {

    Page<Instituto> findAllByStatusTrue(Pageable paginacao);

    Instituto findAllByAcronimo(String acronimo);

    Instituto findAllByNome(String nome);

}

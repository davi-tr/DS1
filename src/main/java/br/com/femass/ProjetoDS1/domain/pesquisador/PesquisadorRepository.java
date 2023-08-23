package br.com.femass.ProjetoDS1.domain.pesquisador;

import br.com.femass.ProjetoDS1.domain.instituto.Instituto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PesquisadorRepository extends JpaRepository<Pesquisador, Long> {

    Page<Instituto> findAllByStatusTrue(Pageable paginacao);
}

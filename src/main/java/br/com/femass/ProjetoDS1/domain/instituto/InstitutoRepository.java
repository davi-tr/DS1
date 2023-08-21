// Esta é uma interface de repositório que define métodos para acessar dados de Institutos no banco de dados.

package br.com.femass.ProjetoDS1.domain.instituto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Declaração da interface InstitutoRepository que estende JpaRepository para operações de CRUD.
public interface InstitutoRepository extends JpaRepository<Instituto, Long> {

    // Método para buscar todos os Institutos com status "true" e paginar os resultados.
    Page<Instituto> findAllByStatusTrue(Pageable paginacao);

    // Método para obter uma referência para um Instituto com um ID específico e status "true".
    Instituto getReferenceByIdAndStatusTrue(Long id);

    // Método para encontrar um Instituto pelo acrônimo e status "true".
    Instituto findAllByAcronimoAndStatusTrue(String acronimo);

    // Método para encontrar um Instituto pelo nome e status "true".
    Instituto findAllByNomeAndStatusTrue(String nome);
}

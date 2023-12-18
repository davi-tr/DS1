// Este é o controlador responsável por lidar com as requisições relacionadas aos Institutos.

// Importações de classes e pacotes necessárias para o funcionamento do controller.
package br.com.femass.ProjetoDS1.controller;

import br.com.femass.ProjetoDS1.domain.instituto.*;
import br.com.femass.ProjetoDS1.repository.instituto.InstitutoRepository;
import br.com.femass.ProjetoDS1.repository.pesquisador.PesquisadorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

// Define que esta classe é um controlador que irá receber as requisições relacionadas aos Institutos.
@RestController
@RequestMapping("/instituto")
public class InstitutoController {

    // Injeção de dependência do repositório do Instituto.
    @Autowired
    private InstitutoRepository repository;
    @Autowired
    private PesquisadorRepository repositoryPesquisador;

    // Método para criar um novo Instituto a partir dos dados fornecidos.
    @PostMapping()
    public ResponseEntity cadastro(@RequestBody @Valid DadosCadastroInstituto dados, UriComponentsBuilder uriBuilder){
        // Cria um novo Instituto com base nos dados fornecidos e o salva no repositório.
        var instituto = new Instituto(dados);
        var institutoBusca = repository.findAllByAcronimoAndStatusTrue(dados.acronimo());
        if(institutoBusca != null){
            return ResponseEntity.badRequest().body(new MensagemErro("Acronimo já existe"));
        }
        repository.save(instituto);

        // Gera uma URI para o novo Instituto e retorna uma resposta com o Instituto criado.
        var uri = uriBuilder.path("instituto/id={id}").buildAndExpand(instituto.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosUnicoInstituto(instituto));
    }

    // Método para listar todos os Institutos ativos.
    @GetMapping()
    public ResponseEntity <Page<DadosListagemInstituto>> listar(@PageableDefault (sort = {"id"}, direction = Sort.Direction.DESC, size = Integer.MAX_VALUE ) Pageable paginacao) {
        // Obtém uma página de Institutos ativos do repositório e a transforma em uma página de dados para listagem.
        var page = repository.findAllByStatusTrue(paginacao).map(DadosListagemInstituto::new);
        return ResponseEntity.ok(page);
    }

    // Método para encontrar um Instituto pelo seu ID.
    @GetMapping("/id={id}")
    public ResponseEntity encontrarPorID(@PathVariable Long id){
        // Obtém uma referência para o Instituto pelo ID e retorna os dados desse Instituto.
        var instituto = repository.getReferenceByIdAndStatusTrue(id);
        return ResponseEntity.ok(new DadosUnicoInstituto(instituto));
    }

    // Método para encontrar um Instituto pelo seu acrônimo.
    @GetMapping("/ac={ac}")
    public ResponseEntity encontrarAcronimo(@PathVariable String ac){
        // Obtém todos os Institutos com o acrônimo fornecido e retorna os dados de um deles.
        var instituto = repository.findAllByAcronimoAndStatusTrue(ac);
        return ResponseEntity.ok(new DadosUnicoInstituto(instituto));
    }

    // Método para encontrar um Instituto pelo seu nome.
    @GetMapping("/nome={nome}")
    public ResponseEntity encontrarNome(@PathVariable String nome){
        // Obtém todos os Institutos com o nome fornecido e retorna os dados de um deles.
        var instituto = repository.findAllByNomeAndStatusTrue(nome);
        return ResponseEntity.ok(new DadosUnicoInstituto(instituto));
    }

    @GetMapping("/quantidadeInstitutos")
    public ResponseEntity totalAutores(){
        var total = repository.totalInstituto();
        DadosTotalInstituto dados = new DadosTotalInstituto(total);
        return ResponseEntity.ok(dados);
    }

    // Método para deletar um Instituto pelo seu ID.
    @DeleteMapping("/{id}")
    public ResponseEntity deletarInstituto(@PathVariable Long id){
        // Obtém uma referência para o Instituto pelo ID, marca-o como excluído e o salva no repositório.
        var instituto = repository.getReferenceById(id);
        var pesquisador = repositoryPesquisador.findAllByInstitutoIdAndStatusTrue(id);
        if (pesquisador.size()!=0){
            return ResponseEntity.badRequest().body(new MensagemErro("O instituto possui pesquisadores cadastrados"));
        }
        instituto.excluir();
        repository.save(instituto);

        // Retorna uma resposta indicando que o Instituto foi excluído com sucesso.
        return ResponseEntity.noContent().build();
    }

    // Método para atualizar os dados de um Instituto existente.
    @PutMapping()
    public ResponseEntity atualiza(@RequestBody @Valid DadosAtualizarInstituto dados){
        // Obtém uma referência para o Instituto pelo ID, atualiza seus dados com base nos novos dados fornecidos e o salva no repositório.
        var instituto = repository.getReferenceById(dados.id());
        instituto.atualizarInstituto(dados);
        repository.save(instituto);

        // Retorna os dados atualizados do Instituto.
        return ResponseEntity.ok(new DadosUnicoInstituto(instituto));
    }

    private record MensagemErro(String mensagem){

    }

    private static class DadosTotalInstituto {
        private final long total;

        public DadosTotalInstituto(Long total) {
            this.total = total;
        }

        public long getTotal() {
            return total;
        }
    }

}

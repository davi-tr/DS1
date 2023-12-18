package br.com.femass.ProjetoDS1.controller;

import br.com.femass.ProjetoDS1.domain.pesquisador.*;
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

@RestController

@RequestMapping("/pesquisador")
public class PesquisadorController {

    @Autowired
    private PesquisadorRepository repository;
    @Autowired
    private InstitutoRepository repositoryInstituto;

    @PostMapping
    public ResponseEntity cadastro(@RequestBody @Valid DadosCadastroPesquisador dados, UriComponentsBuilder uriBuilder){
        var pesquisador = new Pesquisador(dados);
        var pesquisadorBusca = repository.getReferenceByidXMLAndStatusFalse(dados.idPesquisador());
        if (pesquisadorBusca != null){
            pesquisadorBusca.setStatus(true);
            var instituto = repositoryInstituto.getReferenceById(dados.idinstituto());
            pesquisadorBusca.setInstituto(instituto);
            repository.save(pesquisadorBusca);
            var uri = uriBuilder.path("pesquisador/id={id}").buildAndExpand(pesquisadorBusca.getId()).toUri();
            return ResponseEntity.created(uri).body(new DadosUnicoPesquisador(pesquisadorBusca));
        }
        var pesquisadorEncontra = repository.getReferenceByidXMLAndStatusTrue(dados.idPesquisador());
        if(pesquisadorEncontra !=null){
            return ResponseEntity.badRequest().body(new MensagemErro("Pesquisador já existe"));
        }
        var instituto = repositoryInstituto.getReferenceById(dados.idinstituto());
        pesquisador.setInstituto(instituto);
        repository.save(pesquisador);

        var uri = uriBuilder.path("pesquisador/id={id}").buildAndExpand(pesquisador.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosUnicoPesquisador(pesquisador));
    }

    @GetMapping()
    public ResponseEntity <Page<DadosListagemPesquisador>> listar(@PageableDefault (direction = Sort.Direction.DESC, size = Integer.MAX_VALUE)Pageable paginacao){
        var page = repository.findAllByStatusTrue(paginacao).map(DadosListagemPesquisador::new);
        System.out.println("rota 1");
        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarPesquisador(@PathVariable Long id){
        var pesquisador = repository.getReferenceById(id);
        pesquisador.excluir();
        repository.save(pesquisador);

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizarPesquisador dados){
        var pesquisador = repository.getReferenceByIdAndStatusTrue(dados.id());
        var instituto = repositoryInstituto.getReferenceById(dados.idNovoInstituto());
        pesquisador.atualizar(dados, instituto);
        repository.save(pesquisador);

        return ResponseEntity.ok(new DadosUnicoPesquisador(pesquisador));
    }

    @GetMapping("/id={id}")
    public ResponseEntity encontrarPorId(@PathVariable Long id){

        var pesquisador = repository.getReferenceByIdAndStatusTrue(id);
        return ResponseEntity.ok(new DadosUnicoPesquisador(pesquisador));
    }

    @GetMapping("/quantidadeAutores")
    public ResponseEntity totalAutores(){
        var total = repository.totalPesquisador();
        DadosTotalPesquisador dados = new DadosTotalPesquisador(total);
        return ResponseEntity.ok(dados);
    }

    @GetMapping("/idXML={idXML}")
    public ResponseEntity encontrarPorXML(@PathVariable String idXML){
        var pesquisador = repository.findAllByIdXMLAndStatusTrue(idXML);
        return ResponseEntity.ok(new DadosUnicoPesquisador(pesquisador));
    }

    @GetMapping("/XMLid={idXML}")
    public ResponseEntity <Page<DadosListagemPesquisador>> buscaXML(@PageableDefault (direction = Sort.Direction.DESC, size = Integer.MAX_VALUE)Pageable paginacao, @PathVariable String idXML){
        var page = repository.findAllByIdXMLContainingAndStatusTrue(idXML, paginacao).map(DadosListagemPesquisador::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/nome={nome}")
    public ResponseEntity <Page<DadosListagemPesquisador>> encontrarPorNome(@PageableDefault (direction = Sort.Direction.DESC, size = Integer.MAX_VALUE)Pageable paginacao, @PathVariable String nome){
        var page = repository.findAllByNomeContainingAndStatusTrue(nome, paginacao).map(DadosListagemPesquisador::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/search={search}")
    public ResponseEntity <Page<DadosListagemPesquisador>> encontrarGeral(@PageableDefault (direction = Sort.Direction.DESC, size = Integer.MAX_VALUE)Pageable paginacao, @PathVariable String search){
        if(checkIfnumber(search)){
            var page = repository.findAllByIdXMLContainingAndStatusTrue(search, paginacao).map(DadosListagemPesquisador::new);
            System.out.println(checkIfnumber(search));
            return ResponseEntity.ok(page);
        }
        else {
            var page = repository.findAllByNomeContainingAndStatusTrue(search, paginacao).map(DadosListagemPesquisador::new);
            return ResponseEntity.ok(page);
        }
    }

    @GetMapping("/idInstituto={id}")
    public ResponseEntity<Page<DadosListagemPesquisador>> listarInstitutos(@PathVariable Long id, @PageableDefault (sort = {"id"}, direction = Sort.Direction.DESC, size = Integer.MAX_VALUE ) Pageable paginacao) {
         var page = repository.findAllByInstitutoIdAndStatusTrue(id, paginacao).map(DadosListagemPesquisador::new);
         return ResponseEntity.ok(page);
    }


    private record MensagemErro(String mensagem) {

    }
    static boolean checkIfnumber(String str) {
        return Character.isDigit(str.charAt(0));
    }

    private static class DadosTotalPesquisador {
        private final long total;

        public DadosTotalPesquisador(Long total) {
            this.total = total;
        }

        public long getTotal() {
            return total;
        }
    }
}

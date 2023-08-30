package br.com.femass.ProjetoDS1.controller;

import br.com.femass.ProjetoDS1.domain.instituto.DadosAtualizarInstituto;
import br.com.femass.ProjetoDS1.domain.pesquisador.*;
import br.com.femass.ProjetoDS1.domain.instituto.InstitutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
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
        var instituto = repositoryInstituto.getReferenceById(dados.idinstituto());
        pesquisador.setInstituto(instituto);
        repository.save(pesquisador);

        var uri = uriBuilder.path("pesquisador/id={id}").buildAndExpand(pesquisador.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosUnicoPesquisador(pesquisador));
    }

    @GetMapping()
    public ResponseEntity <Page<DadosListagemPesquisador>> listar(@PageableDefault (direction = Sort.Direction.DESC, size = Integer.MAX_VALUE)Pageable paginacao){
        var page = repository.findAllByStatusTrue(paginacao).map(DadosListagemPesquisador::new);
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

    @GetMapping("/idXML={idXML}")
    public ResponseEntity encontrarPorXML(@PathVariable String idXML){
        var pesquisador = repository.findAllByIdXMLAndStatusTrue(idXML);
        return ResponseEntity.ok(new DadosUnicoPesquisador(pesquisador));
    }

    @GetMapping("/idInstituto={id}")
    public ResponseEntity<Page<DadosListagemPesquisador>> listarInstitutos(@PathVariable Long id, @PageableDefault (sort = {"id"}, direction = Sort.Direction.DESC, size = Integer.MAX_VALUE ) Pageable paginacao) {
         var page = repository.findAllByInstitutoIdAndStatusTrue(id, paginacao).map(DadosListagemPesquisador::new);
         return ResponseEntity.ok(page);
    }

}

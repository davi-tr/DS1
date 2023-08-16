package br.com.femass.ProjetoDS1.controller;

import br.com.femass.ProjetoDS1.domain.instituto.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/instituto")
@CrossOrigin
public class InstitutoController {

    @Autowired
    private InstitutoRepository repository;

    @PostMapping()
    public ResponseEntity cadastro(@RequestBody @Valid DadosCadastroInstituto dados, UriComponentsBuilder uriBuilder){
        var instituto = new Instituto(dados);
        repository.save(instituto);

        var uri = uriBuilder.path("instituto/id={id}").buildAndExpand(instituto.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosUnicoInstituto(instituto));
    }

    @GetMapping()
    public ResponseEntity <Page<DadosListagemInstituto>> listar(@PageableDefault (size = 2, sort = {"acronimo"} ) Pageable paginacao) {
        var page = repository.findAllByStatusTrue(paginacao).map(DadosListagemInstituto::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/id={id}")
    public ResponseEntity encontrarPorID(@PathVariable Long id){
        var instituto = repository.getReferenceByIdAndStatusTrue(id);

        return ResponseEntity.ok(new DadosUnicoInstituto(instituto));
    }
    @GetMapping("/ac={ac}")
    public ResponseEntity encontrarAcronimo(@PathVariable String ac){
        var instituto = repository.findAllByAcronimoAndStatusTrue(ac);

        return ResponseEntity.ok(new DadosUnicoInstituto(instituto));
    }

    @GetMapping("/nome={nome}")
    public ResponseEntity encontrarNome(@PathVariable String nome){
        var instituto = repository.findAllByNomeAndStatusTrue(nome);

        return ResponseEntity.ok(new DadosUnicoInstituto(instituto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarInstituto(@PathVariable Long id){
        var instituto = repository.getReferenceById(id);
        instituto.excluir();
        repository.save(instituto);

        return ResponseEntity.noContent().build();
    }

    @PutMapping()
    public ResponseEntity atualiza(@RequestBody @Valid DadosAtualizarInstituto dados){
        var instituto = repository.getReferenceById(dados.id());
        instituto.atualizarInstituto(dados);
        repository.save(instituto);

        return ResponseEntity.ok(new DadosUnicoInstituto(instituto));
    }



}

package br.com.femass.ProjetoDS1.domain.controller;

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
public class InstitutoController {

    @Autowired
    private InstitutoRepository repository;

    @PostMapping()
    public ResponseEntity cadastro(@RequestBody @Valid DadosCadastroInstituto dados, UriComponentsBuilder uriBuilder){
        var instituto = new Instituto(dados);
        repository.save(instituto);

        var uri = uriBuilder.path("instituto/{id}").buildAndExpand(instituto.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosUnicoInstituto(instituto));
    }

    @GetMapping()
    public ResponseEntity <Page<DadosListagemInstituto>> listar(@PageableDefault (size = 10, sort = {"acronimo"} ) Pageable paginacao) {
        var page = repository.findAllByStatusTrue(paginacao).map(DadosListagemInstituto::new);
        return ResponseEntity.ok(page);
    }

}

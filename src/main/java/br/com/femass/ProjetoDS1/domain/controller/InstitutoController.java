package br.com.femass.ProjetoDS1.domain.controller;

import br.com.femass.ProjetoDS1.domain.instituto.DadosCadastroInstituto;
import br.com.femass.ProjetoDS1.domain.instituto.DadosUnicoInstituto;
import br.com.femass.ProjetoDS1.domain.instituto.Instituto;
import br.com.femass.ProjetoDS1.domain.instituto.InstitutoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/instituto")
public class InstitutoController {

    @Autowired
    private InstitutoRepository repository;

    @PostMapping("/")
    public ResponseEntity cadastro(@RequestBody @Valid DadosCadastroInstituto dados, UriComponentsBuilder uriBuilder){
        var instituto = new Instituto(dados);
        repository.save(instituto);

        var uri = uriBuilder.path("instituto/{id}").buildAndExpand(instituto.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosUnicoInstituto(instituto));
    }
}

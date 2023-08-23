package br.com.femass.ProjetoDS1.controller;

import br.com.femass.ProjetoDS1.domain.pesquisador.DadosUnicoPesquisador;
import br.com.femass.ProjetoDS1.domain.instituto.InstitutoRepository;
import br.com.femass.ProjetoDS1.domain.pesquisador.DadosCadastroPesquisador;
import br.com.femass.ProjetoDS1.domain.pesquisador.Pesquisador;
import br.com.femass.ProjetoDS1.domain.pesquisador.PesquisadorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}

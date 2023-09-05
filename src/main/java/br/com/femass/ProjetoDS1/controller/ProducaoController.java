package br.com.femass.ProjetoDS1.controller;

import br.com.femass.ProjetoDS1.domain.pesquisador.Pesquisador;
import br.com.femass.ProjetoDS1.domain.pesquisador.PesquisadorRepository;
import br.com.femass.ProjetoDS1.domain.producao.DadosCadastroProducao;
import br.com.femass.ProjetoDS1.domain.producao.DadosUnicoProducao;
import br.com.femass.ProjetoDS1.domain.producao.Producao;
import br.com.femass.ProjetoDS1.domain.producao.ProducaoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/producao")
public class ProducaoController {
    @Autowired
    private PesquisadorRepository repositoryPesquisador;
    @Autowired
    private ProducaoRepository repository;


    @PostMapping
    public ResponseEntity cadastro(@RequestBody @Valid DadosCadastroProducao dados, UriComponentsBuilder uriBuilder){
        var producao = new Producao(dados);
        var finded = producao.encontrarProducao(producao.EncontrarXML(dados.idPesquisador()));
        for (var id : finded) {
            System.out.println(id);
            var pesquisador = repositoryPesquisador.getReferenceByidXMLAndStatusTrue(id);
            producao.setPesquisadores((List<Pesquisador>) pesquisador);
        }
        repository.save(producao);

        var uri = uriBuilder.path("producao/id={id}").buildAndExpand(producao.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosUnicoProducao(producao));
    }
}

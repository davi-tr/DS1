package br.com.femass.ProjetoDS1.controller;

import br.com.femass.ProjetoDS1.domain.pesquisador.PesquisadorRepository;
import br.com.femass.ProjetoDS1.domain.producao.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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
        Long idProd = null;
        for (var tot : finded) {
            String[] partes = tot.split("-");
            if (partes.length == 2) {
                var prod = new Producao();
                prod.setStatus(true);
                prod.setTipo(Tipo.ARTIGO);
                String tituloDoArtigo = partes[0];
                String anoDoArtigo = partes[1];

                prod.setTitulo(tituloDoArtigo);
                prod.setAno(anoDoArtigo);

                var pesquisadorFind = repositoryPesquisador.getReferenceByidXMLAndStatusTrue(dados.idPesquisador());

                var pesquisador = repositoryPesquisador.findAllByIdXMLAndIdAndStatusTrue(dados.idPesquisador(), pesquisadorFind.getId());
                System.out.println(pesquisador);

                prod.setPesquisador(pesquisador);

                System.out.println(prod);
                repository.save(prod);
                idProd = pesquisadorFind.getId();
            }


        }
        if (!finded.isEmpty()){
            var findAll = repository.findAllByid_pesquisadorAndStatusTrue(idProd);

            var uri = uriBuilder.path("producao/id={id}").buildAndExpand(repositoryPesquisador.getReferenceByIdAndStatusTrue(idProd)).toUri();

            return ResponseEntity.created(uri).body(findAll);
        }

        return ResponseEntity.badRequest().body(new MensagemErro("Não a artigos no XML"));


    }
    private record MensagemErro(String mensagem){

    }
}

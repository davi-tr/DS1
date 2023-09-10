package br.com.femass.ProjetoDS1.controller;

import br.com.femass.ProjetoDS1.domain.pesquisador.Pesquisador;
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
        var idProd = new Pesquisador();
        boolean flag = false;
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
                if(pesquisadorFind == null){
                    return ResponseEntity.badRequest().build();
                }

                var pesquisador = repositoryPesquisador.findAllByIdXMLAndIdAndStatusTrue(dados.idPesquisador(), pesquisadorFind.getId());
                System.out.println(pesquisador);

                prod.setPesquisador(pesquisador);

                System.out.println(prod);
                repository.save(prod);
                if (flag == false) {
                    idProd = pesquisadorFind;
                    flag = true;
                }
            }


        }
        if (!finded.isEmpty()){
            System.out.println("batata");
            var findAll = repository.findAllByPesquisadorIdAndStatusTrue(idProd.getId()).stream().map(DadosListagemProducao::new);
            System.out.println(findAll);
            var uri = uriBuilder.path("producao/id={id}").buildAndExpand(repositoryPesquisador.getReferenceByIdAndStatusTrue(idProd.getId())).toUri();

            return ResponseEntity.ok(findAll);
        }

        return ResponseEntity.badRequest().body(new MensagemErro("Não a artigos no XML"));


    }
    private record MensagemErro(String mensagem){

    }
}

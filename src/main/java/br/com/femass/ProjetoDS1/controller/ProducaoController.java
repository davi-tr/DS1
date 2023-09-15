package br.com.femass.ProjetoDS1.controller;

import br.com.femass.ProjetoDS1.domain.pesquisador.Pesquisador;
import br.com.femass.ProjetoDS1.domain.pesquisador.PesquisadorRepository;
import br.com.femass.ProjetoDS1.domain.producao.*;
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
@RequestMapping("/producao")
public class ProducaoController {
    @Autowired
    private PesquisadorRepository repositoryPesquisador;
    @Autowired
    private ProducaoRepository repository;





    @PostMapping
    public ResponseEntity <Page<DadosListagemProducao>> cadastro(@PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, size = Integer.MAX_VALUE ) Pageable paginacao, @RequestBody @Valid DadosCadastroProducao dados, UriComponentsBuilder uriBuilder){

        var producao = new Producao(dados);
        var finded = producao.encontrarArtigos(producao.EncontrarXML(dados.idPesquisador()));
        var livros = producao.encontrarLivroeCapitulo(producao.EncontrarXML(dados.idPesquisador()));
        var idProd = new Pesquisador();
        boolean flag = false;
        int conta = 0;
        for (var tot : finded) {
            String[] partes = tot.split("-(\\d)");
            if (partes.length == 2) {
                conta++;
                var prod = new Producao();
                prod.setStatus(true);
                prod.setTipo(Tipo.ARTIGO);
                String tituloDoArtigo = partes[0];
                String anoDoArtigo = partes[1];

                prod.setTitulo(tituloDoArtigo);
                prod.setAno(anoDoArtigo);

                var pesquisadorFind = repositoryPesquisador.getReferenceByidXMLAndStatusTrue(dados.idPesquisador());
                if(pesquisadorFind == null){
                    System.out.println("pesquisador não existe no banco");
                    return ResponseEntity.badRequest().build();
                }

                var pesquisador = repositoryPesquisador.findAllByIdXMLAndIdAndStatusTrue(dados.idPesquisador(), pesquisadorFind.getId());
                var prodRepo = repository.getReferenceByTitulo(tituloDoArtigo);
                var pesquisadorArtigo = repository.findAllByPesquisadorIdAndTituloAndStatusTrue(pesquisadorFind.getId(), tituloDoArtigo);
                if(!pesquisadorArtigo.isEmpty()){
                    if (conta>1) {
                        System.out.println("item já cadastrado");
                        continue;
                    }
                }
                if(prodRepo != null){
                    var pesquisadorNovo = repositoryPesquisador.getReferenceByidXMLAndStatusTrue(dados.idPesquisador());
                    prodRepo.adicionar(pesquisadorNovo);

                    System.out.println(prodRepo);
                    repository.save(prodRepo);
                    idProd = pesquisadorFind;
                }

                prod.setPesquisador(pesquisador);

                System.out.println(prod);
                repository.save(prod);
                if (flag == false) {
                    idProd = pesquisadorFind;
                    flag = true;
                }
            }


        }
        for(var livro : livros){
            String[] partes = livro.split("-(\\d)");
            if (partes.length == 2) {
                conta++;
                var prod = new Producao();
                prod.setStatus(true);
                prod.setTipo(Tipo.LIVRO);
                String tituloDoArtigo = partes[0];
                String anoDoArtigo = partes[1];

                prod.setTitulo(tituloDoArtigo);
                prod.setAno(anoDoArtigo);

                var pesquisadorFind = repositoryPesquisador.getReferenceByidXMLAndStatusTrue(dados.idPesquisador());
                if(pesquisadorFind == null){
                    System.out.println("pesquisador não existe no banco");
                    return ResponseEntity.badRequest().build();
                }

                var pesquisador = repositoryPesquisador.findAllByIdXMLAndIdAndStatusTrue(dados.idPesquisador(), pesquisadorFind.getId());
                var prodRepo = repository.getReferenceByTitulo(tituloDoArtigo);
                var pesquisadorArtigo = repository.findAllByPesquisadorIdAndTituloAndStatusTrue(pesquisadorFind.getId(), tituloDoArtigo);
                if(!pesquisadorArtigo.isEmpty()){
                    if (conta>1){
                        System.out.println("item já cadastrado");
                        continue;
                    }else {
                        continue;
                    }
                }
                if(prodRepo != null){
                    var pesquisadorNovo = repositoryPesquisador.getReferenceByidXMLAndStatusTrue(dados.idPesquisador());
                    prodRepo.adicionar(pesquisadorNovo);

                    System.out.println(prodRepo);
                    repository.save(prodRepo);
                    idProd = pesquisadorFind;
                }

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
            var findAll = repository.findAllByPesquisadorIdAndStatusTrue(idProd.getId(), paginacao).map(DadosListagemProducao::new);

            var uri = uriBuilder.path("producao/id={id}").buildAndExpand(repositoryPesquisador.getReferenceByIdAndStatusTrue(idProd.getId())).toUri();

            return ResponseEntity.created(uri).body(findAll);
        }

        return ResponseEntity.badRequest().build();


    }

    @GetMapping
    public ResponseEntity <Page<DadosListagemProducao>>listar (@PageableDefault (direction = Sort.Direction.DESC, size = Integer.MAX_VALUE)Pageable paginacao){
        var page = repository.findAllByStatusTrue(paginacao).map(DadosListagemProducao::new);
        return ResponseEntity.ok(page);
    }


    private record MensagemErro(String mensagem){

    }
    @GetMapping("/maisdeumpesquisador")
    public ResponseEntity <Page<DadosListagemProducao>> listarMaisDeUm (@PageableDefault (direction = Sort.Direction.DESC, size = Integer.MAX_VALUE)Pageable paginacao){
        var page = repository.encontrarProducaoComMaisDeUmPesquisador(paginacao).map(DadosListagemProducao::new);
        return ResponseEntity.ok(page);
    }
}

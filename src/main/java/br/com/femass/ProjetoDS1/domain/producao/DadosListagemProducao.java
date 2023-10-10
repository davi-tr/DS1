package br.com.femass.ProjetoDS1.domain.producao;

import br.com.femass.ProjetoDS1.domain.autorComplementar.AutorComplementar;
import br.com.femass.ProjetoDS1.domain.autorComplementar.DadosListagemAutorComplementar;
import br.com.femass.ProjetoDS1.domain.pesquisador.DadosListagemPesquisador;
import br.com.femass.ProjetoDS1.domain.pesquisador.DadosUnicoPesquisador;
import br.com.femass.ProjetoDS1.domain.pesquisador.Pesquisador;

import java.util.List;
import java.util.stream.Collectors;

public record DadosListagemProducao(Long id, String titulo, String ano, Tipo tipo, Object pesquisador, Object autorComplementar) {

    public DadosListagemProducao(Producao producao) {
        this(producao.getId(), producao.getTitulo(), producao.getAno(), producao.getTipo(),
                producao.getAutores().stream()
                        .filter(item -> item instanceof Pesquisador)
                        .map(item -> (Pesquisador)item)
                        .map(DadosListagemPesquisador::new)
                        .collect(Collectors.toList()), producao.getAutores().stream()
                        .filter(item -> item instanceof AutorComplementar)
                        .map(item -> (AutorComplementar)item)
                        .map(DadosListagemAutorComplementar::new)
                        .collect(Collectors.toList())); // Collect the mapped objects into a list
    }
}

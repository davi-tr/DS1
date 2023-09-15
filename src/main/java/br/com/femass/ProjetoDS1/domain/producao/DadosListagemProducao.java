package br.com.femass.ProjetoDS1.domain.producao;

import br.com.femass.ProjetoDS1.domain.pesquisador.DadosListagemPesquisador;
import br.com.femass.ProjetoDS1.domain.pesquisador.DadosUnicoPesquisador;
import br.com.femass.ProjetoDS1.domain.pesquisador.Pesquisador;

import java.util.List;
import java.util.stream.Collectors;

public record DadosListagemProducao(Long id, String titulo, String ano, Tipo tipo, Object pesquisador) {

    public DadosListagemProducao(Producao producao) {
        this(producao.getId(), producao.getTitulo(), producao.getAno(), producao.getTipo(),
                producao.getPesquisador().stream()
                        .map(DadosListagemPesquisador::new)
                        .collect(Collectors.toList())); // Collect the mapped objects into a list
    }
}

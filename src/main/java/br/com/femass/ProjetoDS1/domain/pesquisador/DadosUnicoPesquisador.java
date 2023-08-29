package br.com.femass.ProjetoDS1.domain.pesquisador;

import br.com.femass.ProjetoDS1.domain.instituto.Instituto;
import br.com.femass.ProjetoDS1.domain.pesquisador.Pesquisador;

public record DadosUnicoPesquisador(Long id, String idXML, String nome, Instituto instituto ) {

    public DadosUnicoPesquisador(Pesquisador pesquisador){
        this(pesquisador.getId(), pesquisador.getIdXML(), pesquisador.getNome(), pesquisador.getInstituto());
    }
}

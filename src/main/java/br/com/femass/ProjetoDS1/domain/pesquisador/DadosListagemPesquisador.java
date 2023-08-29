package br.com.femass.ProjetoDS1.domain.pesquisador;

import br.com.femass.ProjetoDS1.domain.instituto.Instituto;

public record DadosListagemPesquisador(Long id, String idXML, String nome, Instituto instituto) {

    public DadosListagemPesquisador(Pesquisador pesquisador){
        this(pesquisador.getId(), pesquisador.getIdXML(), pesquisador.getNome(), pesquisador.getInstituto());
    }
}

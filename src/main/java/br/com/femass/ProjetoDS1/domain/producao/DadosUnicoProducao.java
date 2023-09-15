package br.com.femass.ProjetoDS1.domain.producao;

public record DadosUnicoProducao(Long id) {
    public DadosUnicoProducao(Producao producao){
        this(producao.getId());
    }
}

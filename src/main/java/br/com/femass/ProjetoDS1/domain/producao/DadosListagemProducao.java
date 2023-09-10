package br.com.femass.ProjetoDS1.domain.producao;

public record DadosListagemProducao(Long id, String titulo, String ano, Tipo tipo) {

    public DadosListagemProducao(Producao producao){
        this(producao.getId(), producao.getTitulo(), producao.getAno(), producao.getTipo());
    }
}

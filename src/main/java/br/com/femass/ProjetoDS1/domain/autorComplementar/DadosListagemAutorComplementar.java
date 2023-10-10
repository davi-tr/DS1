package br.com.femass.ProjetoDS1.domain.autorComplementar;

public record DadosListagemAutorComplementar(Long id,String nomeCompleto, String nomeCita) {

    public DadosListagemAutorComplementar(AutorComplementar autorComplementar){
        this(autorComplementar.getId(), autorComplementar.getNomeCompleto(), autorComplementar.getNomeCita());
    }
}

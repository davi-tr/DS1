package br.com.femass.ProjetoDS1.domain.instituto;

public record DadosUnicoInstituto(String nome, String acronimo) {
    public DadosUnicoInstituto(Instituto instituto){
        this(instituto.getNome(), instituto.getAcronimo());
    }
}

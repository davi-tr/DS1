package br.com.femass.ProjetoDS1.domain.instituto;

public record DadosUnicoInstituto(Long id, String nome, String acronimo) {
    public DadosUnicoInstituto(Instituto instituto){
        this(instituto.getId(),instituto.getNome(), instituto.getAcronimo());
    }
}

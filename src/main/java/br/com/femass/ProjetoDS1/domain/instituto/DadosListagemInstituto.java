package br.com.femass.ProjetoDS1.domain.instituto;

//maneira que dados são recebidos
public record DadosListagemInstituto(Long id, String nome, String acronimo) {
    public DadosListagemInstituto(Instituto instituto) {
        this(instituto.getId(), instituto.getNome(), instituto.getAcronimo());
    }
}

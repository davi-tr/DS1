// Esta é uma classe de registro (record) que define os dados usados para listar informações resumidas de um Instituto.
// Ela contém informações como o ID, nome e acrônimo do Instituto, que são usados na listagem.

package br.com.femass.ProjetoDS1.domain.instituto;

// Definição da classe de registro (record) DadosListagemInstituto.
public record DadosListagemInstituto(Long id, String nome, String acronimo) {
    // Construtor que recebe um objeto do tipo Instituto para criar uma instância de DadosListagemInstituto.
    public DadosListagemInstituto(Instituto instituto) {
        // Usa os métodos de acesso do Instituto para obter o ID, nome e acrônimo e inicializa os campos do registro.
        this(instituto.getId(), instituto.getNome(), instituto.getAcronimo());
    }
}

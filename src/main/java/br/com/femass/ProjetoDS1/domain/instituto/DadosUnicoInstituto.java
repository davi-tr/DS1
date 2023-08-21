// Esta é uma classe de registro (record) que define os dados usados para representar informações detalhadas de um único Instituto.
// Ela contém informações como o ID, nome e acrônimo do Instituto, que são usados para exibir detalhes de um Instituto.

package br.com.femass.ProjetoDS1.domain.instituto;

// Definição da classe de registro (record) DadosUnicoInstituto.
public record DadosUnicoInstituto(Long id, String nome, String acronimo) {
    // Construtor que recebe um objeto do tipo Instituto para criar uma instância de DadosUnicoInstituto.
    public DadosUnicoInstituto(Instituto instituto) {
        // Usa os métodos de acesso do Instituto para obter o ID, nome e acrônimo e inicializa os campos do registro.
        this(instituto.getId(), instituto.getNome(), instituto.getAcronimo());
    }
}

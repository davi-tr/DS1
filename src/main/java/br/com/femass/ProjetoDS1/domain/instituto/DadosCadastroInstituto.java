// Esta é uma classe de registro (record) que define os dados usados para cadastrar um novo Instituto.
// Ela contém informações como o nome e acrônimo do Instituto, que são necessários para o cadastro.

package br.com.femass.ProjetoDS1.domain.instituto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

// Definição da classe de registro (record) DadosCadastroInstituto.
public record DadosCadastroInstituto(
        // Anotação "@NotBlank" indica que o campo "nome" não pode estar em branco (nulo ou vazio).
        @NotBlank
        String nome,
        // Anotação "@NotBlank" indica que o campo "acronimo" não pode estar em branco (nulo ou vazio).
        @NotBlank
        String acronimo
)
{
    // Esta classe de registro (record) é usada para definir os dados necessários para o cadastro de um Instituto.

    // A anotação "@NotBlank" é usada para validar que os campos "nome" e "acronimo" não podem estar em branco,
    // ou seja, eles devem ter conteúdo e não podem ser nulos ou vazios.

    // Essa classe é uma forma conveniente de definir uma estrutura de dados imutável para a criação de novos Institutos.
}

// Esta é uma classe de registro (record) que define os dados usados para atualizar um Instituto.
// Ela contém informações como o ID do Instituto, nome e acrônimo, que podem ser atualizados.

package br.com.femass.ProjetoDS1.domain.instituto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// Definição da classe de registro (record) DadosAtualizarInstituto.
public record DadosAtualizarInstituto(
        // Anotação indicando que o campo "id" não pode ser nulo.
        @NotNull
        Long id,
        // Nome do Instituto que pode ser atualizado.
        String nome,
        // Acrônimo do Instituto que pode ser atualizado.
        String acronimo
)
{
    // Esta classe de registro (record) é uma forma concisa de definir uma classe com campos imutáveis.
    // Ela possui um construtor automático que aceita os parâmetros especificados no cabeçalho.

    // A anotação "@NotNull" indica que o campo "id" não pode ser nulo.
    // Os campos "nome" e "acronimo" podem ser nulos, pois não possuem anotações de validação.
    // Esses campos podem ser atualizados quando novos valores são fornecidos.

    // Os registros (records) são úteis para definir estruturas de dados simples com imutabilidade.
    // Eles podem ser comparados, impressos e possuem métodos de acesso gerados automaticamente.
}

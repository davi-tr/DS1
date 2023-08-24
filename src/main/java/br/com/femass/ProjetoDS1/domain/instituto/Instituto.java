// Esta é a classe que representa um Instituto no sistema.
// Ela possui informações como ID, nome, acrônimo e status do Instituto, além de métodos para atualizar e excluir o Instituto.

package br.com.femass.ProjetoDS1.domain.instituto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

// Define que esta classe representa uma entidade no banco de dados com o nome "instituto".
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "instituto")
@Entity(name = "Instituto")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Instituto {
    // Anotações JPA para mapear as propriedades da entidade no banco de dados.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String acronimo;
    @JsonIgnore
    private boolean status;

    // Construtor que recebe dados de cadastro para criar um novo Instituto.
    public Instituto(DadosCadastroInstituto dados) {
        // Define o status como "true" para indicar que o Instituto está ativo.
        this.status = true;
        this.nome = dados.nome();
        this.acronimo = dados.acronimo();
    }

    // Método para marcar o Instituto como excluído (status "false").
    public void excluir() {
        this.status = false;
    }

    // Método para atualizar os dados do Instituto com base em dados fornecidos.
    public void atualizarInstituto(DadosAtualizarInstituto dados) {
        // Verifica se os novos dados não são nulos e, se não forem, atualiza as propriedades correspondentes.
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.acronimo() != null) {
            this.acronimo = dados.acronimo();
        }
    }
}

// Esta é uma classe de exceção personalizada que estende a classe RuntimeException.
// Ela é usada para representar exceções relacionadas à validação de dados no contexto do projeto.

package br.com.femass.ProjetoDS1.domain;

// Declaração da classe ValidacaoException, que estende RuntimeException.
public class ValidacaoException extends RuntimeException {
    // Construtor da classe que recebe uma mensagem de erro.
    public ValidacaoException(String mensagem) {
        // Chama o construtor da classe pai (RuntimeException) com a mensagem de erro.
        super(mensagem);
    }
}

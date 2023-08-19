package br.com.femass.ProjetoDS1.domain.instituto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
// @notblank uma String restrita é válida desde que não seja nula e o comprimento aparado seja maior que zero.
public record DadosCadastroInstituto(
        @NotBlank
        String nome,
        @NotBlank
        String acronimo
)
{
}

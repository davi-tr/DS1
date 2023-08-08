package br.com.femass.ProjetoDS1.domain.instituto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroInstituto(
        @NotBlank
        String nome,
        @NotBlank
        String acronimo
)
{
}

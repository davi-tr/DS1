package br.com.femass.ProjetoDS1.domain.instituto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroPesquisador(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String instituto
)
{
}

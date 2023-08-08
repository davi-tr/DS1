package br.com.femass.ProjetoDS1.domain.instituto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarInstituto(
        @NotNull
        Long id,
        String nome,
        String acronimo
)
{
}

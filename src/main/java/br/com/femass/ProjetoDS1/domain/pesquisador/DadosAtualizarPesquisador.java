package br.com.femass.ProjetoDS1.domain.pesquisador;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarPesquisador(
        @NotNull
        Long id,

        @NotNull
        Long idNovoInstituto
) {
}

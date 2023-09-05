package br.com.femass.ProjetoDS1.domain.producao;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroProducao(
        @NotNull
        String idPesquisador
) {
}

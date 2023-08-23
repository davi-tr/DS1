package br.com.femass.ProjetoDS1.domain.pesquisador;

import br.com.femass.ProjetoDS1.domain.instituto.DadosUnicoInstituto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroPesquisador(
        @NotNull
        String idPesquisador,
        @Valid
        @NotNull
        DadosUnicoInstituto instituto
) {
}

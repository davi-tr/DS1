package br.com.femass.ProjetoDS1.domain.AutorComplementar;

import br.com.femass.ProjetoDS1.domain.autor.Autor;
import br.com.femass.ProjetoDS1.domain.producao.Producao;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "autor_complementar")
@Entity(name = "AutorComplementar")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class AutorComplementar extends Autor {

    @Column(name="nomeCompleto")
    private String nomeCompleto;
    @Column(name="nomeCita")
    private String nomeCita;

    @ManyToMany
    private List<Producao> producao;

    public AutorComplementar(String nomeCompleto, String nomeCita){
        this.nomeCompleto = nomeCompleto;
        this.nomeCita = nomeCita;
    }

}

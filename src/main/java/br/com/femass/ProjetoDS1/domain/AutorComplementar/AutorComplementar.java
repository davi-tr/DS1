package br.com.femass.ProjetoDS1.domain.AutorComplementar;

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
public class AutorComplementar {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="nomeCompleto")
    private String nomeCompleto;
    @Column(name="nomeCita")
    private String nomeCita;

    @ManyToMany(mappedBy = "autorComplementar")
    private List<Producao> producao;

    public AutorComplementar(String nomeCompleto, String nomeCita){
        this.nomeCompleto = nomeCompleto;
        this.nomeCita = nomeCita;
    }

}

package br.com.femass.ProjetoDS1.domain.AutorComplementar;

import br.com.femass.ProjetoDS1.domain.producao.Producao;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "autorComplementar")
@Entity(name = "AutorComplementar")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class AutorComplementar {
    @Id
    @GeneratedValue
    private Long id;
    private String nomeCompleto;
    private String nomeCita;

    @ManyToMany
    private List<Producao> producao;


}

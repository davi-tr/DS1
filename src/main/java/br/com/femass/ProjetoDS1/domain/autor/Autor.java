package br.com.femass.ProjetoDS1.domain.autor;

import br.com.femass.ProjetoDS1.domain.producao.Producao;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "autor")
@Entity(name = "Autor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Autor {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
}

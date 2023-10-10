package br.com.femass.ProjetoDS1.domain.autorComplementar;

import br.com.femass.ProjetoDS1.domain.autor.Autor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "autor_complementar")
@Entity(name = "AutorComplementar")
@NoArgsConstructor
@Data
public class AutorComplementar extends Autor {

    @Column(name="nomeCompleto")
    private String nomeCompleto;
    @Column(name="nomeCita")
    private String nomeCita;

    public AutorComplementar(String nomeCompleto, String nomeCita){
        this.nomeCompleto = nomeCompleto;
        this.nomeCita = nomeCita;
    }

}

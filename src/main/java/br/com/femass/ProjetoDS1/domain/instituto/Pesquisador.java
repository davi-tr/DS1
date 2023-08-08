package br.com.femass.ProjetoDS1.domain.instituto;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "pesquisador")
@Entity(name = "Pesquisador")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pesquisador {
     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     private String nome;
     private String email;
     private String instituto;

     public void atualizarInfo(DadosCadastroPesquisador dados){
         if(dados.nome() != null){
             this.nome = dados.nome();
         }
         if(dados.email() != null){
             this.email = dados.email();
         }
         if(dados.instituto() !=null){
             this.instituto = dados.instituto();
         }
     }

}

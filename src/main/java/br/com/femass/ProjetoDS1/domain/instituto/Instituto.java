package br.com.femass.ProjetoDS1.domain.instituto;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "instituto")
@Entity(name = "Instituto")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Instituto {
     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     private String nome;
     private String acronimo;
     private boolean status;

     public Instituto(DadosCadastroInstituto dados){
         this.status = true;
         this.nome = dados.nome();
         this.acronimo = dados.acronimo();
     }

     public void excluir(){
         this.status = false;
     }

     public void atualizarInstituto(DadosAtualizarInstituto dados){
         if(dados.nome() != null){
             this.nome = dados.nome();
         }
         if(dados.acronimo() != null){
             this.acronimo = dados.acronimo();
         }
     }


}

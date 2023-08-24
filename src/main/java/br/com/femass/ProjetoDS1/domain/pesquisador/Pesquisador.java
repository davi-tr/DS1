package br.com.femass.ProjetoDS1.domain.pesquisador;


import br.com.femass.ProjetoDS1.domain.instituto.Instituto;
import br.com.femass.ProjetoDS1.domain.instituto.InstitutoRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "pesquisador")
@Entity(name= "Pesquisador")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pesquisador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String idXML;

    private String nome;
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "idISTITUTO")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Instituto instituto;

    private transient InstitutoRepository repository;
    public void setInstituto(Instituto instituto) {
        this.instituto = instituto;
    }

    public Pesquisador(DadosCadastroPesquisador dados){
        this.status = true;
        String valoresXML[] = LerXML(EncontrarXML(dados.idPesquisador()));
        this.idXML = valoresXML[0];
        this.nome = valoresXML[1];
    }

    public void atualizar(DadosAtualizarPesquisador dados, Instituto instituto){
        if (dados.idNovoInstituto() != null){
            this.instituto = instituto;
        }
    }
    public void excluir(){
        this.status = false;
    }

    public static String EncontrarXML(String idPesquisador){
        String diretorio = "./Curriculos_XML";

        // Defina o nome do arquivo que você está procurando
        String nomeArquivo = idPesquisador;
        String nomeArquivoFormatado = nomeArquivo + ".xml";
        String caminhoXML;

        // Crie um objeto File representando o diretório
        File pasta = new File(diretorio);

        // Verifique se a pasta existe e é um diretório
        if (pasta.exists() && pasta.isDirectory()) {
            // Liste os arquivos no diretório
            File[] arquivos = pasta.listFiles();

            if (arquivos != null) {
                // Percorra os arquivos para encontrar o arquivo desejado
                for (File arquivo : arquivos) {
                    if (arquivo.isFile() && arquivo.getName().equals(nomeArquivoFormatado)) {
                        caminhoXML = arquivo.getAbsolutePath();

                        return caminhoXML; // Encerre o loop após encontrar o arquivo
                    }
                }
            }

            return "Arquivo não encontrado na pasta.";
        } else {
            return "Diretório especificado não existe ou não é uma pasta.";
        }
    }
    public static String[] LerXML(String caminho){
        try {
            File inputFile = new File(caminho);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputFile);

            document.getDocumentElement().normalize();

            Element dadosGeraisElement = (Element) document.getElementsByTagName("DADOS-GERAIS").item(0);
            Element idfind = (Element) document.getElementsByTagName("CURRICULO-VITAE").item(0);

            String numeroIdentificador = idfind.getAttribute("NUMERO-IDENTIFICADOR");
            String nomeCompleto = dadosGeraisElement.getAttribute("NOME-COMPLETO");

            return new String[] { numeroIdentificador, nomeCompleto };
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

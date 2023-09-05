package br.com.femass.ProjetoDS1.domain.producao;

import br.com.femass.ProjetoDS1.domain.pesquisador.Pesquisador;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "producao")
@Entity(name = "Producao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Producao{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String ano;
    private boolean status;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "pesquisadorListado")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Pesquisador> pesquisadores;

    public Producao(DadosCadastroProducao dados){
        this.status = true;
        String controle = EncontrarXML(dados.idPesquisador());
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


    public static List<String> encontrarProducao(String caminho) {
        try {
            File inputFile = new File(caminho);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputFile);

            document.getDocumentElement().normalize();

            ArrayList<String> artigos = new ArrayList<>();
            NodeList artigoList = document.getElementsByTagName("ARTIGO-PUBLICADO");

            for (int i = 0; i < artigoList.getLength(); i++) {
                Node node = artigoList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementoArtigo = (Element) node;
                    String tituloDoArtigo = elementoArtigo.getAttribute("TITULO-DO-ARTIGO");
                    String anoDoArtigo = elementoArtigo.getAttribute("ANO-DO-ARTIGO");
                    artigos.add("Título: " + tituloDoArtigo + ", Ano: " + anoDoArtigo);
                }
            }

            return artigos;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

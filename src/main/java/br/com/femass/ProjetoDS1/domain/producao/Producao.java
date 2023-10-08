package br.com.femass.ProjetoDS1.domain.producao;

import br.com.femass.ProjetoDS1.domain.AutorComplementar.AutorComplementar;
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
import java.util.List;

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
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "producao_resultante",
                        joinColumns = @JoinColumn(name ="id_producao"),
                        inverseJoinColumns = @JoinColumn(name = "id_pesquisador"))
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Pesquisador> pesquisador;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "producao_resultante",
            joinColumns = @JoinColumn(name ="id_producao"),
            inverseJoinColumns = @JoinColumn(name = "id_AutorComplementar"))
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<AutorComplementar> autorComplementar;



    public Producao(DadosCadastroProducao dados){
        this.status = true;
        String controle = EncontrarXML(dados.idPesquisador());
        this.tipo = Tipo.ARTIGO;
    }

    public void adicionar (Pesquisador NovoPesquisador){
        pesquisador.add(NovoPesquisador);
    }
    public void adicionarComplementar (AutorComplementar novoAutor){
        autorComplementar.add(novoAutor);
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

    public static List<String> encontrarAutoresComplementares(String caminho, String tituloEspecifico) {
        try {
            File inputFile = new File(caminho);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputFile);

            document.getDocumentElement().normalize();

            ArrayList<String> autoresComplementares = new ArrayList<>();
            NodeList artigoList = document.getElementsByTagName("ARTIGO-PUBLICADO");

            for (int i = 0; i < artigoList.getLength(); i++) {
                Node node = artigoList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element artigo = (Element) node;
                    Element dadosBasicos = (Element) artigo.getElementsByTagName("DADOS-BASICOS-DO-ARTIGO").item(0);
                    String tituloArtigo = dadosBasicos.getAttribute("TITULO-DO-ARTIGO");

                    if (tituloArtigo.equals(tituloEspecifico)) {
                        NodeList autoresList = artigo.getElementsByTagName("AUTORES");

                        for (int j = 0; j < autoresList.getLength(); j++) {
                            Node autorNode = autoresList.item(j);

                            if (autorNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element autor = (Element) autorNode;
                                String nomeCompleto = autor.getAttribute("NOME-COMPLETO-DO-AUTOR");
                                String nomeCita = autor.getAttribute("NOME-PARA-CITACAO");
                                autoresComplementares.add(nomeCompleto + "-2" + nomeCita);
                            }
                        }
                    }
                }
            }

            return autoresComplementares;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static List<String> encontrarArtigos(String caminho) {
        try {
            File inputFile = new File(caminho);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputFile);

            document.getDocumentElement().normalize();

            ArrayList<String> artigos = new ArrayList<>();
            NodeList artigoList = document.getElementsByTagName("ARTIGO-PUBLICADO");
            NodeList artigoList2 = document.getElementsByTagName("DADOS-BASICOS-DO-ARTIGO");

            for (int i = 0; i < artigoList2.getLength(); i++) {
                Node node = artigoList2.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementoArtigo = (Element) node;
                    String tituloDoArtigo = elementoArtigo.getAttribute("TITULO-DO-ARTIGO");
                    String anoDoArtigo = elementoArtigo.getAttribute("ANO-DO-ARTIGO");
                    artigos.add(tituloDoArtigo + "-2" + anoDoArtigo);
                }
            }

            return artigos;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<String> encontrarLivroeCapitulo (String caminho){
        try {
            File inputFile = new File(caminho);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputFile);

            document.getDocumentElement().normalize();

            ArrayList<String> artigos = new ArrayList<>();
            NodeList artigoList = document.getElementsByTagName("LIVROS-E-CAPITULOS");
            NodeList artigoList2 = document.getElementsByTagName("DADOS-BASICOS-DO-LIVRO");

            for (int i = 0; i < artigoList2.getLength(); i++) {
                Node node = artigoList2.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementoArtigo = (Element) node;
                    String tituloDoArtigo = elementoArtigo.getAttribute("TITULO-DO-LIVRO");
                    String anoDoArtigo = elementoArtigo.getAttribute("ANO");
                    artigos.add(tituloDoArtigo + "-2" + anoDoArtigo);
                }
            }

            return artigos;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

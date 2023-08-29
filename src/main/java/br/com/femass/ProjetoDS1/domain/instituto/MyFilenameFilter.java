package br.com.femass.ProjetoDS1.domain.instituto;

import br.com.femass.ProjetoDS1.domain.pesquisador.Pesquisador;

import java.io.File;
import java.io.FilenameFilter;

public class MyFilenameFilter implements FilenameFilter {

    String initials;

    // constructor to initialize object
    public MyFilenameFilter(String initials)
    {
        this.initials = initials;
    }
    // overriding the accept method of FilenameFilter
    // interface
    public boolean accept(File dir, String name)
    {
        return name.startsWith(initials);
    }

    public static void main(String[] args)
    {
        Pesquisador pesquisador = new Pesquisador();
        String[] nome = pesquisador.LerXML(pesquisador.EncontrarXML("0194631586754988"));
        pesquisador.setNome(nome[1]);
        pesquisador.setId(Long.valueOf(nome[0]));

    }
}

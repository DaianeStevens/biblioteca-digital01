/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.unijui.xml.docs;

import br.edu.unijui.model.dao.LocacaoImpl;
import br.edu.unijui.xml.ManipuladorXML;
import java.sql.SQLException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author chayk
 */
public class ExportaLocacoes {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        LocacaoImpl locacaoImpl = new LocacaoImpl();

        var locacoesList = locacaoImpl.getLocacaoesLivros();

        Document doc = ManipuladorXML.newDocument();

        Element locacoes = doc.createElement("Locacoes");
        doc.appendChild(locacoes);

        for (var i = 0; i < locacoesList.size(); i++) {
            var dados = locacoesList.get(i);

            Element locacao = doc.createElement("Locacao");
            locacoes.appendChild(locacao);

            Element id = doc.createElement("Id");
            id.setTextContent(Integer.toString(dados.getId()));
            locacao.appendChild(id);

            Element idUsuario = doc.createElement("IdUsuario");
            idUsuario.setTextContent(Integer.toString(dados.getIdUsuario()));
            locacao.appendChild(idUsuario);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
            Element dtPrazoDevolucao = doc.createElement("DtPrazoDevolucao");
            dtPrazoDevolucao.setTextContent(dateFormat.format(dados.getDtPrazoDevolucao()));
            locacao.appendChild(dtPrazoDevolucao);

            // <Valor>
            Element dtLocacao = doc.createElement("DtLocacao");
            dtLocacao.setTextContent(dateFormat.format(dados.getDtLocacao()));
            locacao.appendChild(dtLocacao);

            Element dtDevolucao = doc.createElement("DtDevolucao");
            if (dados.getDtDevolucao() != null) {
                dtDevolucao.setTextContent(dateFormat.format(dados.getDtDevolucao()));
            }
            locacao.appendChild(dtDevolucao);

            Element livros = doc.createElement("Livros");
            locacao.appendChild(livros);
            var livrosList = dados.getLivros();
            for (var l = 0; l < livrosList.size(); l++) {
                var dadosLivro = livrosList.get(l);

                Element livro = doc.createElement("Livro");
                livros.appendChild(livro);

                Element idTable = doc.createElement("Id");
                idTable.setTextContent(Integer.toString(dadosLivro.getId()));
                livro.appendChild(idTable);

                Element idLocacao = doc.createElement("IdLocacao");
                idLocacao.setTextContent(Integer.toString(dadosLivro.getIdLocacao()));
                livro.appendChild(idLocacao);

                Element idLivro = doc.createElement("IdLivro");
                idLivro.setTextContent(Integer.toString(dadosLivro.getIdLivro()));
                livro.appendChild(idLivro);
            }
        }

        ManipuladorXML.writeXmlFile(doc, "./locacoes.xml");
    }
}

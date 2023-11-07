/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.unijui.xml.docs;

import br.edu.unijui.dataBase.DataBase;
import br.edu.unijui.log.Log;
import br.edu.unijui.model.Livro;
import br.edu.unijui.model.dao.LivroImpl;
import br.edu.unijui.xml.ManipuladorXML;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author chayk
 */
public class ImportarLivros {

    private Log log;

    public ImportarLivros() {
        log = new Log();
    }

    public void importar(String caminho) throws ClassNotFoundException, SQLException {

        Document doc = ManipuladorXML.readXmlFile("C:/Users/chayk/OneDrive/Documentos/Facul/8º sem/Linguagem de Programação III/Trabalho 01/biblioteca-digital01/livros.xml");
        Element root = doc.getDocumentElement();
        NodeList livros = root.getElementsByTagName("livro");

        for (int i = 0; i < livros.getLength(); i++) {
            Element livro = (Element) livros.item(i);

            String cod = livro.getAttribute("cod");

            NodeList tituloNodes = livro.getElementsByTagName("titulo");
            String titulo = tituloNodes.getLength() > 0 ? tituloNodes.item(0).getTextContent() : "Título não encontrado";

            NodeList autorNodes = livro.getElementsByTagName("autor");
            String autor = autorNodes.getLength() > 0 ? autorNodes.item(0).getTextContent() : "Autor não encontrado";

            NodeList qtdExemplarNodes = livro.getElementsByTagName("qtd_exemplar");
            String qtdExemplarStr = qtdExemplarNodes.getLength() > 0 ? (qtdExemplarNodes.item(0).getTextContent()) : "0";

            NodeList dtIncluirNodes = livro.getElementsByTagName("dt_incluir");
            String dtIncluirStr = dtIncluirNodes.getLength() > 0 ? dtIncluirNodes.item(0).getTextContent() : "";

            NodeList dtBaixaNodes = livro.getElementsByTagName("dt_baixa");
            String dtBaixaStr = dtBaixaNodes.getLength() > 0 ? dtBaixaNodes.item(0).getTextContent() : "";

            Date dtIncluir = new java.sql.Date(new java.util.Date().getTime());
            Date dtBaixa = null;
            int qtdExemplar = 0;

            if (qtdExemplarStr != "") {
                qtdExemplar = Integer.parseInt(qtdExemplarStr);
            }

            if (dtIncluirStr != "") {
                dtIncluir = Date.valueOf(dtIncluirStr);
            }

            if (dtBaixaStr != "") {
                dtBaixa = Date.valueOf(dtBaixaStr);
            }

            Livro objLivro = new Livro();

            objLivro.setTitulo(titulo);
            objLivro.setAutor(autor);
            objLivro.setQtdExemplar(qtdExemplar);
            objLivro.setDtInclusao(dtIncluir);
            objLivro.setDtBaixa(dtBaixa);

            LivroImpl livroImpl = new LivroImpl();
            livroImpl.insereLivro(objLivro);
        }
        log.GravaLog("INFO", "Importou livros.");
        JOptionPane.showMessageDialog(null, "Livros importados com sucesso!");

    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.unijui.xml.docs;

import br.edu.unijui.model.Livro;
import br.edu.unijui.model.dao.LivroImpl;
import br.edu.unijui.xml.ManipuladorXML;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Document doc = ManipuladorXML.readXmlFile("./livros.xml");
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
            int qtdExemplar = qtdExemplarNodes.getLength() > 0 ? Integer.parseInt(qtdExemplarNodes.item(0).getTextContent()) : 0;

            NodeList dtIncluirNodes = livro.getElementsByTagName("dt_incluir");
            Date dtIncluir = Date.valueOf(dtIncluirNodes.getLength() > 0 ? dtIncluirNodes.item(0).getTextContent() : null);

            NodeList dtBaixaNodes = livro.getElementsByTagName("dt_baixa");
            Date dtBaixa = Date.valueOf(dtBaixaNodes.getLength() > 0 ? dtBaixaNodes.item(0).getTextContent() : null);

            Livro objLivro = new Livro();

            objLivro.setTitulo(titulo);
            objLivro.setAutor(autor);
            objLivro.setQtdExemplar(qtdExemplar);
            objLivro.setDtInclusao(dtIncluir);
            objLivro.setDtBaixa(dtBaixa);
            
           LivroImpl livroImpl = new LivroImpl();
           livroImpl.insereLivro(objLivro);
        }

    }
}

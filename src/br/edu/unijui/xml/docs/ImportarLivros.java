/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.unijui.xml.docs;

import br.edu.unijui.xml.ManipuladorXML;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;

/**
 *
 * @author chayk
 */
public class ImportarLivros {

    public static void main(String[] args) {

        try {

            Document doc = ManipuladorXML.readXmlFile("./livros.xml");

            // Read NODE values
            XPathExpression e1 = ManipuladorXML.getXPathExpression("//livro[@cod='L01']/titulo");
            String titulo = (String) e1.evaluate(doc, XPathConstants.STRING);

            XPathExpression e2 = ManipuladorXML.getXPathExpression("//livro[@cod='L01']/autor");
            String autor = ((String) e2.evaluate(doc, XPathConstants.STRING));

            XPathExpression e3 = ManipuladorXML.getXPathExpression("//livro[@cod='L01']/qtd_exemplar");
            String qtd_exemplar = ((String) e3.evaluate(doc, XPathConstants.STRING));

            XPathExpression e4 = ManipuladorXML.getXPathExpression("//livro[@cod='L01']/dt_incluir");
            String dt_incluir = ((String) e4.evaluate(doc, XPathConstants.STRING));

            XPathExpression e5 = ManipuladorXML.getXPathExpression("//livro[@cod='L01']/dt_baixa");
            String dt_baixa = ((String) e5.evaluate(doc, XPathConstants.STRING));

            System.out.println("Nome: " + titulo);
            System.out.println("Sobrenome: " + autor);
            System.out.println("Quantidade: " + qtd_exemplar);
            System.out.println("Datam inclusão: " + dt_incluir);
            System.out.println("Data Baixa: " + dt_baixa);
            
        } catch (XPathExpressionException ex) {
            System.out.println(ex);
        }

    }
}

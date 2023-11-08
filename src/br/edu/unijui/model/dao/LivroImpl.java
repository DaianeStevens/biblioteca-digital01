package br.edu.unijui.model.dao;

import br.edu.unijui.dataBase.DataBase;
import br.edu.unijui.model.Livro;
import br.edu.unijui.log.Log;
import static java.awt.image.ImageObserver.ERROR;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.Properties;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.XMLFormatter;

/**
 *
 * @author daias
 */
public class LivroImpl implements LivroDAO {

    private Log log;
    private final Connection con;
    private PreparedStatement pstmtListaLivro;
    private PreparedStatement pstmtListaLivroFiltro;
    private PreparedStatement pstmtInsereLivro;

    public LivroImpl() throws ClassNotFoundException, SQLException {
        con = new DataBase().getConnection();
        log = new Log();
        inicializarPreparedStatements();
    }

    private void inicializarPreparedStatements() throws SQLException {
        pstmtListaLivro = con.prepareStatement("select * from livro order by titulo");
        pstmtListaLivroFiltro = con.prepareStatement("select * from livro where lower(titulo) like ? order by titulo");
        pstmtInsereLivro = con.prepareStatement("insert into livro values(default, ?, ?, ?, ?, ?)");

    }

    @Override
    public ArrayList<Livro> getLivrosFiltro(String filtro) {
        if (filtro == null) {
            filtro = "";
        }
        ArrayList<Livro> livros = new ArrayList<Livro>();
        try {
            pstmtListaLivroFiltro.setString(1, '%' + filtro.toLowerCase() + '%');
            ResultSet resultSet = pstmtListaLivroFiltro.executeQuery();

            while (resultSet.next()) {
                Livro livro = new Livro();
                livro.setId(resultSet.getInt(1));
                livro.setTitulo((String) resultSet.getObject(2));
                livro.setAutor((String) resultSet.getObject(3));
                livro.setQtdExemplar(resultSet.getInt(4));
                livro.setDtInclusao((Date) resultSet.getObject(5));
                livro.setDtBaixa((Date) resultSet.getObject(6));
                livros.add(livro);
            }
            log.GravaLog("INFO", "Consulta de livros.");

        } catch (SQLException ex) {
            log.GravaLog("SEVERE", "Erro ao consultar livros.");
            return livros;

        }
        return livros;
    }

    public void insereLivro(Livro livro) {
        try {

            pstmtInsereLivro.setString(1, livro.getTitulo());
            pstmtInsereLivro.setString(2, livro.getAutor());
            pstmtInsereLivro.setInt(3, livro.getQtdExemplar());
            pstmtInsereLivro.setDate(4, (Date) livro.getDtInclusao());
            pstmtInsereLivro.setDate(5, (Date) livro.getDtBaixa());

            pstmtInsereLivro.execute();
            log.GravaLog("INFO", "Inseriu um livro.");

        } catch (SQLException ex) {
            log.GravaLog("INFO", "Erro ao inserir um livro.");
        }

    }

}

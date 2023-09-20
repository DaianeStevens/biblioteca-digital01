package br.edu.unijui.model.dao;

import br.edu.unijui.dataBase.DataBase;
import br.edu.unijui.model.Livro;
import br.edu.unijui.model.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daias
 */
public class LivroImpl implements LivroDAO {

    private final Connection con;
    private PreparedStatement pstmtListaLivro;
    private PreparedStatement pstmtListaLivroFiltro;

    public LivroImpl() throws ClassNotFoundException, SQLException {
        con = new DataBase().getConnection();
        inicializarPreparedStatements();
    }

    private void inicializarPreparedStatements() throws SQLException {
        pstmtListaLivro = con.prepareStatement("select * from livro order by titulo");
        pstmtListaLivroFiltro = con.prepareStatement("select * from livro where lower(titulo) like ? order by titulo");
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
                System.out.println("select livros filtro " + pstmtListaLivro.toString());
                Livro livro = new Livro();
                livro.setId(resultSet.getInt(1));
                livro.setTitulo((String) resultSet.getObject(2));
                livro.setAutor((String) resultSet.getObject(3));
                livro.setQtdExemplar(resultSet.getInt(4));
                livro.setDtInclusao((Date) resultSet.getObject(5));
                livro.setDtBaixa((Date) resultSet.getObject(6));
                livros.add(livro);

            }

        } catch (SQLException ex) {
            {
                Logger.getLogger(UsuarioImpl.class.getName()).log(Level.SEVERE, null, ex);
                return livros;
            }

        }
        return livros;
    }

}

package br.edu.unijui.model.dao;

import br.edu.unijui.dataBase.DataBase;
import br.edu.unijui.model.Livro;
import br.edu.unijui.model.Locacao;
import br.edu.unijui.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daias
 */
public class LocacaoImpl implements LocacaoDAO {

    private final Connection con;
    private PreparedStatement pstmtListaLocacao;
    private PreparedStatement pstmtInsereLocacao;

    public LocacaoImpl() throws ClassNotFoundException, SQLException {
        con = new DataBase().getConnection();
        inicializarPreparedStatements();
    }

    private void inicializarPreparedStatements() throws SQLException {
        pstmtListaLocacao = con.prepareStatement("select * from locacao order by dt_locacao desc");
        pstmtInsereLocacao = con.prepareStatement("insert into locacao (id, id_usuario, dt_prazo_devolucao, dt_locacao, dt_devolucao) values(default, ?,  ?,  ?,   null);");
    }

    @Override
    public ArrayList<Locacao> getLocacaoes() {
        ArrayList<Locacao> locacoes = new ArrayList<Locacao>();
        try {
            ResultSet resultSet = pstmtListaLocacao.executeQuery();
            while (resultSet.next()) {
                System.out.println("select usuarios filtro");
                Locacao locacao = new Locacao();
                locacao.setId(resultSet.getInt(1));
                locacao.setIdUsuario(resultSet.getInt(2));
                locacao.setDtPrazoDevolucao((Date) resultSet.getObject(3));
                locacao.setDtLocacao((Date) resultSet.getObject(4));
                locacao.setDtDevolucao((Date) resultSet.getObject(5));

                locacoes.add(locacao);

            }

        } catch (SQLException ex) {
            {
                Logger.getLogger(UsuarioImpl.class.getName()).log(Level.SEVERE, null, ex);
                return locacoes;
            }

        }
        return locacoes;
    }

    @Override
    public void insereLocacao(Locacao locacao, ArrayList<Livro> livros) {
        try {
            con.setAutoCommit(false);
            try {

                pstmtInsereLocacao.setInt(1, locacao.getIdUsuario());
                pstmtInsereLocacao.setDate(2, locacao.getDtPrazoDevolucao());
                pstmtInsereLocacao.setDate(3, locacao.getDtLocacao());
                pstmtInsereLocacao.execute();

             

                // Obtenha as chaves geradas
                ResultSet generatedKeys = pstmtInsereLocacao.getGeneratedKeys();

                if (generatedKeys.next()) {
                    int idGerado = generatedKeys.getInt(1); // Supondo que o ID seja um valor inteiro
                    // Faça o que desejar com o ID gerado
                    System.out.println("ID da locação inserida: " + idGerado);
                }
   con.commit();
            } catch (Exception ex) {
                try {
                    con.rollback();

                    Logger.getLogger(LocacaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex1) {
                    Logger.getLogger(LocacaoImpl.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
            con.setAutoCommit(true);

        } catch (SQLException ex) {
            Logger.getLogger(LocacaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

package br.edu.unijui.model.dao;

import br.edu.unijui.dataBase.DataBase;
import br.edu.unijui.model.Livro;
import br.edu.unijui.model.Locacao;
import br.edu.unijui.model.LocacaoLivro;
import br.edu.unijui.model.Usuario;
import com.sun.net.httpserver.Authenticator;
import static java.awt.image.ImageObserver.ERROR;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author daias
 */
public class LocacaoImpl implements LocacaoDAO {

    private final Connection con;
    private PreparedStatement pstmtListaLocacao;
    private PreparedStatement pstmtInsereLocacao;
    private PreparedStatement pstmtDeletaLocacao;

    private PreparedStatement pstmtInsereLocacaoLivro;
    private PreparedStatement pstmtDeletaLocacaoLivro;

    public LocacaoImpl() throws ClassNotFoundException, SQLException {
        con = new DataBase().getConnection();
        inicializarPreparedStatements();
    }

    private void inicializarPreparedStatements() throws SQLException {
        pstmtListaLocacao = con.prepareStatement("select * from locacao order by dt_locacao desc");
        pstmtInsereLocacao = con.prepareStatement("insert into locacao values(default, ?,  ?,  ?, null)", PreparedStatement.RETURN_GENERATED_KEYS);
        pstmtDeletaLocacao = con.prepareStatement("delete from locacao where id = ?");

        pstmtInsereLocacaoLivro = con.prepareStatement("insert into locacao_livro values(default, ?,  ?)");
        pstmtDeletaLocacaoLivro = con.prepareStatement("delete from locacao_livro where id_locacao = ?");
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
                boolean teste = pstmtInsereLocacao.execute();

                // Obtenha as chaves geradas
                ResultSet generatedKeys = pstmtInsereLocacao.getGeneratedKeys();

                if (generatedKeys.next()) {
                    int idGerado = generatedKeys.getInt(1);

                    for (Livro livro : livros) {
                        pstmtInsereLocacaoLivro.setInt(1, idGerado);
                        pstmtInsereLocacaoLivro.setInt(2, livro.getId());
                        pstmtInsereLocacaoLivro.execute();
                    }
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
            JOptionPane.showMessageDialog(null, "Locação cadastrada com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar locação!", "Erro!", ERROR);
            Logger.getLogger(LocacaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void deletaLocacao(Integer id) {
//        try {
//            pstmtDeletaLocacao.setInt(1, id);
//            boolean teste = pstmtDeletaLocacao.execute();
//            JOptionPane.showMessageDialog(null, "Locação excluída com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Erro ao excluir locação!", "Erro!", JOptionPane.ERROR_MESSAGE);
//            Logger.getLogger(LocacaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }

        try {
            con.setAutoCommit(false);
            try {
                
                pstmtDeletaLocacaoLivro.setInt(1, id);
                pstmtDeletaLocacaoLivro.execute();
                pstmtDeletaLocacao.setInt(1, id);
                pstmtDeletaLocacao.execute();

                con.commit();

            } catch (SQLException ex) {
                try {
                    con.rollback();

                    Logger.getLogger(LocacaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex1) {
                    Logger.getLogger(LocacaoImpl.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
            con.setAutoCommit(true);
            JOptionPane.showMessageDialog(null, "Locação cadastrada com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar locação!", "Erro!", ERROR);
            Logger.getLogger(LocacaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

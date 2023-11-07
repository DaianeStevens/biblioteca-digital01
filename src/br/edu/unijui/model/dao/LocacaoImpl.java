package br.edu.unijui.model.dao;

import br.edu.unijui.dataBase.DataBase;
import br.edu.unijui.log.Log;
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
import java.time.LocalDate;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author daias
 */
public class LocacaoImpl implements LocacaoDAO {

    private Log log;
    private final Connection con;
    private PreparedStatement pstmtListaLocacao;
    private PreparedStatement pstmtInsereLocacao;
    private PreparedStatement pstmtDeletaLocacao;

    private PreparedStatement pstmtInsereLocacaoLivro;
    private PreparedStatement pstmtDeletaLocacaoLivro;
    private PreparedStatement pstmtDevolveLocacaoLivro;
    private PreparedStatement pstmtListaLocacaoLivro;

    public LocacaoImpl() throws ClassNotFoundException, SQLException {
        con = new DataBase().getConnection();
        log = new Log();
        inicializarPreparedStatements();
    }

    private void inicializarPreparedStatements() throws SQLException {
        pstmtListaLocacao = con.prepareStatement("select * from locacao order by dt_locacao desc");
        pstmtInsereLocacao = con.prepareStatement("insert into locacao values(default, ?,  ?,  ?, null)", PreparedStatement.RETURN_GENERATED_KEYS);
        pstmtDeletaLocacao = con.prepareStatement("delete from locacao where id = ?");

        pstmtListaLocacaoLivro = con.prepareStatement("select * from locacao_livro where id_locacao=?");
        pstmtInsereLocacaoLivro = con.prepareStatement("insert into locacao_livro values(default, ?,  ?)");
        pstmtDeletaLocacaoLivro = con.prepareStatement("delete from locacao_livro where id_locacao = ?");
        pstmtDevolveLocacaoLivro = con.prepareStatement("UPDATE locacao SET dt_devolucao=? WHERE id=?");
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
            log.GravaLog("INFO", "Consulta de locações.");
        } catch (SQLException ex) {
            {
                log.GravaLog("SEVERE", "Erro ao consultar locações.");
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
                log.GravaLog("INFO", "Inseriu uma locação.");
            } catch (Exception ex) {
                log.GravaLog("SEVERE", "Erro ao inserir um usuário.");
                try {
                    con.rollback();
                } catch (SQLException ex1) {
                    log.GravaLog("SEVERE", "Erro SQLException.");
                }
            }
            con.setAutoCommit(true);
            JOptionPane.showMessageDialog(null, "Locação cadastrada com sucesso!");
        } catch (SQLException ex) {
            log.GravaLog("SEVERE", "Erro SQLException.");
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar locação!", "Erro!", ERROR);

        }

    }

    @Override
    public void deletaLocacao(Integer id) {

        try {
            con.setAutoCommit(false);
            try {

                pstmtDeletaLocacaoLivro.setInt(1, id);
                pstmtDeletaLocacaoLivro.execute();
                pstmtDeletaLocacao.setInt(1, id);
                pstmtDeletaLocacao.execute();

                con.commit();
                log.GravaLog("INFO", "Deletou uma locação.");
            } catch (SQLException ex) {
                try {
                    con.rollback();

                } catch (SQLException ex1) {
                    log.GravaLog("SEVERE", "Erro SQLException.");
                }
            }
            con.setAutoCommit(true);
            JOptionPane.showMessageDialog(null, "Locação cadastrada com sucesso!");
        } catch (SQLException ex) {
            log.GravaLog("SEVERE", "Erro SQLException.");
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar locação!", "Erro!", ERROR);
        }

    }

    @Override
    public void devolveLocacao(Integer id) {
        try {
            LocalDate dataAtual = LocalDate.now();
            Date dataAtualComoSqlDate = Date.valueOf(dataAtual);
            pstmtDevolveLocacaoLivro.setDate(1, dataAtualComoSqlDate);
            pstmtDevolveLocacaoLivro.setInt(2, id);
            pstmtDevolveLocacaoLivro.execute();

            log.GravaLog("INFO", "Registrou uma devolução.");

            JOptionPane.showMessageDialog(null, "Locação devolvida com sucesso!");
        } catch (SQLException ex) {
            log.GravaLog("SEVERE", "Erro SQLException.");
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar locação!", "Erro!", ERROR);
        }
    }

    @Override
    public ArrayList<Locacao> getLocacaoesLivros() {
        ArrayList<Locacao> locacoes = new ArrayList<Locacao>();
        try {
            ResultSet resultSet = pstmtListaLocacao.executeQuery();
            while (resultSet.next()) {
                System.out.println("select usuarios filtro");
                Locacao locacao = new Locacao();
                var id = resultSet.getInt(1);
                locacao.setId(id);
                locacao.setIdUsuario(resultSet.getInt(2));
                locacao.setDtPrazoDevolucao((Date) resultSet.getObject(3));
                locacao.setDtLocacao((Date) resultSet.getObject(4));
                locacao.setDtDevolucao((Date) resultSet.getObject(5));

                ArrayList<LocacaoLivro> livros = new ArrayList<LocacaoLivro>();
                pstmtListaLocacaoLivro.setInt(1, id);
                ResultSet resultSetLivros = pstmtListaLocacaoLivro.executeQuery();

                while (resultSetLivros.next()) {
                    LocacaoLivro locacaoLivro = new LocacaoLivro();
                    locacaoLivro.setId(resultSetLivros.getInt(1));
                    locacaoLivro.setIdLocacao(resultSetLivros.getInt(2));
                    locacaoLivro.setIdLivro(resultSetLivros.getInt(3));

                    livros.add(locacaoLivro);
                }

                locacao.setLivros(livros);
                locacoes.add(locacao);

            }

            log.GravaLog("INFO", "Consulta de locações.");

        } catch (SQLException ex) {

            log.GravaLog("SEVERE", "Erro SQLException.");
            return locacoes;

        }
        return locacoes;
    }
}

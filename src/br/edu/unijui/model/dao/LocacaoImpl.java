package br.edu.unijui.model.dao;

import br.edu.unijui.dataBase.DataBase;
import br.edu.unijui.model.Locacao;
import br.edu.unijui.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daias
 */
public class LocacaoImpl implements LocacaoDAO {

    private final Connection con;
    private PreparedStatement pstmtListaLocacao;

    public LocacaoImpl() throws ClassNotFoundException, SQLException {
        con = new DataBase().getConnection();
        inicializarPreparedStatements();
    }

    private void inicializarPreparedStatements() throws SQLException {
        pstmtListaLocacao = con.prepareStatement("select * from locacao order by dt_locacao desc");
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

}

package br.edu.unijui.model.dao;

import br.edu.unijui.dataBase.DataBase;
import br.edu.unijui.model.Usuario;
import java.sql.Connection;
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
public class UsuarioImpl implements UsuarioDAO {

    private final Connection con;
    private PreparedStatement pstmtListaUsuario;

    public UsuarioImpl() throws ClassNotFoundException, SQLException {
        con = new DataBase().getConnection();
        inicializarPreparedStatements();
    }

    private void inicializarPreparedStatements() throws SQLException {
        pstmtListaUsuario = con.prepareStatement("select * from usuario where lower(nome) like ? order by nome");
    }

    @Override
    public ArrayList<Usuario> getUsuariosFiltro(String filtro) {
        if (filtro == null) {
            filtro = "";
        }
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            pstmtListaUsuario.setString(1, '%' + filtro.toLowerCase() + '%');
            ResultSet resultSet = pstmtListaUsuario.executeQuery();
            while (resultSet.next()) {
                System.out.println("select usuarios filtro");
                Usuario usuario = new Usuario();
                usuario.setId(resultSet.getInt(1));
                usuario.setNome((String) resultSet.getObject(2));
                usuario.setSenha((String) resultSet.getObject(3));
                usuario.setTelefone((String) resultSet.getObject(4));
                usuario.setEndereco((String) resultSet.getObject(5));
                usuario.setBairro((String) resultSet.getObject(6));
                usuario.setCidade((String) resultSet.getObject(7));
                usuario.setUf((String) resultSet.getObject(8));
                usuario.setCpf((String) resultSet.getObject(9));
                usuario.setAtivo((boolean) resultSet.getObject(10));
                usuarios.add(usuario);

            }

        } catch (SQLException ex) {
            {
                Logger.getLogger(UsuarioImpl.class.getName()).log(Level.SEVERE, null, ex);
                return usuarios;
            }

        }
        return usuarios;

    }

}

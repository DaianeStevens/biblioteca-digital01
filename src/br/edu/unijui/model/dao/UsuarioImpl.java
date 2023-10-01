package br.edu.unijui.model.dao;

import br.edu.unijui.dataBase.DataBase;
import br.edu.unijui.model.Usuario;
import static java.awt.image.ImageObserver.ERROR;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author daias
 */
public class UsuarioImpl implements UsuarioDAO {

    private final Connection con;
    private PreparedStatement pstmtListaUsuario;
    private PreparedStatement pstmtInsereUsuario;

    public UsuarioImpl() throws ClassNotFoundException, SQLException {
        con = new DataBase().getConnection();
        inicializarPreparedStatements();
    }

    private void inicializarPreparedStatements() throws SQLException {
        pstmtListaUsuario = con.prepareStatement("select * from usuario where lower(nome) like ? order by nome");
        pstmtInsereUsuario = con.prepareStatement("insert into usuario values(default, ?, ?, ?, ?, ?, ?, ?, ?, 1)");
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

     public void insereUsuario(Usuario usuario) {
        try {

            pstmtInsereUsuario.setString(1, usuario.getNome());
            pstmtInsereUsuario.setString(2, usuario.getSenha());
            pstmtInsereUsuario.setString(3,usuario.getTelefone());
            pstmtInsereUsuario.setString(4, usuario.getEndereco());
            pstmtInsereUsuario.setString(5, usuario.getBairro());
            pstmtInsereUsuario.setString(6, usuario.getCidade());
            pstmtInsereUsuario.setString(7, usuario.getUf());
            pstmtInsereUsuario.setString(8, usuario.getCpf());

            pstmtInsereUsuario.execute();
            JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso!");

        } catch (SQLException ex) {
            Logger.getLogger(LivroImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

package br.edu.unijui.model.dao;

import br.edu.unijui.model.Usuario;
import java.util.ArrayList;

/**
 *
 * @author daias
 */
public interface UsuarioDAO {
    ArrayList<Usuario> getUsuariosFiltro(String filtro);
    
    void insereUsuario(Usuario usuario);

}

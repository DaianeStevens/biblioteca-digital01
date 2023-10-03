package br.edu.unijui.model.dao;

import br.edu.unijui.model.Livro;
import java.util.ArrayList;

/**
 *
 * @author daias
 */
public interface LivroDAO {

    ArrayList<Livro> getLivrosFiltro(String filtro);

    void insereLivro(Livro livro);

}

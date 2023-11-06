package br.edu.unijui.model.dao;

import br.edu.unijui.model.Livro;
import br.edu.unijui.model.Locacao;
import java.util.ArrayList;

/**
 *
 * @author daias
 */
public interface LocacaoDAO {

    ArrayList<Locacao> getLocacaoes();

    void insereLocacao(Locacao locacao, ArrayList<Livro> livros);

    void deletaLocacao(Integer id);
    void devolveLocacao(Integer id);
    ArrayList<Locacao> getLocacaoesLivros();
}

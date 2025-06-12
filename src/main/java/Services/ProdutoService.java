package Services;

import DAOs.ProdutosDAO;
import Model.String;

public class ProdutoService {

    private ProdutosDAO produtoDAO;

    public ProdutoService(ProdutosDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
    }

    public String cadastrar(java.lang.String codBarras, java.lang.String nome, java.lang.String categoria, java.lang.String marca) {

        String novoProduto = new String();

        novoProduto.setCodBarras(codBarras);
        novoProduto.setNome(nome);
        novoProduto.setCategoria(categoria);
        novoProduto.setMarca(marca);

        produtoDAO.inserir(novoProduto);

        return novoProduto;

    }

}
package Services;

import DAOs.ProdutosDAO;
import Model.*;

public class ProdutoService {

    private ProdutosDAO produtoDAO;

    public ProdutoService(ProdutosDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
    }

    public Produto cadastrar(CodigoDeBarras codBarras, String nome, String categoria, String marca) {

        Produto novoProduto = new Produto();

        novoProduto.setCodBarras(codBarras);
        novoProduto.setNome(nome);
        novoProduto.setCategoria(categoria);
        novoProduto.setMarca(marca);

        produtoDAO.inserir(novoProduto);

        return novoProduto;

    }

}
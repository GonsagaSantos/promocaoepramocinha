package Services;

import DAOs.ProdutosDAO;
import Model.Produtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProdutoService {

    private ProdutosDAO produtoDAO;

    public ProdutoService(ProdutosDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
    }

    public Produtos cadastrar(String codBarras, String nome, String categoria, String marca) {

        Produtos novoProduto = new Produtos();

        novoProduto.setCodBarras(codBarras);
        novoProduto.setNome(nome);
        novoProduto.setCategoria(categoria);
        novoProduto.setMarca(marca);

        produtoDAO.inserir(novoProduto);

        return novoProduto;

    }

}
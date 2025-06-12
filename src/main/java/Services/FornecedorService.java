package Services;

import DAOs.EstoqueDAO;
import DAOs.PedidoDAO;
import DAOs.ProdutosDAO;
import Model.Estoque;
import Model.Pedido;
import Model.Produto;

import java.math.BigDecimal;

public class FornecedorService {
    private PedidoDAO pedidoDAO;
    private ProdutosDAO produtoDAO;
    private EstoqueDAO estoqueDAO;

    public FornecedorService(PedidoDAO pedidoDAO, ProdutosDAO produtoDAO, EstoqueDAO estoqueDAO) {
        this.pedidoDAO = pedidoDAO;
        this.produtoDAO = produtoDAO;
        this.estoqueDAO = estoqueDAO;
    }

    public Pedido cadastrarNovoPedido(Long idProduto, CodigoDeBarras codBarras, CNPJ cnpjFornecedor, int quantidade, BigDecimal precoTotal) {

        Produto produtoPedido = produtoDAO.consultar(codBarras.getCodigoApenasNumeros());

        if(produtoPedido == null) {
            throw new RuntimeException("Produto não encontrado!\nCódigo de barras: " + codBarras);
        }

        Estoque produtoConsultado = EstoqueDAO.consultar(idProduto);

        if(produtoConsultado == null) {
            throw new RuntimeException("Cadastre o produto no estoque primeiro!");
        }

        produtoPedido.setCodBarras(codBarras);
        produtoPedido.get

    }

}

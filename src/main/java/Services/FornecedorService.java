package Services;

import DAOs.EstoqueDAO;
import DAOs.PedidoDAO;
import DAOs.ProdutosDAO;
import Model.Estoque;
import Model.Pedido;
import Model.Produto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FornecedorService {
    private PedidoDAO pedidoDAO;
    private ProdutosDAO produtoDAO;
    private EstoqueDAO estoqueDAO;

    public FornecedorService(PedidoDAO pedidoDAO, ProdutosDAO produtoDAO, EstoqueDAO estoqueDAO) {
        this.pedidoDAO = pedidoDAO;
        this.produtoDAO = produtoDAO;
        this.estoqueDAO = estoqueDAO;
    }

    public Pedido cadastrarNovoPedido(long id, CodigoDeBarras codBarras, CNPJ cnpjFornecedor, int quantidade) {
        if (codBarras == null || cnpjFornecedor == null || quantidade <= 0) {
            throw new IllegalArgumentException("Dados do pedido inválidos: código de barras, CNPJ e quantidade são obrigatórios e a quantidade deve ser positiva.");
        }

        Produto produtoBaseCadastrado = produtoDAO.consultar(codBarras.getCodigoApenasNumeros());

        if (produtoBaseCadastrado == null) {
            throw new RuntimeException("Produto não encontrado no cadastro de produtos!\nCódigo de barras: " + codBarras.getCodigoFormatado());
        }

        Estoque produtoEmEstoque = estoqueDAO.consultar(id);

        if (produtoEmEstoque == null) {
            throw new RuntimeException("Cadastre o produto no estoque primeiro! Produto: " + produtoBaseCadastrado.getNome());
        }

        BigDecimal precoUnitarioCompra = produtoEmEstoque.getPrecoCompra();
        if (precoUnitarioCompra == null) {
            throw new RuntimeException("Preço de compra do produto no estoque (" + produtoBaseCadastrado.getNome() + ") não definido.");
        }
        BigDecimal precoTotalPedido = precoUnitarioCompra.multiply(new BigDecimal(quantidade));

        Pedido novoPedido = new Pedido();
        novoPedido.setIdPedido(System.currentTimeMillis());
        novoPedido.setCodBarras(codBarras);
        novoPedido.setCpnjFornecedor(cnpjFornecedor);
        novoPedido.setQuantidade(quantidade);
        novoPedido.setDataPedido(LocalDate.now());
        novoPedido.setPreco(precoTotalPedido);

        try {
            pedidoDAO.inserir(novoPedido);
            System.out.println("Pedido de compra registrado com sucesso para o produto: " + produtoBaseCadastrado.getNome());
        } catch (Exception e) {
            System.err.println("Erro ao inserir o pedido de compra: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Falha ao registrar o pedido de compra.", e);
        }

        try {
            int novaQuantidadeEstoque = produtoEmEstoque.getQuantidade() + quantidade;
            produtoEmEstoque.setQuantidade(novaQuantidadeEstoque);
            estoqueDAO.alterar(produtoEmEstoque);
            System.out.println("Estoque atualizado: " + produtoBaseCadastrado.getNome() + " agora com " + novaQuantidadeEstoque + " unidades.");
        } catch (Exception e) {
            System.err.println("Erro ao atualizar o estoque após o pedido: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Pedido registrado, mas falha ao atualizar o estoque.", e);
        }

        return novoPedido;
    }
}
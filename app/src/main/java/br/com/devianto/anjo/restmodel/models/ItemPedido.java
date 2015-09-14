package br.com.devianto.anjo.restmodel.models;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


import java.io.Serializable;
import java.math.BigDecimal;

import br.com.thiagocortat.mylibrary.utilities.Constante;


@DatabaseTable
public class ItemPedido
        implements Serializable {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId = true)
    private Long id = 0L;

//    @DatabaseField(foreign = true, foreignAutoRefresh = true)
//    private Pedido pedido;

    @Expose
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Produto produto;

    @Expose
    @DatabaseField
    private Long quantidade;

    @Expose
    @DatabaseField
    private double preco = 1;

    public ItemPedido() {
    }

    public ItemPedido(Produto produto, Pedido pedido) {
        this();
        this.produto = produto;
        this.quantidade = 1L;
//        this.pedido = pedido;
    }

    public void aumentar() {
        quantidade++;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ItemPedido) {
            ItemPedido other = (ItemPedido) obj;
            if (other.getProduto().equals(this.getProduto())) {
                return true;
            } else {
                return false;
            }

        }
        return false;
    }

    public Long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public Long getQuantidade() {
        return quantidade;
    }


    public BigDecimal getTotal() {
        return produto.getPreco()
                .multiply(new BigDecimal(quantidade))
                .add(getMargemProjetandoo())
                .add(getMargemTI())
                .add(getMargemGestor())
                .add(getMargemVendedoras())
                .add(getMargemLider())
                .divide(Constante.PERCENTUAL_GATEWAY_PAGAMENTO, 2, BigDecimal.ROUND_UP)
                .add(Constante.TAXA_GATEWAY_PAGAMENTO);
    }

    public BigDecimal getMargemGestor() {
        return produto.getMargemGestor().multiply(new BigDecimal(quantidade));
    }

    public BigDecimal getMargemLider() {
        return produto.getMargemLider().multiply(new BigDecimal(quantidade));
    }

    public BigDecimal getMargemTI() {
        return produto.getMargemTI().multiply(new BigDecimal(quantidade));
    }

    public BigDecimal getMargemVendedoras() {
        return produto.getMargemVendedoras().multiply(new BigDecimal(quantidade));
    }

    public BigDecimal getMargemProjetandoo() {
        return produto.getMargemProjetandoo().multiply(new BigDecimal(quantidade));
    }

    public BigDecimal getTotalLiquido() {
        return produto.getPreco()
                .multiply(new BigDecimal(quantidade));
    }


//    public void setPedido(Pedido pedido1) {
//        pedido = pedido1;
//    }

    public void setQuantidade(Long long1) {
        quantidade = long1;
    }

    public BigDecimal getAkatus() {

        return this.getTotal().subtract(produto.getPreco()
                .multiply(new BigDecimal(quantidade))
                .add(this.getMargemGestor())
                .add(this.getMargemProjetandoo())
                .add(this.getMargemTI())
                .add(this.getMargemVendedoras()));
    }
}

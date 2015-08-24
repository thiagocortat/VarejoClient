package br.com.devianto.anjo.restmodel.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import br.com.devianto.anjo.repository.RestClient;

@DatabaseTable
public class Pedido
        implements Serializable {
    private static final long serialVersionUID = 1L;

    @Expose
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Cliente cliente;

    @Expose
//    @SerializedName("pagamento")
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private DadosPagamento dadosPagamento;

    @DatabaseField
    private Date dataEnvio;

    @DatabaseField
    private Date dataPedido = new Date();

    @DatabaseField
    private Date dataPagamento;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private FormaPagamento formaPagamento;

    @DatabaseField(generatedId = true)
    private Long id;

    @ForeignCollectionField(eager = true)
    private Collection<ItemPedido> itens = new ArrayList<ItemPedido>();

    @DatabaseField
    private String idTransacao;

    private Loja loja;

    public Pedido() {
        this.loja = new Loja(RestClient.ID_LOJA);
    }

    public Pedido(Cliente cliente) {
        this();
        this.cliente = cliente;
        this.loja = new Loja(RestClient.ID_LOJA);
    }

    public void adicionar(Produto produto) {
        ItemPedido item = new ItemPedido(produto, null);
        if (!this.itens.contains(item)) {
            this.itens.add(item);
        } else {
            for (ItemPedido i : this.itens) {
                if (i.equals(item)) {
                    i.aumentar();
                    break;
                }
            }
        }

    }

    public void enviado() {
        Date date = new Date();
        dataEnvio = date;
    }

    public Cliente getCliente() {
        return cliente;
    }

//    public DadosPagamento getDadosPagamento() {
//        return dadosPagamento;
//    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public Collection<ItemPedido> getItens() {
        return itens;
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (ItemPedido item : itens) {
            total = total.add(item.getTotal());
        }

        return total;
    }

    public BigDecimal getTotalLiquido() {
        BigDecimal total = BigDecimal.ZERO;
        for (ItemPedido item : itens) {
            total = total.add(item.getTotalLiquido());
        }
        return total;
    }

    public void remover(ItemPedido itempedido) {
        itens.remove(itempedido);
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setDadosPagamento(DadosPagamento dadospagamento) {
        dadosPagamento = dadospagamento;

    }

    public void setFormaPagamento(FormaPagamento formapagamento) {
        formaPagamento = formapagamento;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public String getIdTransacao() {
        return this.idTransacao;
    }

    public Long getId() {
        return this.id;
    }

//    public void adicionar(Produto produto) {
//
//        this.adicionar(produto, null);
//
//    }
}

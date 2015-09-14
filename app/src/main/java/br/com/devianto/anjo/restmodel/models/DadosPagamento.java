package br.com.devianto.anjo.restmodel.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

import br.com.devianto.anjo.restmodel.models.constraints.ValidationConstraint;

@DatabaseTable
public class DadosPagamento extends ValidationConstraint
        implements Serializable {

    private static final long serialVersionUID = 1L;

    @Expose
    @DatabaseField
    @SerializedName("codigosegura")
    private String cvv;

    @Expose
    @DatabaseField
    @SerializedName("dataExpiracao")
    private Date dataValidade;

    @Expose
    @DatabaseField(foreign = true)
    @SerializedName("meioPagamento")
    private FormaPagamento formaPagamento;

    @DatabaseField(generatedId = true)
    private Long id;

    @Expose
    @DatabaseField
    @SerializedName("portador")
    private Portador portador;

    @Expose
    @DatabaseField
    @SerializedName("numero")
    private String numeroCartao;
//
//    @DatabaseField(foreign = true)
//    private Pedido pedido;

    @Expose
    @DatabaseField
    @SerializedName("cpf")
    private String cpf;

    @Expose
    @DatabaseField
    private Integer quantidadeParcelas = 1;

    private void validateCartaoCredito() {
//        if (GenericValidator.isBlankOrNull(numeroCartao)) {
//            add("Número do cartão de crédito é obrigatório");
//        }
//        if (!GenericValidator.isBlankOrNull(numeroCartao) && !GenericValidator.isCreditCard(numeroCartao)) {
//            add("Número do cartão de crédito inválido");
//        }
//        if (dataValidade == null ) {
//            add("Data de validade é obrigatória");
//        } else {
//            if (dataValidade.before(DateTime.now().toDate())) {
//                add("Cartão de crédito vencido");
//            }
//        }
//        if (GenericValidator.isBlankOrNull(portador)) {
//            add("Nome do portador do cartão é obrigatório");
//        } else if (portador.split(" ").length == 0) {
//            add("Nome do portador deve ser composto por nome e sobrenome");
//        }

    }

    public Portador getPortador() {
        return portador;
    }

    public void setPortador(Portador portador) {
        this.portador = portador;
    }

    public Integer getQtdParcelas() {
        return this.quantidadeParcelas;
    }

    public void setQtdParcelas(Integer numeroParcelas) {
        this.quantidadeParcelas = numeroParcelas;
    }

    public void setCVV(String cvv) {
        this.cvv = cvv;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }



    public void setNumero(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

//    public void setPedido(Pedido pedido) {
//        this.pedido = pedido;
//        this.formaPagamento = pedido.getFormaPagamento();
//    }
//
//    public Pedido getPedido() {
//        return this.pedido;
//    }

    protected void validate() {
        validateCartaoCredito();
    }

    public Date getValidade() {
        return this.dataValidade;
    }

    public String getNumero() {
        return numeroCartao;
    }


    public String getCVV() {
        return this.cvv;
    }

    public String getCPF() {
        if (cpf != null) {
            cpf.replace(".", "").replace("-","");
        }
        return this.cpf;
    }

    public void setCPF(String cpf) {

        if (cpf != null) {
            cpf.replace(".", "").replace("-","");
        }
        this.cpf = cpf;
    }

    @Override
    public boolean isValid() {
        this.validate();
        return super.isValid();
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }
}

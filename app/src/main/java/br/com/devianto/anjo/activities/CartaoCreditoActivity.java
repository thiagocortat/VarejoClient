package br.com.devianto.anjo.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.androidquery.AQuery;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.devianto.anjo.R;
import br.com.devianto.anjo.alerts.ActionDialog;
import br.com.devianto.anjo.alerts.ErrorAlert;
import br.com.devianto.anjo.components.MaskedWatcher;
import br.com.devianto.anjo.restmodel.models.DadosPagamento;
import br.com.devianto.anjo.restmodel.models.Pedido;
import br.com.devianto.anjo.restmodel.models.Portador;
import br.com.devianto.anjo.utilities.ParseUtilities;
import br.com.devianto.anjo.utilities.PriceUtilities;

public class CartaoCreditoActivity extends AbstractMeioPagamentoActivity
        implements
        OnClickListener {

    private AQuery aq;

    private ProgressDialog progress;

    @Override
    public void onClick(View view) {
        Pedido pedido = PriceUtilities.getPedido();
        Intent intent = null;

        if (view.getId() == R.id.salvar) {
            DadosPagamento dadospagamento = null;
            try {
                dadospagamento = criarDadosPagamento(pedido);
            }catch (Exception e) {
                e.printStackTrace();
            }
            if (dadospagamento == null){
                new ErrorAlert(this)
                        .setTitle("Dados Incompletos")
                        .setMessage("Por favor informe os dados do cartão")
                        .show();
            }

            else if (aq.id(R.id.parcelas).getSelectedItemPosition() == 0) {
                new ErrorAlert(this)
                        .setTitle("Dados Incompletos")
                        .setMessage("Por favor informe o número de parcelas")
                        .show();
            }

            else if (dadospagamento.isValid()) {
//                (new PedidoService(this)).save(dadospagamento);

                if( this.isNetworkConnected() ) {

                    //TODO: AQUI É FEITO O PROCESSO DA COMPRA - PAGAMENTO
//                    intent = new Intent(this, AutenticacaoParaEnvioDePedidoActivity.class);
//                    startActivity(intent);
//                    this.finish();
                    PriceUtilities.getPedido().setDadosPagamento(dadospagamento);
                    sendPurchaseToServer();

                }
                else {
//                    PriceUtilities.novoPedido();
                    new ErrorAlert(this)
                            .setTitle("Pedido")
                            .setMessage("Você está sem conexão de Internet, por favor tente novamente!")
                            .setCancelable(true)
                            .show();
                }
            }
            else {
                new ErrorAlert(this)
                        .setTitle("Dados de Cartão de crédito")
                        .setMessages(dadospagamento.errors())
                        .setCancelable(true)
                        .show();
            }

        }
        else {
            intent = new Intent(this, PagamentoActivity.class);
            startActivity(intent);
            this.finish();
        }

    }

    private DadosPagamento criarDadosPagamento(Pedido pedido) {
        DadosPagamento dadospagamento = new DadosPagamento();
//        dadospagamento.setPedido(pedido);
//        dadospagamento.setPortador(getPortador());
        dadospagamento.setPortador(new Portador(pedido.getCliente(), getPortador()));
        dadospagamento.setNumero(getNumeroCartao());
        dadospagamento.setDataValidade(getDataValidade());
        dadospagamento.setCVV(getCVV());
        dadospagamento.setCPF(getCPF());
        dadospagamento.setQtdParcelas(getNumeroParcelas());
        return dadospagamento;
    }

    public Integer getNumeroParcelas() {
        if (aq.id(R.id.parcelas).getSelectedItemPosition() > 0) {
            return aq.id(R.id.parcelas).getSelectedItemPosition();
        } else {
            return 1;
        }
    }

    public String getCPF() {
        return aq.id(R.id.cpf).getText().toString().replace(".", "").replace("-","");
    }

    public String getCVV() {
        return aq.id(R.id.cvv).getText().toString();
    }

    public Date getDataValidade() {
        return ParseUtilities.toDate(aq.id(R.id.validade).getText().toString(), "MM/yy");
    }

    public String getNumeroCartao() {
        return aq.id(R.id.cartao).getText().toString();
    }

    public String getPortador() {
        return aq.id(R.id.nome).getText().toString();
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_cartao_credito);

        Toolbar toolbar = (Toolbar) findViewById(br.com.thiagocortat.mylibrary.R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        aq = new AQuery(this);
        aq.id(R.id.salvar).clicked(this);
        aq.id(R.id.cancelar).clicked(this);

        Spinner spinner = aq.id(R.id.parcelas).getSpinner();
        List<String> parcelas = new ArrayList<String>();

        parcelas.add("Seleciona a quantidade de parcelas");
        parcelas.add("À vista");
        parcelas.add("2 vezes");
        parcelas.add("3 vezes");
        parcelas.add("4 vezes");
        parcelas.add("5 vezes");
        parcelas.add("6 vezes");
        parcelas.add("7 vezes");
        parcelas.add("8 vezes");
        parcelas.add("9 vezes");
        parcelas.add("10 vezes");
        parcelas.add("11 vezes");
        parcelas.add("12 vezes");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, parcelas);
        spinner.setAdapter(adapter);

        new MaskedWatcher("##/##", aq.id(R.id.validade).getEditText()).setAcceptOnlyNumbers(true);
        new MaskedWatcher("###.###.###-##", aq.id(R.id.cpf).getEditText()).setAcceptOnlyNumbers(true);
    }

    public void setPortador(String portador) {
        aq.id(R.id.nome).text(portador);
    }

    public void setCPF(String cpf) {
        aq.id(R.id.cpf).text(cpf);
    }

    public void setDataValidade(Date dataValidade) {
        aq.id(R.id.validade).text(ParseUtilities.formatDate(dataValidade,"MM/yy"));
    }

    public void setNumeroCartao(String cartao) {
        aq.id(R.id.cartao).text(cartao);
    }

    public void setCVV(String CVV) {
        aq.id(R.id.cvv).text(CVV);
    }

//    @Override
//    public void onBackPressed() {
//        Intent intent = new Intent(this,PagamentoActivity.class);
//        startActivity(intent);
//        this.finish();
//    }
}

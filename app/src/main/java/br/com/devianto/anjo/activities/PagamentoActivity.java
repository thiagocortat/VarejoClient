package br.com.devianto.anjo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;

import java.util.ArrayList;
import java.util.List;

import br.com.devianto.anjo.R;
import br.com.devianto.anjo.repository.RestClient;
import br.com.devianto.anjo.restmodel.models.ApiFormaPagamentos;
import br.com.devianto.anjo.restmodel.models.FormaPagamento;
import br.com.devianto.anjo.restmodel.models.Pedido;
import br.com.devianto.anjo.restmodel.models.User;
import br.com.devianto.anjo.utilities.ParseUtilities;
import br.com.devianto.anjo.utilities.PriceUtilities;
import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class PagamentoActivity extends AbstractActivity
        implements  OnClickListener {

    @InjectView(R.id.salvar)        protected Button salvar;
    @InjectView(R.id.cancelar)      protected Button cancelar;
    @InjectView(R.id.linear_item)   protected LinearLayout linearItem;
    @InjectView(R.id.formapagamento) protected RadioGroup formapagamento;
    @InjectView(R.id.scroll)        protected ScrollView scroll;
    @InjectView(R.id.total_pedido)  protected TextView totalPedido;

    private Pedido pedido;

//    private PedidoService pedidoservice = new PedidoService(this);

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.salvar) {

            FormaPagamento formapagamento = this.getFormaPagamento();
            pedido.setFormaPagamento(formapagamento);
//            pedidoservice.save(pedido);
            Intent intent = new Intent(this, CartaoCreditoActivity.class);
            startActivity(intent);

        } else if (view.getId() == R.id.cancelar) {
            Intent intent = new Intent(this, ShoppingCartActivity.class);
            startActivity(intent);
        }

    }

    public FormaPagamento getFormaPagamento() {
        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.formapagamento);
        int i = radiogroup.getCheckedRadioButtonId();
        return (FormaPagamento) radiogroup.findViewById(i).getTag();
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_forma_pagamento_main);
        ButterKnife.inject(this);

        Toolbar toolbar = (Toolbar) findViewById(br.com.thiagocortat.mylibrary.R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        salvar.setOnClickListener(this);
        cancelar.setOnClickListener(this);

        pedido = PriceUtilities.getPedido();

        showProgress();

        RestClient client = new RestClient();
        client.getApiService().obtainFormasPagamento(new Callback<ApiFormaPagamentos>() {
            @Override
            public void success(ApiFormaPagamentos api, Response response) {

                int i = 0;
                for (FormaPagamento forma : api.getFormaPagamento()) {

                    RadioButton radiobutton = (RadioButton) getLayoutInflater().inflate(R.layout.radio_item, null);
                    radiobutton.setTag(forma);
                    radiobutton.setText(forma.getNome());
                    radiobutton.setHint(forma.getNome());
                    formapagamento.addView(radiobutton);
                    if (i == 0) {
                        radiobutton.setChecked(true);
                    }
                    i++;
                }

                hideProgress();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(PagamentoActivity.this, "ERRO: " + error.getMessage(), Toast.LENGTH_LONG).show();
                hideProgress();
            }
        });




//        String[] strPagamentos = getResources().getStringArray(R.array.meios_pagamento);
//        List<FormaPagamento> formasPagamento = new ArrayList<>();
////        List<FormaPagamento> formasPagamento = new FormaPagamentoRepository(this).list();
//
//        long id = 0;
//        for (String meioPag : strPagamentos) {
//            formasPagamento.add(new FormaPagamento(id, meioPag));
//        }

//        int i = 0;
//        for (FormaPagamento forma : formasPagamento) {
//
//            RadioButton radiobutton = (RadioButton) getLayoutInflater().inflate(R.layout.radio_item, null);
////            RadioButton radiobutton = new RadioButton(this);
////            radiobutton.setId(forma.getId().intValue()); //Não colocar - dá bug
//            radiobutton.setTag(forma);
//            radiobutton.setText(forma.getNome());
//            radiobutton.setHint(forma.getNome());
//            formapagamento.addView(radiobutton);
//            if (i == 0) {
//                radiobutton.setChecked(true);
//            }
//            i++;
//        }



        totalPedido.setText(ParseUtilities.formatMoney(pedido.getTotal()));

    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        RadioGroup radio = (RadioGroup) findViewById(R.id.formapagamento);
        radio.check(formaPagamento.getId().intValue());
    }
}

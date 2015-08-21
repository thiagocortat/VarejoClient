package br.com.devianto.anjo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.devianto.anjo.R;
import br.com.devianto.anjo.adapters.ShoppingCartAdapter;
import br.com.devianto.anjo.alerts.ClienteDialog;
import br.com.devianto.anjo.restmodel.models.Pedido;
import br.com.devianto.anjo.restmodel.models.User;
import br.com.devianto.anjo.utilities.PriceUtilities;
import br.com.thiagocortat.mylibrary.utilities.ParseUtilities;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ShoppingCartActivity extends AbstractActivity {

    @InjectView(R.id.lista_itens_pedidos)
    ListView listView;
    @InjectView(R.id.total_pedido)
    TextView totalPedido;

    private Toolbar toolbar;


    @OnClick ({R.id.continuar_comprando, R.id.finalizar_pedido})
    public void onClick(View view) {
        if (view.getId() == R.id.finalizar_pedido) {
            Pedido pedido = PriceUtilities.getPedido();
			new ClienteDialog(this)
                    .setPedido(pedido)
					.setMessage("Cadastro de cliente")
                    .show();
//            Toast.makeText(this, "Funcionalidade em desenvolvimento.", Toast.LENGTH_LONG).show();

//            User currentUser = User.getCurrentInstance();
//            if (currentUser == null) {
//                loadLoginView();
//            }
//            else {
//    			Intent intent = new Intent(this, PagamentoActivity.class);
//    			startActivity(intent);
//            }

        } else if (view.getId() == R.id.continuar_comprando) {
//			Intent intent = new Intent(this, HomeActivity.class);
//			startActivity(intent);
            this.finish();
        }

    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_shopping_cart_main);
        ButterKnife.inject(this);

        toolbar = (Toolbar) findViewById(br.com.thiagocortat.mylibrary.R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Pedido pedido = PriceUtilities.getPedido();
        ShoppingCartAdapter shoppingcartadapter = new ShoppingCartAdapter(this, new ArrayList<>(pedido.getItens()));
        listView.setAdapter(shoppingcartadapter);

        totalPedido.setText(ParseUtilities.formatMoney(pedido.getTotal()));

    }

    private void loadLoginView() {
        Intent intent = new Intent(this, LoginActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}

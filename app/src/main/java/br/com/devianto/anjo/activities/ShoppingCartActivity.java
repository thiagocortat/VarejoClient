package br.com.devianto.anjo.activities;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.androidquery.AQuery;
import java.util.ArrayList;

import br.com.devianto.anjo.R;
import br.com.devianto.anjo.adapters.ShoppingCartAdapter;
import br.com.devianto.anjo.alerts.ClienteDialog;
import br.com.devianto.anjo.restmodel.models.ItemPedido;
import br.com.devianto.anjo.restmodel.models.Pedido;
import br.com.thiagocortat.mylibrary.utilities.ParseUtilities;

public class ShoppingCartActivity extends AbstractActivity
		implements
		OnClickListener {

    @Override
	public void onClick(View view) {
		if (view.getId() == R.id.finalizar_pedido) {
			Pedido pedido = br.com.devianto.anjo.utilities.PriceUtilities.getPedido();
			new ClienteDialog(this)
                    .setPedido(pedido)
					.setMessage("Cadastro de cliente")
                    .show();
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
        AQuery aq = new AQuery(this);
		Pedido pedido = br.com.devianto.anjo.utilities.PriceUtilities.getPedido();
		ListView listview = aq.id(R.id.lista_itens_pedidos).getListView();
		ShoppingCartAdapter shoppingcartadapter = new ShoppingCartAdapter(this,
				new ArrayList<ItemPedido>(pedido.getItens()));
		listview.setAdapter(shoppingcartadapter);
		aq.id(R.id.total_pedido).text(
				ParseUtilities.formatMoney(pedido.getTotal()));
		aq.id(R.id.continuar_comprando).clicked(this);
		aq.id(R.id.finalizar_pedido).clicked(this);
	}

//    @Override
//    public void onBackPressed() {
//        final Intent intent = new Intent(this,HomeActivity.class);
//        startActivity(intent);
//        this.finish();
//    }
}

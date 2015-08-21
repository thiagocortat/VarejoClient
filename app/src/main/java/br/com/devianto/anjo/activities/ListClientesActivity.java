package br.com.devianto.anjo.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.androidquery.AQuery;

import br.com.devianto.anjo.R;
import br.com.devianto.anjo.restmodel.models.Pedido;
import br.com.devianto.anjo.utilities.Constante;

public class ListClientesActivity extends AbstractActivity
{
    private AQuery aq;

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.activity_clientes_main);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        aq = new AQuery(this);
        if (getIntent().getExtras() != null)
        {
            Pedido pedido = (Pedido)getIntent().getExtras().get(Constante.PEDIDO);
            setPedido(pedido);
        }


//        ClienteRepository clienterepository = new ClienteRepository(this);
//        List<Cliente> clientes = clienterepository.list();
//        if (clientes != null && !clientes.isEmpty())
//        {
//            ListView lst = aq.id(R.id.clientes).getListView();
//            ClienteAdapter clienteadapter = new ClienteAdapter(this, clienterepository.list(), getPedido());
//            lst.setAdapter(clienteadapter);
//        } else
//        {
//            Intent intent = new Intent(this, ClienteActivity.class);
//            intent.putExtra(Constante.PEDIDO, getPedido());
//            startActivity(intent);
//            this.finish();
//        }
    }

//    @Override
//    public void onBackPressed() {
//        final Intent intent = new Intent(this,ShoppingCartActivity.class);
//        startActivity(intent);
//        this.finish();
//    }
}

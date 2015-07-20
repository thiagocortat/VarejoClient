package br.com.devianto.anjo.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OpenHelperManager;


import br.com.devianto.anjo.R;
import br.com.devianto.anjo.database.DbHelper;
import br.com.devianto.anjo.restmodel.models.Pedido;
import br.com.thiagocortat.mylibrary.activities.BaseActivity;
import br.com.thiagocortat.mylibrary.interfaces.OnLayoutInjectListener;
import butterknife.ButterKnife;
import fr.castorflex.android.circularprogressbar.CircularProgressBar;
import fr.castorflex.android.circularprogressbar.CircularProgressDrawable;

public abstract class AbstractActivity extends BaseActivity implements OnLayoutInjectListener {

    private volatile DbHelper helper;

    //    private Context context;
    private Pedido pedido;

    protected Pedido getPedido()
    {
		return this.pedido;
    }

	protected void setPedido(final Pedido pedido)
    {
		this.pedido = pedido;
    }
	
    public AbstractActivity() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (helper == null) {
            helper = OpenHelperManager.getHelper(this, DbHelper.class);
        }
        super.onCreate(savedInstanceState);
    }


    public boolean isNetworkConnected() {

        ConnectivityManager manager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info =  manager.getActiveNetworkInfo();
        if( info != null && ( info.isAvailable() || info.isConnected() ) ){
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (helper != null) {
            OpenHelperManager.releaseHelper();
            helper = null;
        }
    }

}

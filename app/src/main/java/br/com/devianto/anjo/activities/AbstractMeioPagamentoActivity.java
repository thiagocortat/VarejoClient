package br.com.devianto.anjo.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.androidquery.AQuery;

import org.apache.commons.validator.GenericValidator;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import br.com.devianto.anjo.R;
import br.com.devianto.anjo.alerts.ActionDialog;
import br.com.devianto.anjo.alerts.ErrorAlert;
import br.com.devianto.anjo.exceptions.IntegrationException;
import br.com.devianto.anjo.exceptions.NoUniqueRegistryException;
import br.com.devianto.anjo.repository.RestClient;
import br.com.devianto.anjo.restmodel.models.ApiPedido;
import br.com.devianto.anjo.utilities.PriceUtilities;
import br.com.devianto.anjo.utilities.StringPattern;

public abstract class AbstractMeioPagamentoActivity extends AbstractActivity {

	public static final String TAG = AbstractMeioPagamentoActivity.class.getName();
//	private AQuery aq;

	private ProgressDialog progress;
	public AbstractMeioPagamentoActivity() {
		super();
	}

	public void sendPurchaseToServer(){

		new AsyncTask<Void, Void, String>() {

			@Override
			protected void onPreExecute() {
				Log.d(TAG, "Iniciando a carga do pagamento");
				progress = ProgressDialog.show(AbstractMeioPagamentoActivity.this,
						getString(R.string.app_name), "Aguarde, estamos enviando seu pedido para a nossa central", true);
			}

			@Override
			protected String doInBackground(Void... avoid) {
				try {

					//TODO:FAZER O PAGAMENTO AQUI!!!!!!

//					AutenticacaoParaEnvioDePedidoActivity activity = (AutenticacaoParaEnvioDePedidoActivity) context;
//					//Colocando devido a um Bug NullPointerException no métodp abaixo
//					integration = new IntegrationProcess(activity.getEmail(), activity.getPassword(), activity);
//					integration.enviarPedido();

					RestClient client = new RestClient();
					return client.getApiService().checkout(new ApiPedido(PriceUtilities.getPedido()));

				} catch (Exception e) {
					return e.getMessage();
				}
				//return "Pedido enviado com sucesso";
			}

			@Override
			protected void onPostExecute(String message) {

				if (progress.isShowing()) {
					progress.dismiss();
				}

				(new ActionDialog(AbstractMeioPagamentoActivity.this))
						.setTitle(getString(R.string.app_name))
						.setMessage("Seu pedido foi concluído com sucesso, obrigado pela preferência")
						.show();

				PriceUtilities.novoPedido();

				//TODO:AQUI POSSO CHAMAR UMA ACTIVITY DE RECIBO DE COMPRA!!!!!!

//				intent = new Intent(this, AutenticacaoParaEnvioDePedidoActivity.class);
//				startActivity(intent);
//				this.finish();
			}
		}.execute();
	}

//	private boolean isValid() {
//
//		List<String> messages = new ArrayList<String>();
//
//		if (GenericValidator.isBlankOrNull(this.getEmail())) {
//			messages.add("E-mail é obrigatória");
//		} else if (!GenericValidator.isEmail(this.getEmail())) {
//			messages.add("E-mail é inválido");
//		}
//
//		if (GenericValidator.isBlankOrNull(this.getPassword())) {
//			messages.add("Senha é obrigatória");
//		}
//		if (!messages.isEmpty()) {
//			new ErrorAlert(this).setTitle("Pedido").setMessages(messages)
//					.show();
//			return false;
//		} else {
//			return true;
//		}
//	}
	
//	@Override
//	public void onClick(View view) {
//
//		if (view.getId() == R.id.logar) {
//
//				if( isValid() ) {
//					this.doAction();
//				}
//		}
//        else {
//			this.doBack();
//		}
//
//	}

//	protected abstract void doAction();
//
//	protected abstract void doBack();
//
//    protected abstract int getLayout();

//    protected AQuery getAquery() {
//        return aq;
//    }

//	@Override
//	protected void onCreate(Bundle bundle) {
//		super.onCreate(bundle);
//		setContentView(getLayout());
//		aq = new AQuery(this);
//		aq.id(R.id.logar).clicked(this);
//	}
//
//	public String getEmail() {
//		return aq.id(R.id.email).getText().toString();
//	}
//
//	public String getPassword() {
//		return aq.id(R.id.password).getText().toString();
//	}
//
//    public void setEmail(String email) {
//        aq.id(R.id.email).text(email);
//    }
//
//    public void setPassword(String password) {
//        aq.id(R.id.password).text(password);
//    }
//
//    @Override
//    public void onBackPressed() {
//        this.doBack();
//    }
}
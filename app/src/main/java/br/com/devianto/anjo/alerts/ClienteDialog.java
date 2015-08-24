package br.com.devianto.anjo.alerts;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;

import br.com.devianto.anjo.R;
import br.com.devianto.anjo.activities.ClienteActivity;
import br.com.devianto.anjo.activities.ListClientesActivity;
import br.com.devianto.anjo.adapters.ProdutoAdapter;
import br.com.devianto.anjo.components.MaskedWatcher;
import br.com.devianto.anjo.repository.RestClient;
import br.com.devianto.anjo.restmodel.models.ApiProduto;
import br.com.devianto.anjo.restmodel.models.Pedido;
import br.com.devianto.anjo.utilities.StringPattern;
import br.com.devianto.anjo.utilities.StringUtils;
import br.com.thiagocortat.mylibrary.utilities.Constante;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ClienteDialog extends AbstractDialog {

    ProgressBar progressBar;
    MaterialEditText edtCPF;
    LinearLayout linearLayout;
    View mView;
    private Pedido pedido;

    private RestClient client;

    public ClienteDialog(Context context) {
        super(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        mView = inflater.inflate(R.layout.view_cpf_cliente, null);

        progressBar = (ProgressBar) mView.findViewById(R.id.progress);
        edtCPF = (MaterialEditText) mView.findViewById(R.id.cpf);
        new MaskedWatcher("###.###.###-##", edtCPF).setAcceptOnlyNumbers(true);

        linearLayout = (LinearLayout) mView.findViewById(R.id.linearLayout);
//        linearLayout.setVisibility(View.GONE);

        mView.findViewById(R.id.salvar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cpf = edtCPF.getText().toString();
                if (StringUtils.isEmpty(cpf))
                    Toast.makeText(getContext(), "CPF vazio ou inválido!", Toast.LENGTH_LONG).show();
                else {
                    startProgress();
                    client.getApiService().obtainUserData(cpf, clienteCallback);
                }
            }
        });

//        getBuilder().setView(view);

        client = new RestClient();
    }

    public ClienteDialog setPedido(Pedido pedido) {
        this.pedido = pedido;
        return this;
    }

    public void show() {
        final AlertDialog dialog = getBuilder()
//                .setPositiveButton("Cliente cadastrado", null)
//                .setNegativeButton("Cliente novo", null)
                .setView(mView)
                .create();

        dialog.setButton(Dialog.BUTTON_POSITIVE, "Cliente novo", new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Activity context = (Activity) getContext();
                Intent intent = new Intent(context, ClienteActivity.class);
                intent.putExtra(Constante.PEDIDO, pedido);
                context.startActivity(intent);

            }
        });


//        dialog.setButton(Dialog.BUTTON_POSITIVE, "Cliente cadastrado", new OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//                linearLayout.setVisibility(View.VISIBLE);
//
////                Activity context = (Activity) getContext();
////                Intent intent = new Intent(context, ListClientesActivity.class);
////                intent.putExtra(Constante.PEDIDO, pedido);
////                context.startActivity(intent);
//
//            }
//        });

        dialog.show();

//        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Activity context = (Activity) getContext();
//                Intent intent = new Intent(context, ClienteActivity.class);
//                intent.putExtra(Constante.PEDIDO, pedido);
//                context.startActivity(intent);
//                dialog.dismiss();
//            }
//        });

//        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                linearLayout.setVisibility(View.VISIBLE);
//            }
//        });
    }


    public void startProgress() {
        linearLayout.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    public void stopProgress() {
        linearLayout.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    private Callback<HashMap> clienteCallback = new Callback<HashMap>() {
        @Override
        public void success(HashMap apiProduto, Response response) {

            stopProgress();
            Toast.makeText(getContext(), "FOI :)", Toast.LENGTH_LONG).show();
        }

        @Override
        public void failure(RetrofitError error) {
            Toast.makeText(getContext(), "Cliente não cadastrado.", Toast.LENGTH_LONG).show();
            stopProgress();

        }
    };

}

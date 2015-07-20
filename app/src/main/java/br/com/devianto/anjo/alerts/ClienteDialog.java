package br.com.devianto.anjo.alerts;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

import br.com.devianto.anjo.restmodel.models.Pedido;
import br.com.thiagocortat.mylibrary.utilities.Constante;

public class ClienteDialog extends AbstractDialog {

    private Pedido pedido;

    public ClienteDialog(Context context) {
        super(context);
    }

    public ClienteDialog setPedido(Pedido pedido) {
        this.pedido = pedido;
        return this;
    }

    public void show() {
//        AlertDialog dialog = getBuilder().create();
//
//        dialog.setButton(Dialog.BUTTON_NEGATIVE, "Cliente novo", new OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Activity context = (Activity) getContext();
//                Intent intent = new Intent(context, ClienteActivity.class);
//                intent.putExtra(Constante.PEDIDO, pedido);
//                context.startActivity(intent);
//
//            }
//        });
//
//
//        dialog.setButton(Dialog.BUTTON_POSITIVE, "Cliente cadastrado", new OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Activity context = (Activity) getContext();
//                Intent intent = new Intent(context, ListClientesActivity.class);
//                intent.putExtra(Constante.PEDIDO, pedido);
//                context.startActivity(intent);
//
//            }
//        });
//
//        dialog.show();
    }


}

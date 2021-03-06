package br.com.devianto.anjo.alerts;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

import br.com.devianto.anjo.MainActivity;


public class ActionDialog extends AbstractDialog {

    public ActionDialog(Context context) {
        super(context);
    }

    public void show() {
        getBuilder().setNeutralButton("OK", new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Activity current = (Activity) getContext();
                Intent intent = new Intent(current, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                current.startActivity(intent);
                current.finish();

            }
        }).create().show();
    }


}

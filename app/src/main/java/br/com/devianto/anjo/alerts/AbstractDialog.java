package br.com.devianto.anjo.alerts;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import java.util.List;

public abstract class AbstractDialog {

    private final AlertDialog.Builder builder;
    private final Context context;

    public AbstractDialog(Context context) {
        this.builder = new AlertDialog.Builder(context);
        this.context = context;
    }

    protected AlertDialog.Builder getBuilder() {
        return builder;
    }

    protected Context getContext() {
        return context;
    }

    public AbstractDialog setCancelable(boolean flag) {
        builder.setCancelable(flag);
        return this;
    }

    public AbstractDialog setMessage(String s) {
        builder.setMessage(s);
        return this;
    }

    public AbstractDialog setMessages(List<String> messages) {
        StringBuffer sb = new StringBuffer();
        for (String message : messages) {
            sb.append(message).append("\n\r");
        }
        setMessage(sb.toString());
        return this;
    }

    public AbstractDialog setTitle(String s) {
        builder.setTitle(s);
        return this;
    }

    public abstract void show();
}

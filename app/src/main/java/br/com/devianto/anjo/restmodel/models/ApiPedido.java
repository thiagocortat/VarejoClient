package br.com.devianto.anjo.restmodel.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thiagocortat on 5/24/15.
 */
public class ApiPedido implements Serializable{

    public ApiPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Expose
    @SerializedName("pedido")
    private Pedido pedido;

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}

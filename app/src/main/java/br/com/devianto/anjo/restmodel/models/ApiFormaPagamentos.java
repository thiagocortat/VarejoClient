package br.com.devianto.anjo.restmodel.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by thiagocortat on 5/24/15.
 */
public class ApiFormaPagamentos implements Serializable{

    @Expose
    @SerializedName("list")
    private List<FormaPagamento> list;

    public List<FormaPagamento> getFormaPagamento() {
        return list;
    }

    public void setFormaPagamentos(List<FormaPagamento> formaPagamentos) {
        this.list = formaPagamentos;
    }
}

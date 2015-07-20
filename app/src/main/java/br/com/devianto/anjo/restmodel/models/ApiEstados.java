package br.com.devianto.anjo.restmodel.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by thiagocortat on 5/24/15.
 */
public class ApiEstados implements Serializable{

    @Expose
    @SerializedName("list")
    private List<Estado> list;

    public List<Estado> getEstados() {
        return list;
    }

    public void setEstados(List<Estado> estados) {
        this.list = estados;
    }
}

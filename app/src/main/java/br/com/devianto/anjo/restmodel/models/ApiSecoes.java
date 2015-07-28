package br.com.devianto.anjo.restmodel.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by thiagocortat on 5/24/15.
 */
public class ApiSecoes implements Serializable{

    @Expose
    @SerializedName("list")
    private List<Secao> list;

    public List<Secao> getSecoes() {
        return list;
    }

    public void setSecoes(List<Secao> secoes) {
        this.list = secoes;
    }
}

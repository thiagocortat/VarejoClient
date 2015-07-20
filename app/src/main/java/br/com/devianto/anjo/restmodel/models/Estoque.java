package br.com.devianto.anjo.restmodel.models;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Thiago Cortat on 19/07/15.
 */
@DatabaseTable
public class Estoque implements Serializable {


    public static final String MINIMO_FIELD_NAME = "minimo";
    public static final String DISPONIVEL_FIELD_NAME = "disponivel";
    public static final String MAXIMO_FIELD_NAME = "maximo";
    public static final String RESSUPRIMENTO_FIELD_NAME = "pontoRessuprimento";

//    @Expose
//    @DatabaseField(id = true  , columnName = ID_FIELD_NAME )
//    private Long id;
//
//    @DatabaseField(columnName = NOME_FIELD_NAME)
//    @Expose
//    private String nome;
//
//    @DatabaseField(columnName = CHANNEL_FIELD_NAME)
//    @Expose
//    private String channel;
//
//    public Estoque() {
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getNome() {
//        return nome;
//    }
//
//    public String getChannel() {
//        return channel;
//    }
}

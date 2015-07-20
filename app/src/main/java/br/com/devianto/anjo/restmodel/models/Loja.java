package br.com.devianto.anjo.restmodel.models;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Thiago Cortat on 19/07/15.
 */
@DatabaseTable
public class Loja implements Serializable {

    public static final String ID_FIELD_NAME = "id";

    public static final String NOME_FIELD_NAME = "nome";

    public static final String CHANNEL_FIELD_NAME = "canal";

    @Expose
    @DatabaseField(id = true  , columnName = ID_FIELD_NAME )
    private Long id;

    @DatabaseField(columnName = NOME_FIELD_NAME)
    @Expose
    private String nome;

    @DatabaseField(columnName = CHANNEL_FIELD_NAME)
    @Expose
    private String channel;

    public Loja() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getChannel() {
        return channel;
    }
}

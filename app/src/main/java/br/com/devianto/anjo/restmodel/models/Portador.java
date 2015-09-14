package br.com.devianto.anjo.restmodel.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;

import br.com.devianto.anjo.restmodel.models.constraints.ValidationConstraint;
import br.com.thiagocortat.mylibrary.utilities.ParseUtilities;

@DatabaseTable
public class Portador  extends Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

//    public static final String DATA_NASCIMENTO_FIELD_NAME = "datanascimento";
//
//    public static final String EMAIL_FIELD_NAME = "email";
//
//    public static final String ENDERECO_FIELD_NAME = "endereco";
//
//    public static final String BACKOFFICE_ID_FIELD_NAME = "backoffice_id";
//
////    public static final String ID_FIELD_NAME = "id";

    public static final String NOME_FIELD_NAME = "nome";


//    @Expose
//    @SerializedName("dataNascimento")
//    @DatabaseField(columnName = Portador.DATA_NASCIMENTO_FIELD_NAME)
//    private Date dataNascimento;

//    @Expose
//    @SerializedName("email")
//    @DatabaseField(columnName = Portador.EMAIL_FIELD_NAME)
//    private String email;

//    @Expose
//    @SerializedName("cpf")
//    private String cpf;
//
//    @Expose
//    @SerializedName("telefone")
//    private String telefone;


//    @DatabaseField(columnName = Portador.ID_FIELD_NAME, generatedId = true)
//    private Long internalId;

//    @Expose
//    @SerializedName("id")
//    @DatabaseField(columnName = Portador.BACKOFFICE_ID_FIELD_NAME)
//    private Long backofficeId;

    @Expose
    @DatabaseField(columnName = Portador.NOME_FIELD_NAME)
    private String nome;

//    @Expose
//    @SerializedName("endereco")
//    @DatabaseField(columnName = Portador.ENDERECO_FIELD_NAME, foreign = true,  canBeNull = false, foreignAutoCreate = true, foreignAutoRefresh = true)
//    private Endereco endereco;

    public Portador() {
        super();
    }

    public Portador(Cliente cliente, String nome) {
       this.dataNascimento = cliente.dataNascimento;
       this.email = cliente.email;
        this.nome = nome;
        this.endereco = cliente.getEndereco();
    }

    public String getNome() {
        return nome;
    }

//    public void setNome(String nome) {
//        this.nome = nome;
//    }

    //    public String getEmail() {
//        return email;
//    }
//
//
//
//
//    public Endereco getEndereco() {
//        return this.endereco;
//    }
//
//    public void setEndereco(Endereco endereco) {
//        this.endereco = endereco;
//    }
//
//
//
//    public void setEmail(String email) {
//        this.email = email;
//    }

}

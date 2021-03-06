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
public class Cliente extends ValidationConstraint
        implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String DATA_NASCIMENTO_FIELD_NAME = "datanascimento";

    public static final String EMAIL_FIELD_NAME = "email";

    public static final String ENDERECO_FIELD_NAME = "endereco";

    public static final String BACKOFFICE_ID_FIELD_NAME = "backoffice_id";

    public static final String ID_FIELD_NAME = "id";

    public static final String PRIMEIRO_NOME_FIELD_NAME = "primeironome";

    public static final String ULTIMO_NOME_FIELD_NAME = "ultimonome";

    @Expose
    @SerializedName("dataNascimento")
    @DatabaseField(columnName = Cliente.DATA_NASCIMENTO_FIELD_NAME)
    protected Date dataNascimento;

    @Expose
    @SerializedName("email")
    @DatabaseField(columnName = Cliente.EMAIL_FIELD_NAME)
    protected String email;

    @DatabaseField(columnName = Cliente.ID_FIELD_NAME, generatedId = true)
    private Long internalId;

    @Expose
    @SerializedName("id")
    @DatabaseField(columnName = Cliente.BACKOFFICE_ID_FIELD_NAME)
    private Long backofficeId;

    @Expose
    @SerializedName("primeiroNome")
    @DatabaseField(columnName = Cliente.PRIMEIRO_NOME_FIELD_NAME)
    protected String primeiroNome;

    @Expose
    @SerializedName("ultimoNome")
    @DatabaseField(columnName = Cliente.ULTIMO_NOME_FIELD_NAME)
    protected String ultimoNome;

    @Expose
    @SerializedName("endereco")
    @DatabaseField(columnName = Cliente.ENDERECO_FIELD_NAME, foreign = true,  canBeNull = false, foreignAutoCreate = true, foreignAutoRefresh = true)
    protected Endereco endereco;

    @Expose
    @SerializedName("cpf")
    private String cpf = "07032327702";

    @Expose
    @SerializedName("sexo")
    private String sexo = "MASCULINO";

    public Cliente() {
        super();
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return internalId;
    }


    public String getNomeCompleto() {
        return String.format("%s %s", this.primeiroNome, this.ultimoNome);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public JSONObject toJSON()
            throws JSONException {
        JSONObject jsonobject = new JSONObject();
        jsonobject.put("nome", getNomeCompleto());
        jsonobject.put("sobrenome", getNomeCompleto());
        jsonobject.put("email", getEmail());
        jsonobject.put("nascimento", ParseUtilities.formatDate(getDataNascimento(), "yyyy-MM-dd"));
        jsonobject.put("endereco", endereco.getLogradouro());
        jsonobject.put("estado", endereco.getEstado());
        jsonobject.put("bairro", endereco.getBairro());
        jsonobject.put("cep", endereco.getCep());
        jsonobject.put("cidade", endereco.getCidade());
        jsonobject.put("telefone", endereco.getTelefone());
        jsonobject.put("celular", endereco.getCelular());
        return jsonobject;
    }

    @Override
    protected void validate() {
//        if (GenericValidator.isBlankOrNull(primeiroNome)) {
//            super.add("Primeiro nome é obrigatório");
//        }
//        if (GenericValidator.isBlankOrNull(ultimoNome)) {
//            super.add("Ultimo nome é obrigatório");
//        }
//        if (dataNascimento == null) {
//            add("Data de nascimento é obrigatório");
//        }
//        if (GenericValidator.isBlankOrNull(email) || !GenericValidator.isEmail(email)) {
//            add("E-mail inválido");
//        }

        if (!endereco.isValid()) {
            addAll(endereco.errors());
        }

    }

    public void setId(Long id) {
        this.internalId = id;
    }

    public Endereco getEndereco() {
        return this.endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getUltimoNome() {
        return ultimoNome;
    }

    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getBackofficeId() {
        return backofficeId;
    }

    public void setBackofficeId(Long backofficeId) {
        this.backofficeId = backofficeId;
    }
}

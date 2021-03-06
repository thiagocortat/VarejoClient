package br.com.devianto.anjo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.androidquery.AQuery;

import org.apache.commons.validator.GenericValidator;

import java.util.Date;
import java.util.List;

import br.com.devianto.anjo.R;
import br.com.devianto.anjo.alerts.ErrorAlert;
import br.com.devianto.anjo.components.MaskedWatcher;
import br.com.devianto.anjo.events.FormatarDataNascimentoLostFocus;
import br.com.devianto.anjo.exceptions.NoUniqueRegistryException;
import br.com.devianto.anjo.repository.EstadosRepository;
import br.com.devianto.anjo.restmodel.models.Cliente;
import br.com.devianto.anjo.restmodel.models.Endereco;
import br.com.devianto.anjo.restmodel.models.Estado;
import br.com.devianto.anjo.restmodel.models.Pedido;
import br.com.devianto.anjo.utilities.Constante;
import br.com.devianto.anjo.utilities.ParseUtilities;
import br.com.devianto.anjo.utilities.PriceUtilities;


public class ClienteActivity extends AbstractActivity
        implements
        OnClickListener {
    private AQuery aq;
    private Long id = 0L;

    private Cliente mCliente;

//    private String[] states, statesID, statesUF;

    private void loadFromCliente(Cliente cliente) {

        this.setId(cliente.getId());
        this.setPrimeiroNome(cliente.getPrimeiroNome());
        this.setUltimoNome(cliente.getUltimoNome());
        this.setDataNascimento(cliente.getDataNascimento());
        this.setEmail(cliente.getEmail());
        this.setCidade(cliente.getEndereco().getCidade());
        this.setBairro(cliente.getEndereco().getBairro());
        this.setEndereco(cliente.getEndereco().getLogradouro());
        this.setCep(cliente.getEndereco().getCep());
        setEstado(cliente.getEndereco().getEstado());
        this.setTelefone(cliente.getEndereco().getTelefone());
//        this.setCelular(cliente.getEndereco().getCelular());

    }

//    public void setCelular(String celular) {
//
//        try {
//            celular = celular.replace("(", "").replace(")", "").replace("-", "");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        aq.id(R.id.celular).text(celular);
//    }

    public void setTelefone(String telefone) {
        try {
            telefone = telefone.replace("(", "").replace(")", "").replace("-", "");
        }catch (Exception e){
            e.printStackTrace();
        }

        aq.id(R.id.telefone).text(telefone);
    }

    private void setId(Long id) {
        this.id = id;
    }

    public void setCep(String cep) {

        try {
            cep = cep.replace("-", "");
        }catch (Exception e){
            e.printStackTrace();
        }

        aq.id(R.id.cep).text(cep);
    }


    public void setEndereco(String endereco) {
        aq.id(R.id.endereco).text(endereco);
    }

    public void setBairro(String bairro) {
        aq.id(R.id.bairro).text(bairro);
    }

    public void setCidade(String cidade) {
        aq.id(R.id.cidade).text(cidade);
    }

    public void setEmail(String email) {
        aq.id(R.id.email).text(email);
    }

    public void setDataNascimento(Date dataNascimento) {
        aq.id(R.id.dataNascimento).text(ParseUtilities.formatDate(dataNascimento, Constante.DATE_FOR_MASK_FORMAT));
    }

    public void setNome(String nome) {
        aq.id(R.id.nomeCompleto).text(nome);
    }

    @SuppressWarnings("unchecked")
    public void setEstado(Estado estado) {
        Spinner estados = aq.id(R.id.estados).getSpinner();
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) estados.getAdapter();
        int position = adapter.getPosition(estado.getNome());
        estados.setSelection(position);
    }

    @SuppressWarnings("unchecked")
    public Estado getEstado() {

        int position = aq.id(R.id.estados).getSelectedItemPosition();
        String state = getResources().getStringArray(R.array.arrayStates)[position];
        String stateID = getResources().getStringArray(R.array.arrayStatesID)[position];
        String stateUF = getResources().getStringArray(R.array.arrayStatesSIGLA)[position];

        return new Estado(Long.parseLong(stateID), state, stateUF);
//        return (Estado) aq.id(R.id.estados).getSelectedItem();
    }

    private Cliente createCliente() {

        if (mCliente == null)
            mCliente = new Cliente();
        if (mCliente.getId() == null || mCliente.getId() == 0)
            mCliente.setId(this.id);
        if (mCliente.getEndereco() == null)
            mCliente.setEndereco(new Endereco());

        mCliente.setDataNascimento(getDataNascimento());
        mCliente.setEmail(this.getEmail());
        mCliente.setPrimeiroNome(this.getPrimeiroNome());
        mCliente.setUltimoNome(this.getUltimoNome());

        mCliente.getEndereco().setEstado(this.getEstado());
        mCliente.getEndereco().setTelefone(getTelefone());
//        mCliente.getEndereco().setCelular(getCelular());
        mCliente.getEndereco().setCep(this.getCep());
        mCliente.getEndereco().setBairro(this.getBairro());
        mCliente.getEndereco().setCidade(this.getCidade());
        mCliente.getEndereco().setLogradouro(this.getEndereco());
        return mCliente;

    }

    public String getCelular() {
        return aq.id(R.id.celular).getText().toString();
    }

    public String getTelefone() {
        return aq.id(R.id.telefone).getText().toString();
    }

    public String getCep() {
        return aq.id(R.id.cep).getText().toString();
    }

    public String getEndereco() {
        return aq.id(R.id.endereco).getText().toString();
    }

    public String getBairro() {
        return aq.id(R.id.bairro).getText().toString();
    }

    public String getCidade() {
        return aq.id(R.id.cidade).getText().toString();
    }

    public String getEmail() {
        return aq.id(R.id.email).getText().toString();
    }

    public Date getDataNascimento() {
        String dt = aq.id(R.id.dataNascimento).getText().toString();
        if(!GenericValidator.isBlankOrNull(dt)) {
            return ParseUtilities.toDate(dt, Constante.DATE_LONG_FORMAT);
        }else{
            return null;
        }
    }

    public void setPrimeiroNome(String primeiroNome) {
        aq.id(R.id.primeiroNome).text(primeiroNome);
    }

    public void setUltimoNome(String ultimoNome) {
        aq.id(R.id.ultimoNome).text(ultimoNome);
    }

    public String getPrimeiroNome() {
        return aq.id(R.id.primeiroNome).getText().toString();
    }

    public String getUltimoNome() {
        return aq.id(R.id.ultimoNome).getText().toString();
    }

    private void salvar() {

        try {
            Pedido pedido = PriceUtilities.getPedido();

//            ClienteService clienteservice = new ClienteService(this);
            Date dataNascimento = this.getDataNascimento();

            if ( dataNascimento == null ) {
                (new ErrorAlert(this))
                        .setTitle("Não foi possível salvar o cliente.")
                        .setCancelable(true)
                        .setMessage(
                                "Não foi possível salvar o cliente. A data de nascimento inválida")
                        .show();
            } else {
                final Cliente cliente = createCliente();
                if (!cliente.isValid()) {
                    (new ErrorAlert(this))
                            .setTitle("Não foi possível salvar o cliente.")
                            .setCancelable(true)
                            .setMessages(cliente.errors())
                            .show();
                } else {
//                    clienteservice.save(cliente);
                    pedido.setCliente(cliente);
                    Intent intent = new Intent(this, PagamentoActivity.class);
                    startActivity(intent);
                    this.finish();
                }

            }
        } catch (Exception e) {
            (new ErrorAlert(this))
                    .setTitle("Não foi possível salvar o cliente.")
                    .setCancelable(true)
                    .setMessage(e.getMessage())
                    .show();
        }

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.salvar) {
            salvar();
        } else {
//            Intent intent = new Intent(this, HomeActivity.class);
//            startActivity(intent);
            this.finish();
        }
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_cliente_main);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        aq = new AQuery(this);
        Spinner spinner = aq.id(R.id.estados).getSpinner();


////        List<Estado> estados = new EstadosRepository(this).getAll();
////        estados.add(0, new Estado("", "Selecione um Estado"));
//        ArrayAdapter<String> arrayadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, states);
//        spinner.setAdapter(arrayadapter);

        aq.id(R.id.salvar).clicked(this);
        aq.id(R.id.cancelar).clicked(this);
        aq.id(R.id.dataNascimento).getEditText().setOnFocusChangeListener(new FormatarDataNascimentoLostFocus());

        new MaskedWatcher("##/##/####", aq.id(R.id.dataNascimento).getEditText()).setAcceptOnlyNumbers(true);
        new MaskedWatcher("#####-###", aq.id(R.id.cep).getEditText()).setAcceptOnlyNumbers(true);
//        new MaskedWatcher("(##)#####-####", aq.id(R.id.celular).getEditText()).setAcceptOnlyNumbers(true);
        new MaskedWatcher("(##)#####-####", aq.id(R.id.telefone).getEditText()).setAcceptOnlyNumbers(true);

//       mCliente = (Cliente) getIntent().getExtras().get(Constante.CLIENTE);

//        if (mCliente != null) {
//            loadFromCliente(mCliente);
//        }
    }

}

package br.com.devianto.anjo.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.com.devianto.anjo.R;
import br.com.devianto.anjo.adapters.ImagemAdapter;
import br.com.devianto.anjo.interfaces.OnSelectedImagem;
import br.com.devianto.anjo.restmodel.models.Imagem;
import br.com.devianto.anjo.restmodel.models.Pedido;
import br.com.devianto.anjo.restmodel.models.Produto;
import br.com.devianto.anjo.utilities.PriceUtilities;
import br.com.thiagocortat.mylibrary.activities.BaseActivity;
import br.com.thiagocortat.mylibrary.utilities.Constante;
import br.com.thiagocortat.mylibrary.utilities.ParseUtilities;
import br.com.thiagocortat.mylibrary.utilities.StringUtils;
import butterknife.InjectView;


public class DetailActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar toolbar;

    @InjectView(R.id.imagemProduto) protected ImageView imagemProduto;
    @InjectView(R.id.titulo)        protected TextView titulo;
    @InjectView(R.id.descricao)     protected TextView descricao;
    @InjectView(R.id.preco)         protected TextView preco;
    @InjectView(R.id.adicionar)     protected Button adicionar;
    @InjectView(R.id.recyclerView)  protected RecyclerView mRecyclerView;

    protected Produto mProduto;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected ImagemAdapter mAdapter;
//    private PhotoViewAttacher mAttacher;

    @Override
    public void onBeforeInjectViews(Bundle savedInstanceState) {
        Bundle args = getIntent().getExtras();
        if (args != null){
            if (args.containsKey(Constante.Extra.PRODUTO))
                mProduto = (Produto) args.getSerializable(Constante.Extra.PRODUTO);
        }
    }

    @Override
    public int getLayoutResource() { return R.layout.activity_detail; }

    @Override
    public void onAfterInjectViews(Bundle savedInstanceState) {

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        toolbar = (Toolbar) findViewById(br.com.thiagocortat.mylibrary.R.id.toolbar);
        setSupportActionBar(toolbar);

        List<Imagem> list = new ArrayList<>(mProduto.getImagens());
        mAdapter = new ImagemAdapter(list);
        mAdapter.setListener(new OnSelectedImagem() {
            @Override
            public void selectedImagem(Imagem imagem) {
                setImagemProduto(imagem.getFileName());
            }
        });

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        setImagemProduto(mProduto.getDefaultImage());

        titulo.setText(mProduto.getTitulo());

        String strDecricao = mProduto.getDescricao();
        if (StringUtils.isEmpty(strDecricao))
            strDecricao = "SEM DESCRIÇÃO";
        descricao.setText(strDecricao);

        preco.setText(ParseUtilities.formatMoney(mProduto.getPrecoVenda()));

        adicionar.setTag(mProduto);
        adicionar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
//        Produto produto = (Produto) view.getTag();
        Intent intent = new Intent(this, ShoppingCartActivity.class);
        Pedido pedido = PriceUtilities.getPedido();
        pedido.adicionar(mProduto);
        startActivity(intent);

    }

    private void setImagemProduto(String path){

        try{
//            Uri uri = Uri.fromFile(new File(path));
            Picasso.with(this).load(path).into(imagemProduto, new Callback() {
                @Override
                public void onSuccess() {
                    // The MAGIC happens here!
//                    mAttacher = new PhotoViewAttacher(imagemProduto);
                }

                @Override
                public void onError() {
                }
            });
        }catch (NullPointerException e) {
            imagemProduto.setImageResource(R.drawable.img_default_placeholder);
        }



    }


//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//
//        // Need to call clean-up
//        if(mAttacher != null)
//            mAttacher.cleanup();
//    }
}

package br.com.devianto.anjo.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.devianto.anjo.R;
import br.com.devianto.anjo.activities.DetailActivity;
import br.com.devianto.anjo.restmodel.models.Produto;
import br.com.thiagocortat.mylibrary.utilities.Constante;
import br.com.thiagocortat.mylibrary.utilities.ParseUtilities;
import br.com.thiagocortat.mylibrary.views.SquareImageView;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder>
        implements View.OnClickListener {

    private static final String TAG = "ProdutoAdapter";

    private List<Produto> products;
    private Context context;

    public ProdutoAdapter(Context context, List<Produto> produtos) {
        this.context = context;
        this.products = produtos;
    }

    @Override
    public ProdutoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_resumo, viewGroup, false);
        view.setOnClickListener(this);
        return new ProdutoViewHolder(context, view);
    }

    @Override
    public void onBindViewHolder(ProdutoViewHolder holder, int position) {

        Produto produto = products.get(position);

        Glide.with(context).load(produto.getDefaultImage())
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.img_default_placeholder)
                .into(holder.imagem);

        holder.titulo.setText(produto.getTitulo());
        holder.descricao.setText(produto.getDescricao());
        holder.preco.setText(ParseUtilities.formatMoney(produto.getPrecoVenda()));


        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public void onClick(View view) {

        int position = (int) view.getTag();
        Produto produto = products.get(position);
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(Constante.Extra.PRODUTO, produto);
//        Pedido pedido = PriceUtilities.getPedido();
//        pedido.adicionar(produto, atributo);
        context.startActivity(intent);

    }

    public void setProducts(List<Produto> products) {
        this.products.clear();
        notifyDataSetChanged();

        this.products.addAll(products);
        notifyItemRangeInserted(0, products.size());
    }

    class ProdutoViewHolder extends RecyclerView.ViewHolder {

        Context context;
        SquareImageView imagem;
        TextView titulo, descricao, preco;

        public ProdutoViewHolder( Context context,View view){
            super(view);

            this.context = context;
            imagem = (SquareImageView) view.findViewById(R.id.imagem);
            titulo = (TextView) view.findViewById(R.id.titulo);
            descricao = (TextView) view.findViewById(R.id.descricao);
            preco = (TextView) view.findViewById(R.id.preco);



        }


    }

}
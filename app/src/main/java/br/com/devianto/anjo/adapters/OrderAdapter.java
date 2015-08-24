package br.com.devianto.anjo.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import br.com.devianto.anjo.R;
import br.com.devianto.anjo.activities.DetailActivity;
import br.com.devianto.anjo.restmodel.models.Produto;
import br.com.thiagocortat.mylibrary.utilities.Constante;
import br.com.thiagocortat.mylibrary.utilities.ParseUtilities;
import br.com.thiagocortat.mylibrary.views.SquareImageView;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ProdutoViewHolder>
        implements OnClickListener {

    private static final String TAG = "OrderAdapter";

    private List<Produto> products;
    private Context context;

    public OrderAdapter(Context context, List<Produto> produtos) {
        this.context = context;
        this.products = produtos;
    }

    @Override
    public ProdutoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_order, viewGroup, false);
        view.setOnClickListener(this);
        return new ProdutoViewHolder(context, view);
    }

    @Override
    public void onBindViewHolder(ProdutoViewHolder holder, int position) {

        Produto produto = products.get(position);

        holder.titulo.setText(produto.getTitulo());
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
        TextView titulo, preco;

        public ProdutoViewHolder( Context context,View view){
            super(view);
            this.context = context;

            titulo = (TextView) view.findViewById(R.id.titulo);
            preco = (TextView) view.findViewById(R.id.preco);

        }


    }

}
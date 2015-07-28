package br.com.devianto.anjo.adapters;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import br.com.devianto.anjo.R;
import br.com.devianto.anjo.restmodel.models.ItemPedido;
import br.com.devianto.anjo.utilities.ParseUtilities;

public class ShoppingCartAdapter extends ArrayAdapter<ItemPedido> {

//    private AQuery aq;
    private List<ItemPedido> itens;

    public ShoppingCartAdapter(Context context, List<ItemPedido> itens) {
        super(context, android.R.layout.simple_list_item_1, itens);
        this.itens = itens;
    }

    public View getView(int i, View view, ViewGroup viewgroup) {
        Activity activity = (Activity) getContext();
        ItemPedido itempedido = (ItemPedido) itens.get(i);
        ViewHolder viewHolder;
        if (view == null) {
            view = activity.getLayoutInflater().inflate(R.layout.item_pedido, null);

            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Glide.with(activity).load(itempedido.getProduto().getDefaultImage())
                .crossFade()
                .fitCenter()
                .into(viewHolder.imagem);

//        Uri uri = Uri.fromFile(new File(itempedido.getProduto().getDefaultImage()));
//        // 1st: reset the imageView
//        Picasso.with(activity).cancelRequest(viewHolder.imagem);
//        // 2nd start a new load for the imageView
//        Picasso.with(activity).load(uri).skipMemoryCache().into(viewHolder.imagem);

//        viewHolder.remover.setTag(itempedido);
//        viewHolder.remover.setOnClickListener(new RemoverItemPedidoOnClickListener());

        viewHolder.idProduto.setText(itempedido.getId().toString());
        viewHolder.nome.setText(itempedido.getProduto().getTitulo());
        viewHolder.preco.setText(ParseUtilities.formatMoney(itempedido.getTotal()));

//        if (itempedido.getAtributo() != null) {
//            viewHolder.atributo.setText(itempedido.getAtributo().getDescricao());
//        }else {
//            viewHolder.atributo.setText("");
//        }


        viewHolder.preco.setText(ParseUtilities.formatMoney(itempedido.getTotal()));

        viewHolder.quantidade.setText(itempedido.getQuantidade().toString());
        viewHolder.quantidade.setTag(itempedido);
//        viewHolder.quantidade.setOnFocusChangeListener(new AtualizarQuantidadeItemPedidoEvent());

        return view;
    }



    private class ViewHolder {

        ImageView imagem;
        TextView idProduto, atributo, nome, preco;
        EditText quantidade;
        Button remover;


        public ViewHolder(View view){

            imagem = (ImageView) view.findViewById(R.id.imagem);
            idProduto = (TextView) view.findViewById(R.id.id);
            quantidade = (EditText) view.findViewById(R.id.quantidade);
            atributo = (TextView) view.findViewById(R.id.atributo);
            nome = (TextView) view.findViewById(R.id.nome);
            preco = (TextView) view.findViewById(R.id.preco);
            remover = (Button) view.findViewById(R.id.remover);

        }
    }
}

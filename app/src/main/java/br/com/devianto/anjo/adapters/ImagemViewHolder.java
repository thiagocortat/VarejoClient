package br.com.devianto.anjo.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import br.com.devianto.anjo.R;


/**
 * Created by thiagocortat on 1/15/15.
 */
public class ImagemViewHolder extends RecyclerView.ViewHolder {



    protected ImageView vImageView;
//    protected TextView vName;
//    protected TextView vSurname;
//    protected TextView vEmail;
//    protected TextView vTitle;

    public ImagemViewHolder(View v) {
        super(v);
        vImageView = (ImageView) v.findViewById(R.id.imagemProduto);

//        vName =  (TextView) v.findViewById(R.id.txtName);
//        vSurname = (TextView)  v.findViewById(R.id.txtSurname);
//        vEmail = (TextView)  v.findViewById(R.id.txtEmail);
//        vTitle = (TextView) v.findViewById(R.id.title);
    }

//    @Override
//    public void onClick(View view) {
//        Log.d(TAG, "onClick " + getPosition() + " " + mItem);
//    }

}

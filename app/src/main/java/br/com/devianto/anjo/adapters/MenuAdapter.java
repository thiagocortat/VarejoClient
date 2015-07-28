package br.com.devianto.anjo.adapters;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.devianto.anjo.R;
import br.com.devianto.anjo.restmodel.models.Secao;

/**
 * Created by thiagocortat on 7/27/15.
 */
public class MenuAdapter extends BaseAdapter {


    protected final Context mContext;


    private List<Secao> mItems = new ArrayList<Secao>();


    public MenuAdapter(Context context){
        mContext = context;
    }

    public MenuAdapter(Context mContext, List<Secao> mItems) {
        this.mContext = mContext;
        this.mItems.addAll(mItems);
        notifyDataSetChanged();
    }

    public void addItem(Secao item) {
        this.mItems.add(item);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return this.mItems.size();
    }


    @Override
    public Secao getItem(int i) {
        return this.mItems.get(i);
    }


    @Override
    public long getItemId(int i) {
        return this.mItems.get(i).getId();
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(this.mContext).inflate(R.layout.item_menu, parent, false);
        }


        TextView txtName = ViewHolder.get(view, R.id.textView);


        final Secao item = getItem(position);


        txtName.setText(item.getNome());


        return view;
    }


    public Context getContext() {
        return this.mContext;
    }


    public static class ViewHolder {
        // I added a generic return type to reduce the casting noise in client code
        @SuppressWarnings("unchecked")
        public static <T extends View> T get(View view, int id) {
            SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
            if (viewHolder == null) {
                viewHolder = new SparseArray<View>();
                view.setTag(viewHolder);
            }
            View childView = viewHolder.get(id);
            if (childView == null) {
                childView = view.findViewById(id);
                viewHolder.put(id, childView);
            }
            return (T) childView;
        }
    }
}
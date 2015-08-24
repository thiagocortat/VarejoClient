package br.com.devianto.anjo.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import br.com.devianto.anjo.R;
import br.com.devianto.anjo.adapters.OrderAdapter;
import br.com.devianto.anjo.adapters.ProdutoAdapter;
import br.com.devianto.anjo.repository.RestClient;
import br.com.devianto.anjo.restmodel.models.ApiProduto;
import br.com.thiagocortat.mylibrary.base.BaseFragment;
import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrdersFragment extends BaseFragment {

    @InjectView(R.id.cpf)           protected MaterialEditText edtCPF;
    @InjectView(R.id.layoutTop)     protected LinearLayout layoutTop;
    @InjectView(R.id.txNotContent)  protected TextView txNotContent;
    @InjectView(R.id.recyclerView)  protected RecyclerView recyclerView;
    @InjectView(R.id.frameRecycler) protected FrameLayout frameRecycler;

    protected RestClient rest;
    protected OrderAdapter adapter;

    public static OrdersFragment newInstance() {

        Bundle args = new Bundle();
        OrdersFragment fragment = new OrdersFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onBeforeInjectViews(Bundle savedInstanceState) {
        super.onBeforeInjectViews(savedInstanceState);

        rest = new RestClient();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_orders;
    }

    @Override
    public void onAfterInjectViews(Bundle savedInstanceState) {

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


    }

    private Callback<ApiProduto> produtoCallback = new Callback<ApiProduto>() {
        @Override
        public void success(ApiProduto apiProduto, Response response) {

            if (adapter == null) {
                adapter = new OrderAdapter(getActivity(), apiProduto.getProdutos());
                recyclerView.setAdapter(adapter);
            }
            else {
                adapter.setProducts(apiProduto.getProdutos());
            }

            setContentEmpty((apiProduto.getProdutos().size() == 0));

            hideProgress();
        }

        @Override
        public void failure(RetrofitError error) {
            Toast.makeText(getActivity(), "OPS, some kind of problem had happend! :(", Toast.LENGTH_LONG).show();
            setContentEmpty(true);
            hideProgress();
        }
    };
}

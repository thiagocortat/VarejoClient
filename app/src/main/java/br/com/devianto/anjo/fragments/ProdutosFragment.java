package br.com.devianto.anjo.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import br.com.devianto.anjo.R;
import br.com.devianto.anjo.adapters.ProdutoAdapter;
import br.com.devianto.anjo.repository.ProductRepository;
import br.com.devianto.anjo.repository.RestClient;
import br.com.devianto.anjo.restmodel.models.ApiProduto;
import br.com.thiagocortat.mylibrary.base.BaseFragment;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProdutosFragment extends BaseFragment {

    @InjectView(R.id.recyclerView)
    protected RecyclerView recyclerView;

    protected ProdutoAdapter adapter;

    public static ProdutosFragment newInstance() {
        ProdutosFragment f = new ProdutosFragment();
        return (f);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_recycleview;
    }

    @Override
    public void onAfterInjectViews(Bundle savedInstanceState) {

//        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
//        manager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(manager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        showProgress();

        getApiProducts();

    }


    public void getApiProducts(){

        RestClient rest = new RestClient();
        rest.getApiService().obtainProdutos(new Callback<ApiProduto>() {
            @Override
            public void success(ApiProduto apiProduto, Response response) {
                Toast.makeText(getActivity(), "Data found!", Toast.LENGTH_LONG).show();

                adapter = new ProdutoAdapter(getActivity(), apiProduto.getProdutos());
                recyclerView.setAdapter(adapter);

                hideProgress();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), "OPS, some kind of problem had happend! :(", Toast.LENGTH_LONG).show();

                hideProgress();
            }
        });

    }


}

package br.com.devianto.anjo.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mobileia.mcdropdownmenu.MCDropdownMenu;
import com.mobileia.mcdropdownmenu.OnMenuSelectedListener;

import br.com.devianto.anjo.R;
import br.com.devianto.anjo.adapters.MenuAdapter;
import br.com.devianto.anjo.adapters.ProdutoAdapter;
import br.com.devianto.anjo.repository.RestClient;
import br.com.devianto.anjo.restmodel.models.ApiProduto;
import br.com.devianto.anjo.restmodel.models.ApiSecoes;
import br.com.devianto.anjo.restmodel.models.Secao;
import br.com.thiagocortat.mylibrary.base.BaseFragment;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProdutosFragment extends BaseFragment {

    @InjectView(R.id.recyclerView)  protected RecyclerView recyclerView;
    @InjectView(R.id.menu)          protected MCDropdownMenu dropdownMenu;
    @InjectView(R.id.progressMenu)  protected ProgressBar progressMenu;
    @InjectView(R.id.frameRecycler) protected FrameLayout frameRecycler;
    @InjectView(R.id.txNotContent) protected TextView txNotContent;

    protected TextView txMenu;
    protected ProdutoAdapter adapter;
    protected MenuAdapter menuAdapter;
    protected  RestClient rest;

    public static ProdutosFragment newInstance() {
        ProdutosFragment f = new ProdutosFragment();
        return (f);
    }

    @Override
    public void onBeforeInjectViews(Bundle savedInstanceState) {
        super.onBeforeInjectViews(savedInstanceState);

        rest = new RestClient();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_recycleview;
    }

    @Override
    public void onAfterInjectViews(Bundle savedInstanceState) {

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        dropdownMenu.setLayoutMainView(R.layout.menu_main_view);

        dropdownMenu.setOnMenuSelectedListener(new OnMenuSelectedListener() {
            @Override
            public void onSelected(View listview, int selectedIndex) {

                Secao secao = menuAdapter.getItem(selectedIndex);
                txMenu.setText(secao.getNome());
                getApiProdutosBySecao(secao.getId());
                dropdownMenu.dismiss();
            }
        });

        txMenu = (TextView) dropdownMenu.findViewById(R.id.textView2);

        showProgress();
        getApiProducts();
        getApiSecoes();

    }

    public void showHeaderProgress(boolean show) {

        progressMenu.setVisibility(show ? View.VISIBLE : View.GONE);
        dropdownMenu.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    @Override
    public void setContentEmpty(boolean isEmpty) {

        txNotContent.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
        recyclerView.setVisibility(isEmpty ? View.GONE : View.VISIBLE);
    }

    public void getApiProducts(){

        rest.getApiService().obtainProdutos(produtoCallback);

    }


    public void getApiSecoes() {

        showHeaderProgress(true);
        rest.getApiService().obtainSecoes(new Callback<ApiSecoes>() {
            @Override
            public void success(ApiSecoes apiSecoes, Response response) {
                // Create adapter
                menuAdapter = new MenuAdapter(getActivity(), apiSecoes.getSecoes());
                dropdownMenu.setAdapter(menuAdapter);

                showHeaderProgress(false);
            }

            @Override
            public void failure(RetrofitError error) {

                menuAdapter = new MenuAdapter(getActivity(), ApiSecoes.getHomeSecao());
                dropdownMenu.setAdapter(menuAdapter);
                showHeaderProgress(false);
            }
        });
    }


    public void getApiProdutosBySecao(long secaoId) {

        showProgress(frameRecycler);

        rest.getApiService().obtainProdutosBySecao(secaoId, produtoCallback);
    }


    private Callback<ApiProduto> produtoCallback = new Callback<ApiProduto>() {
        @Override
        public void success(ApiProduto apiProduto, Response response) {

            if (adapter == null) {
                adapter = new ProdutoAdapter(getActivity(), apiProduto.getProdutos());
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

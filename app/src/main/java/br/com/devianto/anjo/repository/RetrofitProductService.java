package br.com.devianto.anjo.repository;

import br.com.devianto.anjo.restmodel.GiantBombResponse;
import br.com.devianto.anjo.restmodel.models.ApiEstados;
import br.com.devianto.anjo.restmodel.models.ApiProduto;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by thiagocortat on 5/24/15.
 */
public interface RetrofitProductService {

    @GET("/1/produtos/todosAtributos/list.json")
    @Headers({ "Content-type: application/json; charset=UTF-8" })
    void obtainProdutos(Callback<ApiProduto> callback);

    @GET("/1/secoes.json")
    @Headers({ "Content-type: application/json; charset=UTF-8" })
    void obtainSecoes(Callback<ApiEstados> callback);

    @GET("/1/secoes.json")
    @Headers({ "Content-type: application/json; charset=UTF-8" })
    void obtainEstados(Callback<ApiEstados> callback);


    @GET("/estados.json")
    @Headers({ "Content-type: application/json; charset=UTF-8" })
    void obtainEstados(@Header("Authorization") String authorization, Callback<String> callback);
}

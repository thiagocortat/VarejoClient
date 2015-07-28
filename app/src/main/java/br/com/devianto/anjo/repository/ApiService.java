package br.com.devianto.anjo.repository;


import java.util.HashMap;

import br.com.devianto.anjo.restmodel.models.ApiEstados;
import br.com.devianto.anjo.restmodel.models.ApiProduto;
import br.com.devianto.anjo.restmodel.models.ApiSecoes;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by thiagocortat on 5/24/15.
 */
public interface ApiService {

    @GET("/loja/1/produtos/todosAtributos/list.json")
    @Headers({ "Content-type: application/json; charset=UTF-8" })
    void obtainProdutos(Callback<ApiProduto> callback);

    @GET("/secao/{id}/produtos/todosAtributos/list.json")
    @Headers({ "Content-type: application/json; charset=UTF-8" })
    void obtainProdutosBySecao(@Path("id")long id, Callback<ApiProduto> callback);

    @GET("/1/secoes.json")
    @Headers({ "Content-type: application/json; charset=UTF-8" })
    void obtainSecoes(Callback<ApiSecoes> callback);

    @GET("/1/secoes.json")
    @Headers({ "Content-type: application/json; charset=UTF-8" })
    void obtainEstados(Callback<ApiEstados> callback);

//    @GET("/perfis.json")
//    @Headers({ "Content-type: application/json; charset=UTF-8" })
//    void login(Callback<User> callback);
//
//    @GET("/produtos.json")
//    @Headers({ "Content-type: application/json; charset=UTF-8" })
//    void obtainProdutos(Callback<ApiProdutos> callback);
//
//    @GET("/auditoria/produtos.json")
//    @Headers({ "Content-type: application/json; charset=UTF-8" })
//    void obtainProdutosToAuditate(Callback<ApiProdutos> callback);
//
//    @GET("/{id}/produto.json")
//    @Headers({ "Content-type: application/json; charset=UTF-8" })
//    void obtainProduto(@Path("id") int groupId, Callback<ApiProduto> callback);
//
//    @GET("/produto/{codBarra}.json")
//    @Headers({ "Content-type: application/json; charset=UTF-8" })
//    void obtainProdutoByCode(@Path("codBarra") String barcode, Callback<ApiProduto> callback);
//
//    @POST("/produto/update.json")
//    @Headers({ "Content-type: application/json; charset=UTF-8" })
//    void updateProduto(@Body ApiUpdateProduto apiUpdateProduto, Callback<HashMap> callback);
//
//
//    @GET("/financeiro/faturamento.json")
//    @Headers({ "Content-type: application/json; charset=UTF-8" })
//    void obtainFaturamento(Callback<ApiFaturamento> callback);
//
//    //Sample: financeiro/2015-01-01/2015-02-01/faturamento.json
//    @GET("/financeiro/{dt-begin}/{dt-end}/faturamento.json")
//    @Headers({ "Content-type: application/json; charset=UTF-8" })
//    void obtainFaturamentoByDate(@Path("dt-begin") String dtBegin, @Path("dt-end") String dtEnd, Callback<ApiFaturamento> callback);
//
//    @GET("/canais-comunicacao.json")
//    @Headers({ "Content-type: application/json; charset=UTF-8" })
//    void obtainCanais(Callback<ApiChannels> callback);

}

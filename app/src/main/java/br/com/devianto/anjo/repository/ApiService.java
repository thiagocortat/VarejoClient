package br.com.devianto.anjo.repository;


import java.util.HashMap;

import br.com.devianto.anjo.restmodel.models.ApiEstados;
import br.com.devianto.anjo.restmodel.models.ApiFormaPagamentos;
import br.com.devianto.anjo.restmodel.models.ApiProduto;
import br.com.devianto.anjo.restmodel.models.ApiSecoes;
import br.com.devianto.anjo.restmodel.models.User;
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

//    produto=http://www.allinshopp.com.br/ellasa/2/produtos.json
//    secao=http://www.allinshopp.com.br/ellasa/2/secoes.json
//    estado=http://www.allinshopp.com.br/ellasa/estados.json
//    cep=http://www.allinshopp.com.br/ellasa/cep.json
//    cliente=http://www.allinshopp.com.br/ellasa/clientes.json
//    pedido=http://www.allinshopp.com.br/ellasa/2/carrinho-de-compras/checkout.json

    @GET("/perfis.json")
    @Headers({ "Content-type: application/json; charset=UTF-8" })
    void login(Callback<User> callback);

    @GET("/3/carrinho-de-compras/checkout.json")
    @Headers({ "Content-type: application/json; charset=UTF-8" })
    void checkout(Callback<HashMap> callback);

    @GET("/3/clientes.json")
    @Headers({ "Content-type: application/json; charset=UTF-8" })
    void registerUser(Callback<HashMap> callback);

    @GET("/formaspagamento.json")
    @Headers({ "Content-type: application/json; charset=UTF-8" })
    void obtainFormasPagamento(Callback<ApiFormaPagamentos> callback);

    @GET("/loja/3/produtos/todosAtributos/list.json")
    @Headers({ "Content-type: application/json; charset=UTF-8" })
    void obtainProdutos(Callback<ApiProduto> callback);

    @GET("/secao/{id}/produtos/todosAtributos/list.json")
    @Headers({ "Content-type: application/json; charset=UTF-8" })
    void obtainProdutosBySecao(@Path("id")long id, Callback<ApiProduto> callback);

    @GET("/3/secoes.json")
    @Headers({ "Content-type: application/json; charset=UTF-8" })
    void obtainSecoes(Callback<ApiSecoes> callback);

    @GET("/estado.json")
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

package br.com.devianto.anjo.repository;

import android.util.Base64;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import retrofit.ErrorHandler;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

/**
 * Created by thiagocortat on 5/24/15.
 */
public class ProductRepository {

    private RetrofitProductService retrofitProduct;

//    private final String apiKey = "07dce392be760422f40d67bc7945d7f1aaac7f4e";
    private final String apiBaseUrl = "http://www.allinshopp.com.br/ellasa/";

    public ProductRepository() {
        init();
    }

    private void init() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Date.class, new DateTypeAdapter())
                .create();

        RestAdapter.Builder builder= new RestAdapter.Builder()
                .setConverter(new GsonConverter(gson))
                .setEndpoint(apiBaseUrl);
//                .setClient(new OkClient(new OkHttpClient()));

            // concatenate username and password with colon for authentication
            final String credentials = "marcelosrodrigues@gmail.com" + ":" + "2pk0#3ty?";
            builder.setRequestInterceptor(new RequestInterceptor() {
                @Override
                public void intercept(RequestInterceptor.RequestFacade request) {

//                    //String string = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
//                    String string;
//                    try {
//                        string = Base64.encodeToString(credentials.getBytes("UTF-8"), Base64.NO_WRAP);
//                    } catch (UnsupportedEncodingException e) {
//                        // Weird, no UTF-8 encoding found?
//                        string = Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
//                    }
                    request.addHeader("Accept", "application/json");
                    request.addHeader("Content-Type", "application/json; charset=UTF-8");
//                    request.addHeader("Authorization", string);
                    request.addHeader("Authorization", "Basic bWFyY2Vsb3Nyb2RyaWd1ZXNAZ2xvYm8uY29tOjJwazAjM3R5Pw==");
                }
            });


        RestAdapter restAdapter = builder.build();

        retrofitProduct = restAdapter.create(RetrofitProductService.class);
    }

    public RetrofitProductService getApiService()
    {
        return retrofitProduct;
    }

}

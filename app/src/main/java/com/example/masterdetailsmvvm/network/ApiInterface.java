package com.example.masterdetailsmvvm.network;

import com.example.masterdetailsmvvm.model.MostViewed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("http://api.nytimes.com/svc/mostpopular/v2/mostviewed/{section}/{period}.json?api-key=KtQyZWkXetDW9QTQ9PxaWGEV6lilMqrx")
    Call<MostViewed> getMostViewed(@Path("section") String section, @Path("period") String period);

}

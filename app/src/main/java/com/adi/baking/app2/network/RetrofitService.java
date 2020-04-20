package com.adi.baking.app2.network;


import com.adi.baking.app2.model.RecipeName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {
    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<List<RecipeName>> getRecipeInfo();

//    @GET("/3/movie/{movie_id}/reviews?api_key=" + API_KEY + "&language=en-US")
//    Call<ReviewList> getReviewForMovie(@Path("movie_id") int movieId);
}

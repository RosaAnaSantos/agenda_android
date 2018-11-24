package com.iesvirgendelcarmen.socialtech;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FotoAPI {

    @GET("movie/popular?language=es")
    public Call<Foto> getPeliculasPopulares(@Query("api_key") String api_key, @Query("page") int page);

    @GET("movie/{id}")
    public Call<Foto> getPelicula(@Path("id") int id, @Query("api_key") String api_key);

}

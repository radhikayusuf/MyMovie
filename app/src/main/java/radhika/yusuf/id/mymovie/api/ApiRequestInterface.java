package radhika.yusuf.id.mymovie.api;

import radhika.yusuf.id.mymovie.api.api_dao.MainResponse;
import radhika.yusuf.id.mymovie.api.api_dao.ReviewResponse;
import radhika.yusuf.id.mymovie.api.api_dao.VideosResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Radhika Yusuf on 14/06/17.
 */

public interface ApiRequestInterface {

    @GET("movie/{sort}")
    Call<MainResponse> getMovie(@Path("sort")String sort, @Query("api_key")String api_key, @Query("page")int page);

    @GET("movie/{id_movie}/reviews")
    Call<ReviewResponse> getReviews(@Path("id_movie")String id, @Query("api_key")String api_key, @Query("page")int page);

    @GET("movie/{id_movie}/videos")
    Call<VideosResponse> getVideos(@Path("id_movie")String id, @Query("api_key")String api_key);
}

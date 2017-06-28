package radhika.yusuf.id.mymovie.api;

import radhika.yusuf.id.mymovie.api.apiDao.MainResponse;
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
}

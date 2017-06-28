package radhika.yusuf.id.mymovie.api;

import radhika.yusuf.id.mymovie.utils.Constant;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Radhika Yusuf on 14/06/17.
 */

public class ApiClient {
    public static ApiRequestInterface service(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build();
        return retrofit.create(ApiRequestInterface.class);
    }
}

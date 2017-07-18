package radhika.yusuf.id.mymovie.api.api_dao;

import android.os.Parcel;

import java.util.List;

/**
 * Created by Radhika Yusuf on 13/07/17.
 */

public class ReviewResponse extends BaseApiDao<List<ReviewData>>{


    protected ReviewResponse(Parcel in) {
        super(in);
    }
}

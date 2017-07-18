package radhika.yusuf.id.mymovie.api.api_dao;

import android.os.Parcel;

import java.util.List;

/**
 * Created by Radhika Yusuf on 13/07/17.
 */

public class VideosResponse extends BaseApiDao<List<VideosData>> {
    protected VideosResponse(Parcel in) {
        super(in);
    }
}

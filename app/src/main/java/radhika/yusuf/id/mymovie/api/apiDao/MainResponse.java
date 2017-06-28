package radhika.yusuf.id.mymovie.api.apiDao;

import android.os.Parcel;

import java.util.List;

/**
 * Created by Radhika Yusuf on 14/06/17.
 */

public class MainResponse extends BaseApiDao<List<MainData>> {


    protected MainResponse(Parcel in) {
        super(in);
    }
}

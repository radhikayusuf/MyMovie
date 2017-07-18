package radhika.yusuf.id.mymovie.utils;

import radhika.yusuf.id.mymovie.api.api_dao.MainData;
import radhika.yusuf.id.mymovie.api.api_dao.VideosData;

/**
 * Created by Radhika Yusuf on 16/06/17.
 */

public interface RecyclerViewClickItem {
    void onClickItem(MainData mainData, boolean isFavorite);
}

package radhika.yusuf.id.mymovie.ui.detail_video;

import android.content.Context;
import android.databinding.ObservableField;
import android.view.View;

import radhika.yusuf.id.mymovie.R;
import radhika.yusuf.id.mymovie.api.api_dao.VideosData;

/**
 * Created by Radhika Yusuf on 14/07/17.
 */

public class VideoRowVM {

    public ObservableField<String> bTitle = new ObservableField<>("");
    public ObservableField<String> bType = new ObservableField<>("");
    public ObservableField<Boolean> bSelected = new ObservableField<>(false);

    public VideoRowVM(VideosData data, boolean selected, Context mContext) {
        bTitle.set(data.getName());
        bType.set(mContext.getString(R.string.type_trailer, data.getType()));
        bSelected.set(selected);
    }
}

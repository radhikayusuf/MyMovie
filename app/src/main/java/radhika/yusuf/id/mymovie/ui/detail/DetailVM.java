package radhika.yusuf.id.mymovie.ui.detail;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.databinding.ObservableField;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import radhika.yusuf.id.mymovie.R;
import radhika.yusuf.id.mymovie.api.api_dao.MainData;
import radhika.yusuf.id.mymovie.data.MovieContract;

/**
 * Created by Radhika Yusuf on 16/06/17.
 */

public class DetailVM {

    private Context mContext;
    private MainData mData;


    public ObservableField<String> imageUrlBackDrop = new ObservableField<>();

    public DetailVM(Context mContext, MainData data, boolean favorite) {
        this.mContext = mContext;
        this.mData = data;

        this.imageUrlBackDrop.set(data.getBackdrop_path());
    }


}

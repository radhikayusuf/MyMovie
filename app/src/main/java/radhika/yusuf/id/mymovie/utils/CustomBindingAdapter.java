package radhika.yusuf.id.mymovie.utils;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import at.grabner.circleprogress.CircleProgressView;

/**
 * Created by Radhika Yusuf on 16/06/17.
 */

public class CustomBindingAdapter {

    @BindingAdapter("bind:setImageUrl")
    public static void setImage(ImageView view, String url){
        Picasso.with(view.getContext())
                .load(Constant.BASE_POSTER_URL+url)
                .into(view);
    }

    @BindingAdapter({"bind:setProgres"})
    public static void setProgress(CircleProgressView view, String score) {
        if (score != null) {
            view.setValue(Float.valueOf(String.valueOf(score)) * 10);
        }
    }
}

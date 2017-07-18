package radhika.yusuf.id.mymovie.utils;

import android.databinding.BindingAdapter;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import at.grabner.circleprogress.CircleProgressView;
import radhika.yusuf.id.mymovie.R;

/**
 * Created by Radhika Yusuf on 16/06/17.
 */

public class CustomBindingAdapter {

    @BindingAdapter("bind:setImageUrl")
    public static void setImage(ImageView view, String url){
        Picasso.with(view.getContext())
                .load(Constant.BASE_POSTER_URL+url)
                .placeholder(R.drawable.popcorn_placeholder)
                .error(R.drawable.error_image)
                .into(view);
    }

    @BindingAdapter("bind:setBackdrop")
    public static void setImageBackDrop(ImageView view, String url){
        Picasso.with(view.getContext())
                .load( (Constant.BASE_POSTER_URL).replace("w185","w500") +url)
                .placeholder(R.drawable.popcorn_placeholder)
                .error(R.drawable.error_image)
                .into(view);
    }

    @BindingAdapter({"bind:setProgress"})
    public static void setProgress(CircleProgressView view, String score) {
        if (score != null) {
            view.setValue(Float.valueOf(String.valueOf(score)) * 10);
        }else{
            view.setValue(Float.valueOf(0) * 10);
        }
    }

    @BindingAdapter({"bind:setButtonFav"})
    public static void setFavButton(Button view, boolean fav) {
        view.setBackgroundResource(fav ? R.drawable.solid_orange : R.drawable.border_orange);
        view.setTextColor(fav ? view.getContext().getResources().getColor(R.color.colorWhite) : view.getContext().getResources().getColor(R.color.colorPrimary));
        view.setText(fav ? R.string.remove_favorite : R.string.add_favorite);
    }
}

package radhika.yusuf.id.mymovie.ui.detail_info;

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
 * Created by Radhika Yusuf on 12/07/17.
 */

public class InfoVM {

    private Context mContext;
    private MainData mData;

    public ObservableField<String> bTitle = new ObservableField<>();
    public ObservableField<String> bDesc = new ObservableField<>();
    public ObservableField<String> bReleaseDate = new ObservableField<>();
    public ObservableField<String> bVote = new ObservableField<>();
    public ObservableField<Boolean> bAdult = new ObservableField<>();
    public ObservableField<String> imageUrl = new ObservableField<>();
    public ObservableField<Boolean> bFav = new ObservableField<>(false);


    public InfoVM(MainData data, Context context, boolean favorite) {
        this.mContext = context;
        this.mData = data;

        this.bTitle.set(data.getTitle());
        this.bDesc.set(data.getOverview());
        this.bAdult.set(data.isAdult());
        this.bVote.set(String.valueOf(data.getVote_average()));
        this.bReleaseDate.set(mContext.getString(R.string.release_date, data.getRelease_date()));
        this.imageUrl.set(data.getPoster_path());
        this.bFav.set(favorite);

    }

    public void onClickFav(View v){
        if(!bFav.get()){
            ContentValues contentValues = new ContentValues();

            contentValues.put(MovieContract.MovieEntry.COLUMN_FAVORITE_IDS, mData.getId());
            contentValues.put(MovieContract.MovieEntry.COLUMN_TITLE, mData.getTitle());
            contentValues.put(MovieContract.MovieEntry.COLUMN_ORI_TITLE, mData.getOriginal_title());
            contentValues.put(MovieContract.MovieEntry.COLUMN_VOTE_COUNT, mData.getVote_count());
            contentValues.put(MovieContract.MovieEntry.COLUMN_VIDEO, mData.isVideo() ? 1 : 0);
            contentValues.put(MovieContract.MovieEntry.COLUMN_VOTE_AVG, mData.getVote_average());
            contentValues.put(MovieContract.MovieEntry.COLUMN_POPULARITY, mData.getPopularity());
            contentValues.put(MovieContract.MovieEntry.COLUMN_POSTER_PATH, mData.getPoster_path());
            contentValues.put(MovieContract.MovieEntry.COLUMN_ORIGINAL_LANG, mData.getOriginal_language());
            contentValues.put(MovieContract.MovieEntry.COLUMN_GENRE, listToString(mData.getGenre_ids()));
            contentValues.put(MovieContract.MovieEntry.COLUMN_BACKDROP_PATH, mData.getBackdrop_path());
            contentValues.put(MovieContract.MovieEntry.COLUMN_ADULT, mData.isAdult() ? 1 : 0);
            contentValues.put(MovieContract.MovieEntry.COLUMN_OVERVIEW, mData.getOverview());
            contentValues.put(MovieContract.MovieEntry.COLUMN_RELEASE_DATE, mData.getRelease_date());

            Uri uri = mContext.getContentResolver().insert(MovieContract.MovieEntry.CONTENT_URI, contentValues);

            if(uri != null) {
                Toast.makeText(mContext, R.string.add_fav, Toast.LENGTH_LONG).show();
                bFav.set(true);
            }


        }else{
            int id = mData.getId();

            String stringId = Integer.toString(id);
            Uri uri = MovieContract.MovieEntry.CONTENT_URI;
            uri = uri.buildUpon().appendPath(stringId).build();

            Toast.makeText(mContext, R.string.delete_fav, Toast.LENGTH_LONG).show();

            mContext.getContentResolver().delete(uri, null, null);
            bFav.set(false);
        }
    }

    private String listToString(List<Integer> genre_ids) {
        if(genre_ids != null){
            String returnResult = String.valueOf(genre_ids.get(0));
            for (int i = 1; i < genre_ids.size(); i++) {
                returnResult+="|"+genre_ids;
            }
            return returnResult;
        }else{
            return "0";
        }
    }
}

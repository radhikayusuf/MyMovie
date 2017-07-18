package radhika.yusuf.id.mymovie.utils;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.util.TypedValue;

import java.util.ArrayList;
import java.util.List;

import radhika.yusuf.id.mymovie.api.api_dao.MainData;
import radhika.yusuf.id.mymovie.data.MovieContract;

/**
 * Created by Radhika Yusuf on 12/07/17.
 */

public class Helper {

    public static MainData cursorToMainData(Cursor cursor, int position){
        int idIndex = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_FAVORITE_IDS);
        int titleIndex = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_TITLE);
        int oriTitleIndex = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_ORI_TITLE);
        int overviewIndex = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_OVERVIEW);
        int posterIndex = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_POSTER_PATH);
        int voteCountIndex = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_VOTE_COUNT);
        int voteAvgIndex = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_VOTE_AVG);
        int adultIndex = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_ADULT);
        int videoIndex = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_VIDEO);
        int backdropIndex = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_BACKDROP_PATH);
        int originalLangIndex = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_ORIGINAL_LANG);
        int popularityIndex = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_POPULARITY);
        int genreIndex = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_GENRE);
        int releaseDate = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_RELEASE_DATE);

        cursor.moveToPosition(position);

        MainData mainData = new MainData(
                cursor.getInt(voteCountIndex),
                cursor.getInt(idIndex),
                cursor.getInt(videoIndex) == 1,
                cursor.getDouble(voteAvgIndex),
                cursor.getString(titleIndex),
                cursor.getDouble(popularityIndex),
                cursor.getString(posterIndex),
                cursor.getString(originalLangIndex),
                cursor.getString(oriTitleIndex),
                stringToList(cursor.getString(genreIndex)),
                cursor.getString(backdropIndex),
                cursor.getInt(adultIndex) == 1,
                cursor.getString(overviewIndex),
                cursor.getString(releaseDate));
        return mainData;
    }


    public static List<Integer> stringToList(String text){
        List<Integer> returnList = new ArrayList<>();
        if(text.contains("|")){
            for (String s : text.split("|")) {
                returnList.add(Integer.parseInt(s));
            }
        }else{
            returnList.add(Integer.parseInt(text));
        }

        return returnList;
    }

    public static float intToDP(Context context, int value){
        Resources r = context.getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, r.getDisplayMetrics());
    }
}

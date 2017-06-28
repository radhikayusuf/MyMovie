package radhika.yusuf.id.mymovie.utils;

import android.content.Context;
import android.content.SharedPreferences;

import radhika.yusuf.id.mymovie.R;

/**
 * Created by Radhika Yusuf on 16/06/17.
 */

public class MyPrefences {

    public static boolean saveChangeSort(String text, Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.prefences_sort) ,context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.prefences_sort_key), text);
        return editor.commit();
    }

    public static String getStateSort(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.prefences_sort), context.MODE_PRIVATE);
        return sharedPreferences.getString(context.getString(R.string.prefences_sort_key), context.getString(R.string.populart_sort));
    }


}

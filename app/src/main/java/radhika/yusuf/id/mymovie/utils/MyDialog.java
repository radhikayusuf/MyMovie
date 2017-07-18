package radhika.yusuf.id.mymovie.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.ArrayAdapter;

import radhika.yusuf.id.mymovie.R;

/**
 * Created by Radhika Yusuf on 16/06/17.
 */

public class MyDialog {

    private static int state = 0;

    public static void showSortDialog(final Context context){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.sort_dialog_title));

        final DialogListAdapter adapter = new DialogListAdapter(context, android.R.layout.select_dialog_singlechoice);
        adapter.add(context.getString(R.string.populart_sort));
        adapter.add(context.getString(R.string.top_rated_sort));
        adapter.add(context.getString(R.string.favorite_sort));


        builder.setSingleChoiceItems(adapter, getIndexSort(MyPrefences.getStateSort(context), adapter), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                state = i;
            }
        });

        builder.setPositiveButton(context.getString(R.string.positive_button), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyPrefences.saveChangeSort(adapter.getItem(state), context);
            }
        });

        builder.setNegativeButton(context.getString(R.string.negative_button), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private static int getIndexSort(String text, ArrayAdapter<String> adapter){
        int result = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            if(adapter.getItem(i).equalsIgnoreCase(text)){
                result = i;
                break;
            }
        }
        return result;
    }
}

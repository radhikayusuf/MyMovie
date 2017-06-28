package radhika.yusuf.id.mymovie.utils;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Radhika Yusuf on 27/06/17.
 */

public class DialogListAdapter extends ArrayAdapter<String> {

    public DialogListAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    public DialogListAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public DialogListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull String[] objects) {
        super(context, resource, objects);
    }

    public DialogListAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull String[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public DialogListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
    }

    public DialogListAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List<String> objects) {
        super(context, resource, textViewResourceId, objects);
    }



    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getDropDownView(position, convertView, parent);
        return initView(position, convertView);
    }

    private View initView(int position, View convertView) {
        if(convertView == null)
            convertView = View.inflate(getContext(),
                    android.R.layout.simple_list_item_2,
                    null);
        TextView tvText1 = (TextView)convertView.findViewById(android.R.id.text1);
        tvText1.setText(getItem(position));
        tvText1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
        tvText1.setPadding(8,8,8,8);
        return convertView;
    }
}

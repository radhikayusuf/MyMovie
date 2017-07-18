package radhika.yusuf.id.mymovie.ui.main;

import android.content.Context;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import radhika.yusuf.id.mymovie.R;
import radhika.yusuf.id.mymovie.api.api_dao.MainData;
import radhika.yusuf.id.mymovie.databinding.LoadMoreBinding;
import radhika.yusuf.id.mymovie.databinding.RowMainBinding;
import radhika.yusuf.id.mymovie.utils.Helper;
import radhika.yusuf.id.mymovie.utils.MyPrefences;
import radhika.yusuf.id.mymovie.utils.RecyclerViewClickItem;

/**
 * Created by Radhika Yusuf on 16/06/17.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<MainData> mData;
    private List<Integer> mDataFavorite;
    private Context mContext;
    private RecyclerViewClickItem evt;
    private boolean showLoading = true;
    private Cursor mCursor;

    public MainAdapter(List<MainData> mData, List<Integer> dataFavorite, RecyclerViewClickItem evt, Context context) {
        this.mData = mData;
        this.mContext = context;
        this.evt = evt;
        this.mDataFavorite = dataFavorite;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding mBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), viewType, parent, false);
        return new ViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if(holder.getmBinding() instanceof RowMainBinding){
            final MainData data = isFavorite() && mCursor!=null ? Helper.cursorToMainData(mCursor, position) : mData.get(position);
            MainRowVM vm = new MainRowVM(mContext, data, findfavoriteUser(data.getId()));
            ((RowMainBinding) holder.getmBinding()).setVm(vm);
            holder.getmBinding().getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    evt.onClickItem(data, findfavoriteUser(data.getId()));
                }
            });
        } else if(holder.getmBinding() instanceof LoadMoreBinding){
            holder.getmBinding().getRoot().setVisibility(showLoading ? View.VISIBLE : View.GONE);
        }
    }

    private boolean findfavoriteUser(int id) {
        boolean returnResult = false;
        if(mDataFavorite != null){
            for (Integer integer : mDataFavorite) {
                if(id == integer){
                    returnResult = true;
                    break;
                }
            }
        }
        return returnResult;
    }

    @Override
    public int getItemCount() {
        if(!isFavorite()){
            return mData != null ? mData.size() + 1 : 0;
        }else{
            return mCursor != null ? mCursor.getCount() : 0;
        }
    }

    public Cursor swapCursor(Cursor c) {
        if (mCursor == c) {
            return null;
        }
        Cursor temp = mCursor;
        this.mCursor = c;

        if (c != null) {
            this.notifyDataSetChanged();
        }
        return temp;
    }

    @Override
    public int getItemViewType(int position) {
        if(!isFavorite()){
            return position != mData.size() ? R.layout.row_main : R.layout.load_more;
        }else{
            return R.layout.row_main;
        }
    }

    public void setShowLoading(boolean showLoading) {
        this.showLoading = showLoading;
    }

    public boolean isFavorite() {
        return (MyPrefences.getStateSort(mContext)).equalsIgnoreCase(mContext.getString(R.string.favorite_sort));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding mBinding;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        public ViewDataBinding getmBinding() {
            return mBinding;
        }
    }
}

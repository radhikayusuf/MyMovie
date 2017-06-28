package radhika.yusuf.id.mymovie.ui.main;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import radhika.yusuf.id.mymovie.R;
import radhika.yusuf.id.mymovie.api.apiDao.MainData;
import radhika.yusuf.id.mymovie.databinding.LoadMoreBinding;
import radhika.yusuf.id.mymovie.databinding.RowMainBinding;
import radhika.yusuf.id.mymovie.utils.RecyclerViewClickItem;

/**
 * Created by Radhika Yusuf on 16/06/17.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<MainData> mData;
    private Context mContext;
    private RecyclerViewClickItem evt;
    private boolean showLoading = true;

    public MainAdapter(List<MainData> mData, RecyclerViewClickItem evt) {
        this.mData = mData;
        this.evt = evt;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        ViewDataBinding mBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), viewType, parent, false);
        return new ViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if(holder.getmBinding() instanceof RowMainBinding){
            MainRowVM vm = new MainRowVM(mContext, mData.get(position));
            ((RowMainBinding) holder.getmBinding()).setVm(vm);
            holder.getmBinding().getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    evt.onClickItem(position);
                }
            });
        } else if(holder.getmBinding() instanceof LoadMoreBinding){
            holder.getmBinding().getRoot().setVisibility(showLoading ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() + 1 : 0;
    }

    @Override
    public int getItemViewType(int position) {
        return position != mData.size() ? R.layout.row_main : R.layout.load_more;
    }

    public void setShowLoading(boolean showLoading) {
        this.showLoading = showLoading;
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

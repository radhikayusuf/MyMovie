package radhika.yusuf.id.mymovie.ui.detail_review;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import radhika.yusuf.id.mymovie.R;
import radhika.yusuf.id.mymovie.api.api_dao.ReviewData;
import radhika.yusuf.id.mymovie.databinding.RowReviewBinding;

/**
 * Created by Radhika Yusuf on 13/07/17.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder>{

    private List<ReviewData> mData;

    public ReviewAdapter(List<ReviewData> mData) {
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RowReviewBinding mBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.row_review, parent, false);
        return new ViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getBinding().setVm(new ReviewRowVM(mData.get(position)));
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RowReviewBinding binding;

        public ViewHolder(RowReviewBinding mBinding) {
            super(mBinding.getRoot());
            this.binding = mBinding;
        }

        public RowReviewBinding getBinding() {
            return binding;
        }
    }
}

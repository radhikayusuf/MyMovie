package radhika.yusuf.id.mymovie.ui.detail_video;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import radhika.yusuf.id.mymovie.R;
import radhika.yusuf.id.mymovie.api.api_dao.VideosData;
import radhika.yusuf.id.mymovie.databinding.RowVideoBinding;

/**
 * Created by Radhika Yusuf on 14/07/17.
 */

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.ViewHolder> {

    private List<VideosData> mData;
    private int selectedIndex = 0;
    private VideosInterface callback;
    private Context mContext;

    public VideosAdapter(List<VideosData> mData, VideosInterface item) {
        this.mData = mData;
        this.callback = item;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RowVideoBinding mBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.row_video, parent, false);
        mContext = parent.getContext();
        return new ViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.getBinding().setVm(new VideoRowVM(mData.get(position), position == selectedIndex, mContext));
        holder.getBinding().imagePlayVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onClickItemVideo(mData.get(position));
                selectedIndex = position;
                VideosAdapter.this.notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RowVideoBinding mBinding;

        public ViewHolder(RowVideoBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public RowVideoBinding getBinding() {
            return mBinding;
        }
    }


    public interface VideosInterface{
        void onClickItemVideo(VideosData data);
    }
}

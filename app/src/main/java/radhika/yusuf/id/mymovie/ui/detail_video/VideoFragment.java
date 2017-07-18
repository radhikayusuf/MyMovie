package radhika.yusuf.id.mymovie.ui.detail_video;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import radhika.yusuf.id.mymovie.R;
import radhika.yusuf.id.mymovie.api.api_dao.MainData;
import radhika.yusuf.id.mymovie.databinding.FragmentVideoBinding;
import radhika.yusuf.id.mymovie.ui.detail.DetailActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment {


    private MainData mData;
    private FragmentVideoBinding mBinding;
    private VideoVM vm;
    private Context videoActivity;

    public VideoFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_video, container, false);
        return mBinding.getRoot();
    }

    public void setData(MainData data) {
        mData = data;
        vm = new VideoVM(getContext(), data, videoActivity);
        mBinding.setVm(vm);

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.videoActivity = context;

    }
}

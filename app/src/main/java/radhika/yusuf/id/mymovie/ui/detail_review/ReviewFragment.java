package radhika.yusuf.id.mymovie.ui.detail_review;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import radhika.yusuf.id.mymovie.R;
import radhika.yusuf.id.mymovie.api.api_dao.MainData;
import radhika.yusuf.id.mymovie.databinding.FragmentReviewBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewFragment extends Fragment {


    private MainData mData;
    private FragmentReviewBinding mBinding;

    public ReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_review, container, false);
        return mBinding.getRoot();
    }

    public void setData(MainData data) {
        mData = data;
        mBinding.setVm(new ReviewVM(getContext(), data));
    }
}

package radhika.yusuf.id.mymovie.ui.detail_info;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import radhika.yusuf.id.mymovie.R;
import radhika.yusuf.id.mymovie.api.api_dao.MainData;
import radhika.yusuf.id.mymovie.databinding.FragmentInfoBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {

    private FragmentInfoBinding mBinding;

    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_info, container, false);
        return mBinding.getRoot();
    }

    public void setData(MainData data, boolean favorite) {
        mBinding.setVm(new InfoVM(data, getContext(), favorite));
    }
}

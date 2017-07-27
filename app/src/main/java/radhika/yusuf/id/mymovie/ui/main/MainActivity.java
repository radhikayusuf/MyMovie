package radhika.yusuf.id.mymovie.ui.main;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import radhika.yusuf.id.mymovie.R;
import radhika.yusuf.id.mymovie.api.api_dao.MainData;
import radhika.yusuf.id.mymovie.databinding.ActivityMainBinding;
import radhika.yusuf.id.mymovie.utils.MyDialog;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class MainActivity extends AppCompatActivity implements
        SharedPreferences.OnSharedPreferenceChangeListener,
        MainVM.MainVMInterface{

    private ActivityMainBinding mBinding;
    private MainVM vm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        vm = new MainVM(this, savedInstanceState != null, getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE, getSupportLoaderManager());
        mBinding.refreshLayout.setRefreshing(savedInstanceState == null);
        mBinding.setVm(vm);
        setSupportActionBar(mBinding.toolbar);
        mBinding.fab.animate()
                .translationY(300)
                .setDuration(0);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(getString(R.string.savedinstance_recyclerview_data), new ArrayList<Parcelable>(vm.getData()));
        outState.putBoolean(getString(R.string.savedinstance_req), vm.statusReq.get());
        int state = ((GridLayoutManager)vm.layoutManager).findFirstVisibleItemPosition();
        Log.wtf("onSaveInstanceState: ", String.valueOf(state));
        outState.putInt(getString(R.string.savedinstance_recyclerview_state), state);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        List<MainData> mData = savedInstanceState.getParcelableArrayList(getString(R.string.savedinstance_recyclerview_data));
        vm.statusReq.set(savedInstanceState.getBoolean(getString(R.string.savedinstance_req)));
        vm.setData(mData);
        scrollRecycler(savedInstanceState.getInt(getString(R.string.savedinstance_recyclerview_state)));
    }

    private void scrollRecycler(final int i) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {
                    mBinding.rcMain.scrollToPosition(i+2);
                }else{
                    mBinding.rcMain.scrollToPosition(i);
                }

            }
        }, 200);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort:
                MyDialog.showSortDialog(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        SharedPreferences sp = getSharedPreferences(getString(R.string.prefences_sort), MODE_PRIVATE);
        sp.unregisterOnSharedPreferenceChangeListener(this);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        SharedPreferences sp = getSharedPreferences(getString(R.string.prefences_sort), MODE_PRIVATE);
        sp.registerOnSharedPreferenceChangeListener(this);
        vm.restartLoader();
        super.onResume();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        mBinding.refreshLayout.setRefreshing(true);
        vm.sort(sharedPreferences.getString(s, getString(R.string.populart_sort)));
    }

    @Override
    public void onDoneLoad() {
        mBinding.refreshLayout.setRefreshing(false);
    }

    @Override
    public void onScroll(boolean scroll) {
        if (!scroll){
            mBinding.fab.animate()
                    .translationY(0)
                    .setDuration(300);
        }else{
            mBinding.fab.animate()
                    .translationY(300)
                    .setDuration(300);
        }
    }

    public void smoothScroll(View view) {
        mBinding.rcMain.smoothScrollToPosition(0);
    }
}

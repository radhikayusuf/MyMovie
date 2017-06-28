package radhika.yusuf.id.mymovie.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import jp.wasabeef.blurry.Blurry;
import radhika.yusuf.id.mymovie.R;
import radhika.yusuf.id.mymovie.api.apiDao.MainData;
import radhika.yusuf.id.mymovie.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding mBinding;
    private DetailVM vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        MainData data = getIntent().getParcelableExtra(getString(R.string.savedinstance_recyclerview_data));
        vm = new DetailVM(this, data);
        mBinding.setVm(vm);
        setUpToolbar();
    }

    private void setUpToolbar() {
        setSupportActionBar(mBinding.toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        upArrow.setColorFilter(getResources().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void startThisActivity(Context context, MainData data){
        Intent i = new Intent(context, DetailActivity.class);
        i.putExtra(context.getString(R.string.savedinstance_recyclerview_data), data);
        context.startActivity(i);
    }


}

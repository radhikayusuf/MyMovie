package radhika.yusuf.id.mymovie.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import radhika.yusuf.id.mymovie.R;
import radhika.yusuf.id.mymovie.api.api_dao.MainData;
import radhika.yusuf.id.mymovie.api.api_dao.VideosData;
import radhika.yusuf.id.mymovie.databinding.ActivityDetailV2Binding;
import radhika.yusuf.id.mymovie.ui.detail_info.InfoFragment;
import radhika.yusuf.id.mymovie.ui.detail_review.ReviewFragment;
import radhika.yusuf.id.mymovie.ui.detail_video.VideoFragment;
import radhika.yusuf.id.mymovie.ui.detail_video.VideoVM;
import radhika.yusuf.id.mymovie.ui.detail_video.VideosAdapter;
import radhika.yusuf.id.mymovie.utils.Constant;
import radhika.yusuf.id.mymovie.utils.RecyclerViewClickItem;

public class DetailActivity extends AppCompatActivity implements
        YouTubePlayer.OnInitializedListener,
        VideoVM.MainVMInterface,
        VideosAdapter.VideosInterface {

    private static final int RECOVERY_DIALOG_REQUEST = 101;

    private final String TAG = DetailActivity.class.getSimpleName();

    private ActivityDetailV2Binding mBinding;
    private DetailVM vm;
    private InfoFragment infoFragment;
    private ReviewFragment reviewFragment;
    private VideoFragment videoFragment;
    private Fragment fragment;
    private String selectedVideo;
    private MainData MainData;
    private YouTubePlayer mPlayer = null;
    private MenuInflater menuInflater;
    private VideosData mData;
    private MainData mMainData;
    private Menu menu;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_info:
                    mBinding.appBar.setExpanded(true, true);
                    fragment = infoFragment;
                    mBinding.packYoutube.setVisibility(View.GONE);
                    mBinding.toolbar.getMenu().clear();
                    changeFragment();
                    return true;
                case R.id.navigation_review:
                    mBinding.appBar.setExpanded(true, true);
                    fragment = reviewFragment;
                    mBinding.packYoutube.setVisibility(View.GONE);
                    mBinding.toolbar.getMenu().clear();
                    changeFragment();
                    return true;
                case R.id.navigation_video:
                    mBinding.appBar.setExpanded(false, true);
                    fragment = videoFragment;
                    mBinding.packYoutube.setVisibility(View.VISIBLE);
                    menuInflater.inflate(R.menu.menu_detail, menu);
                    changeFragment();
                    return true;
            }
            return false;
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_v2);
        mMainData = getIntent().getParcelableExtra(getString(R.string.savedinstance_recyclerview_data));
        vm = new DetailVM(this, mMainData, getIntent().getBooleanExtra(getString(R.string.prefences_favorite_key), false));
        mBinding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mBinding.setVm(vm);
        setUpToolbar(mMainData.getTitle());
        initFragment();
//        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
//            @Override
//            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//                youTubePlayer.loadVideo(selectedVideo);
//            }
//
//            @Override
//            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//
//            }
//        };
    }

    private void initPlayer() {
        YouTubePlayerSupportFragment frag =
                (YouTubePlayerSupportFragment) getSupportFragmentManager().findFragmentById(R.id.youtube_fragment);
        if (frag != null) {
            frag.onDestroy();
            frag.initialize(Constant.KEY_SERVICE, this);
        }
    }

    private void initFragment() {
        infoFragment = (InfoFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_info);
        infoFragment.setData(mMainData, getIntent().getBooleanExtra(getString(R.string.prefences_favorite_key), false));
        reviewFragment = (ReviewFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_review);
        reviewFragment.setData(mMainData);
        videoFragment = (VideoFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_video);
        videoFragment.setData(mMainData);
        fragment = infoFragment;
        changeFragment();
    }

    private void setUpToolbar(String title) {
        setSupportActionBar(mBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(title);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menuInflater = getMenuInflater();
        this.menu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.menu_open_youtube:
                if(selectedVideo != null){
                    openOnYoutube();
                }else{
                    Toast.makeText(this, R.string.please_wait, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.menu_share:
                if(mData != null){
                    shareMovie(mData);
                }else{
                    Toast.makeText(this, R.string.please_wait, Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openOnYoutube() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.intent_share_url_data_url, selectedVideo))));
    }

    private void shareMovie(VideosData data) {
        Intent i = new Intent();
        i.setAction(Intent.ACTION_SEND);
        i.putExtra(Intent.EXTRA_TEXT, getString(R.string.intent_share_url_data_name, this.mMainData.getTitle())
                + getString(R.string.intent_share_url_data_type, data.getType())
                + getString(R.string.intent_share_url_data_url, data.getKey()));
        i.setType("text/plain");
        startActivity(Intent.createChooser(i, getString(R.string.intent_chooser_title)));
    }

    public static void startThisActivity(Context context, MainData data, boolean isFavorite) {
        Intent i = new Intent(context, DetailActivity.class);
        i.putExtra(context.getString(R.string.savedinstance_recyclerview_data), data);
        i.putExtra(context.getString(R.string.prefences_favorite_key), isFavorite);
        context.startActivity(i);
    }

    private void changeFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(infoFragment);
        transaction.hide(reviewFragment);
        transaction.hide(videoFragment);
        transaction.show(fragment);
        transaction.commit();
    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b && selectedVideo != null) {
            youTubePlayer.cueVideo(selectedVideo);
        }
        mPlayer = youTubePlayer;
        mPlayer.setFullscreen(false);
        mPlayer.setShowFullscreenButton(false);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            Toast.makeText(this, R.string.error_player, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDoneLoadData(boolean success, List<VideosData> data) {
        if (success) {
            initPlayer();
            mData = data.get(0);
            selectedVideo = data.size() > 0 ? data.get(0).getKey() : null;
        }
    }


    @Override
    public void onClickItemVideo(VideosData data) {
        this.mData = data;
        selectedVideo = data.getKey();
        initPlayer();
    }
}

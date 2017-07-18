package radhika.yusuf.id.mymovie.ui.detail_review;

import android.databinding.ObservableField;

import radhika.yusuf.id.mymovie.api.api_dao.ReviewData;

/**
 * Created by Radhika Yusuf on 13/07/17.
 */

public class ReviewRowVM {

    public ObservableField<String> bAuth = new ObservableField<>("");
    public ObservableField<String> bContent = new ObservableField<>("");

    public ReviewRowVM(ReviewData reviewData) {
        bAuth.set(reviewData.getAuthor());
        bContent.set(reviewData.getContent());
    }

}

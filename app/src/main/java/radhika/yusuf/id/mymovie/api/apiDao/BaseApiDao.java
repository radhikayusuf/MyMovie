package radhika.yusuf.id.mymovie.api.apiDao;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Radhika Yusuf on 16/06/17.
 */

public class BaseApiDao<T> implements Parcelable {

    private int page, total_results, total_pages;
    private T results;

    protected BaseApiDao(Parcel in) {
        page = in.readInt();
        total_results = in.readInt();
        total_pages = in.readInt();
    }

    public static final Creator<BaseApiDao> CREATOR = new Creator<BaseApiDao>() {
        @Override
        public BaseApiDao createFromParcel(Parcel in) {
            return new BaseApiDao(in);
        }

        @Override
        public BaseApiDao[] newArray(int size) {
            return new BaseApiDao[size];
        }
    };

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(page);
        parcel.writeInt(total_results);
        parcel.writeInt(total_pages);
    }
}

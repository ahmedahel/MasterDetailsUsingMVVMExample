package com.example.masterdetailsmvvm.model;


import android.util.Log;
import androidx.databinding.BaseObservable;
import androidx.lifecycle.MutableLiveData;
import com.example.masterdetailsmvvm.network.ApiClient;
import com.example.masterdetailsmvvm.network.ApiInterface;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MostViewed extends BaseObservable {
    private String status;
    private String copyright;
    private int num_results;
    private List<Results> results;

    private MutableLiveData<List<Results>> mMostViewed = new MutableLiveData<>();

    public MutableLiveData<List<Results>> getMostViewed() {
        return mMostViewed;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public int getNum_results() {
        return num_results;
    }

    public void setNum_results(int num_results) {
        this.num_results = num_results;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public static class Results extends BaseObservable{


        private String url;
        private String adx_keywords;
        private Object column;
        private String section;
        private String byline;
        private String type;
        private String title;
        @SerializedName("abstract")
        private String abstractX;
        private String published_date;
        private String source;
        private long id;
        private long asset_id;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAdx_keywords() {
            return adx_keywords;
        }

        public void setAdx_keywords(String adx_keywords) {
            this.adx_keywords = adx_keywords;
        }

        public Object getColumn() {
            return column;
        }

        public void setColumn(Object column) {
            this.column = column;
        }

        public String getSection() {
            return section;
        }

        public void setSection(String section) {
            this.section = section;
        }

        public String getByline() {
            return byline;
        }

        public void setByline(String byline) {
            this.byline = byline;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAbstractX() {
            return abstractX;
        }

        public void setAbstractX(String abstractX) {
            this.abstractX = abstractX;
        }

        public String getPublished_date() {
            return published_date;
        }

        public void setPublished_date(String published_date) {
            this.published_date = published_date;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getAsset_id() {
            return asset_id;
        }

        public void setAsset_id(long asset_id) {
            this.asset_id = asset_id;
        }
    }





    public void fetchList() {
        Callback<MostViewed> callback = new Callback<MostViewed>() {
            @Override
            public void onResponse(Call<MostViewed> call, Response<MostViewed> res) {
                MostViewed body = res.body();
                if (res.code() == 200) {
                    mMostViewed.setValue(body.getResults());
                }

            }

            @Override
            public void onFailure(Call<MostViewed> call, Throwable t) {
                Log.e("Test", t.getMessage(), t);
            }
        };

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        apiService.getMostViewed("all-sections", "7").enqueue(callback);

    }
}

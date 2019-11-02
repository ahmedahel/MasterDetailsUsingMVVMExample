package com.example.masterdetailsmvvm.viewmodel;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.masterdetailsmvvm.adapter.SimpleItemRecyclerViewAdapter;
import com.example.masterdetailsmvvm.model.MostViewed;

import java.util.List;

public class MostViewedViewModel extends ViewModel {


    private MostViewed mMostViewed;
    private SimpleItemRecyclerViewAdapter adapter;
    private MutableLiveData<MostViewed.Results> selected;
    public ObservableInt loading;
    public ObservableInt showEmpty;

    public void init() {
        mMostViewed = new MostViewed();
        selected = new MutableLiveData<>();
        adapter = new SimpleItemRecyclerViewAdapter(this);
        loading = new ObservableInt(View.GONE);
        showEmpty = new ObservableInt(View.GONE);
    }

    public void fetchList() {
        mMostViewed.fetchList();
    }

    public MutableLiveData<List<MostViewed.Results>> getMostViewed() {
        return mMostViewed.getMostViewed();
    }

    public SimpleItemRecyclerViewAdapter getAdapter() {
        return adapter;
    }

    public void setMostViewedsInAdapter(List<MostViewed.Results> mostViewed) {
        this.adapter.setMostVieweds(mostViewed);
        this.adapter.notifyDataSetChanged();
    }

    public MutableLiveData<MostViewed.Results> getSelected() {
        return selected;
    }

    public void onItemClick(Integer index) {
        MostViewed.Results db = getMostViewedAt(index);
        selected.setValue(db);
    }


    public MostViewed.Results getMostViewedAt(Integer index) {
        if (mMostViewed.getMostViewed().getValue() != null && index != null && mMostViewed.getMostViewed().getValue().size() > index) {
            return mMostViewed.getMostViewed().getValue().get(index);
        }
        return null;
    }

}

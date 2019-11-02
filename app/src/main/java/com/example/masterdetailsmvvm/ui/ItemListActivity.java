package com.example.masterdetailsmvvm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.example.masterdetailsmvvm.R;
import com.example.masterdetailsmvvm.databinding.ActivityItemListBinding;
import com.example.masterdetailsmvvm.model.MostViewed;
import com.example.masterdetailsmvvm.viewmodel.MostViewedViewModel;
import java.util.List;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private MostViewedViewModel mMostViewedViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        setupBindings(savedInstanceState);
    }


    private void setupBindings(Bundle savedInstanceState) {
        ActivityItemListBinding activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_item_list);
        mMostViewedViewModel = ViewModelProviders.of(this).get(MostViewedViewModel.class);
        if (savedInstanceState == null) {
            mMostViewedViewModel.init();
        }
        activityBinding.setModel(mMostViewedViewModel);
        setupListUpdate();

    }


    private void setupListUpdate() {
        mMostViewedViewModel.loading.set(View.VISIBLE);
        mMostViewedViewModel.fetchList();
        mMostViewedViewModel.getMostViewed().observe(this, new Observer<List<MostViewed.Results>>() {
            @Override
            public void onChanged(List<MostViewed.Results> mostViewedList) {
                mMostViewedViewModel.loading.set(View.GONE);
                if (mostViewedList.size() == 0) {
                    mMostViewedViewModel.showEmpty.set(View.VISIBLE);
                } else {
                    mMostViewedViewModel.showEmpty.set(View.GONE);
                    mMostViewedViewModel.setMostViewedsInAdapter(mostViewedList);
                }
            }
        });
        setupListClick();
    }

    private void setupListClick() {
        mMostViewedViewModel.getSelected().observe(this, new Observer<MostViewed.Results>() {
            @Override
            public void onChanged(MostViewed.Results mostViewed) {
                if (mostViewed != null) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(ItemDetailFragment.ARG_ITEM_TITLE, mostViewed.getTitle());
                        arguments.putString(ItemDetailFragment.ARG_ITEM_ABSTRACT, mostViewed.getAbstractX());
                        ItemDetailFragment fragment = new ItemDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.item_detail_container, fragment)
                                .commit();
                    } else {
                         Intent intent = new Intent(getApplicationContext(), ItemDetailActivity.class);
                        intent.putExtra(ItemDetailFragment.ARG_ITEM_TITLE, mostViewed.getTitle());
                        intent.putExtra(ItemDetailFragment.ARG_ITEM_ABSTRACT, mostViewed.getAbstractX());
                         startActivity(intent);
                    }
                }
            }
        });
    }




}

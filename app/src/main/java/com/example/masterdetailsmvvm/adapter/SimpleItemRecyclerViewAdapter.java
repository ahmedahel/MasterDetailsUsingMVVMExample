package com.example.masterdetailsmvvm.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.masterdetailsmvvm.BR;
import com.example.masterdetailsmvvm.R;
import com.example.masterdetailsmvvm.model.MostViewed;
import com.example.masterdetailsmvvm.viewmodel.MostViewedViewModel;

import java.util.List;

public class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.GenericViewHolder> {

     private List<MostViewed.Results> mMostViewed;
    private MostViewedViewModel viewModel;

    public SimpleItemRecyclerViewAdapter( MostViewedViewModel viewModel) {
         this.viewModel = viewModel;
     }

    @Override
    public int getItemCount() {
        return mMostViewed == null ? 0 : mMostViewed.size();
    }

    public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_list_content, parent, false);
        return new GenericViewHolder(binding);
    }


    public void setMostVieweds(List<MostViewed.Results> mMostViewed) {
        this.mMostViewed = mMostViewed;
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder holder, int position) {
        holder.bind(viewModel, position);
    }


    class GenericViewHolder extends RecyclerView.ViewHolder {
        final ViewDataBinding binding;
        GenericViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(MostViewedViewModel viewModel, Integer position) {
            System.out.println("set binding");
            binding.setVariable(BR.viewModel, viewModel);
            binding.setVariable(BR.position, position);
            binding.executePendingBindings();
        }
    }




}
package com.hugebelieve.genericrecyclerview.Gists;

import android.databinding.DataBindingUtil;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.databinding.ViewDataBinding;

public class GenericRecyclerViewAdapter extends RecyclerView.Adapter {
    private RecyclerViewBinder customBindingWithAllFunctions;
    private ViewDataBinding recyclerViewBinding;
    @LayoutRes private int recyclerViewLayout;

    public GenericRecyclerViewAdapter(@LayoutRes int recyclerViewLayout, RecyclerViewBinder functionBinder) {
        this.customBindingWithAllFunctions = functionBinder;
        this.recyclerViewLayout = recyclerViewLayout;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the recyclerView layout here
        recyclerViewBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                recyclerViewLayout, parent, false);
        return new CustomViewHolder(recyclerViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        //After recyclerView layout has been successfully binded
        //Give callback with binded view
        this.customBindingWithAllFunctions.AfterBindingCompete(recyclerViewBinding, holder);
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder){
        super.onViewAttachedToWindow(holder);
        ((CustomViewHolder) holder).bind();
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder){
        super.onViewDetachedFromWindow(holder);
        ((CustomViewHolder) holder).unbind();
    }

    @Override
    public int getItemCount() {
        return this.customBindingWithAllFunctions.ReturnDataSetSizeForAdapter();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    
    //Generic view holder
    private class CustomViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;
        private CustomViewHolder(final ViewDataBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }

        private void bind() {
            if(binding!=null){
                binding = DataBindingUtil.bind(itemView);
            }
        }
        private void unbind(){
            if(binding!=null){
                binding.unbind();
            }
        }
    }
    
    //Interface for generic recyclerView adapter
    public interface RecyclerViewBinder {
        int ReturnDataSetSizeForAdapter();
        void AfterBindingCompete(@NonNull ViewDataBinding bindingToUse, @NonNull RecyclerView.ViewHolder holder);
    }
}

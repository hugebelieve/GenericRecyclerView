package com.hugebelieve.genericrecyclerview;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import com.hugebelieve.genericrecyclerview.Gists.GenericRecyclerViewAdapter;
import com.hugebelieve.genericrecyclerview.databinding.HomeRecyclerviewBinding;
import java.util.ArrayList;
import java.util.List;

public class VerticalViewActivity extends AppCompatActivity {

    List<Pair<String,String>> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recyclerViewInIt();
    }

    public void addRecyclerviewData(List<Pair<String,String>> data){

    }
    public void recyclerViewInIt(){
        data = new ArrayList<>();
        addRecyclerviewData(data);
        RecyclerView recyclerView = findViewById(R.id.main_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));

        GenericRecyclerViewAdapter genericAdapter;
        genericAdapter = new GenericRecyclerViewAdapter(R.layout.home_recyclerview,
                new GenericRecyclerViewAdapter.RecyclerViewBinder() {
                    @Override
                    public int ReturnDataSetSizeForAdapter() {
                        return data.size();
                    }
                    @Override
                    public void AfterBindingCompete(@NonNull ViewDataBinding bindingToUse, @NonNull RecyclerView.ViewHolder holder) {
                        HomeRecyclerviewBinding homeRecyclerviewBinding = (HomeRecyclerviewBinding) bindingToUse;
                        int holderPosition = holder.getAdapterPosition();
                        initialiseEachRoeItem(homeRecyclerviewBinding, holderPosition);
                    }
                });
        recyclerView.setAdapter(genericAdapter);
    }

    public void initialiseEachRoeItem(HomeRecyclerviewBinding homeRecyclerviewBinding, int holderPosition){

    }
}

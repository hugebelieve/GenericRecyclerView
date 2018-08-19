package com.hugebelieve.genericrecyclerview;

import android.app.Activity;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hugebelieve.genericrecyclerview.Gists.GenericRecyclerViewAdapter;
import com.hugebelieve.genericrecyclerview.databinding.HorizontalLayoutBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HorizontalViewActivity extends Activity {

    List<ClassModel> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        data = new ArrayList<>();
        FillTempMovieData.fillData(data);

        recyclerViewInIt();
    }

    public void recyclerViewInIt(){
        RecyclerView recyclerView = findViewById(R.id.main_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));

        GenericRecyclerViewAdapter genericAdapter;
        genericAdapter = new GenericRecyclerViewAdapter();
        genericAdapter.setChildLayout(R.layout.horizontal_layout);
        genericAdapter.setDataSizeInterface(new GenericRecyclerViewAdapter.DataSizeInterface() {
            @Override
            public int ReturnDataSetSizeForAdapter() {
                return data.size();
            }
        });
        genericAdapter.setRecyclerViewBinding(new GenericRecyclerViewAdapter.RecyclerViewBinder() {
            @Override
            public void AfterBindingCompete(@NonNull ViewDataBinding bindingToUse, @NonNull RecyclerView.ViewHolder holder) {
                HorizontalLayoutBinding recyclerLayoutBinding = (HorizontalLayoutBinding) bindingToUse;
                int holderPosition = holder.getAdapterPosition();
                initialiseEachRowItem(recyclerLayoutBinding, holderPosition);
            }
        });
        recyclerView.setAdapter(genericAdapter);
    }

    public void initialiseEachRowItem(HorizontalLayoutBinding recyclerLayoutBinding, int holderPosition){
        ClassModel dataForCurrentRow = data.get(holderPosition);
        Picasso.get().load(dataForCurrentRow.getPhoto()).fit().centerCrop().into(recyclerLayoutBinding.photo);
        recyclerLayoutBinding.title.setText(dataForCurrentRow.getTitle());
    }
}

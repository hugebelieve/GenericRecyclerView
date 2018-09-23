package com.hugebelieve.genericrecyclerview;

import android.app.Activity;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.hugebelieve.library.genericrecyclerviewadapter.GenericRecyclerViewAdapter;
import com.hugebelieve.genericrecyclerview.databinding.GridLayoutBinding;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class GridViewActivity extends Activity {

    List<ClassModel> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        ((TextView)findViewById(R.id.mainTitle)).setText("Grid Recyclerview");


        data = new ArrayList<>();
        FillTempMovieData.fillData(data);

        recyclerViewInIt();
    }

    public void recyclerViewInIt(){
        RecyclerView recyclerView = findViewById(R.id.main_recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        GenericRecyclerViewAdapter genericAdapter;
        genericAdapter = new GenericRecyclerViewAdapter();
        genericAdapter.setChildLayout(R.layout.grid_layout);
        genericAdapter.setDataSizeInterface(new GenericRecyclerViewAdapter.DataSizeInterface() {
            @Override
            public int ReturnDataSetSizeForAdapter() {
                return data.size();
            }
        });
        genericAdapter.setRecyclerViewBinding(new GenericRecyclerViewAdapter.RecyclerViewBinder() {
            @Override
            public void AfterBindingCompete(@NonNull ViewDataBinding bindingToUse, @NonNull RecyclerView.ViewHolder holder) {
                GridLayoutBinding recyclerLayoutBinding = (GridLayoutBinding) bindingToUse;
                int holderPosition = holder.getAdapterPosition();
                initialiseEachRowItem(recyclerLayoutBinding, holderPosition);
            }
        });
        recyclerView.setAdapter(genericAdapter);
    }

    public void initialiseEachRowItem(GridLayoutBinding recyclerLayoutBinding, int holderPosition){
        final ClassModel dataForCurrentRow = data.get(holderPosition);
        Picasso.get().load(dataForCurrentRow.getPhoto()).fit().centerCrop().into(recyclerLayoutBinding.sectionImage);
        recyclerLayoutBinding.sectionTitle.setText(dataForCurrentRow.getTitle());
        recyclerLayoutBinding.ratingText.setText(dataForCurrentRow.getRating());

        recyclerLayoutBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, dataForCurrentRow.getTitle(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}

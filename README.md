# GenericRecyclerView
An android repository containing gists for adapter of a generic recyclerview, this adapter can be used to inflate any recyclerview with any layout in just 10 lines(ten lines).

Include the following line of code in your app gradle file
## implementation 'com.hugebelieve.library:Generic-RecyclerView-Adapter:1.0.1'

For tutorial on how to use this library view the following video
[Android RecyclerView initialization in just 10 lines!!!](https://www.youtube.com/watch?v=FCnnbv3p26g&t)

Checkout the following Demonstration<br/>
![](showcase.gif)

Here is an example code for Vertical RecyclerView shown in the image</br>
```
public void recyclerViewInIt(){
        RecyclerView recyclerView = findViewById(R.id.main_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        GenericRecyclerViewAdapter genericAdapter;
        genericAdapter = new GenericRecyclerViewAdapter();
        genericAdapter.setChildLayout(R.layout.vertical_layout);
        genericAdapter.setDataSizeInterface(new GenericRecyclerViewAdapter.DataSizeInterface() {
            @Override
            public int ReturnDataSetSizeForAdapter() {
                return data.size();
            }
        });
        genericAdapter.setRecyclerViewBinding(new GenericRecyclerViewAdapter.RecyclerViewBinder() {
            @Override
            public void AfterBindingCompete(@NonNull ViewDataBinding bindingToUse, @NonNull RecyclerView.ViewHolder holder) {
                VerticalLayoutBinding recyclerLayoutBinding = (VerticalLayoutBinding) bindingToUse;
                int holderPosition = holder.getAdapterPosition();
                initialiseEachRowItem(recyclerLayoutBinding, holderPosition);
            }
        });
        recyclerView.setAdapter(genericAdapter);
    }
```

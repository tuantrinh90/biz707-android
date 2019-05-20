package bg.player.com.playerbackground.module;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

import java8.util.stream.StreamSupport;

public class BaseRecycleAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected List<T> listItems = new ArrayList<>();

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listItems == null ? 0 : listItems.size();
    }

    public void setDataList(List<T> items) {
        if (this.listItems != null)
            listItems.clear();
        addDataList(items);
    }

    private void addDataList(List<T> items) {
        if (this.listItems != null && items != null) {
            this.listItems.addAll(items);
            StreamSupport.stream(listItems).distinct();
            notifyDataSetChanged();
        }
    }

    private void addItem(T item) {
        if (this.listItems != null && item != null) {
            this.listItems.add(item);
            notifyItemChanged(listItems.size() - 1);
        }

    }

    public void clearData() {
        if (this.listItems != null)
            listItems.clear();
    }

    public List<T> getListItems() {
        return listItems;
    }
}

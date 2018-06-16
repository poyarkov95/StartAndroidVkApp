package startandroid.apoyark.com.startandroidvkapp.common;

import android.support.v4.util.ArrayMap;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import startandroid.apoyark.com.startandroidvkapp.model.view.BaseViewModel;
import startandroid.apoyark.com.startandroidvkapp.ui.holder.BaseViewHolder;

/**
 * Created by User on 13.05.2018.
 */

public class BaseAdapter extends RecyclerView.Adapter<BaseViewHolder<BaseViewModel>>{

    private List<BaseViewModel> list = new ArrayList<>();

    private ArrayMap<Integer, BaseViewModel> mTypeInstances = new ArrayMap<>();

    @Override
    public BaseViewHolder<BaseViewModel> onCreateViewHolder(ViewGroup parent, int viewType) {
        return mTypeInstances.get(viewType).createViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<BaseViewModel> holder, int position) {
        holder.bindViewHolder(getItem(position));
    }

    @Override
    public void onViewRecycled(BaseViewHolder<BaseViewModel> holder) {
        super.onViewRecycled(holder);
        holder.unbindViewHolder();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void registerTypeInstance(BaseViewModel item){
        if(!mTypeInstances.containsKey(item.getType())){
            mTypeInstances.put(item.getType().getValue(), item);
        }
    }

    public void setItems(List<BaseViewModel> items){
        clearList();
        addItems(items);
    }

    public void addItems(List<? extends BaseViewModel> newItems){
        for(BaseViewModel newItem : newItems){
            registerTypeInstance(newItem);
        }

        list.addAll(newItems);

        notifyDataSetChanged();
    }

    public void clearList(){
        list.clear();
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getType().getValue();
    }

    public BaseViewModel getItem(int position){
        return list.get(position);
    }
}
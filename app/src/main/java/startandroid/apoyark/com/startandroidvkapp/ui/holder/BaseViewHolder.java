package startandroid.apoyark.com.startandroidvkapp.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import startandroid.apoyark.com.startandroidvkapp.model.view.BaseViewModel;

/**
 * Created by User on 13.05.2018.
 */

public abstract class BaseViewHolder<Item extends BaseViewModel> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindViewHolder(Item item);

    public abstract  void unbindViewHolder();

}

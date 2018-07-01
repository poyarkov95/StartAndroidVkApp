package startandroid.apoyark.com.startandroidvkapp.model.view;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import startandroid.apoyark.com.startandroidvkapp.R;
import startandroid.apoyark.com.startandroidvkapp.ui.holder.BaseViewHolder;



/**
 * Created by User on 13.05.2018.
 */

public abstract class BaseViewModel {

    public abstract LayoutTypes getType();

    public BaseViewHolder createViewHolder(ViewGroup parent){
        return onCreateViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(getType().getValue(), parent, false));
    }

    protected abstract BaseViewHolder onCreateViewHolder(View v);

    public enum LayoutTypes{
        NewsFeedItemHeader(R.layout.item_news_header),
        NewsFeedItemBody(R.layout.item_news_body),
        NewsFeedItemFooter(R.layout.item_news_footer);

        private final int id;


        LayoutTypes(int id) {
            this.id = id;
        }

        @LayoutRes
        public int getValue() {
            return id;
        }
    }

    public boolean isItemDecorator() {
        return false;
    }
}

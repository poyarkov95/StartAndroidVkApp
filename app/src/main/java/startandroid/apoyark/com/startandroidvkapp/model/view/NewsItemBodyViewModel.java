package startandroid.apoyark.com.startandroidvkapp.model.view;

import android.view.View;

import startandroid.apoyark.com.startandroidvkapp.model.WallItem;
import startandroid.apoyark.com.startandroidvkapp.ui.holder.BaseViewHolder;
import startandroid.apoyark.com.startandroidvkapp.ui.holder.NewsItemBodyHolder;

/**
 * Created by User on 13.05.2018.
 */

public class NewsItemBodyViewModel extends BaseViewModel {
    private int mId;

    private String mText;

    public NewsItemBodyViewModel(WallItem wallItem) {
        this.mId = wallItem.getId();
        this.mText = wallItem.getText();
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.NewsFeedItemBody;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View v) {
        return new NewsItemBodyHolder(v);
    }

    public int getmId() {
        return mId;
    }

    public String getmText() {
        return mText;
    }
}

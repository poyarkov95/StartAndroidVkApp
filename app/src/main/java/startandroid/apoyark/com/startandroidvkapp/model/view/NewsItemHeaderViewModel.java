package startandroid.apoyark.com.startandroidvkapp.model.view;

import android.view.View;

import startandroid.apoyark.com.startandroidvkapp.model.WallItem;
import startandroid.apoyark.com.startandroidvkapp.ui.holder.BaseViewHolder;
import startandroid.apoyark.com.startandroidvkapp.ui.holder.NewsItemHeaderHolder;

/**
 * Created by User on 13.05.2018.
 */

public class NewsItemHeaderViewModel extends BaseViewModel {
    private int mId;

    private String mProfilePhoto;
    private String mProfileName;

    private boolean mIsRepost;
    private String mRepostProfileName;

    public NewsItemHeaderViewModel(WallItem wallItem) {
        this.mId = wallItem.getId();
        this.mProfilePhoto = wallItem.getSenderPhoto();
        this.mProfileName = wallItem.getSenderName();
        this.mIsRepost = wallItem.haveSharedRepost();

        if(mIsRepost){
            this.mRepostProfileName = wallItem.getSharedRepost().getSenderName();
        }
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.NewsFeedItemHeader;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View v) {
        return new NewsItemHeaderHolder(v);
    }

    public int getmId() {
        return mId;
    }

    public String getmProfilePhoto() {
        return mProfilePhoto;
    }

    public String getmProfileName() {
        return mProfileName;
    }

    public boolean ismIsRepost() {
        return mIsRepost;
    }

    public String getmRepostProfileName() {
        return mRepostProfileName;
    }
}

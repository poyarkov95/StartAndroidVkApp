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

    private String mAttachmentString;

    private boolean mIsRepost;

    public NewsItemBodyViewModel(WallItem wallItem) {
        this.mId = wallItem.getId();
        this.mIsRepost = wallItem.haveSharedRepost();

        if(mIsRepost){
            mText = wallItem.getSharedRepost().getText();
            mAttachmentString = wallItem.getSharedRepost().getAttachmentsString();
        } else {
            mText = wallItem.getText();
            mAttachmentString = wallItem.getAttachmentsString();
        }
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.NewsFeedItemBody;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View v) {
        return new NewsItemBodyHolder(v);
    }

    public int getId() {
        return mId;
    }

    public String getText() {
        return mText;
    }

    public String getmAttachmentString() {
        return mAttachmentString;
    }
}

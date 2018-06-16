package startandroid.apoyark.com.startandroidvkapp.model.view;

import android.view.View;

import startandroid.apoyark.com.startandroidvkapp.model.WallItem;
import startandroid.apoyark.com.startandroidvkapp.ui.holder.BaseViewHolder;
import startandroid.apoyark.com.startandroidvkapp.ui.holder.NewsItemFooterHolder;

/**
 * Created by User on 07.06.2018.
 */

public class NewsItemFooterViewModel extends BaseViewModel {

    private int mId;
    private int ownerId;
    private long mDateLong;

    private LikeCounterViewModel mLikes;
    private CommentCounterViewModel mComments;
    private RepostCounterViewModel mReposts;

    public NewsItemFooterViewModel(WallItem wallItem) {
        this.mId = wallItem.getId();
        this.ownerId = wallItem.getOwnerId();
        this.mDateLong = wallItem.getDate();
        this.mLikes = new LikeCounterViewModel(wallItem.getLikes());
        this.mComments = new CommentCounterViewModel(wallItem.getComments());
        this.mReposts = new RepostCounterViewModel(wallItem.getReposts());
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public long getmDateLong() {
        return mDateLong;
    }

    public void setmDateLong(long mDateLong) {
        this.mDateLong = mDateLong;
    }

    public LikeCounterViewModel getmLikes() {
        return mLikes;
    }

    public void setmLikes(LikeCounterViewModel mLikes) {
        this.mLikes = mLikes;
    }

    public CommentCounterViewModel getmComments() {
        return mComments;
    }

    public void setmComments(CommentCounterViewModel mComments) {
        this.mComments = mComments;
    }

    public RepostCounterViewModel getmReposts() {
        return mReposts;
    }

    public void setmReposts(RepostCounterViewModel mReposts) {
        this.mReposts = mReposts;
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.NewsFeedItemFooter;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View v) {
        return new NewsItemFooterHolder(v);
    }
}

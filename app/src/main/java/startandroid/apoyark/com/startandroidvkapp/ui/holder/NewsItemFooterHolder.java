package startandroid.apoyark.com.startandroidvkapp.ui.holder;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import startandroid.apoyark.com.startandroidvkapp.MainApplication;
import startandroid.apoyark.com.startandroidvkapp.R;
import startandroid.apoyark.com.startandroidvkapp.common.utils.Utils;
import startandroid.apoyark.com.startandroidvkapp.model.view.CommentCounterViewModel;
import startandroid.apoyark.com.startandroidvkapp.model.view.LikeCounterViewModel;
import startandroid.apoyark.com.startandroidvkapp.model.view.NewsItemFooterViewModel;
import startandroid.apoyark.com.startandroidvkapp.model.view.RepostCounterViewModel;

/**
 * Created by User on 11.06.2018.
 */

public class NewsItemFooterHolder extends BaseViewHolder<NewsItemFooterViewModel> {

    @BindView(R.id.tv_date)
    public TextView textViewDate;

    @BindView(R.id.tv_likes_count)
    public TextView textViewLikesCount;

    @BindView(R.id.tv_likes_icon)
    public TextView textViewLikesIcon;

    @BindView(R.id.tv_comments_icon)
    public TextView textViewCommentsIcon;

    @BindView(R.id.tv_comments_count)
    public TextView textViewCommentsCount;

    @BindView(R.id.tv_reposts_icon)
    public TextView textViewRepostIcon;

    @BindView(R.id.tv_reposts_count)
    public TextView textViewRepostsCount;

    @Inject
    Typeface googleFontTypeFace;

    private Resources resources;
    private Context context;

    public NewsItemFooterHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        MainApplication.getApplicationComponent().inject(this);

        context = itemView.getContext();
        resources = context.getResources();

        textViewLikesIcon.setTypeface(googleFontTypeFace);
        textViewCommentsIcon.setTypeface(googleFontTypeFace);
        textViewRepostIcon.setTypeface(googleFontTypeFace);
    }

    @Override
    public void bindViewHolder(NewsItemFooterViewModel item) {
        textViewDate.setText(Utils.parseDate(item.getmDateLong(), context));

        bindLikes(item.getmLikes());
        bindComments(item.getmComments());
        bindReposts(item.getmReposts());
    }

    private void bindLikes(LikeCounterViewModel likes) {
        textViewLikesCount.setText(String.valueOf(likes.getCount()));
        textViewLikesCount.setTextColor(resources.getColor(likes.getTextColor()));
        textViewLikesIcon.setTextColor(resources.getColor(likes.getIconColor()));
    }

    private void bindComments(CommentCounterViewModel comments) {
        textViewCommentsCount.setText(String.valueOf(comments.getCount()));
        textViewCommentsCount.setTextColor(resources.getColor(comments.getTextColor()));
        textViewCommentsIcon.setTextColor(resources.getColor(comments.getIconColor()));

    }
    private void bindReposts(RepostCounterViewModel reposts) {
        textViewRepostsCount.setText(String.valueOf(reposts.getCount()));
        textViewRepostsCount.setTextColor(resources.getColor(reposts.getTextColor()));
        textViewRepostIcon.setTextColor(resources.getColor(reposts.getIconColor()));

    }

    @Override
    public void unbindViewHolder() {
        textViewDate.setText(null);
        textViewLikesCount.setText(null);
        textViewCommentsCount.setText(null);
        textViewRepostsCount.setText(null);
    }
}

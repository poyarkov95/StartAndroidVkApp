package startandroid.apoyark.com.startandroidvkapp.ui.holder;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

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

    private TextView textViewDate;

    private TextView textViewLikesCount;
    private TextView textViewLikesIcon;
    private TextView textViewCommentsIcon;
    private TextView textViewCommentsCount;
    private TextView textViewRepostIcon;
    private TextView textViewRepostsCount;

    @Inject
    Typeface googleFontTypeFace;

    private Resources resources;
    private Context context;

    public NewsItemFooterHolder(View itemView) {
        super(itemView);
        MainApplication.getApplicationComponent().inject(this);

        context = itemView.getContext();
        resources = context.getResources();

        textViewDate = (TextView) itemView.findViewById(R.id.tv_date);
        textViewLikesIcon = (TextView) itemView.findViewById(R.id.tv_likes_icon);
        textViewLikesCount = (TextView) itemView.findViewById(R.id.tv_likes_count);
        textViewCommentsIcon = (TextView) itemView.findViewById(R.id.tv_comments_icon);
        textViewCommentsCount = (TextView) itemView.findViewById(R.id.tv_comments_count);
        textViewRepostIcon = (TextView) itemView.findViewById(R.id.tv_reposts_icon);
        textViewRepostsCount = (TextView) itemView.findViewById(R.id.tv_reposts_count);

        textViewLikesIcon.setTypeface(googleFontTypeFace);
        textViewCommentsIcon.setTypeface(googleFontTypeFace);
        textViewRepostIcon.setTypeface(googleFontTypeFace);

    }

    @Override
    public void bindViewHolder(NewsItemFooterViewModel item) {
        textViewDate.setText(Utils.parseDate(item.getmDateLong(), context));
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

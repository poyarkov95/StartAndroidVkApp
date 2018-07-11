package startandroid.apoyark.com.startandroidvkapp.ui.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import startandroid.apoyark.com.startandroidvkapp.R;
import startandroid.apoyark.com.startandroidvkapp.model.view.NewsItemHeaderViewModel;

/**
 * Created by User on 13.05.2018.
 */

public class NewsItemHeaderHolder extends BaseViewHolder<NewsItemHeaderViewModel> {

    @BindView(R.id.civ_profile_image)
    public CircleImageView circleProfileImage;

    @BindView(R.id.tv_profile_name)
    public TextView tvName;

    @BindView(R.id.iv_reposted_icon)
    public ImageView ivRepostIcon;

    @BindView(R.id.tv_reposted_profile_name)
    public TextView tvRepostedProfileName;

    public NewsItemHeaderHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(NewsItemHeaderViewModel model) {
        Context context = itemView.getContext();

        Glide.with(context)
                .load(model.getmProfilePhoto())
                .into(circleProfileImage);
        tvName.setText(model.getmProfileName());

        if(model.ismIsRepost()){
            ivRepostIcon.setVisibility(View.VISIBLE);
            tvRepostedProfileName.setText(model.getmRepostProfileName());
        }
    }

    @Override
    public void unbindViewHolder() {
        circleProfileImage.setImageBitmap(null);
        tvName.setText(null);
        tvRepostedProfileName.setText(null);
    }
}

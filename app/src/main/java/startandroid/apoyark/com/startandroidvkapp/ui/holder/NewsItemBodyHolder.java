package startandroid.apoyark.com.startandroidvkapp.ui.holder;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import startandroid.apoyark.com.startandroidvkapp.MainApplication;
import startandroid.apoyark.com.startandroidvkapp.R;
import startandroid.apoyark.com.startandroidvkapp.model.view.NewsItemBodyViewModel;

/**
 * Created by User on 13.05.2018.
 */

public class NewsItemBodyHolder extends BaseViewHolder<NewsItemBodyViewModel> {

    @BindView(R.id.tv_text)
    public TextView textView;

    @BindView(R.id.tv_attachments)
    public TextView tvAttachments;

    @Inject
    Typeface mFontGoogle;

    public NewsItemBodyHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        MainApplication.getApplicationComponent().inject(this);

        if(tvAttachments != null){
            tvAttachments.setTypeface(mFontGoogle);
        }
    }

    @Override
    public void bindViewHolder(NewsItemBodyViewModel item) {
        textView.setText(item.getText());
        tvAttachments.setText(item.getmAttachmentString());
    }

    @Override
    public void unbindViewHolder() {
        textView.setText(null);
        tvAttachments.setText(null);
    }
}

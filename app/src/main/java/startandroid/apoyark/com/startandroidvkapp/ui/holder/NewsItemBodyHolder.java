package startandroid.apoyark.com.startandroidvkapp.ui.holder;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import startandroid.apoyark.com.startandroidvkapp.MainApplication;
import startandroid.apoyark.com.startandroidvkapp.R;
import startandroid.apoyark.com.startandroidvkapp.model.view.NewsItemBodyViewModel;

/**
 * Created by User on 13.05.2018.
 */

public class NewsItemBodyHolder extends BaseViewHolder<NewsItemBodyViewModel> {

    public TextView textView;

    private TextView tvAttachments;

    @Inject
    Typeface mFontGoogle;

    public NewsItemBodyHolder(View itemView) {
        super(itemView);

        MainApplication.getApplicationComponent().inject(this);
        textView = (TextView)itemView.findViewById(R.id.tv_text);
        tvAttachments = (TextView)itemView.findViewById(R.id.tv_attachments);

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

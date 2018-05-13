package startandroid.apoyark.com.startandroidvkapp.ui.holder;

import android.view.View;
import android.widget.TextView;

import startandroid.apoyark.com.startandroidvkapp.R;
import startandroid.apoyark.com.startandroidvkapp.model.view.NewsItemBodyViewModel;

/**
 * Created by User on 13.05.2018.
 */

public class NewsItemBodyHolder extends BaseViewHolder<NewsItemBodyViewModel> {

    public TextView textView;

    public NewsItemBodyHolder(View itemView) {
        super(itemView);

        textView = (TextView)itemView.findViewById(R.id.tv_text);
    }

    @Override
    public void bindViewHolder(NewsItemBodyViewModel item) {
        textView.setText(item.getmText());
    }

    @Override
    public void unbindViewHolder() {
        textView.setText(null);
    }
}

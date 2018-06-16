package startandroid.apoyark.com.startandroidvkapp.model.view;

import startandroid.apoyark.com.startandroidvkapp.model.Comments;

/**
 * Created by User on 07.06.2018.
 */

public class CommentCounterViewModel extends CounterViewModel {
    private Comments comments;

    public CommentCounterViewModel(Comments comments) {
        super(comments.getCount());

        this.comments = comments;
    }

    public Comments getComments() {
        return comments;
    }
}

package startandroid.apoyark.com.startandroidvkapp.model.view;

import startandroid.apoyark.com.startandroidvkapp.model.Likes;

/**
 * Created by User on 07.06.2018.
 */

public class LikeCounterViewModel extends CounterViewModel {
    private Likes likes;

    public LikeCounterViewModel(Likes likes) {
        super(likes.getCount());
        this.likes = likes;

        if(likes.getCount() == 1){
            setAccentColor();
        }
    }

    public Likes getLikes() {
        return likes;
    }
}

package startandroid.apoyark.com.startandroidvkapp.model.view;

import startandroid.apoyark.com.startandroidvkapp.model.Reposts;

/**
 * Created by User on 07.06.2018.
 */

public class RepostCounterViewModel extends CounterViewModel {
    private Reposts reposts;

    public RepostCounterViewModel(Reposts reposts) {
        super(reposts.getCount());

        this.reposts = reposts;
        if(reposts.getCount() == 1){
            setAccentColor();
        }
    }

    public Reposts getReposts() {
        return reposts;
    }
}

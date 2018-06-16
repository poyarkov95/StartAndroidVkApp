package startandroid.apoyark.com.startandroidvkapp.model.view;

import startandroid.apoyark.com.startandroidvkapp.R;

/**
 * Created by User on 07.06.2018.
 */

public class CounterViewModel {
    protected int mCount;
    protected int mIconColor = R.color.colorIconDis;
    protected int mTextColor = R.color.colorIconDis;

    public CounterViewModel(int count){
        if(count > 0){
            setAccentColor();
        } else {
            setDefaultColor();
        }
    }

    public void setDisabledColor(){
        mIconColor = R.color.colorIconDis;
        mTextColor = R.color.colorIconDis;
    }

    public void setAccentColor() {
        mIconColor = R.color.colorAccent;
        mTextColor = R.color.colorAccent;
    }

    public void setDefaultColor() {
        mIconColor = R.color.colorIcon;
        mTextColor = R.color.colorIcon;

    }

    public int getCount() {
        return mCount;
    }

    public int getIconColor() {
        return mIconColor;
    }

    public int getTextColor() {
        return mTextColor;
    }
}

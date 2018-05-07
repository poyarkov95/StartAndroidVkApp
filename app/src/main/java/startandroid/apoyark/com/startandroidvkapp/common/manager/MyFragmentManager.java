package startandroid.apoyark.com.startandroidvkapp.common.manager;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;

import java.util.Stack;

import startandroid.apoyark.com.startandroidvkapp.ui.activity.BaseActivity;
import startandroid.apoyark.com.startandroidvkapp.ui.fragment.BaseFragment;

/**
 * Created by User on 05.05.2018.
 */

public class MyFragmentManager {
    private static final int EMPTY_FRAGMENT_STACK_SIZE = 1;

    private Stack<BaseFragment> mFragmentStack = new Stack<>();

    private BaseFragment mCurrentFragment;

    public void setFragment(BaseActivity activity, BaseFragment fragment, @IdRes int containerId) {
        if (activity != null && !activity.isFinishing() && isAlreadyContains(fragment)) {
            FragmentTransaction fragmentTransaction = createAddTransaction(activity, fragment, false);
            fragmentTransaction.replace(containerId, fragment);
            commitAddTransaction(activity, fragment, fragmentTransaction, false);
        }
    }

    public void addFragment(BaseActivity activity, BaseFragment fragment, @IdRes int containerId) {
        if (activity != null && !activity.isFinishing() && isAlreadyContains(fragment)) {
            FragmentTransaction fragmentTransaction = createAddTransaction(activity, fragment, true);
            fragmentTransaction.add(containerId, fragment);
            commitAddTransaction(activity, fragment, fragmentTransaction, true);
        }
    }

    public boolean removeFragment(BaseActivity activity, BaseFragment fragment) {
        boolean isRemovable = fragment != null && mFragmentStack.size() > EMPTY_FRAGMENT_STACK_SIZE;
        if (isRemovable) {
            FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
            transaction.remove(fragment);

            mFragmentStack.pop();
            mCurrentFragment = mFragmentStack.lastElement();

            commitTransaction(activity, transaction);
        }
        return isRemovable;
    }

    public boolean removeCurrentFragment(BaseActivity activity) {
        return removeFragment(activity, mCurrentFragment);
    }

    private FragmentTransaction createAddTransaction(BaseActivity activity, BaseFragment fragment, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getTag());
        }

        return fragmentTransaction;
    }

    private void commitAddTransaction(BaseActivity activity, BaseFragment fragment, FragmentTransaction transaction, boolean addToBackStack) {
        if (transaction != null) {
            mCurrentFragment = fragment;

            if (!addToBackStack) {
                mFragmentStack = new Stack<>();
            }

            mFragmentStack.add(fragment);

            commitTransaction(activity, transaction);
        }
    }

    public void commitTransaction(BaseActivity activity, FragmentTransaction transaction) {
        transaction.commit();
        activity.fragmentOnScreen(mCurrentFragment);
    }

    public boolean isAlreadyContains(BaseFragment fragment) {
        if (fragment == null) {
            return false;
        }
        return mCurrentFragment != null && mCurrentFragment.getClass().getName().equals(fragment.getClass().getName());
    }

    public Stack<BaseFragment> getFragmentStack() {
        return mFragmentStack;
    }
}

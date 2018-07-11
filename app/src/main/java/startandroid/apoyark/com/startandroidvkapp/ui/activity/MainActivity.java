package startandroid.apoyark.com.startandroidvkapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

import java.util.ArrayList;
import java.util.List;

import startandroid.apoyark.com.startandroidvkapp.CurrentUser;
import startandroid.apoyark.com.startandroidvkapp.MainApplication;
import startandroid.apoyark.com.startandroidvkapp.R;
import startandroid.apoyark.com.startandroidvkapp.consts.ApiConstants;
import startandroid.apoyark.com.startandroidvkapp.model.Profile;
import startandroid.apoyark.com.startandroidvkapp.presenter.MainPresenter;
import startandroid.apoyark.com.startandroidvkapp.ui.fragment.BaseFragment;
import startandroid.apoyark.com.startandroidvkapp.ui.fragment.NewsFeedFragment;
import startandroid.apoyark.com.startandroidvkapp.view.MainView;

public class MainActivity extends BaseActivity implements MainView {

    @InjectPresenter
    MainPresenter mPresenter;

    private Drawer drawer;

    private AccountHeader accountHeader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainApplication.getApplicationComponent().inject(this);

        mPresenter.checkAuth();
    }

    @Override
    public void startSignIn() {
        VKSdk.login(this, ApiConstants.DEFAULT_LOGIN_SCOPE);
    }

    @Override
    public void signedIn() {
        Toast.makeText(this, "Current user id: " + CurrentUser.getId(), Toast.LENGTH_LONG).show();
        setContent(new NewsFeedFragment());
        setUpDrawer();
    }

    @Override
    public void showCurrentUser(Profile profile) {
        List<IProfile> profileDrawerItems = new ArrayList<>();
        profileDrawerItems.add(new ProfileDrawerItem().withName(profile.getFullName())
                .withEmail(VKAccessToken.currentToken().email).withIcon(profile.getDisplayProfilePhoto()));
        profileDrawerItems.add(new ProfileDrawerItem().withName(R.string.logout_label)
                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
                    accountHeader.removeProfile(0);
                    accountHeader.removeProfile(0);
                    VKSdk.logout();
                    return false;
                }));
        accountHeader.setProfiles(profileDrawerItems);
    }

    @Override
    public void showFragmentFromDrawer(BaseFragment baseFragment) {
        setContent(baseFragment);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                // Пользователь успешно авторизовался
                mPresenter.checkAuth();
            }

            @Override
            public void onError(VKError error) {
                // Произошла ошибка авторизации (например, пользователь запретил авторизацию)
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void setUpDrawer() {
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.screen_name_news)
                .withIcon(GoogleMaterial.Icon.gmd_home);

        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName(R.string.screen_name_my_posts)
                .withIcon(GoogleMaterial.Icon.gmd_list);

        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName(R.string.screen_name_settings)
                .withIcon(GoogleMaterial.Icon.gmd_settings);

        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName(R.string.screen_name_members)
                .withIcon(GoogleMaterial.Icon.gmd_people);

        PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName(R.string.screen_name_topics)
                .withIcon(GoogleMaterial.Icon.gmd_record_voice_over);

        PrimaryDrawerItem item6 = new PrimaryDrawerItem().withIdentifier(6).withName(R.string.screen_name_info)
                .withIcon(GoogleMaterial.Icon.gmd_info);

        PrimaryDrawerItem item7 = new PrimaryDrawerItem().withIdentifier(7).withName(R.string.screen_name_rules)
                .withIcon(GoogleMaterial.Icon.gmd_assessment);


        accountHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .build();

        drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withTranslucentStatusBar(true)
                .withActionBarDrawerToggle(true)
                .withAccountHeader(accountHeader)
                .addDrawerItems(item1, item2, item3,
                        new SectionDrawerItem().withName(R.string.screen_name_group),
                        item4, item5, item6, item7)
                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
                    mPresenter.drawerItemClicked((int) drawerItem.getIdentifier());
                    return false;
                })
                .build();
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.activity_main;
    }
}

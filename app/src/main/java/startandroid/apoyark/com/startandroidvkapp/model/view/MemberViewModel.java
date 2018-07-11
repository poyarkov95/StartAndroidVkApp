package startandroid.apoyark.com.startandroidvkapp.model.view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import startandroid.apoyark.com.startandroidvkapp.R;
import startandroid.apoyark.com.startandroidvkapp.model.Member;
import startandroid.apoyark.com.startandroidvkapp.ui.holder.BaseViewHolder;

public class MemberViewModel extends BaseViewModel {
    public int id;

    public int groupId;

    public String photo;

    private String fullName;

    public MemberViewModel() {
    }

    public MemberViewModel(Member member) {
        this.id = member.getId();
        this.photo = member.getPhoto();
        this.groupId = member.getGroupId();
        this.fullName = member.getFullName();
    }

    public class MemberViewHolder extends BaseViewHolder<MemberViewModel> {

        @BindView(R.id.civ_profile_image)
        public CircleImageView profilePhotoCircleImage;

        @BindView(R.id.tv_profile_name)
        public TextView profileNameTextView;

        public MemberViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindViewHolder(MemberViewModel memberViewModel) {
            Context context = itemView.getContext();

            Glide.with(context)
                    .load(memberViewModel.getPhoto())
                    .into(profilePhotoCircleImage);
            profileNameTextView.setText(memberViewModel.getFullName());
        }

        @Override
        public void unbindViewHolder() {
            profileNameTextView.setText(null);
            profilePhotoCircleImage.setImageBitmap(null);
        }
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.Member;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View v) {
        return new MemberViewHolder(v);
    }

    public int getId() {
        return id;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getPhoto() {
        return photo;
    }

    public String getFullName() {
        return fullName;
    }
}

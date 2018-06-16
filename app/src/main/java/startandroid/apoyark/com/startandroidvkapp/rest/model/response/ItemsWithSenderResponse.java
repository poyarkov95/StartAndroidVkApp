package startandroid.apoyark.com.startandroidvkapp.rest.model.response;

import java.util.ArrayList;
import java.util.List;

import startandroid.apoyark.com.startandroidvkapp.model.Group;
import startandroid.apoyark.com.startandroidvkapp.model.Owner;
import startandroid.apoyark.com.startandroidvkapp.model.Profile;

/**
 * Created by User on 13.05.2018.
 */

public class ItemsWithSenderResponse<T> extends BaseItemResponse<T> {
    private List<Profile> profiles = new ArrayList<>();

    private List<Group> groups = new ArrayList<>();

    public List<Owner> getAllSenders(){
        List<Owner> all = new ArrayList<>();
        all.addAll(profiles);
        all.addAll(groups);
        return all;
    }

    public Owner getSender(int senderId){
        for(Owner owner : getAllSenders()){
            if(owner.getId() == Math.abs(senderId)){
                return owner;
            }
        }
        return null;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}

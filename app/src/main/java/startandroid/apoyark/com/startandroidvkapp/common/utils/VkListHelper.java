package startandroid.apoyark.com.startandroidvkapp.common.utils;

import java.util.List;

import startandroid.apoyark.com.startandroidvkapp.model.Owner;
import startandroid.apoyark.com.startandroidvkapp.model.WallItem;
import startandroid.apoyark.com.startandroidvkapp.rest.model.response.ItemsWithSenderResponse;

/**
 * Created by User on 04.06.2018.
 */

public class VkListHelper {
    public static List<WallItem> getWallList(ItemsWithSenderResponse<WallItem> response){
        List<WallItem> wallItems = response.getItems();

        for(WallItem wallItem : wallItems){
            Owner sender = response.getSender(wallItem.getFromId());
            wallItem.setSenderName(sender.getFullName());
            wallItem.setSenderPhoto(sender.getPhoto());

            if(wallItem.haveSharedRepost()){
                Owner repostSender = response.getSender(wallItem.getSharedRepost().getFromId());
                wallItem.getSharedRepost().setSenderName(repostSender.getFullName());
                wallItem.getSharedRepost().setSenderPhoto(repostSender.getPhoto());
                wallItem.getSharedRepost().setAttachmentsString(Utils.converAttachmentsToFontIcons(wallItem.getSharedRepost().getAttachments()));
            }
        }
        return wallItems;
    }
}

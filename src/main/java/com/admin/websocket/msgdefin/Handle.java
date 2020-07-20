package com.admin.websocket.msgdefin;

import com.admin.websocket.deviceDataModel.UserCard;

public class Handle {
    private UserCard userCard;
    private String handle;
    private String deviceNumber;

    public Handle(UserCard userCard, String handle, String deviceNumber) {
        this.userCard = userCard;
        this.handle = handle;
    }

    public UserCard getUserCard() {
        return userCard;
    }

    public void setUserCard(UserCard userCard) {
        this.userCard = userCard;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }
}

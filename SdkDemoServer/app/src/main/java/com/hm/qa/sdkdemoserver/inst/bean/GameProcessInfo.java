package com.hm.qa.sdkdemoserver.inst.bean;

import com.hm.pluginsdk.beans.Message;
import com.hm.pluginsdk.enums.NetWorkState;

import java.util.ArrayList;
import java.util.List;


public class GameProcessInfo {
    private PlayerError playerError = null;
    private List<NetWorkState> networkStats = new ArrayList<>();
    private List<PlayerStatus> playerStatuses = new ArrayList<>();
    private List<String> sceneChanges = new ArrayList<>();
    private List<PlayStat> playStats = new ArrayList<>();
    private List<String> permissions = new ArrayList<>();
    private List<String> cloudDeviceStatus = new ArrayList<>();
    private List<String> intents  = new ArrayList<>();
    private List<Message> messages = new ArrayList<>();

    public void addNetworkStat(NetWorkState stat){
        this.networkStats.add(stat);
    }
    public void addSceneChanges(String change){
        this.sceneChanges.add(change);
    }
    public void addPlayStat(PlayStat stat){
        this.playStats.add(stat);
    }
    public void addPermission(String permission){
        this.permissions.add(permission);
    }
    public void addCloudDeviceStatus(String status){
        this.cloudDeviceStatus.add(status);
    }
    public void addIntent(String intent){
        this.intents.add(intent);
    }
    public void addMessages(Message msg){
        this.messages.add(msg);
    }
    public void addPlayerStatuses(PlayerStatus status){
        this.playerStatuses.add(status);
    }

    public List<NetWorkState> getNetworkStats() {
        return networkStats;
    }

    public List<String> getSceneChanges() {
        return sceneChanges;
    }

    public List<PlayStat> getPlayStats() {
        return playStats;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public List<String> getCloudDeviceStatus() {
        return cloudDeviceStatus;
    }

    public List<String> getIntents() {
        return intents;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public List<PlayerStatus> getPlayerStatuses() {
        return playerStatuses;
    }

    public PlayerError getPlayerError() {
        return playerError;
    }

    public void setPlayerError(PlayerError playerError) {
        this.playerError = playerError;
    }
}

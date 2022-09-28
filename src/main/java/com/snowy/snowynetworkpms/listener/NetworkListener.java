package com.snowy.snowynetworkpms.listener;

import com.snowy.snowynetworkpms.SnowyNetworkPMs;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;

public class NetworkListener implements Listener {

    public SnowyNetworkPMs network;

    public NetworkListener(SnowyNetworkPMs network) {
        this.network = network;
    }

    public void onQuit(PlayerDisconnectEvent e) {
        if (network.getRecentMessages().containsKey(e.getPlayer().getUniqueId())) {
            network.getRecentMessages().remove(e.getPlayer().getUniqueId());
        }
    }

}

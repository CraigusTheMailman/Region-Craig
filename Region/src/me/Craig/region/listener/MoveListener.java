package me.Craig.region.listener;

import java.util.HashMap;
import java.util.Map;

import me.Craig.region.Regions;
import me.Craig.region.util.Region;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveListener implements Listener{
	
	private static Map<String, String> lastRegion = new HashMap<>();
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		
		Player player = event.getPlayer();
		Location to = event.getTo();
		
		if(!lastRegion.containsKey(player.getName())) {
			lastRegion.put(player.getName(), null);
		}
		
		Region rg = Regions.getRegionHere(to);
		if(rg != null) {
			String name = rg.getName();
			String lastName = lastRegion.get(player.getName());
					
			if(lastName == null || !rg.getName().equals(name)) {
				
				lastRegion.put(player.getName(), name);
				
				if(rg.getEntryMessage() != null) {
					player.sendMessage("§6§l** " + rg.getEntryMessage());
				}
				else {
					player.sendMessage("§6§l** Entered §c§l " + rg.getOwner() + "§6§l's protected land.");
				}
			}
		}
		else {
			
			String lastName = lastRegion.get(player.getName());
			if(lastName != null) {
				Region r = Regions.getRegion(lastName);
				if(r.getLeaveMessage() != null) {
					player.sendMessage("§6§l** " + r.getLeaveMessage());
				}
				else {
					player.sendMessage("§6§l** Left §c§l " + r.getOwner() + "§6§l's protected land.");
				}
				lastRegion.put(player.getName(), null);
			}
		}
		
	}

	public static Map<String, String> getLastRegion() {
		return lastRegion;
	}

	public static void setLastRegion(Map<String, String> lastRegion) {
		MoveListener.lastRegion = lastRegion;
	}

}

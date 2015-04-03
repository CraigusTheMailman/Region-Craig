package me.Craig.region.listener;

import me.Craig.region.Regions;
import me.Craig.region.util.Region;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockListener implements Listener{

	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		Block block = event.getBlock();
		
		Region rg = Regions.getRegionHere(block.getLocation());
		if(rg != null && !rg.getOwner().equals(player.getName())) {
			player.sendMessage("�cYou don't have " + rg.getOwner() + "'s permission!");
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Block block = event.getBlock();
		
		Region rg = Regions.getRegionHere(block.getLocation());
		if(rg != null && !rg.getOwner().equals(player.getName())) {
			player.sendMessage("�cYou don't have " + rg.getOwner() + "'s permission!");
			event.setCancelled(true);
		}
	}
}
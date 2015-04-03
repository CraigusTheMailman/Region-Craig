package me.Craig.region.listener;

import me.Craig.region.Regions;
import me.Craig.region.util.WandPoints;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InteractListener implements Listener{ 

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		
		ItemStack hand = player.getItemInHand();
		if(hand != null & hand.getType() == Material.GOLD_AXE && player.hasPermission("region.create")) {
			
			if(event.getAction() == Action.LEFT_CLICK_BLOCK) {
				Block block = event.getClickedBlock();
				
				if(!Regions.getWandStorage().containsKey(player.getName())) {
					Regions.getWandStorage().put(player.getName(), new WandPoints(null, null));
				}
				
				Regions.getWandStorage().get(player.getName()).setP1(block.getLocation());
				event.setCancelled(true);
				player.sendMessage("§aPosition 1 set!");
			}
			else if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				Block block = event.getClickedBlock();
				
				if(!Regions.getWandStorage().containsKey(player.getName())) {
					Regions.getWandStorage().put(player.getName(), new WandPoints(null, null));
				}
				
				Regions.getWandStorage().get(player.getName()).setP2(block.getLocation());
				event.setCancelled(true);
				player.sendMessage("§aPosition 2 set!");
			}
		}
	}
}

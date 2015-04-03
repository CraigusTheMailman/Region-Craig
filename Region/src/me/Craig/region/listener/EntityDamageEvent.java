package me.Craig.region.listener;

import me.Craig.region.Regions;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageEvent implements Listener{
	
	@EventHandler
	public void onPVP(EntityDamageByEntityEvent event) {
		
		Entity e1 = event.getEntity();
		Entity e2 = event.getDamager();
		
		if((e1 instanceof Player)) {
			
			Player victum = (Player)e1;
			
			Player attacker = null;
			if((e2 instanceof Player)) {
				attacker = (Player)e2;
			}
			else if(e2 instanceof Projectile) {
				Projectile proj = (Projectile)e2;
				@SuppressWarnings("deprecation")
				LivingEntity shooter = proj.getShooter();
				if(shooter instanceof Player) {
					attacker = (Player)shooter;
				}
				
			}
			
			if(attacker == null)return;
			
			if(Regions.getRegionHere(attacker.getLocation()) != null) {
				
				attacker.sendMessage("§cYou may not PVP while in protected land!");
				event.setCancelled(true);
				
			}
			else if(Regions.getRegionHere(victum.getLocation()) != null) {
				attacker.sendMessage("§cYou may not PVP players in protected land!");
				event.setCancelled(true);
			}
			
			
		}
		
	}

}

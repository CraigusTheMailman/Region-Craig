package me.Craig.region;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import me.Craig.region.commands.RegionCommand;
import me.Craig.region.listener.BlockListener;
import me.Craig.region.listener.EntityDamageEvent;
import me.Craig.region.listener.InteractListener;
import me.Craig.region.listener.MoveListener;
import me.Craig.region.util.Region;
import me.Craig.region.util.WandPoints;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public class Regions extends JavaPlugin {

	private static List<Region> regions = new ArrayList<>();
	private static Map<String, WandPoints> wandStorage  = new HashMap<>();
	
	public final Logger logger = Logger.getLogger("Minecraft");
	
	public void onEnable() {
		getCommand("region").setExecutor(new RegionCommand());
		getServer().getPluginManager().registerEvents(new InteractListener(), this);
		getServer().getPluginManager().registerEvents(new BlockListener(), this);
		getServer().getPluginManager().registerEvents(new MoveListener(), this);
		getServer().getPluginManager().registerEvents(new EntityDamageEvent(), this);
		this.logger.info("[Region-Craig]Bringing out Fido...");
		this.logger.info("[Region-Craig]Fattening Fido...");
		this.logger.info("[Region-Craig]Testing Fido...");
		this.logger.info("[Region-Craig]Fido! Watch...Ouch...");
		this.logger.info("[Region-Craig]Brining back Fido...");
		this.logger.info("[Region-Craig]Region-Craig Enabled!");
	}

	public static Map<String, WandPoints> getWandStorage() {
		return wandStorage;
	}
	
	public static void setWandStorage(Map<String, WandPoints> wandStorage) {
		Regions.wandStorage = wandStorage;
	}
	
	public static Region getRegion(String name) {
		for(Region region : getRegions()) {
			if(region.getName().equalsIgnoreCase(name)) {
				return region;
			}
		}
		return null;
	}
	
	public static Region getRegionHere(Location loc) {
		
		List<Region> regionList = Regions.getRegions();
		for(Region rg: regionList) {
			
			Location p1 = rg.getP1();
			Location p2 = rg.getP2();
			
			int minX = p1.getBlockX() < p2.getBlockX() ? p1.getBlockX() : p2.getBlockX();
			int minY = p1.getBlockY() < p2.getBlockY() ? p1.getBlockY() : p2.getBlockY();
			int minZ = p1.getBlockZ() < p2.getBlockZ() ? p1.getBlockZ() : p2.getBlockZ();
			
			int maxX = p1.getBlockX() > p2.getBlockX() ? p1.getBlockX() : p2.getBlockX();
			int maxY = p1.getBlockY() > p2.getBlockY() ? p1.getBlockY() : p2.getBlockY();
			int maxZ = p1.getBlockZ() > p2.getBlockZ() ? p1.getBlockZ() : p2.getBlockZ();
			
			if(loc.getBlockX() >= minX && loc.getBlockX() <= maxX) {
				if(loc.getBlockY() >= minY && loc.getBlockY() <= maxY) {
					if(loc.getBlockZ() >= minZ && loc.getBlockZ() <= maxZ) {
					
						return rg;
					}
				}
			}
			
		}
		
		return null;
	}
	
	public static List<Region> getRegions() {
		return regions;
	}
	
	public static void setRegions(List<Region> regions) {
		Regions.regions = regions;
	}
	
}

package me.Craig.region.commands;

import java.util.List;

import me.Craig.region.Regions;
import me.Craig.region.util.Region;
import me.Craig.region.util.WandPoints;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RegionCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		
		if(args.length == 0) {
			sender.sendMessage("�c�lRegion Help");
			sender.sendMessage("�8> �e/region create �3<Name>");
			sender.sendMessage("�8> �e/region list");
			sender.sendMessage("�8> �e/region tp �3<Name>");
			sender.sendMessage("�8> �e/region delete �3<Name>");
			sender.sendMessage("�8> �e/region wand");
			sender.sendMessage("�8> �e/region setentry �3<Name> <Message>");
			sender.sendMessage("�8> �e/region setleave �3<Name> <Message>");
			sender.sendMessage("�8> �e/region removemessages");
			
		}
		else {
			
			String cmd = args[0];
			
			if(cmd.equalsIgnoreCase("create")) {
				
				if(sender.hasPermission("regions.create")) {
					
					if(args.length == 2) {
						String name = args[1];
						
						Region rg = Regions.getRegion(name);
						if(rg == null) {
							if(Regions.getWandStorage().containsKey(sender.getName())) {
								
								WandPoints wand = Regions.getWandStorage().get(sender.getName());
								if(wand.getP1() != null && wand.getP2() != null) {
									
									if(wand.getP1().getWorld().getName().equals(wand.getP2().getWorld().getName())) {
										
										Region myregion = new Region(name, wand.getP1(), wand.getP2(), sender.getName());
										Regions.getRegions().add(myregion);
										
										sender.sendMessage("�aRegion Created!");
										
									}
									else {
										sender.sendMessage("�cYour points aren't in the same universe!");
									}
									
									return true;
								}
							}
								
							sender.sendMessage("�cPlease define a region with /region wand!");
							
						}else {
							sender.sendMessage("�cThat region already exists!");
						}
					}
					else {
						sender.sendMessage("�cYou need to specify a name!");
					}
				}else {
					sender.sendMessage("�cYou don't have permission!");
				}
				
			}
			else if(cmd.equalsIgnoreCase("list")) {
				
				List<Region> list = Regions.getRegions();
				
				if(list.size() > 0) {
					
					sender.sendMessage("�aDefined Regions (�b" + list.size() + "�a): �e" + list.toString());
				}else {
					sender.sendMessage("�cNo defined regions!");
				}
				
			}
			else if(cmd.equalsIgnoreCase("delete")) {
				
				if(sender.hasPermission("regions.delete")) {
					
					if(args.length == 2) {
						String name = args[1];
						
						Region rg = Regions.getRegion(name);
						if(rg != null) {
							Regions.getRegions().remove(rg);
							sender.sendMessage("�aRegion deleted!");
						}else {
							sender.sendMessage("�cNo regions found named \"" + name + "\"!");
						}
					}
					else {
						sender.sendMessage("�cYou need to specify a name!");
					}
				}else {
					sender.sendMessage("�cYou don't have permission!");
				}
				
			}
			else if(cmd.equalsIgnoreCase("tp")) {
				
				if(sender.hasPermission("region.tp")) {
					
					if(args.length == 2) {
						String name = args[1];
						
						Region rg = Regions.getRegion(name);
						if(rg != null) {
							if((sender instanceof Player)) {
								Player player = (Player)sender;
								
								player.teleport(rg.getP1());
								player.sendMessage("�aTeleported to " + rg.getName() + "!");
							}
						}
						else {
							sender.sendMessage("�cNo regions found named \"" + name + "\"!");
						}
					}
					else {
						sender.sendMessage("�cYou need to specify a name!");
					}
				}
				else {
					sender.sendMessage("�cYou do not have permission!");
				}
				
			}
			else if(cmd.equalsIgnoreCase("wand")) {
				if(sender.hasPermission("region.wand")) {
					
					if((sender instanceof Player)) {
						Player player = (Player)sender;
						
						ItemStack wand = new ItemStack(Material.GOLD_AXE, 1);
						ItemMeta meta = wand.getItemMeta();
						meta.setDisplayName("�bRegion Wand");
						wand.setItemMeta(meta);
						player.getInventory().addItem(wand);
						player.sendMessage("�aThere you go!");
					}
				}
			}
			else if(cmd.equalsIgnoreCase("removemessages")) {
				
				if(sender.hasPermission("region.removemessages")) {
					
					if(args.length == 2) {
						String name = args[1];
						
						Region rg = Regions.getRegion(name);
						if(rg != null) {
							
							rg.setEntryMessage(null);
							rg.setLeaveMessage(null);
							
							sender.sendMessage("�aMessages cleared!");
						}
						else {
							sender.sendMessage("�cNo regions found named \"" + name + "\"!");
						}
					}
					else {
						sender.sendMessage("�cYou need to specify a name!");
					}
				}
				else {
					sender.sendMessage("�cYou do not have permission!");
				}
			
			}
			else if(cmd.equalsIgnoreCase("setentry")) {
				if(sender.hasPermission("region.setentry")) {
					
					if(args.length >= 3) {
						String name = args[1];
						
						Region rg = Regions.getRegion(name);
						if(rg != null) {
							
							String msg = "";
							for(int i = 2; i < args.length; i++) {
								msg = msg + " " + args[i];
							}
							msg = msg .substring(1);
							rg.setEntryMessage(msg);
							sender.sendMessage("�aEntry message set!");
						}
						else {
							sender.sendMessage("�cNo regions found named \"" + name + "\"!");
						}
					}
					else {
						sender.sendMessage("�cYou need to specify a name!");
					}
				}
				else {
					sender.sendMessage("�cYou do not have permission!");
				}
			}
			else if(cmd.equalsIgnoreCase("setleave")) {
				if(sender.hasPermission("region.setleave")) {
					
					if(args.length >= 3) {
						String name = args[1];
						
						Region rg = Regions.getRegion(name);
						if(rg != null) {
							
							String msg = "";
							for(int i = 2; i < args.length; i++) {
								msg = msg + " " + args[i];
							}
							msg = msg .substring(1);
							rg.setLeaveMessage(msg);
							sender.sendMessage("�aLeave message set!");
						}
						else {
							sender.sendMessage("�cNo regions found named \"" + name + "\"!");
						}
					}
					else {
						sender.sendMessage("�cYou need to specify a name!");
					}
				}
				else {
					sender.sendMessage("�cYou do not have permission!");
					
				}
			}else {
				sender.sendMessage("�cUnknown command! Type /region for help");
			}
		}
		return true;
	}
}

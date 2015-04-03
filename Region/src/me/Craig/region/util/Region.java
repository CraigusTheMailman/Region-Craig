package me.Craig.region.util;

import org.bukkit.Location;

public class Region {

	private String name;
	private Location p1;
	private Location p2;
	private String owner;
	
	private String entryMessage = null;
	private String leaveMessage = null;
	
	public String getEntryMessage() {
		return entryMessage;
	}

	public void setEntryMessage(String entryMessage) {
		this.entryMessage = entryMessage;
	}

	public String getLeaveMessage() {
		return leaveMessage;
	}

	public void setLeaveMessage(String leaveMessage) {
		this.leaveMessage = leaveMessage;
	}

	public Region(String name, Location p1, Location p2, String owner) {
		this.name = name;
		this.p1 = p1;
		this.p2 = p2;
		this.owner = owner;
	}
	
	public String getOwner() {
		return owner;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Location getP1() {
		return p1;
	}
	
	public void setP1(Location p1) {
		this.p1 = p1;
	}
	
	public Location getP2() {
		return p2;
	}
	
	public void setP2(Location p2) {
		this.p2 = p2;
	}
	
}

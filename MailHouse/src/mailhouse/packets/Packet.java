package mailhouse.packets;

import java.util.*;
import java.io.*;


public abstract class Packet implements Serializable{
	
	private PacketTypes packetType;
	
	public Packet(PacketTypes packetType){
		this.packetType = packetType;
	}
	
	public PacketTypes getPacketType(){
		return this.packetType;
	}
	
	
}
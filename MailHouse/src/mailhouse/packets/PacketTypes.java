package mailhouse.packets;

import java.util.*;

public enum PacketTypes{
	
	LoginPacket(1),
	ProfilePacket(2),
	SendMailPacket(3),
	GetMailListPacket(4),
	GetMailPacket(5),
	LogoutPacket(6),
	ErrorPacket(-1);
	
	private int packetId;
	
	PacketTypes(int packetId){
		this.packetId = packetId;
	}
	
	
}
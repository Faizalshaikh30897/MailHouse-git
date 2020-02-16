package mailhouse.packets;

import java.util.*;
import mailhouse.domain.*;

public class ProfilePacket extends Packet{
	
	public UserProfile userProfile;
	public User user;
	
	public ProfilePacket(User user,UserProfile userProfile){
		super(PacketTypes.ProfilePacket);
		this.user = user;
		this.userProfile = userProfile;
	}
	
	public User getUser(){
		return this.user;
	}
	
	public void setUser(User user){
		this.user = user;
	}
	
	
	public UserProfile getUserProfile(){
		return this.userProfile;
	}
	
	public void setUserProfile(UserProfile userProfile){
		this.userProfile = userProfile;
	}
	
}
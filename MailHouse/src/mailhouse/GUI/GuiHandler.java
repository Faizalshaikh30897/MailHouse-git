package mailhouse.GUI;

import java.awt.Color;
import java.awt.event.MouseAdapter;

import javax.swing.*;

import mailhouse.client.Client;
import mailhouse.domain.User;

public class GuiHandler extends MouseAdapter {
	
	private Client c;
	private MailHome m;
	
	public GuiHandler(Client c,MailHome m){
		
		this.c=c;
		this.m = m;
	}
	
    public void mouseEntered(java.awt.event.MouseEvent evt) {
    	((JButton) evt.getComponent()).setBackground(Color.lightGray);
    }

    public void mouseExited(java.awt.event.MouseEvent evt) {
    	((JButton) evt.getComponent()).setBackground(UIManager.getColor("control"));
    	
    }
    public void mouseClicked(java.awt.event.MouseEvent evt) {
    	String ac_command = ((JButton) evt.getComponent()).getActionCommand();
    	System.out.print("clicked");
    	if(ac_command.equals("login_click")){
    		
    		String email = this.m.emailText.getText();
    		String password = this.m.passText.getText();
    		User user = this.c.login(email, password);
    		
    		if(user != null){
    			System.out.print("login");
	    		final InboxScreen is = new InboxScreen(c);
	    		MailHome.parent.add(is,"2");
	    		MailHome.c1.show(MailHome.parent, "2");
    		}
    	}
    	

    }
	

}

package mailhouse.GUI;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mailhouse.client.Client;


public class InboxScreen extends JPanel {
	
	public static JLabel baseInbox;
	private Client c;
	
	public InboxScreen(Client c){ 
		this.c = c;
		baseInbox = new JLabel();
		baseInbox.setSize(MailHome.screenSize.width, MailHome.screenSize.height);
		
		BufferedImage imag = null;
		try {
		    imag = ImageIO.read(new File(MailHome.back_grnd));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Image dimg = imag.getScaledInstance(baseInbox.getWidth(), baseInbox.getHeight(),
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		baseInbox.setIcon(imageIcon);
		imageIcon = new ImageIcon(dimg);
		//homeFrame.add(Homebase);
		
		//baseInbox.setIcon(MailHome.imageIcon);
		this.add(baseInbox);
	
	}

}

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

//Page 198
/**
 * Manages the GUI of the applet
 * 
 * @author Hunter Davis
 * @version 0.0.1
 */
public class GUI_Manager extends JApplet
{
    // instance variables - replace the example below with your own
   private final int APPLET_WIDTH = 1000;
   private final int APPLET_HEIGHT = 500;
   private JPanel topDisplay, appPanel, buttonPanel, botPanel, queue;
   private TextArea annoDisplay;
   private JLabel titleLabel, annoText; 
   private TimePanel tPanel; 
   private ArrayList<Content> contentList = new ArrayList<Content>();;
   private JList list_events;
   
    public void init()
   {
      //Sets up the top panel
      topDisplay = new JPanel ();
      topDisplay.setPreferredSize (new Dimension (APPLET_WIDTH, 40));
      topDisplay.setBackground (Color.black);
      topDisplay.setOpaque (true);

      titleLabel = new JLabel("Symphony Interactive Authoring Application");
      titleLabel.setForeground (Color.white);
      
      topDisplay.add(titleLabel);
      
      //Sets up the annotation display panel (commented out to mess around with TextArea class)
      /**annoDisplay = new JPanel();
      *annoDisplay.setLayout (new BoxLayout(annoDisplay, BoxLayout.PAGE_AXIS));
      *annoDisplay.setPreferredSize(new Dimension(APPLET_WIDTH, APPLET_HEIGHT-140));
      *annoDisplay.setBackground(Color.white);
      *annoDisplay.setOpaque(true);
      *
      *annoText = new JLabel("In 1998 Mozart wrote this piece after coming to America. The trumpets here rise in pitch to a B-Flat. Soon the violin will come back in and begin to play a tune inspired by his hometown of London, England.");
      *annoText.setForeground(Color.black);
      *
      *annoDisplay.add(annoText);
      */
      
      annoDisplay = new TextArea("In 1998 Mozart wrote this piece after coming to America. \n " +
      							"The trumpets here rise in pitch to a B-Flat. Soon the violin will come back in and begin to play a tune inspired by his hometown of London, England." +
      							"\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\nYay vert scrolling");
      
      //Buttons!
      JButton btnAddEvent = new JButton("Add Event");
		btnAddEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				String[] choices = new String[]{"Text", "Image", "Animation"};
				int response = JOptionPane.showOptionDialog(null, "What type of annotation do you want?", "Tough Choice", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, choices, "What?");
				Content.ContentType fileType = Content.ContentType.Text;
				String input = "";
				switch(response)
				{
					case 0:
						input = JOptionPane.showInputDialog("Enter desired text");
						fileType = Content.ContentType.Text;
						break;
					case 1:
						input = JOptionPane.showInputDialog("Enter location of desired file");
						fileType = Content.ContentType.Image;
						break;
					case 2:
						input = JOptionPane.showInputDialog("Enter location of desired animation folder");
						fileType = Content.ContentType.Animation;
						break;
				}
				
				contentList.add(new Content(fileType, input));
			}
		});
		
		buttonPanel = new JPanel();
		buttonPanel.add(btnAddEvent);
      
      //Bottom Panel
      tPanel = new TimePanel(600);
      botPanel = new JPanel();
      botPanel.setLayout(new BorderLayout());
      botPanel.add(tPanel, BorderLayout.NORTH);
      
      // Alex's Domain...
      //--------------------------------------
      queue = new JPanel();
      queue.setPreferredSize(new Dimension(APPLET_WIDTH, 60));
      queue.setBackground(Color.black);
      queue.setOpaque(true);
      botPanel.add(queue, BorderLayout.SOUTH);
      
      //placeHolder.setLayout(new CardLayout());
      
      //JPanel noteSet1 = new JPanel();
      //JPanel noteSet2 = new JPanel();
      //noteSet1.setBackground(Color.cyan);
      //noteSet2.setBackground(Color.green);
      
      //need to make whole new JPanel, or not, will ask in class.
      
      //placeHolder.add(noteSet1);
      //placeHolder.add(Box.createRigidArea (new Dimension (0, 60)));
      //placeHolder.add(Box.createHorizontalGlue());
      //placeHolder.add(noteSet2);
      //-------------------------------------------

      //Puts it all together
      appPanel = new JPanel();
      appPanel.setLayout(new BorderLayout());
      appPanel.add(topDisplay, BorderLayout.NORTH);
      appPanel.add(annoDisplay, BorderLayout.CENTER);
      appPanel.add(buttonPanel, BorderLayout.EAST);
      appPanel.add(botPanel, BorderLayout.SOUTH);

      
      getContentPane().add(appPanel);
      
      setSize(APPLET_WIDTH, APPLET_HEIGHT);
      
   }
}

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
   private JPanel topDisplay, appPanel, buttonPanel, botPanel;
   private JLabel titleLabel, noteText; 
   private TimePanel timePanel; 
   private DisplayPanel displayPanel;
   private ListPanel listPanel;
   private ArrayList<Note> noteList = new ArrayList<Note>();;
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
      
      //Sets up the annotation display panel (in progress)
      displayPanel = new DisplayPanel(noteList);
      displayPanel.setLayout (new BorderLayout());
      displayPanel.setPreferredSize(new Dimension(APPLET_WIDTH, APPLET_HEIGHT-140));
      displayPanel.setBackground(Color.white);
      displayPanel.setOpaque(true);
      
      // Test Note
      Note test = new Note();
      test.setAnnotation("Mozart was born in Wien");
      test.setX(0);
      test.setY(0);
      test.setTime(5);
      noteList.add(test);
      
      
      
      
      //Buttons!
      JButton btnAddEvent = new JButton("Add Note");
		btnAddEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				String[] choices = new String[]{"Text", "Image", "Animation"};
				int response = JOptionPane.showOptionDialog(null, "What type of annotation do you want?", "Tough Choice", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, choices, "What?");
				Note freshNote = new Note();
				String input = "";
				switch(response) // Cases 1 and 2 yet to be fully implemented
				{
					case 0:
						input = JOptionPane.showInputDialog("Enter desired text");
						freshNote.setAnnotation(input);
						//fileType = Content.ContentType.Text;
						break;
					case 1:
						input = JOptionPane.showInputDialog("Enter location of desired file");
						//freshNote.setImage()
						//fileType = Content.ContentType.Image;
						break;
					case 2:
						input = JOptionPane.showInputDialog("Enter location of desired animation folder");
						//fileType = Content.ContentType.Animation;
						break;
				}
				
				noteList.add(freshNote);
				displayPanel.updateList(noteList);
				listPanel.updateList(noteList);
			}
		});
		
		JButton btnDelEvent = new JButton("Remove Event");
		btnDelEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				int choice = Integer.parseInt(JOptionPane.showInputDialog("Enter # of annotation you want to delete"));
				noteList.remove(choice-1);
				
				displayPanel.updateList(noteList);
				listPanel.updateList(noteList);
			}
		});
		
		JButton btnChangeEvent = new JButton("Switch Event");
		btnChangeEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				int choice = Integer.parseInt(JOptionPane.showInputDialog("Enter # of annotation you want to display"));
				
				displayPanel.updateSpot(choice);
			}
		});
		
		JButton btnEditEvent = new JButton("Edit Event");
		btnEditEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				int choice = Integer.parseInt(JOptionPane.showInputDialog("Enter # of annotation you want to edit"));
				//String new = JOptionPane.showInputDialog("Enter the new content for this annotation");
				
				noteList.get(choice);
				
				
				displayPanel.updateList(noteList);
				listPanel.updateList(noteList);
				
			}
		});
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(2,2));
		buttonPanel.add(btnAddEvent);
		buttonPanel.add(btnDelEvent);
		buttonPanel.add(btnChangeEvent);
		buttonPanel.add(btnEditEvent);
		
      
      //Bottom Panel
      timePanel = new TimePanel(600);
      botPanel = new JPanel();
      botPanel.setLayout(new BorderLayout());
      botPanel.add(timePanel, BorderLayout.NORTH);
      
      // Alex's Domain...
      //--------------------------------------
      listPanel = new ListPanel(noteList);
      botPanel.add(listPanel, BorderLayout.SOUTH);
      
      //Puts it all together
      appPanel = new JPanel();
      appPanel.setLayout(new BorderLayout());
      appPanel.add(topDisplay, BorderLayout.NORTH);
      appPanel.add(displayPanel, BorderLayout.CENTER);
      appPanel.add(buttonPanel, BorderLayout.EAST);
      appPanel.add(botPanel, BorderLayout.SOUTH);

      
      getContentPane().add(appPanel);
      
      setSize(APPLET_WIDTH, APPLET_HEIGHT);
      
   }
}

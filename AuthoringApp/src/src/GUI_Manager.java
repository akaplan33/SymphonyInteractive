

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
   private JPanel topDisplay, appPanel, buttonPanel, botPanel, queue, annoDisplay;
   private JLabel titleLabel; 
   private TimePanel tPanel; 
   private AnnoPanel aPanel;
   private ListPanel lPanel;
   private ArrayList<Content> contentList = new ArrayList<Content>();;
   private JList list_events;
   private Timer[] annotationTimers;
   
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
      annoDisplay = new JPanel();
      annoDisplay.setLayout (new BorderLayout());
      annoDisplay.setPreferredSize(new Dimension(APPLET_WIDTH, APPLET_HEIGHT-140));
      annoDisplay.setBackground(Color.white);
      annoDisplay.setOpaque(true);
      
      contentList.add(new Content (Content.ContentType.Text, "Filler", 10));
      aPanel = new AnnoPanel(contentList);
      lPanel = new ListPanel(contentList);
      
      annoDisplay.add(aPanel, BorderLayout.WEST);
      annoDisplay.add(lPanel, BorderLayout.EAST);
      
      
      //Buttons!
      JButton btnAddEvent = new JButton("Add Event");
		btnAddEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				String[] choices = new String[]{"Text", "Image", "Animation"};
				int response = JOptionPane.showOptionDialog(null, "What type of annotation do you want?", "Tough Choice", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, choices, "What?");
				Content.ContentType fileType = Content.ContentType.Text;
				String input = "";
				int time;
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
				
				time = Integer.parseInt(JOptionPane.showInputDialog("Enter desired time of annotation"));
				contentList.add(new Content(fileType, input, time));
				updateLists();
			}
		});
		
		JButton btnDelEvent = new JButton("Remove Event");
		btnDelEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				int choice = Integer.parseInt(JOptionPane.showInputDialog("Enter # of annotation you want to delete"));
				contentList.remove(choice);
				
				updateLists();
			}
		});
		
		JButton btnChangeEvent = new JButton("Switch Event");
		btnChangeEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				int choice = Integer.parseInt(JOptionPane.showInputDialog("Enter # of annotation you want to display"));
				
				aPanel.updateSpot(choice);
			}
		});
		
		JButton btnEditEvent = new JButton("Edit Event");
		btnEditEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				int choice = Integer.parseInt(JOptionPane.showInputDialog("Enter # of annotation you want to edit"));
				String newContent = JOptionPane.showInputDialog("Enter the new content for this annotation");
				
				contentList.get(choice)._content = newContent;
				
				updateLists();
			}
		});
		
		JButton btnChangeSongLength = new JButton("Edit Song Length");
		btnChangeSongLength.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				int choice = Integer.parseInt(JOptionPane.showInputDialog("Enter length of song (in seconds)"));
				
				tPanel.setSongTime(choice);
			}
		});
		
		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				tPanel.playSong();
			}
		});
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(3,3));
		buttonPanel.add(btnAddEvent);
		buttonPanel.add(btnDelEvent);
		buttonPanel.add(btnChangeEvent);
		buttonPanel.add(btnEditEvent);
		buttonPanel.add(btnChangeSongLength);
		buttonPanel.add(btnPlay);
		
      
      //Bottom Panel
      tPanel = new TimePanel(600, contentList, aPanel);
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
    
   private void updateLists()
   {
	   tPanel.updateList(contentList);
	   aPanel.updateList(contentList);
	   lPanel.updateList(contentList);
   }
   
   //private void playAnnotations(int currentAnnotation)
   /*{
	   annotationTimers = new Timer[contentList.size()];
	   if(currentAnnotation == contentList.size())
		   return;
	   else{
		   final int usableAnno = currentAnnotation;
		   ActionListener playNext = new ActionListener()
		   {
			   public void actionPerformed (ActionEvent e)
			   {
				   aPanel.updateSpot(usableAnno);
			   }
		   };

		   annotationTimers[currentAnnotation] = new Timer(contentList.get(currentAnnotation).getTime()*1000, playNext);
		   annotationTimers[currentAnnotation].setRepeats(false);
		   annotationTimers[currentAnnotation].start();
		   playAnnotations(currentAnnotation+1);
	   }
   }*/
}

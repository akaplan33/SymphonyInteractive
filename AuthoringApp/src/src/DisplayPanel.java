//********************************************************************
//  AnnoDisplay.java       Author: Hunter Davis (based off KochPanel.java by Lewis/Loftus
//
//  Paints current annotation and provides list of all the annotations
//**********
import java.util.ArrayList;
import java.awt.*;
import javax.swing.JPanel;

public class DisplayPanel extends JPanel
{
   private final int PANEL_WIDTH = 500;
   private final int PANEL_HEIGHT = 500;
   private Note currentDisplay;
   private ArrayList<Note> noteList;
   
   //-----------------------------------------------------------------
   //  Sets the initial fractal order to the value specified.
   //-----------------------------------------------------------------
   public DisplayPanel (ArrayList<Note> noteList)
   {
	  this.noteList = noteList;
	  if(!noteList.isEmpty())
		  currentDisplay = noteList.get(0);
      setBackground (Color.white);
      setPreferredSize (new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
   }


   //-----------------------------------------------------------------
   //  Performs the initial calls to the drawTime method.
   //-----------------------------------------------------------------
   public void paintComponent (Graphics page)
   {
      super.paintComponent (page);

      page.setColor (Color.black);
      //page.drawString(currentDisplay.toString(), 0, 0);

      //drawNote(page);
   }
   
   private void drawNote(Graphics page)
   {
       //currentDisplay.drawFull(page);
   }
   
   public void updateList(ArrayList<Note> noteList)
   {
	   this.noteList = noteList;
	   repaint();
   }
   
   public void updateSpot(int choice)
   {
	   if(choice <= noteList.size())
		   currentDisplay = noteList.get(choice);
	   repaint();
   }
}
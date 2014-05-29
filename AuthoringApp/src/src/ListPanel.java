//********************************************************************
//  ListPanel.java       Author: Hunter Davis (based off KochPanel.java by Lewis/Loftus
//
//  Draws a list of all the current annotations
//**********
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

public class ListPanel extends JPanel
{
   private final int PANEL_WIDTH = 500;
   private final int PANEL_HEIGHT = 100;
   private ArrayList<Note> noteList;
   
   //-----------------------------------------------------------------
   //  Sets the initial fractal order to the value specified.
   //-----------------------------------------------------------------
   public ListPanel (ArrayList<Note> noteList)
   {
	  this.noteList = noteList;
      setBackground (Color.green);
      setPreferredSize (new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
   }


   //-----------------------------------------------------------------
   //  Performs the initial calls to the drawTime method.
   //-----------------------------------------------------------------
   public void paintComponent (Graphics page)
   {
      super.paintComponent (page);

      page.setColor (Color.black);

      drawNotes(page);
   }
   
   private void drawNotes(Graphics page)
   {
       for(int c = 0; c < noteList.size(); c++)
       {
    	   JPanel next = new JPanel();
    	   next.setBorder(BorderFactory.createTitledBorder((""+ c)));
    	   next.add (new JLabel ("" + noteList.get(c)));
    	   this.add(next);
       }
   }
   
   public void updateList(ArrayList<Note> noteList)
   {
	   this.noteList = noteList;
	   repaint();
   }
}
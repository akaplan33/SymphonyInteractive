//********************************************************************
//  TimePanel.java       Author: Hunter Davis (based off KochPanel.java by Lewis/Loftus
//
//  Paints timeline
//********************************************************************

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JPanel;

public class TimePanel extends JPanel
{
   private final int PANEL_WIDTH = 1000;
   private final int PANEL_HEIGHT = 100;
   private final int SONG_TIME;
   private ArrayList<Content> contentList;
   
   //-----------------------------------------------------------------
   //  Sets the initial fractal order to the value specified.
   //-----------------------------------------------------------------
   public TimePanel (int time, ArrayList<Content> contentList)
   {
	  this.contentList = contentList;
      SONG_TIME = time;
      setBackground (Color.yellow);
      setPreferredSize (new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
   }


   //-----------------------------------------------------------------
   //  Performs the initial calls to the drawTime method.
   //-----------------------------------------------------------------
   public void paintComponent (Graphics page)
   {
      super.paintComponent (page);

      page.setColor (Color.black);

      drawTime(page);
   }
   
   private void drawTime(Graphics page)
   {
       double xSpot = -1;
       double singleInterval = (((double)PANEL_WIDTH)/((double)SONG_TIME));
       double interval = singleInterval*5;
       for(int currentTime = 0; currentTime <= SONG_TIME; currentTime += 5)
       {
           page.setColor(Color.black);
           page.drawRect((int)Math.round(xSpot), 80, 1, 20);
           if(currentTime%15 == 0)
               page.drawRect((int)Math.round(xSpot), 60, 1, 40);
           
           if(currentTime%60 == 0)
           {
               page.setColor(Color.red);
               page.drawRect((int)Math.round(xSpot), 20, 1, 80);
           }
           xSpot += interval;
       }
       
       page.setColor(Color.blue);
       for(Content current : contentList)
       {
    	   page.drawRect((int)(singleInterval*(current._time)), 30, 1, PANEL_HEIGHT);
    	   page.drawString(String.valueOf(contentList.indexOf(current)), (int)(singleInterval*(current._time))-2, 25);
       }
       
   }
   
   public void updateList(ArrayList<Content> contentList)
   {
	   this.contentList = contentList;
	   repaint();
   }
   
}
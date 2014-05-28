//********************************************************************
//  TimePanel.java       Author: Hunter Davis (based off KochPanel.java by Lewis/Loftus
//
//  Paints the annotation thumbnails
//********************************************************************

import java.awt.*;
import javax.swing.JPanel;
import java.util.ArrayList;

public class ThumbPanel extends JPanel
{
   private final int PANEL_WIDTH = 1000;
   private final int PANEL_HEIGHT = 80;
   private ArrayList<Content> contentList;
   
   //-----------------------------------------------------------------
   //  Sets the initial fractal order to the value specified.
   //-----------------------------------------------------------------
   public ThumbPanel (ArrayList<Content> contentList)
   {
	  this.contentList = contentList;
      setBackground (Color.white);
      setPreferredSize (new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
   }


   //-----------------------------------------------------------------
   //  Performs the initial calls to the drawCards method.
   //-----------------------------------------------------------------
   public void paintComponent (Graphics page)
   {
      super.paintComponent (page);

      page.setColor (Color.black);

      drawCards(page);
   }
   
   private void drawCards(Graphics page)
   {
	   int cardNum = 0;
	   for(Content drawIt: contentList)
	   {
		   	page.drawRect(10, cardNum*50, 60, 40);
	   }
   }
}
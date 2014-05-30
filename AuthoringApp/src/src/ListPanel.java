package src;

//********************************************************************
//  ListPanel.java       Author: Hunter Davis (based off KochPanel.java by Lewis/Loftus
//
//  Draws a list of all the current annotations
//**********
import java.util.ArrayList;
import java.awt.*;
import javax.swing.JPanel;

public class ListPanel extends JPanel
{
   private final int PANEL_WIDTH = 100;
   private final int PANEL_HEIGHT = 500;
   private ArrayList<Content> contentList;
   
   //-----------------------------------------------------------------
   //  Sets the initial fractal order to the value specified.
   //-----------------------------------------------------------------
   public ListPanel (ArrayList<Content> contentList)
   {
	  this.contentList = contentList;
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

      drawAnno(page);
   }
   
   private void drawAnno(Graphics page)
   {
       for(int c = 0; c < contentList.size(); c++)
       {
    	   page.setColor(Color.BLUE);
    	   page.drawRect(5, c*55, 100, 50);
    	   page.setColor(Color.BLACK);
    	   page.drawString(String.valueOf(c), 10, (c*55)+20);
    	   page.drawString(contentList.get(c)._content, 25, (c*55)+20);
       }
   }
   
   public void sortTime()
   {
	   int min = 0;
	   Content temp;
	   Content[] holding = new Content[contentList.size()];
	   
	   for (int i=0;i<contentList.size();i++)
		   holding[i] = contentList.get(i);
	   
	   for (int index=0;index<holding.length;index++)
	   {
		   min = index;
		   for (int look = index+1; look < holding.length; look++)
		   {
			   if (holding[look] != null && ((holding[look].getTime())-(holding[min].getTime()) < 0))
				   min = look;
		   }
		   temp = holding[min];
		   holding[min] = holding[index];
		   holding[index] = temp;
	   }
	   
	   contentList.clear();
	   
	   for (int i=0;i<holding.length;i++)
		   contentList.add(holding[i]);
	   
   }
   
   public void updateList(ArrayList<Content> contentList)
   {
	   this.contentList = contentList;
	   this.sortTime();
	   repaint();
   }
}
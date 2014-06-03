
//********************************************************************
//  ListPanel.java       Author: Hunter Davis (based off KochPanel.java by Lewis/Loftus
//
//  Draws a list of all the current annotations
//**********
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

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
    	   JPanel next = new JPanel();
    	   next.setBorder(BorderFactory.createTitledBorder((""+ c)));
    	   if (contentList.get(c)._type == Content.ContentType.Text)
    		   next.add (new JLabel ("" + contentList.get(c)));
    	   else if(contentList.get(c)._type == Content.ContentType.Image)
    	   {
    		   Image image, thumbnail;
    		   URL resource = getClass().getResource(contentList.get(c)._content);
    	       try {
    	           image = ImageIO.read(resource);
    	           thumbnail = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
    	           next.add (new JLabel(new ImageIcon(thumbnail)));
    	       } catch (IOException e) {
    	           e.printStackTrace();
    	       }
    	       
    	   }
    	   this.add(next);//keeps on adding same ones...
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
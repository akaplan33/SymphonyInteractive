
//********************************************************************
//  AnnoDisplay.java       Author: Hunter Davis (based off KochPanel.java by Lewis/Loftus
//
//  Paints current annotation and provides list of all the annotations
//**********
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AnnoPanel extends JPanel
{
   private final int PANEL_WIDTH = 500;
   private final int PANEL_HEIGHT = 500;
   private Content currentDisplay;
   private ArrayList<Content> contentList;
   private BufferedImage image;
   
   //-----------------------------------------------------------------
   //  Sets the initial fractal order to the value specified.
   //-----------------------------------------------------------------
   public AnnoPanel (ArrayList<Content> contentList)
   {
	  this.contentList = contentList;
	  if(!contentList.isEmpty())
		  currentDisplay = contentList.get(0);
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
	   if(currentDisplay._type == Content.ContentType.Text)
		   page.drawString(currentDisplay._content, 20, 20);
	   else if(currentDisplay._type == Content.ContentType.Image)
	   {
		    URL resource = getClass().getResource(currentDisplay._content);
	        try {
	            image = ImageIO.read(resource);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        page.drawImage(image, 10, 10, null);
	   }
   }
   
   public void updateList(ArrayList<Content> contentList)
   {
	   this.contentList = contentList;
	   repaint();
   }
   
   public void updateSpot(int choice)
   {
	   if(choice <= contentList.size())
		   currentDisplay = contentList.get(choice);
	   repaint();
   }
}
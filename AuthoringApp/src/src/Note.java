

import java.awt.*;

public class Note 
{
	private int time, x, y;
	private String songTitle;
	private String annotation;
	private Image fullSize;
	private Image thumbnail;
	
	public Note (int time, String songTitle, String annotation, Image fullSize)
	{
		this.time = time;
		this.songTitle = songTitle;
		this.annotation = annotation;
		this.fullSize = fullSize;
		
		thumbnail = fullSize.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
	}
	
	public int getTime()
	{
		return time;
	}
	
	public void setTime(int newTime)
	{
		time = newTime;
	}
	
	public String getAnnotation()
	{
		return annotation;
	}
	
	public void setAnnotation(String newAnnotation)
	{
		annotation = newAnnotation;
	}
	
	public int getX()
	{
		return x;
	}
	
	public void setX(int newX)
	{
		x = newX;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setY(int newY)
	{
		y = newY;
	}
	
	public String toString()
	{
		String result = "";
		
		result += "Song: " + songTitle;
		result += "\nTime: " + time;
		result += "\nAnnotation: " + annotation;
		result += "\nImage: " + fullSize;
		result += "\nThumbnail: " + thumbnail;
		
		return result;
	}
	
	public void drawFull (Graphics page)
	{
		page.drawString(annotation, x, y);
		page.drawImage(fullSize, x, y, null);
	}
	
	public void drawEntry (Graphics page)
	{
		page.drawImage(thumbnail, x, y, null);
		page.drawString(""+time, x+60, y);
	}
	
	
}

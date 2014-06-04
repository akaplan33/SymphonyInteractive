<<<<<<< HEAD
=======

>>>>>>> 4eb6fc6475768680b74f1bdb5354ad0a5ef02f75

import java.io.Serializable;


//edited by Eric Rothman
public class Content implements Serializable{

	public enum ContentType{ Text, Image, Animation }
	public ContentType _type;
	public String _content;
	private int _time;
	
	public Content( ContentType type, String content, int time){
		_time = time;
		_type=type;
		_content=new String(content);
	}
	
	@Override
	public String toString(){
		return "[type: " + _type.toString() + "]  [content: " + _content + "]";
	}

	public int getTime() 
	{
		return _time;
	}
	
	public void setTime(int newTime)
	{
		_time = newTime;
	}

}

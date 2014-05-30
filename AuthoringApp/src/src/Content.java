package src;


public class Content {

	public enum ContentType{ Text, Image, Animation }
	public ContentType _type;
	public String _content;
	private int _time;
	
	public Content( ContentType type, String content, int time){
		_type=type;
		_content=new String(content);
		_time = time;
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

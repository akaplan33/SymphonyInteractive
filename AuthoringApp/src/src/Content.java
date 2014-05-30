<<<<<<< HEAD
package src;

=======
import java.io.Serializable;
>>>>>>> 5e721f7568a37cb5e194a296f5e17b14ddb19cf3

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
<<<<<<< HEAD
=======
	
	/**
	 * @param args
	 */ 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
>>>>>>> 5e721f7568a37cb5e194a296f5e17b14ddb19cf3


	public int getTime() 
	{
		return _time;
	}
	
	public void setTime(int newTime)
	{
		_time = newTime;
	}

}

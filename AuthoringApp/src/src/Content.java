
public class Content {

	public enum ContentType{ Text, Image, Animation }
	public ContentType _type;
	public String _content;
	public int _time;
	
	public Content( ContentType type, String content, int time){
		_type=type;
		_content=new String(content);
		_time = time;
	}
	
	@Override
	public String toString(){
		return "[type: " + _type.toString() + "]  [content: " + _content + "]";
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

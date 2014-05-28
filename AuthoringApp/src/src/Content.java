
public class Content {

	public enum ContentType{ Text, Image, Animation }
	public ContentType _type;
	public String _content;
	
	public Content( ContentType type, String content ){
		_type=type;
		_content=new String(content);
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

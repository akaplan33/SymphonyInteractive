import java.io.*;
import java.util.Scanner;


public class FileManager {
	public static Content extractAnno(File from)
	{
		Scanner scan;
		String content;
		int time, type;
		Content.ContentType cType = null;

		try {
			scan = new Scanner(from);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		type = Integer.parseInt(scan.nextLine());
		content = scan.nextLine();
		time = Integer.parseInt(scan.nextLine());
		
		switch(type)
		{
		case 0:
			cType = null;
		case 1:
			cType = Content.ContentType.Text;
			break;
		case 2:
			cType = Content.ContentType.Image;
			break;
		case 3:
			cType = Content.ContentType.Animation;
			break;
		}
		
		return new Content(cType, content, time);
		
	}
	
	public static void saveAnno(Content save, String fileName)
	{
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(fileName, "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		if(save._type == Content.ContentType.Text)
			writer.println("1");
		else if(save._type == Content.ContentType.Image)
			writer.println("2");
		else if(save._type == Content.ContentType.Animation)
			writer.println("3");
		else
			writer.println("0");
		
		writer.println(save._content);
		writer.println(String.valueOf(save.getTime()));
		writer.close();
	}
}

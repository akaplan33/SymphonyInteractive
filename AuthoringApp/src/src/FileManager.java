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
}

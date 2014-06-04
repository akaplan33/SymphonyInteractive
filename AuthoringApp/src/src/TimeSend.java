
//Eric Rothman

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.Timer;


import java.awt.event.*;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//need to change all content of name of Alex's class Notes?
public class TimeSend {
	private ArrayList<Content> lists;
	private Timer time;
	private int index, second;
	private String fileName;
	private DefaultListModel<Content> listOfNotes;
	private boolean flag=false;
	
	//constructor
	public TimeSend(ArrayList<Content> listOfann)
	{
		lists = listOfann;
		time = new Timer(1000, new CountListener());
		index=0;
		second=0;
		time.start();
	}
	@SuppressWarnings("unchecked")
	public TimeSend()
	{
		try{
        FileInputStream fileIn = new FileInputStream("notesList.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        listOfNotes = (DefaultListModel<Content>) in.readObject();
        in.close();
        fileIn.close();
		Object[] list;
		list =  listOfNotes.toArray();
		lists=new ArrayList<Content>();
		for (int i = 0; i < list.length; i++) {

				lists.add((Content) list[i]);
			
		}
		sort(lists);
		 }catch(IOException i)
	      {
	         i.printStackTrace();
	         try{
	        	 DefaultListModel<Content> listOfNote = new DefaultListModel<Content>();
	        	 Content co = new Content(Content.ContentType.Text,"filler", 10);
	        	 listOfNote.addElement(co);
	        	 FileOutputStream fileOut = new FileOutputStream("notesList.ser");
	        	 ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        	 out.writeObject(listOfNote);
	        	 out.close();
	        	 fileOut.close();
	        	 }catch(IOException j)
	             {
	                 j.printStackTrace();
	             }
	         return;
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("Default List Model class could not be found");
	         c.printStackTrace();
	         return;
	      }
		time = new Timer(1000, new CountListener());
		index=0;
		second=0;
		time.start();
	}
	public TimeSend(String nameOfFile)
	{
		flag=true;
		fileName=nameOfFile;
		try{
        FileInputStream fileIn = new FileInputStream(fileName);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        listOfNotes = (DefaultListModel<Content>) in.readObject();
        in.close();
        fileIn.close();
		Content[] list;
		list = (Content[]) listOfNotes.toArray();
		for (int i = 0; i < list.length; i++) {

				lists.add(list[i]);
			
		}
		sort(lists);
		 }catch(IOException i)
	      {
	         i.printStackTrace();
	         try{
	        	 DefaultListModel<Content> listOfNote = new DefaultListModel<Content>();
	        	 Content co = new Content(Content.ContentType.Text,"filler", 10);
	        	 listOfNote.addElement(co);
	        	 FileOutputStream fileOut = new FileOutputStream(fileName);
	        	 ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        	 out.writeObject(listOfNote);
	        	 out.close();
	        	 fileOut.close();
	        	 }catch(IOException j)
	             {
	                 j.printStackTrace();
	             }
	         return;
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("Default List Model class could not be found");
	         c.printStackTrace();
	         return;
	      }
		time = new Timer(1000, new CountListener());
		index=0;
		second=0;
		time.start();
	}
	//constructor if a defalut list model is used instead of an already exsisting array list
	public TimeSend(DefaultListModel<Content> e)
	{
		Content[] list;
		list = (Content[]) e.toArray();
		for(int i=0; i<list.length; i++)
		{

				lists.add(list[i]);
			
		}
		sort(lists);
		time = new Timer(1000, new CountListener());
		index=0;
		second=0;
		time.start();
	}
	
	//returns current time
	public int getCurrentTime()
	{
		return second;
	}
	//adds an annotation to the list then calls sort
	public void addAnn(Content ann) {
		lists.add(ann);
		sort(lists);
	}
	
	//saves the array list by setting a static default list model to it.
	public void save()
	{
	listOfNotes.clear();
	for(int i =0; i<lists.size(); i++)
	{
	listOfNotes.addElement(lists.get(i));
	}
	if(flag)
	{
		try{
			 FileOutputStream fileOut = new FileOutputStream(fileName);
			 ObjectOutputStream out = new ObjectOutputStream(fileOut);
			 out.writeObject(listOfNotes);
			 out.close();
			 fileOut.close();
			 }catch(IOException i)
		     {
		         i.printStackTrace();
		     }
	}
	else{
	try{
	 FileOutputStream fileOut = new FileOutputStream("notesList.ser");
	 ObjectOutputStream out = new ObjectOutputStream(fileOut);
	 out.writeObject(listOfNotes);
	 out.close();
	 fileOut.close();
	 }catch(IOException i)
     {
         i.printStackTrace();
     }
	}
	}
	public Content getNextNote()
	{
		return lists.get(index+1);
	}
	//sorts array list by time
	private void sort(ArrayList<Content> listOfann) {
		int min;
		Content temp;
		for (int i = 0; i < listOfann.size(); i++) {
			min = i;
			for (int scan = i + 1; scan < listOfann.size(); scan++)
				if (listOfann.get(scan).getTime() < listOfann.get(min).getTime())
					min = scan;
			temp = listOfann.get(min);
			listOfann.set(min, listOfann.get(i));
			listOfann.set(i, temp);
		}
	}
	public Content getNote()
	{
		return lists.get(index);
	}
	//listener. makes time go up and sends annotation
	private class CountListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			second++;
			if(index>=lists.size())
				time.stop();
			if (lists.get(index+1).getTime() == second) {
				index++;
			}
		}
	}
}

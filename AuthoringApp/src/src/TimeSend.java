//Eric Rothman

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.Timer;


import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//need to change all content of name of Alex's class Notes?
public class TimeSend {
	private ArrayList<Note> lists;
	private Timer time;
	private int index, second;
	private String fileName;
	private DefaultListModel<Note> listOfNotes;
	//constructor
	public TimeSend(ArrayList<Note> listOfann)
	{
		lists = listOfann;
		time = new Timer(1000, new CountListener());
		index=0;
		second=0;
		time.start();
	}
	public TimeSend(String nameOfFile)
	{
		fileName=nameOfFile;
		try{
        FileInputStream fileIn = new FileInputStream("fileName");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        listOfNotes = (DefaultListModel) in.readObject();
        in.close();
        fileIn.close();
		Note[] list;
		list = (Note[]) listOfNotes.toArray();
		for (int i = 0; i < list.length; i++) {

				lists.add(list[i]);
			
		}
		sort(lists);
		time = new Timer(1000, new CountListener());
		index=0;
		second=0;
		time.start();
		 }catch(IOException i)
	      {
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("Default List Model class could not be found");
	         c.printStackTrace();
	         return;
	      }
	}
	//constructor if a defalut list model is used instead of an already exsisting array list
	public TimeSend(DefaultListModel<Note> e)
	{
		Note[] list;
		list = (Note[]) e.toArray();
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
	public void addAnn(Note ann) {
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
	//sorts array list by time
	private void sort(ArrayList<Note> listOfann) {
		int min;
		Note temp;
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
	//listener. makes time go up and sends annotation
	private class CountListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			second++;
			if(index>=lists.size())
				time.stop();
			if (lists.get(index).getTime() == second) {
				// send lists.get(index)
				index++;
			}
		}
	}
}

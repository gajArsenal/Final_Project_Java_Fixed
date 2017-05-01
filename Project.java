import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Project extends BaseClass{

	private Scanner fileScanner;
	private FileOutputStream stream;
	
	private String fileInfo = "";
	
	/**
	 * contructor for the Project
	 * @throws IOException
	 */
	public Project() throws IOException{
		super();
	}
	

	@Override
	public void getProjectInfo() {
		// TODO Auto-generated method stub
		System.out.println("super class: " + super.getClass());
		System.out.println("Text Input: " + '\n' + super.field.getText());
	}
	
	/**
	 * Main method for the project
	 * @param args
	 * @throws IOException
	 */
	public static void main(String [] args) throws IOException{
		new Project();
	}

	/**
	 * If a key is typed
	 * tells the program to run a series of tests to determine 
	 * if the text in the text area fits a certain criteria
	 */
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		super.field.setText(super.field.getText() + e.getKeyChar());
		String text = super.field.getText();
		int size = text.length();
		super.field.setText("");
		for(int i = 0; i < size-1; i++){
			super.field.setText(super.field.getText() + text.charAt(i));
		}
		
		//System.out.println("length: " + super.field.getText().length());
		// tests to see if the text on that specific line has reached 35 characters
		// if so, the text will be displayed on the next line
		if(super.field.getText().length() % 35 == 0){
			super.field.setText(super.field.getText() + "\n");
			System.out.println("number of lines: " + super.field.getLineCount());
			if(super.field.getLineCount() > 11){
				JOptionPane.showMessageDialog(null, "Your Text Field has exceeded the maximum line limit: ");
				super.field.setEnabled(false);
			}
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public abstract class BaseClass implements ActionListener, KeyListener{

	public abstract void getProjectInfo();
	
	static final String name = "Board Object";
	protected JFrame frame = new JFrame(name);
	
	protected JPanel northPanel = new JPanel();
	protected JPanel southPanel = new JPanel();
	
	protected JButton saveButton = new JButton("Save Button");
	protected JButton loadButton = new JButton("Load File");
	protected JButton clearButton = new JButton("Clear File");
	protected JButton quitButton = new JButton("Quit Project");
	protected JButton newProject = new JButton("New Project");
	
	protected JTextArea field = new JTextArea();
	
	protected File dataFile;
	/**
	 * Base Class Constructor
	 * instantiates many of the objects that are either components of the frame
	 * or the components of components on the frame
	 * @throws FileNotFoundException 
	 */
	public BaseClass() throws FileNotFoundException{
		
		Scanner input = new Scanner(System.in);
		
		String userFilename = JOptionPane.showInputDialog("Enter A Name For Your Text File: " + '\n' + "The extension (.txt) will be added to your filename");
		
		//String yes = JOptionPane.showInputDialog("Is This a File You Want To Load? >> ");
		
		this.dataFile = new File(userFilename + ".txt");
		try{
			Scanner introFile = new Scanner(this.dataFile);
			
			while(introFile.hasNextLine()){
				this.field.setText(this.field.getText() + '\n' + introFile.nextLine());
			}
			
		}catch(Exception e){
			
			System.out.println("that file is not in system");
			
		}
		
		frame.setSize(500,200);
		
		frame.setLayout(new GridLayout(1,2));
		
		frame.add(this.northPanel);
		frame.add(this.southPanel);
		
		BorderLayout border = new BorderLayout();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.northPanel.setLayout(border);
		this.southPanel.setLayout(new GridLayout(5,1));
		
		this.northPanel.add(field, border.CENTER);
		
		this.field.addKeyListener(this);
		this.southPanel.add(saveButton);
		this.southPanel.add(loadButton);
		this.southPanel.add(clearButton);
		this.southPanel.add(quitButton);
		this.southPanel.add(newProject);
		this.clearButton.addActionListener(this);
		this.saveButton.addActionListener(this);
		this.loadButton.addActionListener(this);
		this.quitButton.addActionListener(this);
		this.newProject.addActionListener(this);
		
		this.northPanel.setBackground(Color.BLUE);
		this.southPanel.setBackground(Color.GREEN);
		
		frame.setVisible(true);
		
	}
	/**
	 * If an action is performed
	 */
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		JButton button = (JButton)event.getSource();
		System.out.println(button.getText());
		String objectText = button.getText();
		
		if(objectText.equals("Save Button")){
			try {
				FileOutputStream stream = new FileOutputStream(this.dataFile);
				for(int i = 0; i < this.field.getText().length(); i++){
					stream.write(this.field.getText().charAt(i));
				}
				JOptionPane.showMessageDialog(null, this.dataFile.getName() + " file has been saved");
				stream.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(objectText.equals("Load File")){
			String loadedFile = "";
			String input = JOptionPane.showInputDialog("Enter the Name Of Your File: " + "\n" + "Extension (.txt) will be added to the file");
			try{
				Scanner inputScanner = new Scanner(new File(input + ".txt"));
				this.dataFile = new File(input);
				while(inputScanner.hasNextLine()){
					loadedFile += (inputScanner.nextLine() + '\n');
				}
				this.field.setText(loadedFile);
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "There does not appear to be a file under that specific name");
				System.out.println(input + " does not appear to be a file");
			}
		}
		
		if(objectText.equals("Clear File")){
			this.field.setText("");
			JOptionPane.showMessageDialog(null, " file has been cleared (this method not save the file)");
			if(this.field.isEnabled() == false){
				this.field.setEnabled(true);
			}
		}
		/**
		 * If the user decides to quit the program
		 * hides the frame and disables the frame
		 */
		if(objectText.equals("Quit Project")){
			JOptionPane.showMessageDialog(null, "Ending Project");
			this.frame.setEnabled(false);
			this.frame.setVisible(false);
		}
		
		if(objectText.equals("New Project")){
			JOptionPane.showMessageDialog(null, "Starting a new project");
			try {
				new Project();
				this.frame.setEnabled(false);
				this.frame.setVisible(false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "There appears to be an issue starting a new project...");
				e.printStackTrace();
			}
		}	
	}
}

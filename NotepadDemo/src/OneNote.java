import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class OneNote extends JFrame {

	JTextArea textArea = new JTextArea();
	private JPanel contentPane;
	JScrollPane scrollPane = new JScrollPane();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OneNote frame = new OneNote();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void resizeTextArea()
	{
		scrollPane.setSize(this.getWidth()-20,this.getHeight()-50);
		textArea.setSize(this.getWidth()-20,this.getHeight()-50);
	}

	
	private void saveFile(){
		String currentDirPath = "D:\\testout.txt";
		JFileChooser saveDialog = new JFileChooser(currentDirPath);
		saveDialog.showSaveDialog(this);
		File file = saveDialog.getSelectedFile();
		String path = file.getPath();
		try {
			FileOperationsDemo.writeFile(path,textArea.getText());
			this.setTitle(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			textArea.setText(e.toString());
			//e.printStackTrace();
		}
	}
	private void openFile(){
		String currentDirPath = "D:\\testout.txt";
		JFileChooser openDialog = new JFileChooser(currentDirPath);
		openDialog.showOpenDialog(this);
		File file = openDialog.getSelectedFile();
		String path = file.getPath();
		try {
			textArea.setText(FileOperationsDemo.readFile(path));
		} catch (IOException e) {
			textArea.setText(e.toString());
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		//System.out.println(file.getPath());
	}

	public OneNote()
	{
		
		this.addComponentListener(new ComponentAdapter() 
		{
			@Override
			public void componentResized(ComponentEvent e) 
			{
				resizeTextArea();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 296);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu File = new JMenu("File");
		menuBar.add(File);
		
		JMenuItem newItem = new JMenuItem("New");
		File.add(newItem);
		
		JMenuItem open = new JMenuItem("Open");
		File.add(open);
		open.addActionListener((e)->{
			openFile();
		});
		JMenuItem save = new JMenuItem("Save");
		File.add(save);
		save.addActionListener((e)->{
			saveFile();
		});
		
		JMenuItem SaveAs = new JMenuItem("SaveAs");
		File.add(SaveAs);
		SaveAs.addActionListener((e)->{
			saveFile();
		
		JMenuItem Exit = new JMenuItem("Exit");
		File.add(Exit);
		});
		
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		scrollPane.setBounds(16, 11, 418, 239);
		contentPane.add(scrollPane);
		contentPane.add(textArea);
		
		
		textArea.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		textArea.setBounds(10, -14, 424, 264);
		
}
}

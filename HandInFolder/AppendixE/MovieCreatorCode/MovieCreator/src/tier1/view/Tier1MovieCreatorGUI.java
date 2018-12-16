package tier1.view;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JOptionPane;


import tier1.controller.Tier1MovieCreatorController;

public class Tier1MovieCreatorGUI implements Tier1MovieCreatorView, Runnable {

	private Tier1MovieCreatorController controller;
	
	private JFrame frame;

	/**
	 * Create the application.
	 */
	public Tier1MovieCreatorGUI() {
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Stefan\\Desktop\\'O'.jpg"));
		frame.getContentPane().setBackground(new Color(105, 105, 105));
		frame.getContentPane().setLayout(null);
		
		JButton createMovie = new JButton("Create Movie");
		createMovie.setBackground(new Color(255, 145, 164));
		createMovie.setContentAreaFilled(false);
        createMovie.setOpaque(true);
		createMovie.setBounds(37, 108, 126, 73);
		createMovie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                  createMovieActionPerformed(evt);
            }
        });
		frame.getContentPane().add(createMovie);
		
		JButton listMovies = new JButton("List Movies");
		listMovies.setBackground(new Color(255, 145, 164));
		listMovies.setContentAreaFilled(false);
		listMovies.setOpaque(true);
		listMovies.setBounds(229, 108, 126, 73);
		frame.getContentPane().add(listMovies);
		listMovies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listMoviesActionPerformed(evt);
          }
      });
		
		JButton quitButton = new JButton("Quit");
		quitButton.setBackground(new Color(255, 145, 164));
		quitButton.setContentAreaFilled(false);
		quitButton.setOpaque(true);
		quitButton.setBounds(425, 108, 126, 73);
		frame.getContentPane().add(quitButton);
		quitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitButtonActionPerformed(evt);
          }
      });
		
		frame.setBackground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 600, 357);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void createMovieActionPerformed(java.awt.event.ActionEvent evt) {
		controller.execute(1);	
	}
	
	private void listMoviesActionPerformed(java.awt.event.ActionEvent evt) {
		controller.execute(2);	
	}
	
	private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {
		controller.execute(0);	
	}
	
	@Override
	public String get(String string) {
		return (String)JOptionPane.showInputDialog(string);
	}

	@Override
	public void show(String text) {
		JOptionPane.showMessageDialog(null, text);	
	}

	@Override
	public void startView(Tier1MovieCreatorController controller) {
		this.controller = controller;
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		initialize();
		this.frame.setVisible(true);
	}
}

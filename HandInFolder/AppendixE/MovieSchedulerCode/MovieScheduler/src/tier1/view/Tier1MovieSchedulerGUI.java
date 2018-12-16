package tier1.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;


import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import tier1.controller.Tier1MovieSchedulerController;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.JList;

public class Tier1MovieSchedulerGUI implements Tier1MovieSchedulerView{

	private JFrame frame;
	private JTextField textField_3;
	private JTextArea textArea;
	private JTextArea textArea_1;
	private JTextArea textArea_2;
	private JTextArea textArea_3;
	private JTextArea textArea_4;
	private Tier1MovieSchedulerController controller;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_5;
	private JTextField textField_6;
	private DefaultListModel<String> days;
	private DefaultListModel<String> times;
	private JList<String> list;
	private JList<String> list_1;

	/**
	 * Constructor to create the application.
	 */
	public Tier1MovieSchedulerGUI() {
		addDays();

		addTimes();
		initialize();
	}

	/**
	 * Initialize the contents of the frame for the Movie Scheduler GUI.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setForeground(Color.YELLOW);
		frame.setBackground(Color.YELLOW);
		frame.setBounds(100, 100, 803, 556);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.YELLOW));
		tabbedPane.setBackground(Color.WHITE);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.MAGENTA));
		panel.setBackground(Color.MAGENTA);
		tabbedPane.addTab("Rooms", null, panel, null);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 0, 255));
		panel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.MAGENTA));
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnNewButton_4 = new JButton("Show");
		btnNewButton_4.setBackground(Color.LIGHT_GRAY);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.execute(4);
			}
		});
		btnNewButton_4.setBounds(494, 455, 89, 23);
		panel_2.add(btnNewButton_4);
		
		JButton btnInput = new JButton("Input");
		btnInput.setBackground(Color.LIGHT_GRAY);
		btnInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.addRoom(textField_2.getText(), textField_5.getText());
			}
		});
		btnInput.setBounds(591, 455, 89, 23);
		panel_2.add(btnInput);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBackground(Color.LIGHT_GRAY);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.deleteRoom(textField_6.getText());
			}
		});
		btnDelete.setBounds(683, 455, 89, 23);
		panel_2.add(btnDelete);
		
		textField_2 = new JTextField();
		textField_2.setBounds(10, 296, 96, 20);
		panel_2.add(textField_2);
		textField_2.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(197, 296, 271, 23);
		panel_2.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBackground(Color.WHITE);
		textField_6.setBounds(676, 296, 96, 20);
		panel_2.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblSize = new JLabel("Size");
		lblSize.setBounds(10, 271, 48, 14);
		panel_2.add(lblSize);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(197, 271, 73, 14);
		panel_2.add(lblDescription);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(676, 271, 48, 14);
		panel_2.add(lblId);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 11, 752, 249);
		panel_2.add(scrollPane_2);
		
		textArea = new JTextArea();
		scrollPane_2.setViewportView(textArea);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.MAGENTA);
		tabbedPane.addTab("Schedule", null, panel_1, null);
		panel_1.setLayout(null);
		
		textField_3 = new JTextField();
		textField_3.setBounds(10, 233, 89, 20);
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Input");
		btnNewButton_2.setBackground(Color.LIGHT_GRAY);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.addScheduledMovie(textField.getText(), textField_3.getText(), list.getSelectedValue(), list_1.getSelectedValue());
			}
		});
		btnNewButton_2.setBounds(677, 420, 89, 23);
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Show");
		btnNewButton_3.setBackground(Color.LIGHT_GRAY);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.execute(5);
			}
		});
		btnNewButton_3.setBounds(578, 420, 89, 23);
		panel_1.add(btnNewButton_3);
		
		textArea_3 = new JTextArea();
		textArea_3.setLineWrap(true);
		textArea_3.setWrapStyleWord(true);
		textArea_3.setBounds(571, 11, 201, 199);
		panel_1.add(textArea_3);
		
		textField = new JTextField();
		textField.setBounds(290, 233, 96, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.setBackground(Color.LIGHT_GRAY);
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.execute(2);
			}
		});
		btnSend.setBounds(578, 455, 89, 23);
		panel_1.add(btnSend);
		
		JButton btnRestart = new JButton("Reset");
		btnRestart.setBackground(Color.LIGHT_GRAY);
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.execute(3);
			}
		});
		btnRestart.setBounds(677, 454, 89, 23);
		panel_1.add(btnRestart);
		
		JLabel lblMovieid = new JLabel("MovieId");
		lblMovieid.setBounds(10, 218, 48, 14);
		panel_1.add(lblMovieid);
		
		JLabel lblRoomid = new JLabel("RoomId");
		lblRoomid.setBounds(290, 221, 48, 14);
		panel_1.add(lblRoomid);
		
		JLabel lblDay = new JLabel("Day");
		lblDay.setBounds(570, 218, 48, 14);
		panel_1.add(lblDay);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setBounds(670, 218, 48, 14);
		panel_1.add(lblTime);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(8, 11, 270, 199);
		panel_1.add(scrollPane);
		
		textArea_1 = new JTextArea();
		scrollPane.setViewportView(textArea_1);
		textArea_1.setLineWrap(true);
		textArea_1.setWrapStyleWord(true);
		textArea_1.setEditable(false);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(290, 11, 271, 199);
		panel_1.add(scrollPane_1);
		
		textArea_2 = new JTextArea();
		scrollPane_1.setViewportView(textArea_2);
		textArea_2.setLineWrap(true);
		textArea_2.setWrapStyleWord(true);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 276, 540, 210);
		panel_1.add(scrollPane_3);
		
		textArea_4 = new JTextArea();
		scrollPane_3.setViewportView(textArea_4);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(571, 234, 89, 23);
		panel_1.add(scrollPane_4);
		
		list = new JList<String>(days);
		scrollPane_4.setViewportView(list);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(673, 234, 97, 23);
		panel_1.add(scrollPane_5);
		
		list_1 = new JList<String>(times);
		scrollPane_5.setViewportView(list_1);
	}



	/* (non-Javadoc)
	 * @see tier1.view.Tier1MovieSchedulerView#showRooms(java.lang.String)
	 */
	@Override
	public void showRooms(String text) {
		textArea.setText(text);
		textArea_2.setText(text);
	}
	
	/* (non-Javadoc)
	 * @see tier1.view.Tier1MovieSchedulerView#showSchedule(java.lang.String)
	 */
	@Override
	public void showSchedule(String text) {
		textArea_4.setText(text);
	}

	/* (non-Javadoc)
	 * @see tier1.view.Tier1MovieSchedulerView#startView(tier1.controller.Tier1MovieSchedulerController)
	 */
	@Override
	public void startView(Tier1MovieSchedulerController controller) {		
		this.controller = controller;
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		controller.execute(1);
	}

	/* (non-Javadoc)
	 * @see tier1.view.Tier1MovieSchedulerView#showMovies(java.lang.String)
	 */
	@Override
	public void showMovies(String text) {
		textArea_1.setText(text);
	}

	/* (non-Javadoc)
	 * @see tier1.view.Tier1MovieSchedulerView#showTime(java.lang.String)
	 */
	@Override
	public void showTime(String text) {
		textArea_3.setText(text);
	}
	
	/**
	 * Method to populate the Days arraylist with days of the week
	 */
	public void addDays() {
		days = new DefaultListModel<>();
		days.addElement("Monday");
		days.addElement("Tuesday");
		days.addElement("Wednesday");
		days.addElement("Thursday");
		days.addElement("Friday");
		days.addElement("Saturday");
		days.addElement("Sunday");
	}

	/**
	 * Method to populate the Times arraylist with times available for scheduling
	 */
	public void addTimes() {
		times = new DefaultListModel<>();
		times.addElement("10:00");
		times.addElement("13:00");
		times.addElement("16:00");
		times.addElement("20:00");
	}
}

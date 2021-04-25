package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import TP5.FSMIO;
import TP5.FSMIOString;
import TP5.UnidefinedTransitionException;

public class FSMIOStringviewer<T1, T2> extends JFrame {

	private static final long serialVersionUID = 1L;

	private JMenuBar barMenu;

	private JMenu file;
	private JMenuItem open;
	private JMenuItem close;
	private JMenuItem quit;

	private JMenu transition;
	private JMenuItem reset;
	private JMenuItem cancel;
	private ArrayList<JMenuItem> entreeL;
	private FSMIO<T1, T2> fsm;

	private JScrollPane panel;
	private JTextArea editeur;

	private JLabel pathFolder;
	private JPanel panelgeneral;
	private JLabel currentState;

	@SuppressWarnings("deprecation")
	public FSMIOStringviewer(String name) {
		super(name);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		file = new JMenu("FILE");
		open = new JMenuItem("open");
		open.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				openFile();

			}
		});
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		close = new JMenuItem("close");
		close.setEnabled(false);
		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				closeFile();

			}
		});
		close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));

		quit = new JMenuItem("quit");
		quit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				quit();

			}
		});
		quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));

		file.add(open);
		file.add(close);
		file.add(quit);

		transition = new JMenu("Transition");
		entreeL = new ArrayList<JMenuItem>();
		reset = new JMenuItem("reset");
		cancel = new JMenuItem("cancel");
		transition.add(reset);
		transition.add(cancel);

		barMenu = new JMenuBar();

		barMenu.add(file);
		this.setJMenuBar(barMenu);

		editeur = new JTextArea();
		panel = new JScrollPane(editeur);
		editeur.setLineWrap(true);
		editeur.setEditable(false);

		pathFolder = new JLabel();
		pathFolder.setVisible(false);

		currentState = new JLabel();

		/// declaration du panel principal
		panelgeneral = new JPanel();
		panelgeneral.setLayout(new BorderLayout());
		panelgeneral.add(pathFolder, BorderLayout.NORTH);
		panelgeneral.add(panel, BorderLayout.CENTER);
		panelgeneral.add(currentState, BorderLayout.SOUTH);

		this.add(panelgeneral);
		this.setContentPane(panelgeneral);
		this.setMinimumSize(new Dimension(300, 200));
		this.setVisible(true);
		this.pack();

	}

	private void openFile() {
		int retID;
		JFileChooser box = new JFileChooser(".");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Fsm string format", "fsm", "serr");
		box.setFileFilter(filter);
		retID = box.showOpenDialog(this);
		if (retID == JFileChooser.APPROVE_OPTION) {
			File f = box.getSelectedFile();
			// editeur.read(new FileReader(f), null);

			fsm = new FSMIO<T1, T2>(f.getAbsolutePath()).getFSMIO();

			// fsm = ((FSMIO<T1, T2>) new FSMIOString(f.getAbsolutePath()).getFSM());

			editeur.setText(fsm.toString());

			for (T1 s : fsm.getEnter()) {
				if (!alreadyContains(s, entreeL)) {
					JMenuItem item = new JMenuItem(s.toString());
					item.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								T2 out = fsm.doTransition(s);
								currentState.setText(
										"New State: " + fsm.getCurrentState() + " Output: " + out.toString());
							} catch (UnidefinedTransitionException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}
					});
					entreeL.add(item);
					transition.add(item);
				}
			}

			barMenu.add(transition);
			close.setEnabled(true);
			pathFolder.setText("File: " + f.getName());
			pathFolder.setVisible(true);
			open.setEnabled(false);
			currentState.setText("FSMIO loaded. Current Sate: " + fsm.getCurrentState());
			currentState.setEnabled(true);
			this.pack();

		}

	}

	private void closeFile() {
		close.setEnabled(false);
		barMenu.remove(transition);
		pathFolder.setText("No file Displayed");
		editeur.setText("");
		currentState.setText("");
		open.setEnabled(true);
	}

	private void quit() {
		this.closeFile();
		System.exit(EXIT_ON_CLOSE);
	}

	private boolean alreadyContains(T1 s, ArrayList<JMenuItem> item) {
		for (JMenuItem i : item) {
			if (i.getText().equals(s))
				return true;
		}
		return false;
	}
}

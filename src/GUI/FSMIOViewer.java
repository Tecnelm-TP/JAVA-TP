package GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FSMIOViewer<T1, T2> extends JFrame {
	private static final long serialVersionUID = 1L;

	private JMenuBar barMenu;

	private JMenu file;
	private JMenuItem open;
	private JMenuItem close;
	private JMenuItem quit;

	private JMenu transition;
	private JMenuItem reset;
	private FSMIOPanel<?, ?> fsm;

	@SuppressWarnings("deprecation")
	public FSMIOViewer(String name) {
		super(name);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		/// ajout des menu
		file = new JMenu("FILE");
		open = new JMenuItem("open");/// ajout de l'action a exectuter
		open.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				openFile();

			}
		});
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK)); /// permet d'utiliser le
																							/// clavier pour rapidement
																							/// executer le code
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
		reset = new JMenuItem("reset");
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fsm.reset();

			}
		});

		transition.add(reset);
		transition.addSeparator();
		transition.setVisible(false);

		barMenu = new JMenuBar();

		barMenu.add(file);
		barMenu.add(transition);

		fsm = new FSMIOPanel<String, String>(null, null); /// creation d'un FSM vide comme panel de base

		this.add(fsm);
		this.setContentPane(fsm);
		this.setJMenuBar(barMenu);

		this.setMinimumSize(new Dimension(300, 200));
		this.setVisible(true);
		this.pack();

	}

	private void openFile() {/// fonction executer lors de l'appuis dans le menu sur open
		int retID;
		JFileChooser box = new JFileChooser(".");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Fsm string format", "fsm", "serr"); /// creation
																											/// du
																											/// filtre
		box.setFileFilter(filter);
		retID = box.showOpenDialog(this);
		if (retID == JFileChooser.APPROVE_OPTION) {
			File f = box.getSelectedFile();
			// editeur.read(new FileReader(f), null);
			this.remove(fsm);
			fsm = new FSMIOPanel<T1, T2>(f, transition); /// création du FSM

			transition.setVisible(true);
			close.setEnabled(true);
			open.setEnabled(false);

			this.add(fsm);
			this.setContentPane(fsm);
		}

	}

	public void setFsm(FSMIOPanel<?, ?> fsm) {
		this.remove(this.fsm);
		fsm.withContainer(transition).create();
		transition.setVisible(true);
		close.setEnabled(true);
		open.setEnabled(false);

		this.add(fsm);
		this.setContentPane(fsm);
	}

	private void closeFile() {
		close.setEnabled(false);
		transition.setVisible(false);
		fsm.closeFile();
		open.setEnabled(true);
	}

	private void quit() {
		this.closeFile();
		fsm.quit();
		System.exit(EXIT_ON_CLOSE);
	}
}

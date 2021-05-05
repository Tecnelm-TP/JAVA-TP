package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import TP5.FSMIO;
import TP5.State;
import TP5.UnidefinedTransitionException;

public class FSMIOPanel<T1, T2> extends JPanel {

	private static final long serialVersionUID = 1L;

	private ArrayList<AbstractButton> entreeL;
	private FSMIO<T1, T2> fsm;

	private JScrollPane panel;
	private JTextArea editeur;

	private JLabel pathFolder;
	private JLabel currentState;
	private JComponent transition;
	private T2 output;
	private State currentstate;
	private File f;

	public FSMIOPanel(File f, JComponent transition) {

		this();
		this.f = f;
		this.transition = transition;
		this.create();

	}

	public FSMIOPanel() {

		super();
		this.f = null;
		this.transition = null;

		editeur = new JTextArea();
		panel = new JScrollPane(editeur);
		editeur.setLineWrap(true);
		editeur.setEditable(false);

		pathFolder = new JLabel();
		pathFolder.setVisible(true);

		currentState = new JLabel();
		entreeL = new ArrayList<AbstractButton>();

		this.setLayout(new BorderLayout());/// placement des élements
		this.add(pathFolder, BorderLayout.NORTH);
		this.add(panel, BorderLayout.CENTER);
		this.add(currentState, BorderLayout.SOUTH);

		this.setMinimumSize(new Dimension(300, 200));
		this.setVisible(true);

	}

	public FSMIOPanel<T1, T2> create() {
		if (f != null) {
			fsm = new FSMIO<T1, T2>(f.getAbsolutePath()).getFSMIO();
			editeur.setText(fsm.toString());

			for (T1 s : fsm.getEnter()) {
				if (!alreadyContains(s, entreeL)) {
					AbstractButton item = new JMenuItem(s.toString());
					item.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								output = fsm.doTransition(s);
								currentstate = fsm.getCurrentState();
								currentState.setText("New State: " + currentstate + " Output: " + output.toString());
							} catch (UnidefinedTransitionException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}
					});
					/// ajout des entrees pour l'execution
					entreeL.add(item);
					if (transition != null)
						transition.add(item);
				}
			}

			pathFolder.setText("File: " + f.getName());
			currentState.setText("FSMIO loaded. Current Sate: " + fsm.getCurrentState());
		} else
			closeFile();
		return this;
	}

	public FSMIOPanel<T1, T2> withFile(File f) {
		this.f = f;
		return this;
	}

	public FSMIOPanel<T1, T2> withContainer(JComponent c) {
		this.transition = c;
		return this;
	}

	protected void closeFile() {
		pathFolder.setText("No file Displayed");
		editeur.setText("");
		currentState.setText("");
		if (transition != null)
			for (JComponent item : entreeL) {
				this.transition.remove(item);
			}

	}

	protected void quit() {
		this.closeFile();
	}

	private boolean alreadyContains(T1 s, ArrayList<AbstractButton> item) {
		for (AbstractButton i : item) {
			if (i.getText().equals(s))
				return true;
		}
		return false;
	}

	/**
	 * @return the output
	 */
	public T2 getOutput() {
		return output;
	}

	/**
	 * @return the currentstate
	 */
	public State getCurrentstate() {
		return currentstate;
	}

	/**
	 * @return the fsm
	 */
	public FSMIO<T1, T2> getFsm() {
		return fsm;
	}

	public void reset() {
		this.fsm.reset();
		this.currentstate = fsm.getCurrentState();
		currentState.setText("FSM Reset . current State : " + currentstate);

	}

}

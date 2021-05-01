package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import TP5.FSMIO;
import TP5.State;
import TP5.UnidefinedTransitionException;

public class FSMIOPanel<T1, T2> extends JPanel {
	

	private static final long serialVersionUID = 1L;


	private ArrayList<JMenuItem> entreeL;
	private FSMIO<T1, T2> fsm;

	private JScrollPane panel;
	private JTextArea editeur;

	private JLabel pathFolder;
	private JLabel currentState;
	private JMenu transition;
	private T2 output;
	private State currentstate;

	
	public FSMIOPanel(File f,JMenu transition) {
		super();
		
		editeur = new JTextArea();
		panel = new JScrollPane(editeur);
		editeur.setLineWrap(true);
		editeur.setEditable(false);

		pathFolder = new JLabel();
		pathFolder.setVisible(true);

		currentState = new JLabel();
		
		this.transition = transition;
		entreeL = new ArrayList<JMenuItem>();
		if(f == null)
		{
			this.closeFile();
		}
		else
			this.openFile(f);
		

		/// declaration du panel principal

		this.setLayout(new BorderLayout());/// placement des élements 
		this.add(pathFolder, BorderLayout.NORTH);
		this.add(panel, BorderLayout.CENTER);
		this.add(currentState, BorderLayout.SOUTH);

		this.setMinimumSize(new Dimension(300, 200));
		this.setVisible(true);


	}

	private void openFile(File f) {
		
		
			fsm = new FSMIO<T1, T2>(f.getAbsolutePath()).getFSMIO();

			editeur.setText(fsm.toString());

			for (T1 s : fsm.getEnter()) {
				if (!alreadyContains(s, entreeL)) {
					JMenuItem item = new JMenuItem(s.toString());
					item.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								output = fsm.doTransition(s);
								currentstate =fsm.getCurrentState();
								currentState.setText(
										"New State: " + currentstate + " Output: " + output.toString());
							} catch (UnidefinedTransitionException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}
					});
					/// ajout des entrees pour l'execution 
					entreeL.add(item);
					transition.add(item);
				}
			}

			pathFolder.setText("File: " + f.getName());
			currentState.setText("FSMIO loaded. Current Sate: " + fsm.getCurrentState());
			
		}

	

	protected void closeFile() {
		pathFolder.setText("No file Displayed");
		editeur.setText("");
		currentState.setText("");
		for(JMenuItem item : entreeL)
		{
			this.transition.remove(item);
		}

	}

	protected void quit() {
		this.closeFile();
	}

	private boolean alreadyContains(T1 s, ArrayList<JMenuItem> item) {
		for (JMenuItem i : item) {
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
	

}

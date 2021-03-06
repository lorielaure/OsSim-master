package edu.upc.fib.ossim.authentification.view;

import java.awt.Container;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import edu.upc.fib.ossim.AppSession;
import edu.upc.fib.ossim.bean.Etudiant;
import edu.upc.fib.ossim.bean.Professeur;
import edu.upc.fib.ossim.dao.DAOFactory;
import edu.upc.fib.ossim.dao.EtudiantDAO;
import edu.upc.fib.ossim.dao.ProfesseurDAO;
import edu.upc.fib.ossim.mcq.MCQSession;
import edu.upc.fib.ossim.utils.EscapeDialog;

public class AuthPanel extends EscapeDialog implements ActionListener{

	private JLabel lblLogin = null;
	private JLabel lblpass = null;
	private JTextField tfLogin = null;
	private JPasswordField tfPass = null;
	private JButton btnConnect = null;
	private Etudiant mEtudiant = null;
	private Professeur mProfesseur = null;

	private static String catEtudiant =  "Etudiant";
	private static String catProfesseur =  "Professeur";

	String[] catStrings = {catEtudiant, catProfesseur };
	JComboBox catList ;

	public AuthPanel() {
		initSpecifics();
	}

	public void initSpecifics() {
		this.setTitle("Login");
		//TODO move the labels to a seperate langugage file and use the Translation class to change between languges
		lblLogin = new JLabel("Login:");
		lblpass = new JLabel("Password:");
		tfLogin  = new JTextField();
		tfPass = new JPasswordField();

		btnConnect =new JButton("Connecter");
		btnConnect.addActionListener(this);

		catList = new JComboBox(catStrings);
		catList.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		catList.setSelectedIndex(0);


		sortComponents();

		this.pack();
		this.setVisible(true);
		this.setResizable(false);

		//TODO check this function behavior in an applet environment
		this.setLocationRelativeTo((Frame)AppSession.getInstance().getApp());

	}

	public void sortComponents() {
		Container pane = this.getContentPane();
		pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));

		this.setSize(300, 200);
		JPanel typePanel = new JPanel(new GridLayout(7,1));

		typePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		typePanel.add(lblLogin);
		typePanel.add(tfLogin);
		typePanel.add(lblpass);		
		typePanel.add(tfPass);
		typePanel.add(catList);

		typePanel.add(btnConnect);

		add(typePanel);


	}


	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnConnect))
		{
			String slected = (String) catList.getModel().getSelectedItem();
			String login = tfLogin.getText();
			String pass = tfPass.getText();
			if(slected.compareTo(catEtudiant) == 0){
				
				EtudiantDAO mEtudiantDAO = (EtudiantDAO) DAOFactory.getDAO(EtudiantDAO.class.getName());
				mEtudiant= mEtudiantDAO.find(login, pass);

				if(mEtudiant != null){
					this.dispose();
					MCQSession.getInstance().getMCQChooserDialog().setVisible(true);

				}else
					JOptionPane.showMessageDialog(this, "Connexion �chou�", "Erreur", JOptionPane.ERROR_MESSAGE);
			}else{
				ProfesseurDAO mProfesseurDAO = (ProfesseurDAO) DAOFactory.getDAO(EtudiantDAO.class.getName());
				mProfesseur = mProfesseurDAO.find(login, pass);
				if(mProfesseur != null){
					this.dispose();
					MCQSession.getInstance().getMCQChooserDialog().setVisible(true);

				}else
					JOptionPane.showMessageDialog(this, "Connexion �chou�", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}


	}



}

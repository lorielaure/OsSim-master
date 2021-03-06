
package edu.upc.fib.ossim.mcq.view;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import edu.upc.fib.ossim.dao.ExerciceDAO;
import edu.upc.fib.ossim.dao.FactoryDAO;
import edu.upc.fib.ossim.mcq.model.Exercice;
import edu.upc.fib.ossim.utils.EscapeDialog;

public class MCQDisplayExo extends EscapeDialog implements HyperlinkListener{
	
	
	private static final long serialVersionUID = 1L;
	private JEditorPane editorPane = null;
	private JScrollPane editorScrollPane = null;
	public String exercices, tests;
	private FactoryDAO factoryDAO; 
	
	ExerciceDAO exerciceDAO;
	

	public MCQDisplayExo() {
		exercices=initListExecices();
		tests= initListTest();
		this.setTitle("Available MCQs ");
		editorPane = new JEditorPane("text/html",null);
		editorPane.setEditable(false);
		editorPane.addHyperlinkListener(this);
		editorPane.setText("<html>"
				+ "<body>"
				+ "<H1> Exercices </H1>"
				+ exercices
				+ "<H1> Tests </H1>"
				+ tests
				+"<H1><a href=historique> History </a></H1>"
				+ "</body></html>");
		
		editorScrollPane=new JScrollPane(editorPane);
		this.add(editorScrollPane);
		this.pack();
		this.setSize(300, 500);

	}

	public String initListExecices() {
		
		this.factoryDAO = FactoryDAO.getInstance();

		List<Exercice> exercices = this.factoryDAO.getExerciceDAO().getListExercicePublies();
		String exo = "<ul>";
		for (int i = 0; i < exercices.size(); i++) {
			exo += "<li><a href='exercice/" + exercices.get(i).getIdExercice() + "'>"
					+ exercices.get(i).getTitreExercice() + "</a></li>";
		}
		exo += "</ul>";
		return exo;
	}

	public String initListTest() {

		this.factoryDAO = FactoryDAO.getInstance();

		List<Exercice> tests = this.factoryDAO.getExerciceDAO().getListTestPublies();
		String test = "<ul>";
		for (int i = 0; i < tests.size(); i++) {
			test += "<li><a href='test/" + tests.get(i).getIdExercice() + "'>"
					+ tests.get(i).getTitreExercice() + "</a></li>";
		}
		test+= "</ul>";
		return test;
	}
	public void hyperlinkUpdate(HyperlinkEvent e) {
		if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
		{
			
			//affichage de l'historique de l'�tudiant loggu� 
			
			if (e.getDescription().equals("historique")&& PanelAuthentification.mEtudiant != null){
				
				new PanelHistoryEtudiant().setVisible(true);	
			}else if(e.getDescription().contains("test") && PanelAuthentification.mProfesseur != null){
				
				//historique professeur : vue pour un test donn�
				String urlToken[] = e.getDescription().split("/");
				if(urlToken.length > 1){
					int  idTest = Integer.parseInt(urlToken[1]);
					new PanelHistoryProfesseur(idTest).setVisible(true);
					
				}
			}
			
			
//			name = JOptionPane.showInputDialog(null,
//					"Enter Your Name:",
//					"", JOptionPane.QUESTION_MESSAGE);
//			if (name != null && !name.equals("")){
			
				
				System.out.println("id="+e.getDescription()); // id de l'exo
				
//				if (url != null) {
//					if (url.toString().endsWith("xml")) {  // Load simulation
//						parseXML(url);
//						try {
//							System.out.println("First Load: "+paths.get(0));
//							System.out.println("link Load: "+(new File(paths.get(0)).toURI().toURL()));
//							//loadSimulation((new File(paths.get(0)).toURI().toURL()));
//							loadSimulation(new URL(paths.get(0)));
//						} catch (MalformedURLException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
//					}
//				}
			
		}
	}
}

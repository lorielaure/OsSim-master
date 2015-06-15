
/*<<<<<<< HEAD
package edu.upc.fib.ossim.mcq.model;

import java.util.List;

public class QR {
	
	private int idQR;
	private int moduleQR; // Module de la question/r�ponse (m�moire ou processus)
	private int blockOnStep;
	private String enonce;
	private boolean includeAnswers;
	private int difficulty;
	private char answerType;
	private int answerNumber;
	private Simulation simulation;
	private List<Answer> answerList;
	
	

	public QR() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QR(int moduleQR, int blockOnStep, String enonce,
			boolean includeAnswers, int difficulty, char answerType,
			int answerNumber, Simulation simulation, List<Answer> answerList) {
		super();
		this.moduleQR = moduleQR;
		this.blockOnStep = blockOnStep;
		this.enonce = enonce;
		this.includeAnswers = includeAnswers;
		this.difficulty = difficulty;
		this.answerType = answerType;
		this.answerNumber = answerNumber;
		this.simulation = simulation;
		this.answerList = answerList;
	}


	
	public int getIdQR() {
		return idQR;
	}

	public void setIdQR(int idQR) {
		this.idQR = idQR;
	}

	public int getAnswerNumber() {
		return answerNumber;
	}
	public void setAnswerNumber(int answerNumber) {
		this.answerNumber = answerNumber;
	}
	public int getModuleQR() {
		return moduleQR;
	}
	public void setModuleQR(int moduleQR) {
		this.moduleQR = moduleQR;
	}
	public int getBlockOnStep() {
		return blockOnStep;
	}
	public void setBlockOnStep(int blockOnStep) {
		this.blockOnStep = blockOnStep;
	}
	public String getEnonce() {
		return enonce;
	}
	public void setEnonce(String enonce) {
		this.enonce = enonce;
	}
	public boolean isIncludeAnswers() {
		return includeAnswers;
	}
	public void setIncludeAnswers(boolean includeAnswers) {
		this.includeAnswers = includeAnswers;
	}
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	public char getAnswerType() {
		return answerType;
	}
	public void setAnswerType(char answerType) {
		this.answerType = answerType;
	}
	public Simulation getSimulation() {
		return simulation;
	}
	public void setSimulation(Simulation simulation) {
		this.simulation = simulation;
	}

	public List<Answer> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<Answer> answerList) {
		this.answerList = answerList;
	}
	
	
	

}
=======
*/

package edu.upc.fib.ossim.mcq.model;

import java.util.List;

public class QR {
	
	private int idQR;
	private int moduleQR; // Module de la question/r�ponse (m�moire ou processus)
	private int blockOnStep;
	private String enonce;
	private boolean includeAnswers;
	private int difficulty;
	private String answerType;
	private int answerNumber;
	private Simulation simulation;
	private List<Answer> answerList;
	
	

	public QR() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QR(int moduleQR, int blockOnStep, String enonce,
			boolean includeAnswers, int difficulty, String answerType,
			int answerNumber, Simulation simulation, List<Answer> answerList) {
		super();
		this.moduleQR = moduleQR;
		this.blockOnStep = blockOnStep;
		this.enonce = enonce;
		this.includeAnswers = includeAnswers;
		this.difficulty = difficulty;
		this.answerType = answerType;
		this.answerNumber = answerNumber;
		this.simulation = simulation;
		this.answerList = answerList;
	}


	
	public int getIdQR() {
		return idQR;
	}

	public void setIdQR(int idQR) {
		this.idQR = idQR;
	}

	public int getAnswerNumber() {
		return answerNumber;
	}
	public void setAnswerNumber(int answerNumber) {
		this.answerNumber = answerNumber;
	}
	public int getModuleQR() {
		return moduleQR;
	}
	public void setModuleQR(int moduleQR) {
		this.moduleQR = moduleQR;
	}
	public int getBlockOnStep() {
		return blockOnStep;
	}
	public void setBlockOnStep(int blockOnStep) {
		this.blockOnStep = blockOnStep;
	}
	public String getEnonce() {
		return enonce;
	}
	public void setEnonce(String enonce) {
		this.enonce = enonce;
	}
	public boolean isIncludeAnswers() {
		return includeAnswers;
	}
	public void setIncludeAnswers(boolean includeAnswers) {
		this.includeAnswers = includeAnswers;
	}
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	public String getAnswerType() {
		return answerType;
	}
	public void setAnswerType(String type) {
		this.answerType = type;
	}
	public Simulation getSimulation() {
		return simulation;
	}
	public void setSimulation(Simulation simulation) {
		this.simulation = simulation;
	}

	public List<Answer> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<Answer> answerList) {
		this.answerList = answerList;
	}
	
	
	

}

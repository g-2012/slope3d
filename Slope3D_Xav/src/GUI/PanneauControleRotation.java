package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Utils.Constantes;

public class PanneauControleRotation extends JPanel {
	/*
	 * Panneau de contr�le qui permet � l'utilisateur d'effectuer diff�rentes op�rations de
	 * rotation de la cam�ra suivant le type de vue choisie dans le menu de r�glages :
	 * - En vue orbitale, r�glage du sens et de la vitesse de rotation
	 * - En vue de dessus, aucune rotation autoris�e
	 * - En vue libre, rotation verticale ou horizontale de la cam�ra, et modification de la sensibilit� des commandes
	 */
	
	/*
	 * Attributs : boutons
	 */
	JButton bSensHora, bSensAnti, // Boutons de choix du sens : respectivement horaire et antihoraire
			bVitPlus, bVitMoins,  // Vitesse de rotation / sensibilit� des commandes de rotation
			bHPlus, bHMoins, // Rotation par rapport au plan vertical (dans le plan horizontal)
			bVPlus, bVMoins; // Rotation par rapport au plan horizontal (dans le plan vertical)
	
		
	/*
	 * Constructeur
	 */
	public PanneauControleRotation(MenuReglage mReg /* permet de connaitre les valeurs des r�glages */, Dimension d) {
		super();
		this.setPreferredSize(d);
		this.setBackground(new Color(225,225,225));
		//this.setFont(new Font("TimesRoman", Font.PLAIN, 10));
		
		/*
		 * Cr�ation des boutons
		 */
		bSensHora = new JButton("Horaire");
		bSensAnti = new JButton("Direct");
		bVitPlus = new JButton("\u002b" /* signe + */);
		bVitMoins = new JButton("\u002d" /* signe - */);
		bHPlus = new JButton("\u002b");
		bHMoins = new JButton("\u002D");
		bVPlus = new JButton("\u002b");
		bVMoins = new JButton("\u002d");
		
		/*
		 * Dimensionnement des boutons
		 */
		bSensHora.setSize(30,30);
		bSensAnti.setSize(30,30);
		bVitPlus.setSize(30,30);
		bVitMoins.setSize(30,30);
		bVPlus.setSize(30,30);
		bVMoins.setSize(30,30);
		bHPlus.setSize(30,30);
		bHMoins.setSize(30,30);
		
		
		/*
		 * Activation ou d�sactivation des boutons selon la vue choisie
		 */
		if (mReg.getChoixCam() == Constantes.CAM_DESSUS) { // Vue du dessus
			// Aucune rotation autoris�e
			bSensAnti.setEnabled(false);
			bSensHora.setEnabled(false);
			bVitMoins.setEnabled(false);
			bVitPlus.setEnabled(false);
			bHPlus.setEnabled(false);
			bHMoins.setEnabled(false);
			bVPlus.setEnabled(false);
			bVMoins.setEnabled(false);			
		}
		else if (mReg.getChoixCam() == Constantes.CAM_ORBITE) { // Cam�ra en orbite
			// L'utilisateur peut r�gler le sens et la vitesse de rotation
			bSensAnti.setEnabled(true);
			bSensHora.setEnabled(true);
			bVitMoins.setEnabled(true);
			bVitPlus.setEnabled(true);
			bHPlus.setEnabled(false);
			bHMoins.setEnabled(false);
			bVPlus.setEnabled(false);
			bVMoins.setEnabled(false);			
		}
		else if (mReg.getChoixCam() == Constantes.CAM_PERSO) { // Vue libre
			// L'utilisateur fait librement tourner la cam�ra
			bSensAnti.setEnabled(false); // Inutile ici
			bSensHora.setEnabled(false); // Inutile ici
			bVitMoins.setEnabled(true);
			bVitPlus.setEnabled(true);
			bHPlus.setEnabled(true);
			bHMoins.setEnabled(true);
			bVPlus.setEnabled(true);
			bVMoins.setEnabled(true);			
		}
		
		/*
		 * Mise en page et int�gration de boutons
		 */
		/* pas de Layout*/
		/*
		this.setLayout(null);
		
		JLabel tRot = new JLabel("Rotation"); // Le titre du panneau
		tRot.setSize(70, 25);
		tRot.setHorizontalAlignment(AbstractButton.CENTER);
		tRot.setVerticalAlignment(AbstractButton.CENTER);
		tRot.setLocation(90, 10);
		this.add(tRot);
		
		//Boutons li�s � l'angle horizontal
		JLabel tHor = new JLabel("Horizontal"); // Titre
		tHor.setSize(70,25);
		tHor.setHorizontalAlignment(AbstractButton.CENTER);
		tHor.setVerticalAlignment(AbstractButton.CENTER);
		tHor.setLocation(40, 45);
		this.add(tHor);
		bHPlus.setLocation(60, 75); // bouton +
		this.add(bHPlus);
		JLabel vHor = new JLabel("XXX"); // Valeur
		vHor.setSize(40, 25);
		vHor.setHorizontalAlignment(AbstractButton.CENTER);
		vHor.setVerticalAlignment(AbstractButton.CENTER);
		vHor.setLocation(55, 110);
		this.add(vHor);
		bHMoins.setLocation(60, 140); // bouton -
		this.add(bHMoins);
		
		//Boutons li�s � l'angle vertical
		JLabel tVer = new JLabel("Horizontal"); // Titre
		tVer.setSize(70,25);
		tVer.setHorizontalAlignment(AbstractButton.CENTER);
		tVer.setVerticalAlignment(AbstractButton.CENTER);
		tVer.setLocation(140, 45);
		this.add(tVer);
		bVPlus.setLocation(160, 75); // bouton +
		this.add(bVPlus);
		JLabel vVer = new JLabel("XXX"); // Valeur
		vVer.setSize(40, 25);
		vVer.setHorizontalAlignment(AbstractButton.CENTER);
		vVer.setVerticalAlignment(AbstractButton.CENTER);
		vVer.setLocation(155, 110);
		this.add(vVer);
		bVMoins.setLocation(160, 140); // bouton -
		this.add(bVMoins);
		
		//Boutons li�s au sens de rotation
		JLabel tSens = new JLabel("Sens"); // Titre
		tSens.setSize(70,25);
		tSens.setHorizontalAlignment(AbstractButton.CENTER);
		tSens.setVerticalAlignment(AbstractButton.CENTER);
		tSens.setLocation(40, 135+45);
		this.add(tSens);
		bSensHora.setLocation(60, 135+75); // bouton sens horaire
		this.add(bSensHora);
		JLabel vSens = new JLabel("XXX"); // Valeur
		vSens.setSize(40, 25);
		vSens.setHorizontalAlignment(AbstractButton.CENTER);
		vSens.setVerticalAlignment(AbstractButton.CENTER);
		vSens.setLocation(55, 135+110);
		this.add(vSens);
		bSensAnti.setLocation(60, 135+140); // bouton sens antihoraire
		this.add(bSensAnti);

		//Boutons li�s � la vitesse
		JLabel tVit = new JLabel("Vitesse"); // Titre
		tVit.setSize(70,25);
		tVit.setHorizontalAlignment(AbstractButton.CENTER);
		tVit.setVerticalAlignment(AbstractButton.CENTER);
		tVit.setLocation(140, 135+45);
		this.add(tVit);
		bVitPlus.setLocation(160, 135+75); // bouton +
		this.add(bVitPlus);
		JLabel vVit = new JLabel("XXX"); // Valeur
		vVit.setSize(40, 25);
		vVit.setHorizontalAlignment(AbstractButton.CENTER);
		vVit.setVerticalAlignment(AbstractButton.CENTER);
		vVit.setLocation(155, 135+110);
		this.add(vVit);
		bVitMoins.setLocation(160, 135+140); // bouton -
		this.add(bVitMoins);
		*/
		
		/* Layout = GroupLayout */
		
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		
		// Espacements entre composants et bordure puis entre composants
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		
		// Cr�ation des �l�ments
		JLabel tRot = new JLabel("Rotation"); // Le titre du panneau
		tRot.setSize(70, 25);
		tRot.setHorizontalAlignment(AbstractButton.CENTER);
		tRot.setVerticalAlignment(AbstractButton.CENTER);
		
		//Boutons li�s � l'angle horizontal
		JLabel tHor = new JLabel("Horizontal"); // Titre
		tHor.setSize(70,25);
		tHor.setHorizontalAlignment(AbstractButton.CENTER);
		tHor.setVerticalAlignment(AbstractButton.CENTER);

		JLabel vHor = new JLabel("XXX"); // Valeur
		vHor.setSize(40, 25);
		vHor.setHorizontalAlignment(AbstractButton.CENTER);
		vHor.setVerticalAlignment(AbstractButton.CENTER);
		
		//Boutons li�s � l'angle vertical
		JLabel tVer = new JLabel("Vertical"); // Titre
		tVer.setSize(70,25);
		tVer.setHorizontalAlignment(AbstractButton.CENTER);
		tVer.setVerticalAlignment(AbstractButton.CENTER);

		JLabel vVer = new JLabel("XXX"); // Valeur
		vVer.setSize(40, 25);
		vVer.setHorizontalAlignment(AbstractButton.CENTER);
		vVer.setVerticalAlignment(AbstractButton.CENTER);
		
		//Boutons li�s au sens de rotation
		JLabel tSens = new JLabel("Sens"); // Titre
		tSens.setSize(70,25);
		tSens.setHorizontalAlignment(AbstractButton.CENTER);
		tSens.setVerticalAlignment(AbstractButton.CENTER);

		JLabel vSens = new JLabel("XXX"); // Valeur
		vSens.setSize(40, 25);
		vSens.setHorizontalAlignment(AbstractButton.CENTER);
		vSens.setVerticalAlignment(AbstractButton.CENTER);

		//Boutons li�s � la vitesse
		JLabel tVit = new JLabel("Vitesse"); // Titre
		tVit.setSize(70,25);
		tVit.setHorizontalAlignment(AbstractButton.CENTER);
		tVit.setVerticalAlignment(AbstractButton.CENTER);

		JLabel vVit = new JLabel("XXX"); // Valeur
		vVit.setSize(40, 25);
		vVit.setHorizontalAlignment(AbstractButton.CENTER);
		vVit.setVerticalAlignment(AbstractButton.CENTER);

		// Ajout des �lements aux groupes :
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(tRot)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(tHor)
								.addComponent(bHPlus)
								.addComponent(vHor)
								.addComponent(bHMoins)	
								)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
										.addComponent(tVer)
										.addComponent(bVPlus)
										.addComponent(vVer)
										.addComponent(bVMoins)
										)
						)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
										.addComponent(tSens)
										.addComponent(bSensHora)
										.addComponent(vSens)
										.addComponent(bSensAnti)
										)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
												.addComponent(tVit)
												.addComponent(bVitPlus)
												.addComponent(vVit)
												.addComponent(bVitMoins)
												)
								)
				);
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(tRot)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addGroup(layout.createSequentialGroup()
								.addComponent(tHor)
								.addComponent(bHPlus)
								.addComponent(vHor)
								.addComponent(bHMoins)
								)
								.addGroup(layout.createSequentialGroup()
										.addComponent(tVer)
										.addComponent(bVPlus)
										.addComponent(vVer)
										.addComponent(bVMoins)
										)
						)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addGroup(layout.createSequentialGroup()
										.addComponent(tSens)
										.addComponent(bSensHora)
										.addComponent(vSens)
										.addComponent(bSensAnti)
										)
										.addGroup(layout.createSequentialGroup()
												.addComponent(tVit)
												.addComponent(bVitPlus)
												.addComponent(vVit)
												.addComponent(bVitMoins)
												)
								)
				);
		
				
		this.setVisible(true);
		
	}

}

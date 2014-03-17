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
	 * Panneau de contrôle qui permet à l'utilisateur d'effectuer différentes opérations de
	 * rotation de la caméra suivant le type de vue choisie dans le menu de réglages :
	 * - En vue orbitale, réglage du sens et de la vitesse de rotation
	 * - En vue de dessus, aucune rotation autorisée
	 * - En vue libre, rotation verticale ou horizontale de la caméra, et modification de la sensibilité des commandes
	 */

	/*
	 * Attributs : boutons
	 */
	JButton bSensHora, bSensAnti, // Boutons de choix du sens : respectivement horaire et antihoraire
	bVitPlus, bVitMoins,  // Vitesse de rotation / sensibilité des commandes de rotation
	bHPlus, bHMoins, // Rotation par rapport au plan vertical (dans le plan horizontal)
	bVPlus, bVMoins; // Rotation par rapport au plan horizontal (dans le plan vertical)


	/*
	 * Constructeur
	 */
	public PanneauControleRotation(MenuReglage mReg /* permet de connaitre les valeurs des réglages */, Dimension d) {
		super();
		this.setPreferredSize(d);
		this.setBackground(new Color(225,225,225));

		/*
		 * Création des boutons
		 */
		bSensHora = new JButton("Horaire");
		bSensAnti = new JButton("Direct");
		bVitPlus = new JButton("\u002b" /* signe + */);
		bVitMoins = new JButton("\u002d" /* signe - */);
		bHPlus = new JButton("\u002b");
		bHMoins = new JButton("\u002d");
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
		 * Activation ou désactivation des boutons selon la vue choisie
		 */
		if (mReg.getChoixCam() == Constantes.CAM_DESSUS) { // Vue du dessus
			// Aucune rotation autorisée
			bSensAnti.setEnabled(false);
			bSensHora.setEnabled(false);
			bVitMoins.setEnabled(false);
			bVitPlus.setEnabled(false);
			bHPlus.setEnabled(false);
			bHMoins.setEnabled(false);
			bVPlus.setEnabled(false);
			bVMoins.setEnabled(false);			
		}
		else if (mReg.getChoixCam() == Constantes.CAM_ORBITE) { // Caméra en orbite
			// L'utilisateur peut régler le sens et la vitesse de rotation
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
			// L'utilisateur fait librement tourner la caméra
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
		 * Mise en page et intégration de boutons
		 */

		// Gestionnaire de mise en page = GroupLayout
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);

		// Espacements entre composants et bordure puis entre composants
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);

		// Création des éléments
		JLabel tRot = new JLabel("Rotation"); // Le titre du panneau
		tRot.setSize(70, 25);
		tRot.setHorizontalAlignment(AbstractButton.CENTER);
		tRot.setVerticalAlignment(AbstractButton.CENTER);

		//Boutons liés à l'angle horizontal
		JLabel tHor = new JLabel("Horizontal"); // Titre
		tHor.setSize(70,25);
		tHor.setHorizontalAlignment(AbstractButton.CENTER);
		tHor.setVerticalAlignment(AbstractButton.CENTER);

		JLabel vHor = new JLabel("XXX"); // Valeur
		vHor.setSize(40, 25);
		vHor.setHorizontalAlignment(AbstractButton.CENTER);
		vHor.setVerticalAlignment(AbstractButton.CENTER);

		//Boutons liés à l'angle vertical
		JLabel tVer = new JLabel("Vertical"); // Titre
		tVer.setSize(70,25);
		tVer.setHorizontalAlignment(AbstractButton.CENTER);
		tVer.setVerticalAlignment(AbstractButton.CENTER);

		JLabel vVer = new JLabel("XXX"); // Valeur
		vVer.setSize(40, 25);
		vVer.setHorizontalAlignment(AbstractButton.CENTER);
		vVer.setVerticalAlignment(AbstractButton.CENTER);

		//Boutons liés au sens de rotation
		JLabel tSens = new JLabel("Sens"); // Titre
		tSens.setSize(70,25);
		tSens.setHorizontalAlignment(AbstractButton.CENTER);
		tSens.setVerticalAlignment(AbstractButton.CENTER);

		JLabel vSens = new JLabel("XXX"); // Valeur
		vSens.setSize(40, 25);
		vSens.setHorizontalAlignment(AbstractButton.CENTER);
		vSens.setVerticalAlignment(AbstractButton.CENTER);

		//Boutons liés à la vitesse
		JLabel tVit = new JLabel("Vitesse"); // Titre
		tVit.setSize(70,25);
		tVit.setHorizontalAlignment(AbstractButton.CENTER);
		tVit.setVerticalAlignment(AbstractButton.CENTER);

		JLabel vVit = new JLabel("XXX"); // Valeur
		vVit.setSize(40, 25);
		vVit.setHorizontalAlignment(AbstractButton.CENTER);
		vVit.setVerticalAlignment(AbstractButton.CENTER);

		// Ajout des élements aux groupes :
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

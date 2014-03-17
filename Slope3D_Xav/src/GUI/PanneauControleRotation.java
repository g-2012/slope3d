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
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

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
		bSensHora.setSize(60,60);
		bSensAnti.setSize(60,60);
		bVitPlus.setSize(60,60);
		bVitMoins.setSize(60,60);
		bVPlus.setSize(60,60);
		bVMoins.setSize(60,60);
		bHPlus.setSize(60,60);
		bHMoins.setSize(60,60);


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
		tRot.setSize(75, 30);
		tRot.setBorder(new LineBorder(Color.GRAY, 1));
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
				layout.createSequentialGroup()
				.addContainerGap(20, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER,false)
						.addComponent(tRot)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER,false)
										.addComponent(tHor,70,70,70)
										.addComponent(bHPlus,60,60,60)
										.addComponent(vHor,60,60,60)
										.addComponent(bHMoins,60,60,60)	
										)
								.addGap(15)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER,false)
										.addComponent(tVer,70,70,70)
										.addComponent(bVPlus,60,60,60)
										.addComponent(vVer,60,60,60)
										.addComponent(bVMoins,60,60,60)
										)
								)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER,false)
										.addComponent(tSens,70,70,70)
										.addComponent(bSensHora,60,60,60)
										.addComponent(vSens,60,60,60)
										.addComponent(bSensAnti,60,60,60)
										)
								.addGap(15)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER,false)
										.addComponent(tVit,70,70,70)
										.addComponent(bVitPlus,60,60,60)
										.addComponent(vVit,60,60,60)
										.addComponent(bVitMoins,60,60,60)
										)						
								)
						)
				.addContainerGap(20, Short.MAX_VALUE)	
				);
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addContainerGap(20, 30)
				.addComponent(tRot)
				.addGap(20)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER,false)
						
						.addGroup(layout.createSequentialGroup()
								.addComponent(tHor,25,25,25)
								.addComponent(bHPlus,60,60,60)
								.addComponent(vHor,25,25,25)
								.addComponent(bHMoins,60,60,60)
								)
						.addGap(10)		
						.addGroup(layout.createSequentialGroup()
								.addComponent(tVer,25,25,25)
								.addComponent(bVPlus,60,60,60)
								.addComponent(vVer,25,25,25)
								.addComponent(bVMoins,60,60,60)
								)
						)
				.addGap(15)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER,false)
						.addGroup(layout.createSequentialGroup()
								.addComponent(tSens,25,25,25)
								.addComponent(bSensHora,60,60,60)
								.addComponent(vSens,25,25,25)
								.addComponent(bSensAnti,60,60,60)
								)
						.addGap(10)
						.addGroup(layout.createSequentialGroup()
								.addComponent(tVit,25,25,25)
								.addComponent(bVitPlus,60,60,60)
								.addComponent(vVit,25,25,25)
								.addComponent(bVitMoins,60,60,60)
								)
						)
				.addContainerGap(20, Short.MAX_VALUE)
				);


		this.setVisible(true);

	}

}

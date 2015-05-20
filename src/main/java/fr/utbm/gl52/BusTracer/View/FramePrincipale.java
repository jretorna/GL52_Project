package fr.utbm.gl52.BusTracer.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.swing.JMapFrame;

import fr.utbm.gl52.BusTracer.Model.GestionnaireSuivi;
import fr.utbm.gl52.BusTracer.Model.GestionnaireSuiviListener;
import fr.utbm.gl52.BusTracer.Utils.Constantes;

/**
 * @version 01.00.00
 * 
 * @author Jeremy RETORNAZ - V01.00.00
 * @date 11 avr. 2015<br>
 * 
 */

public class FramePrincipale extends JFrame {

	private static final long serialVersionUID = 1L;
	/*-------------------*/
	Dimension MIN_DIMENSION_FRAME_PRINCIPALE = new Dimension(800, 400);
	int HEIGHT_BTN = 32;
	int COEF_WIDTH_BTN = 10;
	private JMapFrame jf;
	private JPanel jp, jpButton, jpBtnImport, jpBtnCtrl, jpBtnZoom, jpHeader,
			jpState;
	private JLabel stateMapFixLb, stateMapLb, stateGpsFixLb, stateGpsLb,
			stateRunFixLb, stateRunLb;
	private JButton importMapBtn, importGpsDatasBtn, playBtn, stopBtn,
			pauseBtn, zoomInBtn, zoomOutBtn, zoomOverBtn, zoomBusBtn;
	private BufferedImage playIcon, stopIcon, pauseIcon, zoomInIcon,
			zoomOutIcon, zoomOverIcon, zoomBusIcon;
	private MapContent map;

	GestionnaireSuiviListener gestSuiviListener;
	GestionnaireSuivi gestSuivi;
	/*-------------------*/
	/**
	 * Fonction which create the frame
	 * 
	 * @throws IOException
	 */
	public void create() throws IOException {
		this.jf = new JMapFrame();
		this.jf.setTitle(Constantes.TITLE_FRAME_PRINCIPALE);
		this.jf.setMinimumSize(this.MIN_DIMENSION_FRAME_PRINCIPALE);
		setLookAndFeel();
		this.jf.enableToolBar(true);
		initButton();
		initLabel();
		initPanel();
		initListeners();
		this.jf.setVisible(true);
		this.jf.setState(Frame.MAXIMIZED_VERT);
		this.jf.pack();
		this.jf.setLocationRelativeTo(null);
		this.jf.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initButton() throws IOException {
		this.importMapBtn = new JButton(ViewMessages.getString("View.importMapBtn")); //$NON-NLS-1$
		this.importMapBtn.setPreferredSize(new Dimension(
				this.importMapBtn.getText().length() * this.COEF_WIDTH_BTN,
				this.HEIGHT_BTN));
		this.importGpsDatasBtn = new JButton(
				ViewMessages.getString("View.importGpsDataBtn")); //$NON-NLS-1$
		this.importGpsDatasBtn.setPreferredSize(new Dimension(
				this.importGpsDatasBtn.getText().length() * this.COEF_WIDTH_BTN,
				this.HEIGHT_BTN));
		this.playIcon = ImageIO.read(new File("img/play32.png")); //$NON-NLS-1$
		this.playBtn = new JButton(new ImageIcon(this.playIcon));
		this.setButtonTranslucent(this.playBtn);
		this.stopIcon = ImageIO.read(new File("img/stop32.png")); //$NON-NLS-1$
		this.stopBtn = new JButton(new ImageIcon(this.stopIcon));
		this.setButtonTranslucent(this.stopBtn);
		this.pauseIcon = ImageIO.read(new File("img/pause32.png")); //$NON-NLS-1$
		this.pauseBtn = new JButton(new ImageIcon(this.pauseIcon));
		this.setButtonTranslucent(this.pauseBtn);
		this.zoomInIcon = ImageIO.read(new File("img/ZoomIn32.png")); //$NON-NLS-1$
		this.zoomInBtn = new JButton(new ImageIcon(this.zoomInIcon));
		this.setButtonTranslucent(this.zoomInBtn);
		this.zoomOutIcon = ImageIO.read(new File("img/zoomOut32.png")); //$NON-NLS-1$
		this.zoomOutBtn = new JButton(new ImageIcon(this.zoomOutIcon));
		this.setButtonTranslucent(this.zoomOutBtn);
		this.zoomOverIcon = ImageIO.read(new File("img/zoomBetter32.png")); //$NON-NLS-1$
		this.zoomOverBtn = new JButton(new ImageIcon(this.zoomOverIcon));
		this.setButtonTranslucent(this.zoomOverBtn);
		this.zoomBusIcon = ImageIO.read(new File("img/zoomBus32.png")); //$NON-NLS-1$
		this.zoomBusBtn = new JButton(new ImageIcon(this.zoomBusIcon));
		this.setButtonTranslucent(this.zoomBusBtn);

		setToolTipToMenuBtn();

	}
	private void setToolTipToMenuBtn() {
		this.importMapBtn.setToolTipText(ViewMessages.getString("View.importMapFile")); //$NON-NLS-1$
		this.importGpsDatasBtn.setToolTipText(ViewMessages.getString("View.importGpsFile")); //$NON-NLS-1$
		this.playBtn.setToolTipText(ViewMessages.getString("View.play")); //$NON-NLS-1$
		this.stopBtn.setToolTipText(ViewMessages.getString("View.stop")); //$NON-NLS-1$
		this.pauseBtn.setToolTipText(ViewMessages.getString("View.pause")); //$NON-NLS-1$
		this.zoomInBtn.setToolTipText(ViewMessages.getString("View.zoomIn")); //$NON-NLS-1$
		this.zoomOutBtn.setToolTipText(ViewMessages.getString("View.zoomOut")); //$NON-NLS-1$
		this.zoomOverBtn.setToolTipText(ViewMessages.getString("View.zoomBetter")); //$NON-NLS-1$
		this.zoomBusBtn.setToolTipText(ViewMessages.getString("View.zoomOnBus")); //$NON-NLS-1$
	}
	private void initPanel() {
		this.jpButton = new JPanel(new GridBagLayout());
		this.jpBtnImport = new JPanel(new FlowLayout());
		this.jpBtnImport.add(this.importMapBtn);
		this.jpBtnImport.add(this.importGpsDatasBtn);
		this.jpBtnCtrl = new JPanel(new FlowLayout());
		this.jpBtnCtrl.add(this.playBtn);
		this.jpBtnCtrl.add(this.stopBtn);
		this.jpBtnCtrl.add(this.pauseBtn);
		this.jpBtnZoom = new JPanel(new FlowLayout());
		this.jpBtnZoom.add(this.zoomInBtn);
		this.jpBtnZoom.add(this.zoomOutBtn);
		this.jpBtnZoom.add(this.zoomOverBtn);
		this.jpBtnZoom.add(this.zoomBusBtn);

		/* ---- Placement des boutons de menu ---- */
		GridBagConstraints gb = new GridBagConstraints();
		gb.fill = GridBagConstraints.HORIZONTAL;
		gb.gridx = 0;
		gb.gridy = 0;
		gb.gridwidth = 1;
		this.jpButton.add(this.jpBtnImport, gb);

		gb.gridx = 1;
		gb.gridy = 0;
		gb.gridwidth = 1;
		gb.insets = new Insets(0, 20, 0, 10);
		this.jpButton.add(this.jpBtnCtrl, gb);

		gb.gridx = 2;
		gb.gridy = 0;
		gb.gridwidth = 1;
		gb.insets = new Insets(0, 10, 0, 20);
		this.jpButton.add(this.jpBtnZoom, gb);

		/* ---- Placement des boutons state ---- */
		gb = null;
		gb = new GridBagConstraints();
		this.jpState = new JPanel(new GridBagLayout());
		gb.fill = GridBagConstraints.HORIZONTAL;
		gb.gridx = 0;
		gb.gridy = 0;
		gb.gridwidth = 1;
		this.jpState.add(this.stateMapFixLb, gb);

		gb.gridx = 1;
		gb.gridy = 0;
		gb.gridwidth = 1;
		gb.insets = new Insets(0, 0, 0, 10);
		this.jpState.add(this.stateMapLb, gb);

		gb.gridx = 0;
		gb.gridy = 1;
		gb.gridwidth = 1;
		this.jpState.add(this.stateGpsFixLb, gb);

		gb.gridx = 1;
		gb.gridy = 1;
		gb.gridwidth = 1;
		gb.insets = new Insets(0, 0, 0, 10);
		this.jpState.add(this.stateGpsLb, gb);

		gb.gridx = 0;
		gb.gridy = 2;
		gb.gridwidth = 1;
		this.jpState.add(this.stateRunFixLb, gb);

		gb.gridx = 1;
		gb.gridy = 2;
		gb.gridwidth = 1;
		gb.insets = new Insets(0, 0, 0, 10);
		this.jpState.add(this.stateRunLb, gb);

		/* ---- Placement dans la frame ---- */

		this.jpHeader = new JPanel(new BorderLayout());
		this.jpHeader.add(this.jpButton, BorderLayout.CENTER);
		this.jpHeader.add(this.jpState, BorderLayout.EAST);
		this.jp = new JPanel(new BorderLayout());
		this.jp.add(this.jpHeader);
		// Delete all component already in the toolbar and add our own toolbar
		this.jf.getToolBar().removeAll();
		this.jf.getToolBar().add(this.jp);
		this.jf.getToolBar().setBorderPainted(false);

	}
	private void initLabel() {
		this.stateMapFixLb = new JLabel(Constantes.STATE_MAP_FIX);
		this.stateGpsFixLb = new JLabel(Constantes.STATE_GPS_FIX);
		this.stateRunFixLb = new JLabel(Constantes.STATE_RUN_FIX);

		this.stateMapLb = new JLabel(Constantes.STATE_MAP_NOT_LOADING);
		this.stateGpsLb = new JLabel(Constantes.STATE_GPS_NOT_LOADING);
		this.stateRunLb = new JLabel(Constantes.STATE_WORKING_STOPPED);
	}

	private void setButtonTranslucent(final JButton btn) {
		if (btn != null) {
			btn.setPreferredSize(new Dimension(32, 32));
			btn.setBorder(null);
			btn.setBorderPainted(false);
			btn.setContentAreaFilled(false);
			btn.setOpaque(false);
		}
	}

	private void initListeners() {

		/*--------------- GPS DATAS FILE -----------------*/
		this.importGpsDatasBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				FramePrincipale.this.gestSuivi.chooseGpsDataFile();
			}
		});

		/*--------------- MAP FILE -----------------*/
		this.importMapBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				FramePrincipale.this.gestSuivi.chooseMapFile();
			}
		});

		/*--------------- PLAY PAUSE STOP -----------------*/
		this.gestSuivi = new GestionnaireSuivi();
		this.playBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				changeIconLook("play"); //$NON-NLS-1$
				FramePrincipale.this.gestSuivi.launchAcquisition();
			}
		});

		this.stopBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				changeIconLook("stop"); //$NON-NLS-1$
				FramePrincipale.this.gestSuivi.stopAcquisition();
			}
		});

		this.pauseBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				changeIconLook("pause"); //$NON-NLS-1$
				FramePrincipale.this.gestSuivi.pauseAcquisition();
			}
		});

		this.gestSuiviListener = new GestionnaireSuiviListener() {
			@Override
			public void play() {
				// TODO JR retirer ce com
				System.out.println("Launch Acquisition"); //$NON-NLS-1$
			}

			@Override
			public void stop() {
				// TODO JR - Stop
			}

			@Override
			public void pause() {
				// TODO JR - Pause
			}

			@Override
			public void updateMap(final Layer layer) {
				// TODO JR retirer ce com
				System.out.println("Loading map : " + layer); //$NON-NLS-1$
				if (FramePrincipale.this.map != null) {
					int retour = JOptionPane.showConfirmDialog(
							FramePrincipale.this.jf,
							ViewMessages.getString("View.mapAlreadyExistMsg"),
							ViewMessages.getString("View.mapAlreadyExistTitle"),
							JOptionPane.OK_CANCEL_OPTION);
					if (retour == JOptionPane.OK_OPTION) {
						// TODO JR retirer ce com
						System.out.println("**** Suppression map en cours ****"); //$NON-NLS-1$
						FramePrincipale.this.map = null;
						addNewMap(layer);
					}
				} else {
					addNewMap(layer);
				}
			}

			@Override
			public void updateGpsDatas(final String uri) {
				System.out.println("Import GPS datas : " + uri); //$NON-NLS-1$
				if (uri.equals(Constantes.STATE_GPS_NOT_LOADING.toString())) {
					// TODO JR - Si l'on appui sur "annuler" du JFileChooser et
					// qu'un fichier GPS et d�j� charg�, il faut quand m�me
					// laisser le state � "loaded"
					FramePrincipale.this.stateGpsLb.setText(Constantes.STATE_GPS_NOT_LOADING);
				} else {
					FramePrincipale.this.stateGpsLb.setText(Constantes.STATE_GPS_LOADING);
				}
			}

			@Override
			public void error(	final int _id,
								final String _msg,
								final String _state) {
				JOptionPane.showMessageDialog(FramePrincipale.this.jf, _msg,
						Constantes.ERROR_MSG, JOptionPane.ERROR_MESSAGE);
				setErrorState(_id, _state);
			}
		};
		this.gestSuivi.addGestionnaireSuiviListener(this.gestSuiviListener);

	}
	private void setLookAndFeel() {
		/* Change the Look & Feel of the frame */
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); //$NON-NLS-1$
			SwingUtilities.updateComponentTreeUI(this.jf);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void changeIconLook(final String state) {
		if (state.equals("play")) { //$NON-NLS-1$
			this.playBtn.setIcon(new ImageIcon("img/play32clicked.png")); //$NON-NLS-1$
			this.pauseBtn.setIcon(new ImageIcon("img/pause32.png")); //$NON-NLS-1$
			this.stopBtn.setIcon(new ImageIcon("img/stop32.png")); //$NON-NLS-1$
			this.playBtn.setEnabled(false);
			this.pauseBtn.setEnabled(true);
			this.stopBtn.setEnabled(true);
			this.stateRunLb.setText(Constantes.STATE_WORKING_RUNNING);
		} else if (state.equals("pause")) { //$NON-NLS-1$
			this.playBtn.setIcon(new ImageIcon("img/play32.png")); //$NON-NLS-1$
			this.pauseBtn.setIcon(new ImageIcon("img/pause32clicked.png")); //$NON-NLS-1$
			this.stopBtn.setIcon(new ImageIcon("img/stop32.png")); //$NON-NLS-1$
			this.playBtn.setEnabled(true);
			this.pauseBtn.setEnabled(false);
			this.stopBtn.setEnabled(true);
			this.stateRunLb.setText(Constantes.STATE_WORKING_PAUSE);
		} else if (state.equals("stop")) { //$NON-NLS-1$
			this.playBtn.setIcon(new ImageIcon("img/play32.png")); //$NON-NLS-1$
			this.pauseBtn.setIcon(new ImageIcon("img/pause32clicked.png")); //$NON-NLS-1$
			this.stopBtn.setIcon(new ImageIcon("img/stop32clicked.png")); //$NON-NLS-1$
			this.playBtn.setEnabled(true);
			this.pauseBtn.setEnabled(false);
			this.stopBtn.setEnabled(false);
			this.stateRunLb.setText(Constantes.STATE_WORKING_STOPPED);
		} else {
			// TODO JR retirer ce com
			System.out.println("A problem occurs..."); //$NON-NLS-1$
		}
	}

	private void setErrorState(final int _id, final String _state) {
		switch (_id) {
			case Constantes.ID_MAP :
				this.stateMapLb.setText(_state);
				break;
			case Constantes.ID_GPS :
				this.stateGpsLb.setText(_state);
				break;
			default :
				break;
		}
	}

	private void addNewMap(final Layer layer) {
		map = new MapContent();
		map.addLayer(layer);
		jf.setMapContent(map);
		FramePrincipale.this.stateMapLb.setText(Constantes.STATE_MAP_LOADING);
		FramePrincipale.this.jf.validate();
	}
}

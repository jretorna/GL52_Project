package fr.utbm.gl52.BusTracer.Utils;

import javax.swing.JFileChooser;

/**
 * @version 01.00.00
 *
 * @author Jeremy - V01.00.00
 * @date 1 mai 2015<br>
 *
 */
public class TraitementFichier {
	public boolean hasCorrectExtension(	final String _file,
										final String[] _extension) {
		String ext = _file.substring(_file.lastIndexOf("."));
		for (String e : _extension) {
			if (ext.equals("." + e))
				return true;
		}
		return false;
	}

	public String getFile(final String[] ext) {
		JFileChooser choix = new JFileChooser();
		int retour = choix.showOpenDialog(null);
		if (retour == JFileChooser.APPROVE_OPTION) {
			if (hasCorrectExtension(
					choix.getSelectedFile().getAbsolutePath().toString(), ext)) {
				return choix.getSelectedFile().getAbsolutePath().toString();
			}
		}
		return null;
	}
}

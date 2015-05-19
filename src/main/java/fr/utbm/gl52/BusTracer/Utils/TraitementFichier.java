package fr.utbm.gl52.BusTracer.Utils;

/**
 * @version 01.00.00
 *
 * @author Jeremy - V01.00.00
 * @date 1 mai 2015<br>
 *
 */
public class TraitementFichier {
	public static boolean hasCorrectExtension(	final String _file,
												final String[] _extension) {
		String ext = _file.substring(_file.lastIndexOf("."));
		for (String e : _extension) {
			if (ext.equals("." + e))
				return true;
		}
		return false;
	}
}

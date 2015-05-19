package fr.utbm.gl52.BusTracer;

import java.io.IOException;

import fr.utbm.gl52.BusTracer.View.FramePrincipale;

/**
 * @version 01.00.00
 * 
 * @author Jeremy RETORNAZ - V01.00.00
 * @date 11 avr. 2015<br>
 * 
 */

public class LauncherGL52 {
	/**
	 * fonction main
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(final String[] args) throws IOException {
		new FramePrincipale().create();
	}
}

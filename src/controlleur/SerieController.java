package controlleur;

import java.io.File;

public class SerieController {

	/**
	 * @param args
	 */

	private void getListeEpisodeDossier() {
		File repertoire = new File("/");
		File[] files=repertoire.listFiles();
		
		for (File file : files) {
			System.out.println(file.toString());
		}
	}

}

class SeasonExistsException extends Exception {
	public SeasonExistsException(int numSeason) {
		System.out.println("La saison numéro " + numSeason + " existe déjà !");
	}
}

class SeasonExistsException extends Exception {
	public void SeasonExistsException(int numSeason) {
		System.out.println("La saison numéro " + numSeason + " existe déjà !");
	}
}

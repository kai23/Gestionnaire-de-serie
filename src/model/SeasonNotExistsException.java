class SeasonNotExistsException extends Exception {
	public void SeasonNotExistsException(int num) {
		System.out.println("La saison numéro " + num + " n'existe pas !");
	}
}

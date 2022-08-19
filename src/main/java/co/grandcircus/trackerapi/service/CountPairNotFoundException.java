package co.grandcircus.trackerapi.service;

public class CountPairNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public CountPairNotFoundException(String token) {
		super("Could not find Count Pair with token " + token);
	}
}

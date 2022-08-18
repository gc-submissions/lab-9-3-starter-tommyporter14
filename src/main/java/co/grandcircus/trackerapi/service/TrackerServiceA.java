package co.grandcircus.trackerapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import co.grandcircus.trackerapi.CountPairsRepository;
import co.grandcircus.trackerapi.model.CountPair;

@Service
public class TrackerServiceA implements TrackerService{
	
	@Autowired
	private CountPairsRepository repo;
	@ExceptionHandler(CountPairNotFoundException.class)
	String CountPairNotFoundHandler(CountPairNotFoundException ex) {
		return ex.getMessage();
	}
	private String recent;
	@Override
	public void add(String token) {
		CountPair countPair = repo.findByToken(token);
		if (countPair == null) {
			repo.save(new CountPair(token, 1));
		} else {
			repo.findAndIncrementCountByToken(token);
		}
	}

	@Override
	public void reset() {
		
		repo.deleteAll();
	}

	@Override
	public int getTotalCount() {
		List<CountPair> allCountPairs = repo.findAll();
		int totalCount = 0;
		for (CountPair countPair : allCountPairs) {
			totalCount += countPair.getCount();
		}
		return totalCount;
	}

	@Override
	public boolean getTokenExists(String token) {
		CountPair countPair = repo.findByToken(token);
		if (countPair == null) {
			return false;
		}
		return true;
	}

	@Override
	public int getTokenCount(String token) {
		CountPair countPair = repo.findByToken(token);
		if (countPair == null) {
			return 0;
		}
		return countPair.getCount();
	}
	public void sendRecent(String recent) {
		this.recent = recent;
	}
	@Override
	public String getLatest() {
		return recent;
	}

	@Override
	public CountPair getTop() {
		List<CountPair> allCountPairs = repo.findAll();
		int maxCount = 0;
		String token = "";
		for (CountPair countPair : allCountPairs) {
			if (countPair.getCount() > maxCount) {
				maxCount = countPair.getCount();
				token = countPair.getToken();
			}
		}
		return repo.findByToken(token);
	}

	@Override
	public List<String> getLatest5() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CountPair> getTop5() {
		// TODO Auto-generated method stub
		return null;
	}

	
}

package co.grandcircus.trackerapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.grandcircus.trackerapi.CountPairsRepository;
import co.grandcircus.trackerapi.model.CountPair;

@Service
public class TrackerServiceA implements TrackerService{

	@Autowired
	CountPairsRepository repo;
	
	@Override
	public void add(String token) {
		CountPair countPair = repo.findByToken(token);
		if(countPair == null) {
			repo.save(new CountPair(token, 1));
		}else {
			repo.findAndIncrementCountByToken(token);
		}
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getTokenExists(String token) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getTokenCount(String token) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getLatest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CountPair getTop() {
		// TODO Auto-generated method stub
		return null;
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

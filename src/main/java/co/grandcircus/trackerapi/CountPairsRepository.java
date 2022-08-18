package co.grandcircus.trackerapi;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Update;

import co.grandcircus.trackerapi.model.CountPair;

public interface CountPairsRepository extends MongoRepository<CountPair, String>{
	
	public List<CountPair> findAll();
	CountPair findByToken(String token);
	CountPair save(CountPair entity);
	void deleteAll();
	@Update("{ '$inc' : { 'count' : 1 } }")
	void findAndIncrementCountByToken(String token);
	
}

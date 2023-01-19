package eu.fbk.interlink.gamification.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.UncategorizedMongoDbException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.mongodb.MongoException;

import eu.trentorise.game.core.LogHub;
import eu.trentorise.game.repo.PlayerRepoImpl;
import eu.trentorise.game.repo.StatePersistence;

@Component
public class InterLinkerRepository {

	private static final Logger logger = LoggerFactory.getLogger(PlayerRepoImpl.class);
	@Autowired
	private MongoTemplate mongo;

	public Page<StatePersistence> search(String gameId, String pcName, String period, Pageable pageable) {
		List<StatePersistence> result = null;
		long totalSize = 0;
		try {
			Query q = new Query();
			q.addCriteria(Criteria.where("gameId").is(gameId));
			q.with(Sort.by(Sort.Order.desc("concepts.PointConcept." + pcName + ".obj.score")));
			q.fields().include("concepts.PointConcept." + pcName + ".obj.score");
			q.fields().include("playerId");
			result = mongo.find(q, StatePersistence.class);
			totalSize = mongo.count(q, StatePersistence.class);

		} catch (UncategorizedMongoDbException | MongoException e) {
			exceptionHandler(gameId, e);
		}
		return new PageImpl<>(result, pageable, totalSize);
	}

	private void exceptionHandler(String gameId, Exception e) {
		LogHub.error(gameId, logger, "Exception running mongo query in search", e);
		throw new IllegalArgumentException("Query seems to be not valid");
	}
}

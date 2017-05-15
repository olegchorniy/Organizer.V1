package kpi.ipt.organizer.events.repository.impl;

import com.mongodb.WriteResult;
import kpi.ipt.organizer.events.model.Event;
import kpi.ipt.organizer.events.repository.EventsRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventsRepositoryImpl implements EventsRepository {
    
    /* Event properties */

    private static final String USER_ID = "userId";
    private static final String ID = "id";
    private static final String START_TIME = "start";

    private final MongoOperations mongoOperations;

    public EventsRepositoryImpl(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public Event insert(Event event) {
        mongoOperations.insert(event);
        return event;
    }

    @Override
    public int deleteByIdAndUserId(long userId, String id) {
        Query deleteQuery = getIdAndUserIdQuery(userId, id);
        WriteResult result = mongoOperations.remove(deleteQuery, Event.class, Event.COLLECTION_NAME);

        return result.getN();
    }

    @Override
    public List<Event> findAllByUserIdOrderByStartTime(long userId) {
        Criteria userIdCriteria = Criteria.where(USER_ID).is(userId);
        Sort startTimeSort = new Sort(START_TIME);

        Query findQuery = Query.query(userIdCriteria)
                .with(startTimeSort);

        return mongoOperations.find(findQuery, Event.class, Event.COLLECTION_NAME);
    }

    private static Query getIdAndUserIdQuery(long userId, String eventId) {
        return Query.query(getIdAndUserIdCriteria(userId, eventId));
    }

    private static Criteria getIdAndUserIdCriteria(long userId, String eventId) {
        return Criteria
                .where(USER_ID).is(userId)
                .and(ID).is(eventId);
    }
}
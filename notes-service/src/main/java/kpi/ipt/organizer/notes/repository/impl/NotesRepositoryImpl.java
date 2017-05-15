package kpi.ipt.organizer.notes.repository.impl;

import com.mongodb.WriteResult;
import kpi.ipt.organizer.notes.model.Note;
import kpi.ipt.organizer.notes.repository.NotesRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author olch0615
 *         Date: 5/10/2017
 *         Time: 3:20 PM
 */
@Repository
public class NotesRepositoryImpl implements NotesRepository {

    /* Note properties */

    private static final String USER_ID = "userId";
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String TAGS = "tags";
    private static final String COLOR = "color";
    private static final String CREATION_TIME = "creationTime";

    private final MongoOperations mongoOperations;

    public NotesRepositoryImpl(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public Note findByIdAndUserId(long userId, String noteId) {
        return mongoOperations.findOne(getIdAndUserIdQuery(userId, noteId), Note.class, Note.COLLECTION_NAME);
    }

    @Override
    public List<Note> findAllByUserIdOrderByCreationTimeDesc(long userId) {
        Criteria userIdCriteria = Criteria.where(USER_ID).is(userId);
        Sort creationTimeDescSort = new Sort(Sort.Direction.DESC, CREATION_TIME);

        Query findQuery = Query.query(userIdCriteria)
                .with(creationTimeDescSort);

        return mongoOperations.find(findQuery, Note.class, Note.COLLECTION_NAME);
    }

    @Override
    public List<Note> findAllByUserIdAndText(long userId, String text) {

        Criteria userIdCriteria = Criteria.where(USER_ID).is(userId);

        TextCriteria textCriteria = new TextCriteria()
                .matching(text)
                .caseSensitive(false);

        Query findQuery = TextQuery.queryText(textCriteria)
                .sortByScore()
                .addCriteria(userIdCriteria);

        return mongoOperations.find(findQuery, Note.class, Note.COLLECTION_NAME);
    }

    @Override
    public Note insert(Note note) {
        mongoOperations.insert(note);
        return note;
    }

    @Override
    public int updateByIdAndUserId(Note note) {
        long userId = note.getUserId();
        String noteId = note.getId();

        Query updateQuery = getIdAndUserIdQuery(userId, noteId);
        Update update = new Update()
                .set(TITLE, note.getTitle())
                .set(DESCRIPTION, note.getDescription())
                .set(COLOR, note.getColor())
                .set(TAGS, note.getTags());

        WriteResult result = mongoOperations.updateFirst(updateQuery, update, Note.class, Note.COLLECTION_NAME);

        return result.getN();
    }

    @Override
    public int deleteByIdAndUserId(long userId, String id) {
        Query deleteQuery = getIdAndUserIdQuery(userId, id);
        WriteResult result = mongoOperations.remove(deleteQuery, Note.class, Note.COLLECTION_NAME);

        return result.getN();
    }

    private static Query getIdAndUserIdQuery(long userId, String noteId) {
        return Query.query(getIdAndUserIdCriteria(userId, noteId));
    }

    private static Criteria getIdAndUserIdCriteria(long userId, String noteId) {
        return Criteria
                .where(USER_ID).is(userId)
                .and(ID).is(noteId);
    }
}

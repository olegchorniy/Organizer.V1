package kpi.ipt.organizer.notes.repository.impl;

import kpi.ipt.organizer.notes.model.Note;
import kpi.ipt.organizer.notes.repository.NotesRepository;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.List;

/**
 * @author olch0615
 *         Date: 5/10/2017
 *         Time: 3:20 PM
 */
public class NotesRepositoryImpl implements NotesRepository {

    private final MongoOperations mongoOperations;

    public NotesRepositoryImpl(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public Note findByIdAndUserId(long userId, String noteId) {
        return null;
    }

    @Override
    public List<Note> findAllByUserIdOrderByCreationTimeDesc(long userId) {
        return null;
    }

    @Override
    public Note insert(Note note) {
        mongoOperations.insert(note);
        return note;
    }

    @Override
    public long updateByIdAndUserId(Note note) {
        return 0;
    }

    @Override
    public long deleteByIdAndUserId(String id, long userId) {
        return 0;
    }
}

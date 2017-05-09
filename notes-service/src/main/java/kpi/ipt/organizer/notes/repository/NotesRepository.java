package kpi.ipt.organizer.notes.repository;

import kpi.ipt.organizer.notes.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotesRepository extends MongoRepository<Note, String> {

    List<Note> findAllByUserIdOrderByCreationTimeDesc(long userId);

    long deleteByIdAndUserId(String id, long userId);
}

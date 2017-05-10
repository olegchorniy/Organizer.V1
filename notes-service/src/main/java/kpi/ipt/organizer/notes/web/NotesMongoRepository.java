package kpi.ipt.organizer.notes.web;

import kpi.ipt.organizer.notes.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotesMongoRepository extends MongoRepository<Note, String> {
}

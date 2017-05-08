package kpi.ipt.organizer.repository;

import kpi.ipt.organizer.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotesRepository extends MongoRepository<Note, String> {
}

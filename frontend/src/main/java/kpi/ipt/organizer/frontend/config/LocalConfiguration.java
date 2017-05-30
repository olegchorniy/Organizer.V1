package kpi.ipt.organizer.frontend.config;

import kpi.ipt.organizer.color.Color;
import kpi.ipt.organizer.frontend.client.EventsClient;
import kpi.ipt.organizer.frontend.client.NotesClient;
import kpi.ipt.organizer.frontend.client.UsersClient;
import kpi.ipt.organizer.frontend.model.rest.events.CreateEventRequest;
import kpi.ipt.organizer.frontend.model.rest.events.CreateEventResponse;
import kpi.ipt.organizer.frontend.model.rest.events.Event;
import kpi.ipt.organizer.frontend.model.rest.notes.Note;
import kpi.ipt.organizer.frontend.model.rest.notes.NoteRequest;
import kpi.ipt.organizer.frontend.model.rest.notes.SearchRequest;
import kpi.ipt.organizer.frontend.model.rest.users.AuthRequest;
import kpi.ipt.organizer.frontend.model.rest.users.AuthResponse;
import kpi.ipt.organizer.frontend.model.rest.users.RegistrationRequest;
import kpi.ipt.organizer.frontend.model.rest.users.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.*;

/**
 * @author olch0615
 *         Date: 5/30/2017
 *         Time: 1:49 PM
 */
@Profile("local")
@Configuration
public class LocalConfiguration {

    @Bean
    public EventsClient eventsClient() {
        return new EventsClient() {

            @Override
            public List<Event> getUserEvents(long userId, Long from, Long to) {
                return Arrays.asList(
                        new Event(1, "1", "Title 1", new Date(), new Date(), new Color(0x00FFFF))
                );
            }

            @Override
            public Map<String, Boolean> deleteEvent(long userId, String eventId) {
                return Collections.singletonMap("success", true);
            }

            @Override
            public CreateEventResponse createEvent(long userId, CreateEventRequest eventProperties) {
                return new CreateEventResponse("new-id-1");
            }
        };
    }

    @Bean
    public UsersClient usersClient() {
        return new UsersClient() {
            @Override
            public Map<String, Boolean> register(RegistrationRequest request) {
                return Collections.singletonMap("success", true);
            }

            @Override
            public AuthResponse authenticate(AuthRequest authRequest) {
                return new AuthResponse(true, new User(1, "Name 1", "my@email.com"));
            }
        };
    }

    @Bean
    public NotesClient notesClient() {
        return new NotesClient() {

            @Override
            public List<Note> getUserNotes(long userId) {
                return Arrays.asList(
                        new Note(1, "1", new Date(), "Title 1", "Description 1", Collections.emptyList(), new Color(1)),
                        new Note(1, "2", new Date(), "Title 2", "Description 2", Collections.emptyList(), new Color(2))
                );
            }

            @Override
            public List<Note> searchNotes(long userId, SearchRequest request) {
                return Arrays.asList(
                        new Note(1, "1", new Date(), "Title 1", "Description 1", Collections.emptyList(), new Color(1))
                );
            }

            @Override
            public Note getNote(long userId, String noteId) {
                return null;
            }

            @Override
            public Note createNote(long userId, NoteRequest note) {
                return new Note(1, UUID.randomUUID().toString(), new Date(), "Title X", "Description X", Collections.emptyList(), new Color(1));
            }

            @Override
            public Map<String, Boolean> editNote(long userId, String noteId, NoteRequest note) {
                return Collections.singletonMap("success", true);
            }

            @Override
            public Map<String, Boolean> deleteNote(long userId, String noteId) {
                return Collections.singletonMap("success", true);
            }
        };
    }
}
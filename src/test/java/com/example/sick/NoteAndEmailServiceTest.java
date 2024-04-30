package com.example.sick;

import com.example.sick.api.model.response.MailTextResponse;
import com.example.sick.api.model.response.MailsAndNotesResponse;
import com.example.sick.api.model.response.NotesTextResponse;
import com.example.sick.domain.MailDAOResponse;
import com.example.sick.domain.NoteDAOResponse;
import com.example.sick.repository.MailRepository;
import com.example.sick.repository.NoteRepository;
import com.example.sick.service.NoteAndEmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NoteAndEmailServiceTest {
    @Mock
    private NoteRepository noteRepoMock;
    @Mock
    private MailRepository mailRepoMock;
    @Mock
    private NoteAndEmailService service;

    @BeforeEach
    public void setUp() {
        service = new NoteAndEmailService(noteRepoMock, mailRepoMock);
    }

    @Test
    public void testingGetNoteById() {
        long id = 1L;
        service.getNoteById(id);
        verify(noteRepoMock).selectNotesById(id);
    }

    @Test
    public void testingGetMailById() {
        long id = 1L;
        service.getMailById(id);
        verify(mailRepoMock).selectMailByApplicationId(id);
    }

    @Test
    public void testingGetNoteAndMailById() {
        long id = 1L;
        when(noteRepoMock.selectNotesById(id)).thenReturn(new ArrayList<>());
        when(mailRepoMock.selectMailByApplicationId(id)).thenReturn(new ArrayList<>());

        MailsAndNotesResponse response = service.getNoteAndMailById(id);

        assertEquals(id, response.applicationId());
        assertEquals(0, response.notesTexts().size());
        assertEquals(0, response.mailTexts().size());
    }

    @Test
    public void testingConvertDAOResponseIntoNoteResponseList() {
        LocalDateTime timeDateNow = LocalDateTime.now();
        NoteDAOResponse noteDAO = new NoteDAOResponse(1L, 1, "My test note", timeDateNow);
        NotesTextResponse expected = new NotesTextResponse("My test note", timeDateNow);

        NotesTextResponse actual = service.convertDAOResponseIntoNoteResponseList(noteDAO);

        assertEquals(expected, actual);
    }

    @Test
    public void testingConvertDAOResponseIntoMailResponseList() {
        LocalDateTime timeDateNow = LocalDateTime.now();
        MailDAOResponse mailDAO = new MailDAOResponse(1L, 1, "My test mail", timeDateNow);
        MailTextResponse expected = new MailTextResponse("My test mail", timeDateNow);

        MailTextResponse actual = service.convertDAOResponseIntoMailResponseList(mailDAO);

        assertEquals(expected, actual);


    }
}

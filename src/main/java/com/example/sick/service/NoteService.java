package com.example.sick.service;

import com.example.sick.api.model.request.NoteRequest;
import com.example.sick.api.model.response.NoteResponse;
import com.example.sick.domain.NoteDAORequest;
import com.example.sick.domain.NoteDAOResponse;
import com.example.sick.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NoteService {

    private NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public NoteResponse getNotesById(long id) {

        Optional<NoteDAOResponse> note = noteRepository.selectNotesById(id);
        if (note.isPresent()) {
            return convertDaoResponseIntoNoteResponse(note.get());
        } else {
            return new NoteResponse("");
        }
    }

    private NoteResponse convertDaoResponseIntoNoteResponse(NoteDAOResponse note) {
        return note == null
                ? null
                : new NoteResponse(note.noteText());
    }

    public void createNote(NoteRequest noteRequest) {

        if (noteRequest == null) {
            throw new IllegalArgumentException("Note request must not be null");
        }
        String noteText = noteRequest.noteText() == null ? "" : noteRequest.noteText();
        NoteDAORequest daoRequest = new NoteDAORequest(noteRequest.applicationId(), noteText);

        noteRepository.createNote(daoRequest);
    }
}

package br.com.adaptador.service;
import br.com.dominio.entidade.Evento;
import br.com.dominio.entidade.Usuario;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class EventoService {

    private final Firestore db;

    public EventoService() {
        db = FirestoreClient.getFirestore();
    }

    public String salvarEvento(Evento evento) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("eventos").document();
        ApiFuture<WriteResult> result = docRef.set(evento);
        return docRef.getId();
    }

    public Evento obterEvento(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("eventos").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            return document.toObject(Evento.class);
        } else {
            return null;
        }
    }

    public List<Evento> listarEventos() throws ExecutionException, InterruptedException {
        CollectionReference eventosRef = db.collection("eventos");
        ApiFuture<QuerySnapshot> querySnapshot = eventosRef.get();
        List<Evento> eventos = new ArrayList<>();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            eventos.add(document.toObject(Evento.class));
        }
        return eventos;
    }

    public boolean atualizarEvento(String id, Evento evento) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("eventos").document(id);
        ApiFuture<WriteResult> result = docRef.set(evento);
        return result.isDone();
    }

    public boolean deletarEvento(String id) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> result = db.collection("eventos").document(id).delete();
        return result.isDone();
    }

    public boolean adicionarParticipante(String idEvento, Usuario participante) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("eventos").document(idEvento);
        Evento evento = obterEvento(idEvento);
        if (evento == null) {
            throw new IllegalArgumentException("Evento não encontrado com o ID: " + idEvento);
        }
        List<Usuario> participantes = evento.getParticipantes();
        participantes.add(participante);
        evento.setParticipantes(participantes);
        ApiFuture<WriteResult> result = docRef.set(evento);
        return result.isDone();
    }
}
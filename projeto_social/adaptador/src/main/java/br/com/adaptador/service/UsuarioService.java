package br.com.adaptador.service;

import br.com.dominio.entidade.Usuario;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UsuarioService {

    private final Firestore db;

    public UsuarioService() throws IOException {
        db = FirestoreClient.getFirestore();
    }

    public String salvarUsuario(Usuario usuario) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("usuarios").document(usuario.getCpf().getNumero_cpf());
        ApiFuture<WriteResult> result = docRef.set(usuario);
        return usuario.getCpf().getNumero_cpf();
    }

    public Usuario obterUsuario(String cpf) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("usuarios").document(cpf);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            return document.toObject(Usuario.class);
        } else {
            return null;
        }
    }

    public List<Usuario> listarUsuarios() throws ExecutionException, InterruptedException {
        CollectionReference usuariosRef = db.collection("usuarios");
        ApiFuture<QuerySnapshot> querySnapshot = usuariosRef.get();
        List<Usuario> usuarios = new ArrayList<>();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            usuarios.add(document.toObject(Usuario.class));
        }
        return usuarios;
    }

    public Usuario atualizarUsuario(String cpf, Usuario usuario) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("usuarios").document(cpf);
        ApiFuture<WriteResult> result = docRef.set(usuario);
        return usuario;
    }

    public boolean deletarUsuario(String cpf) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> result = db.collection("usuarios").document(cpf).delete();
        return result.isDone();
    }
}
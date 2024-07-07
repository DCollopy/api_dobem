package br.com.adaptador.service;

import br.com.dominio.entidade.Usuario;
import br.com.dominio.usercase.UsuarioValida;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
@DependsOn("firebaseConfig")
public class UsuarioService {

    private final Firestore db;
    UsuarioValida usuarioValida = new UsuarioValida();

    public UsuarioService() throws IOException {
        db = FirestoreClient.getFirestore();
    }

    public UsuarioService(Firestore db, UsuarioValida usuarioValida) {
        this.db = db;
        this.usuarioValida = usuarioValida;
    }

    public String salvarUsuario(Usuario usuario) throws ExecutionException, InterruptedException {
        Usuario usua = usuarioValida.criaUsuario(usuario);
        DocumentReference docRef = db.collection("usuarios").document(usua.getCpf());
        Map<String, Object> data = new HashMap<>();
        data.put("nome", usua.getNome());
        data.put("sobrenome", usua.getSobrenome());
        data.put("cpf",usua.getCpf());
        ApiFuture<WriteResult> result = docRef.set(data);
        return usuario.getCpf();
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
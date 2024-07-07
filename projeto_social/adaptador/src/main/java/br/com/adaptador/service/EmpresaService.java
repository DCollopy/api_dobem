package br.com.adaptador.service;

import br.com.dominio.entidade.Empresa;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.io.IOException;
import java.util.List;

import java.util.concurrent.ExecutionException;

@Service
public class EmpresaService {

    private final Firestore db;

    public EmpresaService() throws IOException {
        db = FirestoreClient.getFirestore();
    }

    public String salvarEmpresa(Empresa empresa) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("empresas").document(empresa.getCnpj().getNumero_cnpj());
        ApiFuture<WriteResult> result = docRef.set(empresa);
        return empresa.getCnpj().getNumero_cnpj();
    }

    public Empresa obterEmpresa(String cnpj) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("empresas").document(cnpj);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            return document.toObject(Empresa.class);
        } else {
            return null;
        }
    }

    public List<Empresa> listarEmpresas() throws ExecutionException, InterruptedException {
        CollectionReference empresasRef = db.collection("empresas");
        ApiFuture<QuerySnapshot> querySnapshot = empresasRef.get();
        List<Empresa> empresas = new ArrayList<>();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            empresas.add(document.toObject(Empresa.class));
        }
        return empresas;
    }

    public Empresa atualizarEmpresa(String cnpj, Empresa empresa) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("empresas").document(cnpj);
        ApiFuture<WriteResult> result = docRef.set(empresa);
        return empresa;
    }

    public boolean deletarEmpresa(String cnpj) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> result = db.collection("empresas").document(cnpj).delete();
        return result.isDone();
    }
}
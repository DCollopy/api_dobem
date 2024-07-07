package br.com.adaptador.service;

import br.com.dominio.entidade.Empresa;
import br.com.dominio.usercase.EmpresaValida;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.io.IOException;
import java.util.List;

import java.util.concurrent.ExecutionException;

@Service
@DependsOn("firebaseConfig")
public class EmpresaService {

    private final Firestore db;
    EmpresaValida empresaValida = new EmpresaValida();

    public EmpresaService() throws IOException {
        db = FirestoreClient.getFirestore();
    }

    public EmpresaService(Firestore db, EmpresaValida empresaValida) {
        this.db = db;
        this.empresaValida = empresaValida;
    }

    public String salvarEmpresa(Empresa empresa) throws ExecutionException, InterruptedException {
        Empresa emp = empresaValida.criaEmpresa(empresa);
        DocumentReference docRef = db.collection("empresas").document(emp.getCnpj().getNumero_cnpj());
        ApiFuture<WriteResult> result = docRef.set(emp);
        return emp.getCnpj().getNumero_cnpj();
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
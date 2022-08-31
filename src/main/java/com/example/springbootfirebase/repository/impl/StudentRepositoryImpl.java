package com.example.springbootfirebase.repository.impl;

import com.example.springbootfirebase.domain.Student;
import com.example.springbootfirebase.repository.StudentRepository;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Component
public class StudentRepositoryImpl implements StudentRepository {

    private static final String COLLECTION_NAME = "student";
    private final Firestore db;

    public StudentRepositoryImpl(Firestore db) {
        this.db = db;
    }

    @Override
    public List<Student> findAll() {
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();
        try {
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            List<Student> response = new ArrayList<>();
            for (QueryDocumentSnapshot document : documents) {
                response.add(document.toObject(Student.class));
            }
            return response;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Student save(Student student) {
        try {
            db.collection(COLLECTION_NAME)
                    .document(student.getId())
                    .set(student);
            return student;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Student> findById(String id) {
        DocumentReference documentReference = db.collection(COLLECTION_NAME)
                .document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document;

        try {
            document = future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        return Optional.ofNullable(document.toObject(Student.class));
    }

    @Override
    public String delete(String id) {
        db.collection(COLLECTION_NAME)
                .document(id)
                .delete();
        return "SUCCESS";
    }

    @Override
    public List<Student> findByField(String field, String value) {
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME)
                .whereEqualTo(field, value)
                .get();
        try {
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            List<Student> students = new ArrayList<>();
            for (DocumentSnapshot document : documents) {
                students.add(document.toObject(Student.class));
            }
            return students;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}

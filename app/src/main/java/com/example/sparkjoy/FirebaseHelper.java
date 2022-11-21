package com.example.sparkjoy;


import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * The purpose of this class is to hold ALL the code to communicate with Firebase.  This class
 * will connect with Firebase auth and Firebase firestore.  Each class that needs to verify
 * authentication OR access data from the database will reference a variable of this class and
 * call a method of this class to handle the task.  Essentially this class is like a "gopher" that
 * will go and do whatever the other classes want or need it to do.  This allows us to keep all
 * our other classes clean of the firebase code and also avoid having to update firebase code
 * in many places.  This is MUCH more efficient and less error prone.
 */
public class FirebaseHelper {
    public final String TAG = "sparky";
    private static String uid = null;      // var will be updated for currently signed in user
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private ArrayList<DailyInfo> dailyInfos;

    public FirebaseHelper() {
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }


    public FirebaseAuth getmAuth() {
        return mAuth;
    }

    public void logOutUser() {
        mAuth.signOut();
        this.uid = null;
    }


    public void updateUid(String uid) {
        this.uid = uid;
    }

    public void attachReadDataToUser() {
        // This is necessary to avoid the issues we ran into with data displaying before it
        // returned from the asynch method calls
        if (mAuth.getCurrentUser() != null) {
            uid = mAuth.getUid();
            readData(new FirestoreCallback() {
                @Override
                public void onCallback(ArrayList<DailyInfo> dailyInfos) {
                    Log.d(TAG, "Inside attachReadDataToUser, onCallback " + dailyInfos.toString());
                }
            });
        }
        else {
            Log.d(TAG, "No one logged in");
        }
    }

    public void addUserToFirestore(String name, String newUID) {
        // Create a new user with their name
        Map<String, Object> user = new HashMap<>();
        user.put("name", name);
        // Add a new document with a docID = to the authenticated user's UID
        db.collection("users").document(newUID)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, name + "'s user account added");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding user account", e);
                    }
                });
    }

    public void addData(DailyInfo d) {
        // add Memory m to the database
        // this method is overloaded and incorporates the interface to handle the asynch calls
        addData(d, new FirestoreCallback() {
            @Override
            public void onCallback(ArrayList<DailyInfo> myDailyInfo) {
                Log.i(TAG, "Inside addData, onCallback :" + myDailyInfo.toString());
            }
        });
    }

    private void addData(DailyInfo d, FirestoreCallback firestoreCallback) {
        db.collection("users").document(uid).collection("myDailyInfo")
                .add(d)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        // This will set the docID key for the Memory that was just added.
                        db.collection("users").document(uid).collection("myDailyInfo").
                                document(documentReference.getId()).update("docID", documentReference.getId());
                        Log.i(TAG, "just added ");
                        readData(firestoreCallback);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i(TAG, "Error adding document", e);
                    }
                });
    }

    private void readData(FirestoreCallback firestoreCallback) {
        dailyInfos.clear();        // empties the AL so that it can get a fresh copy of data
        db.collection("users").document(uid).collection("myDailyInfo")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot doc: task.getResult()) {
                                DailyInfo d = doc.toObject(DailyInfo.class);
                                dailyInfos.add(d);
                            }

                            Log.i(TAG, "Success reading data: "+ dailyInfos.toString());
                            firestoreCallback.onCallback(dailyInfos);
                        }
                        else {
                            Log.d(TAG, "Error getting documents: " + task.getException());
                        }
                    }
                });
    }

    public interface FirestoreCallback {
        void onCallback(ArrayList<DailyInfo> myList);
    }

}
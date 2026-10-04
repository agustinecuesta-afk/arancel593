package com.importacion.backend.security;

import com.google.firebase.auth.FirebaseAuthException;

@FunctionalInterface
public interface FirebaseIdTokenVerifier {

    VerifiedFirebaseUser verify(String idToken) throws FirebaseAuthException;
}

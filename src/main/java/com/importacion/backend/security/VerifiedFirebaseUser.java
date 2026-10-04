package com.importacion.backend.security;

public final class VerifiedFirebaseUser {

    private final String email;
    private final boolean emailVerified;

    public VerifiedFirebaseUser(String email, boolean emailVerified) {
        this.email = email;
        this.emailVerified = emailVerified;
    }

    public String getEmail() {
        return email;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }
}

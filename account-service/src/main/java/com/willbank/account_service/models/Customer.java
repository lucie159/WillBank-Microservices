package com.willbank.account_service.models;

// Boite pour stocker les données reçues du Service Client.
public class Customer {
    private Long id;
    private String nom;
    private String prenom;
    private String email;

    // pour la désérialisation JSON)
    public Customer() {}


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
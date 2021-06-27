package br.com.adotapet.api.adocao;

import br.com.adotapet.api.pets.Pet;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "adocoes", schema = "public")
public class Adocao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pets_id", foreignKey = @ForeignKey(name = "fk_adocoes_pets"), nullable = false)
    private Pet pets;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String email;

    public Adocao() {
    }

    public Adocao(Long id, Pet pets, String nome, String telefone, String cidade, String email) {
        this.id = id;
        this.pets = pets;
        this.nome = nome;
        this.telefone = telefone;
        this.cidade = cidade;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pet getPets() {
        return pets;
    }

    public void setPets(Pet pets) {
        this.pets = pets;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "pets_id", foreignKey = @ForeignKey(name = "fk_adocoes_pets"))
    private Pet pets;

    @Column()
    private String nome;

    @Column()
    private Double telefone;

    @Column()
    private String cidade;

    @Column()
    private String email;

    public Adocao() {
    }

    public Adocao(Long id, Pet pets, String nome, Double telefone, String cidade, String email) {
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

    public Double getTelefone() {
        return telefone;
    }

    public void setTelefone(Double telefone) {
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

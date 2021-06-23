package br.com.adotapet.api.pets;

import br.com.adotapet.api.vacina.Vacina;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "pets", schema="public")
public class Pet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EspecieEnum especie;

    @Enumerated(EnumType.STRING)
    private SexoEnum sexo;

    @Column()
    private String nome;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column()
    private String imagem;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "pets_vacinas",
            joinColumns = @JoinColumn(name = "pet_id"),
            inverseJoinColumns = @JoinColumn(name = "vacina_id")
    )
    private List<Vacina> vacinas;

    public Pet() {
    }

    public Pet(Long id, EspecieEnum especie, SexoEnum sexo, String nome, LocalDate dataNascimento, String imagem, List<Vacina> vacinas) {
        this.id = id;
        this.especie = especie;
        this.sexo = sexo;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.imagem = imagem;
        this.vacinas = vacinas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EspecieEnum getEspecie() {
        return especie;
    }

    public void setEspecie(EspecieEnum especie) {
        this.especie = especie;
    }

    public SexoEnum getSexo() {
        return sexo;
    }

    public void setSexo(SexoEnum sexo) {
        this.sexo = sexo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public List<Vacina> getVacinas() {
        return vacinas;
    }

    public void setVacinas(List<Vacina> vacinas) {
        this.vacinas = vacinas;
    }
}


package br.com.adotapet.api.vacina;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author aula
 */
@Entity
@Table(name = "vacinas", schema="public")
public class Vacina implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "qtd_doses", nullable = false)
    private Integer qtdDoses;

    public Vacina() {
    }

    public Vacina(Long id, String nome, Integer qtdDoses) {
        this.id = id;
        this.nome = nome;
        this.qtdDoses = qtdDoses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQtdDoses() {
        return qtdDoses;
    }

    public void setQtdDoses(Integer qtdDoses) {
        this.qtdDoses = qtdDoses;
    }
}

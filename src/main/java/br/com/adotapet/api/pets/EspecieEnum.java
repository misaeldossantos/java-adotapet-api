package br.com.adotapet.api.pets;

public enum EspecieEnum {
    AVES("Aves"),
    COELHOS("Coelhos"),
    FUROES("Furões"),
    PEIXES("Peixes"),
    REPTEIS("Répteis"),
    ROEDORES("Roedores"),
    CAES("Cães"),
    GATOS("Gatos");

    private String descricao;

    EspecieEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

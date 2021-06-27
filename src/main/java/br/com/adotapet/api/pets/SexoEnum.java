package br.com.adotapet.api.pets;

public enum SexoEnum {
    MACHO("Macho"),
    FEMEA("Fêmea");

    private String descricao;

    SexoEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

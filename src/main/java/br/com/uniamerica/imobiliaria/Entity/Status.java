package br.com.uniamerica.imobiliaria.Entity;

public enum LisitingStatus {

    Venda("Vendido"),
    Listado("Listado"),
    Reservado("Reservado"),
   Alugado("Alugado");
    private String type;

    LisitingStatus(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

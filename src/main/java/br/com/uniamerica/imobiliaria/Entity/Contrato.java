package br.com.uniamerica.imobiliaria.Entity;

public enum Contract {

    Venda("Venda"),
    Locação("Locação");
    private String type;

    Contract(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}


package br.com.fipe.fipeApp.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosMarca(String codigo, String nome) {

    @Override
    public String toString() {
        return "" +
                "Codigo: " + codigo + "  | " +
                "Nome: " + nome + '\'' +
                ' ';
    }
}

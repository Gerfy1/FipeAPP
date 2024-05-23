package br.com.fipe.fipeApp.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record FipeVeiculo(
        @JsonAlias("Valor") String valor,
        @JsonAlias("Marca") String marca,
        @JsonAlias("Modelo") String modelo,
        @JsonAlias("AnoModelo") Integer ano,
        @JsonAlias("Combustivel") String combustivel,
        @JsonAlias("CodigoFipe") String codigoFipe,
        @JsonAlias("SiglaCombustivel") char siglaCombustivel)
{
    @Override
    public String toString() {
        return
                "Valor: " + valor + " | " +
                "Marca: " + marca + " | " +
                "Modelo: " + modelo + " | " +
                "Ano: " + ano + " | " +
                "Combustivel: " + combustivel + " | " +
                "Codigo Fipe: " + codigoFipe + " | " +
                "Sigla Combustivel: " + siglaCombustivel + " | ";
    }
}

package br.com.fipe.fipeApp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ModelosMarca(List<DadosMarca> modelos) {
}

package br.com.fipe.fipeApp.principal;

import br.com.fipe.fipeApp.model.DadosMarca;
import br.com.fipe.fipeApp.model.FipeVeiculo;
import br.com.fipe.fipeApp.model.ModelosMarca;
import br.com.fipe.fipeApp.service.ConsumoAPI;
import br.com.fipe.fipeApp.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private final String URL_PADRAO = "https://parallelum.com.br/fipe/api/v1/";
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();
    private Scanner leitura = new Scanner(System.in);

    public void exibirMenu(){
        var menuFIPE = """
                ------------------------------
                Escolha dentre essas opções: 
                Carro
                Moto
                Caminhao
                """;
        System.out.println(menuFIPE);
        var opcao = leitura.nextLine();
        String endereco = "";

        if(opcao.toLowerCase().contains("car")){
            endereco = URL_PADRAO+"carros/marcas";
        } else if(opcao.toLowerCase().contains("caminh")){
            endereco = URL_PADRAO+"caminhoes/marcas";
        } else{
            endereco = URL_PADRAO+"motos/marcas";
        }
        var json = consumo.obterDados(endereco);
        var marcas = conversor.obterDadosLista(json, DadosMarca.class);
        marcas.stream()
                .sorted(Comparator.comparing(DadosMarca::codigo))
                .forEach(System.out::println);

        System.out.println("Digite o codigo da marca: ");
        var codigoMarca = leitura.nextLine();
        endereco =  endereco+"/"+codigoMarca+"/modelos";
        json = consumo.obterDados(endereco);
        var modeloLista = conversor.obterDados(json, ModelosMarca.class);
        System.out.println("\nModelos dessa marca: ");
        modeloLista.modelos().stream()
                .sorted(Comparator.comparing(DadosMarca::codigo))
                .forEach(System.out::println);
        System.out.println("\nDigite um trecho do nome do veiculo para a consulta: ");
        var nomeModelo = leitura.nextLine();

        List<DadosMarca> modelosFiltrados = modeloLista.modelos().stream()
                .filter( m -> m.nome().toLowerCase().contains(nomeModelo.toLowerCase()))
                .collect(Collectors.toList());
        System.out.println("\nModelos filtrados: ");
        modelosFiltrados.forEach(System.out::println);

        System.out.println("Digite o codigo da modelo: ");
        var codigoModelo = leitura.nextLine();

        endereco =  endereco+"/"+codigoModelo+"/anos";
        json = consumo.obterDados(endereco);
        List<DadosMarca> anos = conversor.obterDadosLista(json, DadosMarca.class);
        List<FipeVeiculo> veiculos = new ArrayList<>();
        for (int i = 0; i < anos.size() ; i++) {
            var enderecoAnos = endereco +"/"+anos.get(i).codigo();
            json = consumo.obterDados(enderecoAnos);
            FipeVeiculo fipeVeiculo = conversor.obterDados(json, FipeVeiculo.class);
            veiculos.add(fipeVeiculo);
        }
        System.out.println("\nVeiculos filtrados com avaliações por ano: ");
        veiculos.forEach(System.out::println);
    }
}

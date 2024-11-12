import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int tamanhoTabela = 7500;
        TabelaHash tabelaMultiplicacao = new TabelaHashAurea(tamanhoTabela);
        TabelaHash tabelaSimples = new TabelaHashASCII(tamanhoTabela);

        List<String> nomes = lerNomes("src/female_names.txt");

        System.out.println("Inserindo nomes nas tabelas hash...");
        for (String nome : nomes) {
            tabelaMultiplicacao.inserir(nome);
            tabelaSimples.inserir(nome);
        }

        System.out.println("\nRealizando buscas...");
        for (int i = 0; i < 1000; i++) {
            String nomeBusca = nomes.get(i);
            tabelaMultiplicacao.buscar(nomeBusca);
            tabelaSimples.buscar(nomeBusca);
        }

        System.out.println("\n=== RELATÓRIO COMPARATIVO ===");
        System.out.println("\n--- Tabela Hash com Método da Multiplicação ---");
        tabelaMultiplicacao.printarEstatisticas();
        System.out.println("\n--- Tabela Hash com Função Simples ---");
        tabelaSimples.printarEstatisticas();
    }

    private static List<String> lerNomes(String nomeArquivo) {
        List<String> nomes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                nomes.add(linha.trim());
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo: " + e.getMessage());
        }
        return nomes;
    }
}
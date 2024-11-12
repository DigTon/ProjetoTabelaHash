public abstract class TabelaHash {
    protected String[] tabela;
    protected int tamanho;
    protected int colisoes;
    protected long tempoInsercao;
    protected long tempoBusca;
    protected int[] colisoesporPosicao;

    public TabelaHash(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new String[tamanho];
        this.colisoes = 0;
        this.tempoInsercao = 0;
        this.tempoBusca = 0;
        this.colisoesporPosicao = new int[tamanho];
    }

    protected abstract int funcaoHash(String chave);

    public void inserir(String chave) {
        long tempoInicio = System.nanoTime();

        int indice = funcaoHash(chave);
        int indiceOriginal = indice;

        // Sondagem linear
        while (tabela[indice] != null) {
            colisoes++;
            colisoesporPosicao[indiceOriginal]++;
            indice = (indice + 1) % tamanho;

            if (indice == indiceOriginal) {
                System.out.println("Tabela está cheia!");
                return;
            }
        }

        tabela[indice] = chave;
        tempoInsercao += System.nanoTime() - tempoInicio;
    }

    public boolean buscar(String chave) {
        long tempoInicio = System.nanoTime();

        int indice = funcaoHash(chave);
        int indiceOriginal = indice;

        while (tabela[indice] != null) {
            if (tabela[indice].equals(chave)) {
                tempoBusca += System.nanoTime() - tempoInicio;
                return true;
            }

            indice = (indice + 1) % tamanho;

            if (indice == indiceOriginal) {
                break;
            }
        }

        tempoBusca += System.nanoTime() - tempoInicio;
        return false;
    }

    public void printarEstatisticas() {
        System.out.println("\nEstatisticas da Tabela Hash " + this.getClass().getSimpleName());
        System.out.println("Total de Colisões: " + colisoes);
        System.out.println("Tempo de Inserção (ms): " + tempoInsercao / 1_000_000.0);
        System.out.println("Tempo de Busca (ms): " + tempoBusca / 1_000_000.0);

        System.out.println("\nColisões por indice:");
        for (int i = 0; i < tamanho; i++) {
            if (colisoesporPosicao[i] > 0) {
                System.out.println("Posição " + i + ": " + colisoesporPosicao[i] + " colisões");
            }
        }

        System.out.println("\nDistribuição das Chaves:");
        int ocupadas = 0;
        for (int i = 0; i < tamanho; i++) {
            if (tabela[i] != null) {
                ocupadas++;
            }
        }
        System.out.println("Posições ocupadas: " + ocupadas + "/" + tamanho);
    }
}
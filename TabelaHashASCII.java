public class TabelaHashASCII extends TabelaHash {
    public TabelaHashASCII(int tamanho) {
        super(tamanho);
    }

    @Override
    protected int funcaoHash(String chave) {
        int soma = 0;


        for (char c : chave.toCharArray()) {
            soma += (int) c;
        }

        soma *= 7;

        return Math.abs(soma % tamanho);
    }
}
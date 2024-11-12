public class TabelaHashAurea extends TabelaHash {
    private static final double A = 0.6180339887;

    public TabelaHashAurea(int tamanho) {
        super(tamanho);
    }

    @Override
    protected int funcaoHash(String chave) {
        long hash = 0;
        for (char c : chave.toCharArray()) {
            hash = 31 * hash + c;
        }
        double temp = (hash * A) % 1;
        return (int) (tamanho * temp);
    }
}
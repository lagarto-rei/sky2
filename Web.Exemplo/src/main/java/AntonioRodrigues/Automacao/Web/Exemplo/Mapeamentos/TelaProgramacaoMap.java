package AntonioRodrigues.Automacao.Web.Exemplo.Mapeamentos;

public enum TelaProgramacaoMap {
	BotaoListaCanais("//a[@href='/programacao/lista-canais']/div");

    private String _mapeamento;
    
    TelaProgramacaoMap(String map) {
        this._mapeamento = map;
    }

    @Override
    public String toString(){
        return _mapeamento;
    }
}

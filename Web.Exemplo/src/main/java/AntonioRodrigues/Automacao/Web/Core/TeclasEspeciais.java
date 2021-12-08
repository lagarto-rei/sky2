package AntonioRodrigues.Automacao.Web.Core;

public enum TeclasEspeciais {
	Enter("\uE007");
	
    private String _mapeamento;
    
    TeclasEspeciais(String map) {
        this._mapeamento = map;
    }

    @Override
    public String toString(){
        return _mapeamento;
    }
}

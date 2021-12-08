package AntonioRodrigues.Automacao.Web.Exemplo.Mapeamentos;

public enum TelaListaCanaisMap {
	CampoFiltroCanais("searchChannel"),
	TextoCanalFiltrado("//li[not(@style='display: none;')]/div/div[@class='col-xs-7 col-sm-7']/p");
	
    private String _mapeamento;
    
    TelaListaCanaisMap(String map) {
        this._mapeamento = map;
    }

    @Override
    public String toString(){
        return _mapeamento;
    }
}

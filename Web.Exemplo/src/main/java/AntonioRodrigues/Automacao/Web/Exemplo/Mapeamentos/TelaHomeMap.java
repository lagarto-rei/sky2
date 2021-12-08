package AntonioRodrigues.Automacao.Web.Exemplo.Mapeamentos;

public enum TelaHomeMap {
	BotaoLogin("//ytd-button-renderer[@class='style-scope ytd-masthead style-suggestive size-small']//paper-button[@id='button']"),
	BotaoHome("//ytd-topbar-logo-renderer[@id='logo']//div[@id='logo-icon-container']"),
	CampoPesquisa("//input[@id='search']"),
	BotaoPesquisar("//button[@id='search-icon-legacy']"),
	ImagemLogado("//yt-img-shadow[@class='style-scope ytd-topbar-menu-button-renderer no-transition']//img[@id='img']"),
	LinkProgramacao("//a[@href='/programacao']/strong"),
	BotaoFecharModal("//button[@class='close']");
	
	
    private String _mapeamento;
    
    TelaHomeMap(String map) {
        this._mapeamento = map;
    }

    @Override
    public String toString(){
        return _mapeamento;
    }
}

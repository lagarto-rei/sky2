 # language: pt
Funcionalidade: Exemplo de uso do modelo da automacao

    Contexto: Eu como usuario dos fontes de automacao
              Necessito de um exemplo de uso dos fontes
              Para que possa realizar a implementacao de testes automatizados no clente

	@CTA0001
	Cenario: Validar filtro de pesquisa de canais
	Dado Que configuro o documento de evidencia da execucao
	| IdDemanda		| Sprint	| Sistema			| DescricaoCenario 							| ResultadoEsperado									|
	| CTA0001		| 0			| Portal Sky		| Validação do filtro de pesqusa de canais	| Exibição do canal pesquisado na lista de canais	|
	Dado Estou na tela home do portal Sky
	Quando Acesso a area de Programacao
	E Acesso a Lista de Canais
	E Aplico filtro na pesquisa de canais 
	Entao Verifico se o canal pesquisado e exibido na lista

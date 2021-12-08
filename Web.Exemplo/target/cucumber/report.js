$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri('AntonioRodrigues\Automacao\Web\Exemplo\Features\Exemplo.feature');
formatter.feature({
  "comments": [
    {
      "line": 1,
      "value": "# language: pt"
    }
  ],
  "line": 2,
  "name": "Exemplo de uso do modelo da automacao",
  "description": "",
  "id": "exemplo-de-uso-do-modelo-da-automacao",
  "keyword": "Funcionalidade"
});
formatter.background({
  "line": 4,
  "name": "Eu como usuario dos fontes de automacao",
  "description": "        Necessito de um exemplo de uso dos fontes\r\n        Para que possa realizar a implementacao de testes automatizados no clente",
  "type": "background",
  "keyword": "Contexto"
});
formatter.scenario({
  "line": 9,
  "name": "Validar filtro de pesquisa de canais",
  "description": "",
  "id": "exemplo-de-uso-do-modelo-da-automacao;validar-filtro-de-pesquisa-de-canais",
  "type": "scenario",
  "keyword": "Cenario",
  "tags": [
    {
      "line": 8,
      "name": "@CTA0001"
    }
  ]
});
formatter.step({
  "line": 10,
  "name": "Que configuro o documento de evidencia da execucao",
  "rows": [
    {
      "cells": [
        "IdDemanda",
        "Sprint",
        "Sistema",
        "DescricaoCenario",
        "ResultadoEsperado"
      ],
      "line": 11
    },
    {
      "cells": [
        "CTA0001",
        "0",
        "Portal Sky",
        "Validação do filtro de pesqusa de canais",
        "Exibição do canal pesquisado na lista de canais"
      ],
      "line": 12
    }
  ],
  "keyword": "Dado "
});
formatter.step({
  "line": 13,
  "name": "Estou na tela home do portal Sky",
  "keyword": "Dado "
});
formatter.step({
  "line": 14,
  "name": "Acesso a area de Programacao",
  "keyword": "Quando "
});
formatter.step({
  "line": 15,
  "name": "Acesso a Lista de Canais",
  "keyword": "E "
});
formatter.step({
  "line": 16,
  "name": "Aplico filtro na pesquisa de canais",
  "keyword": "E "
});
formatter.step({
  "line": 17,
  "name": "Verifico se o canal pesquisado e exibido na lista",
  "keyword": "Entao "
});
formatter.match({
  "location": "ExemploSteps.Que_configuro_o_documento_de_evidencia_da_execucao(DataTable)"
});
formatter.result({
  "duration": 1046249600,
  "status": "passed"
});
formatter.match({
  "location": "ExemploSteps.Estou_na_tela_home_do_portal_Sky()"
});
formatter.result({
  "duration": 943984300,
  "status": "passed"
});
formatter.match({
  "location": "ExemploSteps.Acesso_a_area_de_Programacao()"
});
formatter.result({
  "duration": 3534860100,
  "status": "passed"
});
formatter.match({
  "location": "ExemploSteps.Acesso_a_Lista_de_Canais()"
});
formatter.result({
  "duration": 6570663000,
  "status": "passed"
});
formatter.match({
  "location": "ExemploSteps.Aplico_filtro_na_pesquisa_de_canais()"
});
formatter.result({
  "duration": 1448152500,
  "status": "passed"
});
formatter.match({
  "location": "ExemploSteps.Verifico_se_o_canal_pesquisado_e_exibido_na_lista()"
});
formatter.result({
  "duration": 3420941600,
  "status": "passed"
});
});
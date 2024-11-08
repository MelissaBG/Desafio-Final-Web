@SmokeTest
Feature: Abrir o Site DemoBlaze

  Scenario: Abrir a página inicial
    Given que eu abra a página inicial do DemoBlaze
    Then a página inicial deve ser carregada corretamente
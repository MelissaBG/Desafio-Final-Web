@SmokeTest
Feature: Automação de compra no site DemoBlaze

  Scenario: Realizar uma compra de monitor no DemoBlaze
    Given que estou na página inicial do DemoBlaze
    When acesso a categoria de monitores
    And seleciono o primeiro monitor
    And adiciono o monitor ao carrinho
    And vou para o carrinho
    And finalizo a compra preenchendo os campos obrigatórios
    Then a compra deve ser finalizada com sucesso

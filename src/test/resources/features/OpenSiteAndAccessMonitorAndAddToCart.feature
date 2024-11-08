@SmokeTest
Feature: Adicionar Monitor ao Carrinho no DemoBlaze

  Scenario: O usuário adiciona um monitor ao carrinho
    Given que eu abra a página inicial do DemoBlaze
    When eu navego até a categoria "Monitor"
    And eu adiciono o monitor ao carrinho
    Then o monitor deve estar no carrinho

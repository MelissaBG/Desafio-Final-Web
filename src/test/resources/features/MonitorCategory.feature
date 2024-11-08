Feature: Abrir o Site DemoBlaze e Acessar a Categoria Monitor

  Scenario: Usuário abre a página inicial e navega para a categoria Monitor
    Given que eu abra a página inicial do DemoBlaze
    When eu navego para a categoria "Monitor"
    Then eu devo ver a lista de Monitores
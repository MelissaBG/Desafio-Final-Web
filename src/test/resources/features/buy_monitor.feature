Feature: Compra de Monitor

  Scenario: Usuário compra um monitor com sucesso
    Given que o usuário acessa o site DemoBlaze
    When o usuário navega até a categoria de Monitores
    And o usuário adiciona um monitor ao carrinho
    And o usuário vai para o carrinho
    And o usuário finaliza a compra com informações válidas
    Then a compra deve ser concluída com sucesso
    And uma mensagem de confirmação deve ser exibida


  Scenario: Usuário tenta comprar um monitor sem preencher todos os campos obrigatórios
    Given que o usuário acessa o site DemoBlaze
    When o usuário navega até a categoria de Monitores
    And o usuário adiciona um monitor ao carrinho
    And o usuário vai para o carrinho
    And o usuário tenta finalizar a compra sem preencher todos os campos
    Then uma mensagem de erro deve ser exibida
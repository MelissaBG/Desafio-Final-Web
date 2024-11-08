# Automação de Testes - DemoBlaze

## Visão Geral
Esta automação foi criada para validar o fluxo de compra de um monitor no site **DemoBlaze**, que inclui as seguintes etapas:
- Abertura da página inicial
- Acesso à categoria de Monitores
- Adição de um monitor ao carrinho
- Acesso ao carrinho
- Finalização da compra
- Validações em cada etapa do processo

A automação utiliza o **Selenium WebDriver** com o **JUnit 5** para garantir que o processo de navegação e compras ocorra como esperado. As validações são realizadas após cada etapa para garantir que a aplicação se comporte corretamente.

## Estrutura do Projeto

A estrutura do projeto está organizada da seguinte forma:

src
└── main
    └── java
    └── utilities
        └── WebDriverManager.java
└── websetup
└── BaseTest.java
└── test
└── java
└── steps
└── OpenSiteSteps.java
└── AccessMonitorCategorySteps.java
└── AddMonitorToCartSteps.java
└── GoToCartSteps.java
└── FinalizePurchaseSteps.java
└── PurchaseValidationsSteps.java
└── runners
└── TestRunner.java
└── resources
└── features
└── OpenSiteAndAccessMonitorAndFinalizePurchase.feature



## Descrição das Classes e Funções

### WebDriverManager.java
Responsável pela configuração e gerenciamento da instância do **WebDriver** (ChromeDriver), garantindo que o navegador seja iniciado e fechado corretamente.

#### Métodos principais:
- `getDriver()`: Inicia o WebDriver, se ainda não estiver iniciado.
- `closeDriver()`: Fecha o WebDriver, liberando os recursos do navegador.

### BaseTest.java
Classe base que contém os métodos de configuração e encerramento de cada teste. Essa classe é herdada pelas demais classes de teste.

#### Métodos principais:
- `setUp()`: Configura o ambiente e inicializa o WebDriver.
- `tearDown()`: Fecha o navegador após a execução do teste.

### Classes de Etapas (`steps`)
Cada classe de **etapas** contém as ações realizadas durante a automação.

- **OpenSiteSteps.java**: Realiza a abertura do site DemoBlaze.
- **AccessMonitorCategorySteps.java**: Acessa a categoria de Monitores.
- **AddMonitorToCartSteps.java**: Adiciona um monitor ao carrinho.
- **GoToCartSteps.java**: Acessa o carrinho de compras.
- **FinalizePurchaseSteps.java**: Finaliza a compra, preenchendo todos os campos de checkout e realizando a compra.
- **PurchaseValidationsSteps.java**: Contém as validações para garantir que o processo de automação ocorreu corretamente.

### TestRunner.java
Classe responsável por orquestrar a execução de todas as etapas da automação.

### OpenSiteAndAccessMonitorAndFinalizePurchase.feature
Arquivo de **Gherkin** que descreve o comportamento esperado da aplicação, detalhando o fluxo de navegação no site, como a adição de um monitor ao carrinho e finalização da compra.

## Fluxo de Execução

1. **Abrir o Site**: A automação começa com a abertura da página inicial do **DemoBlaze**.
2. **Acessar a Categoria de Monitores**: O script clica na categoria **Monitors** no menu de navegação.
3. **Adicionar um Monitor ao Carrinho**: Um monitor específico é selecionado e adicionado ao carrinho.
4. **Ir para o Carrinho**: O script clica no ícone do carrinho e acessa a página de checkout.
5. **Finalizar a Compra**: O script preenche os campos necessários para finalizar a compra.
6. **Validações**: Durante todo o fluxo, são realizadas validações para garantir o correto funcionamento da aplicação.

## Dependências e Requisitos

- **Selenium WebDriver**: Para interação com o navegador.
- **JUnit 5**: Framework de testes utilizado.
- **ChromeDriver**: Driver utilizado para o navegador Google Chrome.
- **WebDriverManager**: Gerencia a versão do ChromeDriver automaticamente.

## Execução do Teste

Para executar os testes, basta rodar a classe `TestRunner.java`, que executará todo o fluxo de navegação e validações.

## Conclusão

Esta automação de testes cobre o processo completo de navegação e compra no site **DemoBlaze**, garantindo que a aplicação esteja funcionando corretamente durante o fluxo de compras. Com o uso do **Selenium WebDriver**, **JUnit** e **WebDriverManager**, a automação é robusta e independente de ambiente.

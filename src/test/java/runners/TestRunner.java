package runners;

import stepsDefinitions.*;

public class TestRunner {
    public static void main(String[] args) {
        OpenSiteSteps openSiteSteps = new OpenSiteSteps();
        AccessMonitorCategorySteps accessMonitorCategorySteps = new AccessMonitorCategorySteps();
        AddMonitorToCartSteps addMonitorToCartSteps = new AddMonitorToCartSteps();
        GoToCartSteps goToCartSteps = new GoToCartSteps();
        FinalizePurchaseSteps finalizePurchaseSteps = new FinalizePurchaseSteps();
        PurchaseValidationsSteps purchaseValidationsSteps = new PurchaseValidationsSteps();
        try {
            openSiteSteps.setUp();
            purchaseValidationsSteps.validateHomePage();
            accessMonitorCategorySteps.accessMonitorCategory();
            purchaseValidationsSteps.validateMonitorCategory();
            addMonitorToCartSteps.addMonitorToCart();
            purchaseValidationsSteps.validateMonitorAddedToCart();
            goToCartSteps.goToCart();
            purchaseValidationsSteps.validateCheckoutPage();
            finalizePurchaseSteps.finalizePurchase();  // Finaliza a compra
            purchaseValidationsSteps.validateOrderCompletion();
        } finally {
            openSiteSteps.tearDown();
        }
    }
}
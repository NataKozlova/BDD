package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class BalancePage {

    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement fromField = $("[data-test-id=from] input");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");

    private int availableAmount;

    public BalancePage(int availableAmount) {
        this.availableAmount = availableAmount;
    }

    //amount
    public DashboardPage increaseBalance(DataHelper.TransferMoney transferMoney) {
        amountField.setValue(String.valueOf(transferMoney.getBalance()));
        fromField.setValue(transferMoney.getCard());
        transferButton.click();
        return new DashboardPage();
    }

}

package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private ElementsCollection cards = $$(".list__item div");

    private SelenideElement heading = $("[data-test-id=dashboard]");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public BalancePage increaseFirstCard(DataHelper.TransferMoney transferMoney) {
        cards.first().$("[data-test-id='action-deposit']").click();
        return new BalancePage(transferMoney.getBalance());
    }

    public void validateFirstBalance(int shouldBeBalance) {
        Assertions.assertEquals(shouldBeBalance, getFirstCardBalance());
    }

    public int getFirstCardBalance() {
        var text = cards.first().text();
        return extractBalance(text);
    }

    public int getSecondCardBalance() {
        var text = cards.get(1).text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}

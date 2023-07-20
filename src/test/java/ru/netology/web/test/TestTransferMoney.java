
package ru.netology.web.test;

import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

class MoneyTransferTest {
    @Test
    void shouldTransferMoneyBetweenOwnCardsV1() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var defaultBalance = 10000;
        var transferBalance = 5000;
        var trasferMoney = DataHelper.getTransferMoneyFromSecondCard(transferBalance);
        var balancePage = dashboardPage.increaseFirstCard(trasferMoney);
        var dashboardPageAfterIncrease = balancePage.increaseBalance(trasferMoney);
        dashboardPageAfterIncrease.validateFirstBalance(defaultBalance + transferBalance);
    }
}

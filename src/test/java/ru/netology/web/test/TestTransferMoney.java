
package ru.netology.web.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

class TestTransferMoney {

    @Test
    void shouldCorrectTransferMoneyToFirstCard() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);

        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstBalanceBeforeTransfer = dashboardPage.getFirstCardBalance();
        var secondBalanceBeforeTransfer = dashboardPage.getSecondCardBalance();
        var transferMoneyToFirstCard = 5000;
        var balancePage = dashboardPage.fillFirstCard();
        var trasferMoney = DataHelper.getTransferMoneyFromSecondCard(transferMoneyToFirstCard);

        var dashboardPageAfterTransfer = balancePage.increaseBalance(trasferMoney);
        var firstBalanceAfterTransfer = dashboardPageAfterTransfer.getFirstCardBalance();
        Assertions.assertEquals(firstBalanceBeforeTransfer + transferMoneyToFirstCard, firstBalanceAfterTransfer);
        var secondBalanceAfterTransfer = dashboardPageAfterTransfer.getSecondCardBalance();
        Assertions.assertEquals(secondBalanceBeforeTransfer - transferMoneyToFirstCard, secondBalanceAfterTransfer);
    }

    @Test
    void shouldCorrectTransferMoneyToSecondCard() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);

        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstBalanceBeforeTransfer = dashboardPage.getFirstCardBalance();
        var secondBalanceBeforeTransfer = dashboardPage.getSecondCardBalance();
        var transferMoneyToSecondCard = 3500;
        var balancePage = dashboardPage.fillSecondCard();
        var trasferMoney = DataHelper.getTransferMoneyFromFirstCard(transferMoneyToSecondCard);

        var dashboardPageAfterTransfer = balancePage.increaseBalance(trasferMoney);
        var firstBalanceAfterTransfer = dashboardPageAfterTransfer.getFirstCardBalance();
        Assertions.assertEquals(firstBalanceBeforeTransfer - transferMoneyToSecondCard, firstBalanceAfterTransfer);
        var secondBalanceAfterTransfer = dashboardPageAfterTransfer.getSecondCardBalance();
        Assertions.assertEquals(secondBalanceBeforeTransfer + transferMoneyToSecondCard, secondBalanceAfterTransfer);
    }
}

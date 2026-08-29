package ru.netology.web.test;

import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @Test
    void shouldTransferMoneyBetweenOwnCards() {

        open("http://localhost:9999");

        var loginPage = new LoginPage();

        var authInfo = DataHelper.getAuthInfo();

        var verificationPage =
                loginPage.validLogin(authInfo);

        var dashboardPage =
                verificationPage.validVerify(
                        DataHelper.getVerificationCode()
                );

        var firstCard = DataHelper.getFirstCard();
        var secondCard = DataHelper.getSecondCard();

        var firstBalanceBefore =
                dashboardPage.getCardBalance(firstCard);

        var secondBalanceBefore =
                dashboardPage.getCardBalance(secondCard);

        // Переводим половину текущего баланса первой карты
        var amount = firstBalanceBefore / 2;

        var transferPage =
                dashboardPage.selectCard(secondCard);

        dashboardPage =
                transferPage.transfer(firstCard, amount);

        var firstBalanceAfter =
                dashboardPage.getCardBalance(firstCard);

        var secondBalanceAfter =
                dashboardPage.getCardBalance(secondCard);

        assertEquals(
                firstBalanceBefore - amount,
                firstBalanceAfter
        );

        assertEquals(
                secondBalanceBefore + amount,
                secondBalanceAfter
        );
    }

    @Test
    void shouldTransferMoneyFromSecondCardToFirst() {

        open("http://localhost:9999");

        var loginPage = new LoginPage();

        var authInfo = DataHelper.getAuthInfo();

        var verificationPage =
                loginPage.validLogin(authInfo);

        var dashboardPage =
                verificationPage.validVerify(
                        DataHelper.getVerificationCode()
                );

        var firstCard = DataHelper.getFirstCard();
        var secondCard = DataHelper.getSecondCard();

        var firstBalanceBefore =
                dashboardPage.getCardBalance(firstCard);

        var secondBalanceBefore =
                dashboardPage.getCardBalance(secondCard);

        // Переводим половину текущего баланса второй карты
        var amount = secondBalanceBefore / 2;

        var transferPage =
                dashboardPage.selectCard(firstCard);

        dashboardPage =
                transferPage.transfer(secondCard, amount);

        var firstBalanceAfter =
                dashboardPage.getCardBalance(firstCard);

        var secondBalanceAfter =
                dashboardPage.getCardBalance(secondCard);

        assertEquals(
                firstBalanceBefore + amount,
                firstBalanceAfter
        );

        assertEquals(
                secondBalanceBefore - amount,
                secondBalanceAfter
        );
    }

    @Test
    void shouldNotTransferMoneyMoreThanBalance() {

        open("http://localhost:9999");

        var loginPage = new LoginPage();

        var authInfo = DataHelper.getAuthInfo();

        var verificationPage =
                loginPage.validLogin(authInfo);

        var dashboardPage =
                verificationPage.validVerify(
                        DataHelper.getVerificationCode()
                );

        var firstCard = DataHelper.getFirstCard();
        var secondCard = DataHelper.getSecondCard();

        var firstBalanceBefore =
                dashboardPage.getCardBalance(firstCard);

        var secondBalanceBefore =
                dashboardPage.getCardBalance(secondCard);

        // Пытаемся перевести сумму больше текущего баланса
        var amount = firstBalanceBefore + 1;

        var transferPage =
                dashboardPage.selectCard(secondCard);

        dashboardPage =
                transferPage.transfer(firstCard, amount);

        var firstBalanceAfter =
                dashboardPage.getCardBalance(firstCard);

        var secondBalanceAfter =
                dashboardPage.getCardBalance(secondCard);

        assertEquals(
                firstBalanceBefore,
                firstBalanceAfter
        );

        assertEquals(
                secondBalanceBefore,
                secondBalanceAfter
        );
    }


}

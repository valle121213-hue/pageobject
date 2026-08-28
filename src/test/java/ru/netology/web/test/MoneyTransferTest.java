package ru.netology.web.test;

import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.TransferPage;

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
                verificationPage.validVerify("12345");

        var firstCard = DataHelper.getFirstCard();
        var secondCard = DataHelper.getSecondCard();

        // Запоминаем баланс карт ДО перевода
        var firstBalanceBefore =
                dashboardPage.getCardBalance(firstCard);

        var secondBalanceBefore =
                dashboardPage.getCardBalance(secondCard);

        // Переводим 1000 рублей с первой карты на вторую
        var transferPage =
                dashboardPage.selectCard(secondCard);

        transferPage.transfer(firstCard, 1000);

        // Проверяем баланс карт ПОСЛЕ перевода
        var firstBalanceAfter =
                dashboardPage.getCardBalance(firstCard);

        var secondBalanceAfter =
                dashboardPage.getCardBalance(secondCard);

        assertEquals(
                firstBalanceBefore - 1000,
                firstBalanceAfter
        );

        assertEquals(
                secondBalanceBefore + 1000,
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
                verificationPage.validVerify("12345");

        var firstCard = DataHelper.getFirstCard();
        var secondCard = DataHelper.getSecondCard();

        var firstBalanceBefore =
                dashboardPage.getCardBalance(firstCard);

        var secondBalanceBefore =
                dashboardPage.getCardBalance(secondCard);

        var transferPage =
                dashboardPage.selectCard(firstCard);

        transferPage.transfer(secondCard, 1000);

        var firstBalanceAfter =
                dashboardPage.getCardBalance(firstCard);

        var secondBalanceAfter =
                dashboardPage.getCardBalance(secondCard);

        assertEquals(
                firstBalanceBefore + 1000,
                firstBalanceAfter
        );

        assertEquals(
                secondBalanceBefore - 1000,
                secondBalanceAfter
        );
    }

    //Перевод суммы больше баланса
    @Test
    void shouldNotTransferMoneyMoreThanBalance() {

        open("http://localhost:9999");

        var loginPage = new LoginPage();

        var authInfo = DataHelper.getAuthInfo();

        var verificationPage =
                loginPage.validLogin(authInfo);

        var dashboardPage =
                verificationPage.validVerify("12345");

        var firstCard = DataHelper.getFirstCard();
        var secondCard = DataHelper.getSecondCard();

        var firstBalanceBefore =
                dashboardPage.getCardBalance(firstCard);

        var secondBalanceBefore =
                dashboardPage.getCardBalance(secondCard);

        var transferPage =
                dashboardPage.selectCard(secondCard);

        transferPage.transfer(firstCard, 11000);

        var firstBalanceAfter =
                dashboardPage.getCardBalance(firstCard);

        var secondBalanceAfter =
                dashboardPage.getCardBalance(secondCard);

        assertEquals(firstBalanceBefore, firstBalanceAfter);
        assertEquals(secondBalanceBefore, secondBalanceAfter);
    }
}

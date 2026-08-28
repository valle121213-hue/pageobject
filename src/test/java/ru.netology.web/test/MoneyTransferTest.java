package ru.netology.web.test;

import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @Test
    void shouldLoginAndGetCardBalance() {

        open("http://localhost:9999");

        var loginPage = new LoginPage();

        var authInfo = DataHelper.getAuthInfo();

        var verificationPage =
                loginPage.validLogin(authInfo);

        var dashboardPage =
                verificationPage.validVerify("12345");

        var firstCard =
                DataHelper.getFirstCard();

        var balance =
                dashboardPage.getCardBalance(firstCard);

        assertEquals(10000, balance);
    }
}

package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private final SelenideElement amountField =
            $("[data-test-id=amount] input");

    private final SelenideElement fromField =
            $("[data-test-id=from] input");

    private final SelenideElement toField =
            $("[data-test-id=to] input");

    private final SelenideElement transferButton =
            $("[data-test-id=action-transfer]");

    private final SelenideElement cancelButton =
            $("[data-test-id=action-cancel]");

    public TransferPage() {
        amountField.shouldBe(Condition.visible);
        fromField.shouldBe(Condition.visible);
        toField.shouldBe(Condition.visible);
        transferButton.shouldBe(Condition.visible);
    }

    public DashboardPage transfer(DataHelper.CardInfo fromCard, int amount) {
        amountField.setValue(String.valueOf(amount));
        fromField.setValue(fromCard.getNumber());
        transferButton.click();

        return new DashboardPage();
    }

    public String getToCard() {
        return toField.getValue();
    }

    public void cancel() {
        cancelButton.click();
    }
}
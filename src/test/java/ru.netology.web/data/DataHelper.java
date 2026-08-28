package ru.netology.web.data;

public class DataHelper {

    public static class AuthInfo {
        private final String login;
        private final String password;

        public AuthInfo(String login, String password) {
            this.login = login;
            this.password = password;
        }

        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo(
                "vasya",
                "qwerty123"
        );
    }


    public static class CardInfo {
        private final String number;
        private final String testId;

        public CardInfo(String number, String testId) {
            this.number = number;
            this.testId = testId;
        }

        public String getNumber() {
            return number;
        }

        public String getTestId() {
            return testId;
        }
    }

    public static CardInfo getFirstCard() {
        return new CardInfo(
                "5559 0000 0000 0001",
                "92df3f1c-a033-48e6-8390-206f6b1f56c0"
        );
    }

    public static CardInfo getSecondCard() {
        return new CardInfo(
                "5559 0000 0000 0002",
                "0f3f5c2a-249e-4c3d-8287-09f7a039391d"
        );
    }
}
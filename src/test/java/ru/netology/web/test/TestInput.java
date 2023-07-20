package ru.netology.web.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class TestInput {
    @Test
    void shouldCheckInput() {
        open("http://localhost:9999");
        $("[data-test-id=login] input").setValue("vasya");
        $("[data-test-id=password] input").setValue("qwerty123");
        $("[data-test-id=action-login]").click();
        $("[data-test-id=code] input").setValue("12345");
        $("[data-test-id=action-verify]").click();
        $("[data-test-id=dashboard]").shouldBe(Condition.visible);
        var listItems = $$(".list__item div");
        var firstCard = listItems.first();
        var secondCard = listItems.get(1);
        System.out.println(firstCard);
        System.out.println(secondCard);

        //listItems.first().$("[data-test-id='action-deposit']").click();
        //System.out.println(listItems.first().text());
    }
}

package com.example.pi_tests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PiTestsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PiTestsApplication.class, args);
        AddToShoppingListTest.sendShoppingQueries();
        AddTasksToListTest.sendTasksQueries();
        ShowTasksListTest.sendShowTasksQuery();
        ShowShoppingListTest.sendShowShoppingListQuery();
        CompleteShoppingListTest.completeShoppingQueries();
        CompleteTaskListTest.completeTaskQueries();
        MailGeneratorTest.mailGeneratorQueries();
        RagQuestionPositive.ragPositiveQueries();
        RagQuestionNegative.ragNegativeQueries();
    }

}

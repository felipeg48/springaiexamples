package com.kousenit.springaiexamples.functions;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ExchangeRateFunctionTest {

    @Autowired @Qualifier("openAiChatModel")
    private ChatModel chatModel;

    @Test
    public void bestDealMacbookAir() {
        var chatClient = ChatClient.create(chatModel);

        String question = """
                At the Amazon.com website in various countries, a Macbook Air costs
                718.25 GPB, 83,900 INR, 749.99 USD, and 177,980 JPY.
                Which is the best deal?
                """;
        String answer = chatClient.prompt()
                .functions("exchangeRateFunction")
                .user(question)
                .call()
                .content();
        System.out.println(answer);
        assertThat(answer).contains("USD");
    }

}
package com.crud.tasks.trello;

import com.crud.tasks.trello.config.TrelloConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TrelloConfigTestSuite {

    @Autowired
    TrelloConfig trelloConfig;

    @Test
    void getterTest() {
        //Given

        //When
        String endpoint = trelloConfig.getTrelloApiEndpoint();
        String token = trelloConfig.getTrelloToken();
        String key = trelloConfig.getTrelloAppKey();
        String userName = trelloConfig.getTrelloUsername();

        //Then
        assertEquals("https://api.trello.com/1", endpoint);
        assertEquals("88573f542789e786895f4850addfa32a", key);
        assertEquals("285b49a033c2e48714c58ce86f1225cfcc745451314eccf9acb92894f8e2b6a5", token);
        assertEquals("jakubciosek", userName);
    }
}

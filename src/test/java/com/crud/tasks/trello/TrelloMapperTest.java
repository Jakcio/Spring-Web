package com.crud.tasks.trello;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.apache.catalina.LifecycleState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TrelloMapperTest {

    @Autowired
    TrelloMapper trelloMapper;


    @Test
    void shouldMapToListDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("1", "Name1", true);
        TrelloList trelloList2 = new TrelloList("2", "Name2", false);

        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList1);
        trelloLists.add(trelloList2);

        //When
        List<TrelloListDto> trelloListsDto = trelloMapper.mapToListDto(trelloLists);

        //Then
        assertEquals(2, trelloListsDto.size());
        assertEquals("1", trelloListsDto.get(0).getId());
        assertEquals("Name2", trelloListsDto.get(1).getName());
        assertFalse(trelloListsDto.get(1).isClosed());
    }

    @Test
    void shouldMapToList() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "Name1", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "Name2", false);

        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(trelloListDto1);
        trelloListsDto.add(trelloListDto2);

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListsDto);

        //Then
        assertEquals(2, trelloLists.size());
        assertEquals("1", trelloLists.get(0).getId());
        assertEquals("Name2", trelloLists.get(1).getName());
        assertFalse(trelloLists.get(1).isClosed());
    }

    @Test
    void shouldMapToBoards() {
        //Given
        TrelloBoardsDto trelloBoardDto1 = new TrelloBoardsDto("1", "Name1", new ArrayList<>());
        TrelloBoardsDto trelloBoardDto2 = new TrelloBoardsDto("2", "Name2", new ArrayList<>());
        List<TrelloBoardsDto> trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(trelloBoardDto1);
        trelloBoardsDto.add(trelloBoardDto2);
        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardsDto);
        //Then
        assertEquals(2, trelloBoards.size());
        assertEquals("1", trelloBoards.get(0).getId());
        assertEquals("Name1", trelloBoards.get(0).getName());
    }

    @Test
    void shouldMapToBoardsDto() {
        //Given
        TrelloBoard trelloBoard1 = new TrelloBoard("1", "Name1", new ArrayList<>());
        TrelloBoard trelloBoard2 = new TrelloBoard("2", "Name2", new ArrayList<>());
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard1);
        trelloBoards.add(trelloBoard2);
        //When
        List<TrelloBoardsDto> trelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        assertEquals(2, trelloBoardsDto.size());
        assertEquals("1", trelloBoardsDto.get(0).getId());
        assertEquals("Name1", trelloBoardsDto.get(0).getName());
    }

    @Test
    void shouldMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Name", "Description", "Pos", "ListId");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals(trelloCardDto.getName(), trelloCard.getName());
        assertEquals(trelloCardDto.getListId(), trelloCard.getListId());
        assertEquals(trelloCardDto.getDescription(), trelloCard.getDescription());
        assertEquals(trelloCardDto.getPos(), trelloCard.getPos());
    }

    @Test
    void shouldMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Name", "Description", "Pos", "ListId");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals(trelloCardDto.getName(), trelloCard.getName());
        assertEquals(trelloCardDto.getListId(), trelloCard.getListId());
        assertEquals(trelloCardDto.getDescription(), trelloCard.getDescription());
        assertEquals(trelloCardDto.getPos(), trelloCard.getPos());
    }


}
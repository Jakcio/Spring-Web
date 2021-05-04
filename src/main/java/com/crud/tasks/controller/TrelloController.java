package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/trello")
@RequiredArgsConstructor
public class TrelloController {

    private final TrelloClient trelloClient;

    @GetMapping("getTrelloBoards")
    public void getTrelloBoards() {

        Optional<List<TrelloBoardDto>> trelloBoards = trelloClient.getTrelloBoards();

        trelloBoards.ifPresent(l -> l.stream().filter(t -> t.getId() != null && t.getName() != null).
                filter(t -> t.getName().contains("Kodilla")).forEach(t ->
                System.out.println(t.getId() + " " + t.getName())
        ));
    }

        @GetMapping("getTrelloBoards2")
        public void getTrelloBoards2() {
        Optional<List<TrelloBoardDto>> trelloBoards = trelloClient.getTrelloBoards();
        trelloBoards.ifPresent(l -> l.forEach(trelloBoardDto ->
            System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName())
        ));
    }


}

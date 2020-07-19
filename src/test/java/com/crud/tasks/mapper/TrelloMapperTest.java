package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTest{

    @Autowired
    TrelloMapper trelloMapper;

    @Test
    public void mapToLists(){
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("1", "List1_Name", false);
        List<TrelloListDto> listDto = new ArrayList<>();
        listDto.add(trelloListDto);
        //When
        List<TrelloList> mappingResult= trelloMapper.mapToList(listDto);
        //Then
        Assert.assertEquals("1", mappingResult.get(0).getId());
        Assert.assertEquals("List1_Name", mappingResult.get(0).getName());
        Assert.assertFalse(mappingResult.get(0).isClosed());
    }

    @Test
    public void mapToListsDto(){
        //Given
        TrelloList trelloList = new TrelloList("2", "List2_Name", true);
        List<TrelloList> list = new ArrayList<>();
        list.add(trelloList);
        //When
        List<TrelloListDto> mappingResult= trelloMapper.mapToListDto(list);
        //Then
        Assert.assertEquals("2", mappingResult.get(0).getId());
        Assert.assertEquals("List2_Name", mappingResult.get(0).getName());
        Assert.assertTrue(mappingResult.get(0).isClosed());
    }

    @Test
    public void mapToBoards(){
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("3", "List3_Name", true);
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        trelloListDtoList.add(trelloListDto);

        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("01", "Board01_Name", trelloListDtoList);
        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(trelloBoardDto);
        //When
        List<TrelloBoard> mappingResult = trelloMapper.mapToBoards(trelloBoardsDto);
        //Then
        Assert.assertEquals("01", mappingResult.get(0).getId());
        Assert.assertEquals("Board01_Name", mappingResult.get(0).getName());
        Assert.assertEquals("3", mappingResult.get(0).getLists().get(0).getId());
        Assert.assertEquals("List3_Name", mappingResult.get(0).getLists().get(0).getName());
        Assert.assertTrue(mappingResult.get(0).getLists().get(0).isClosed());
    }

    @Test
    public void mapToBoardsDto(){
        //Given
        TrelloList trelloList = new TrelloList("4", "List4_Name", false);
        List <TrelloList> lists = new ArrayList<>();
        lists.add(trelloList);

        TrelloBoard trelloBoard = new TrelloBoard("02", "Board02_Name",lists);
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard);
        //When
        List<TrelloBoardDto> mappingResult = trelloMapper.mapToBoardsDto(trelloBoards);

        //Then
        Assert.assertEquals("02", mappingResult.get(0).getId());
        Assert.assertEquals("Board02_Name", mappingResult.get(0).getName());
        Assert.assertEquals("4", mappingResult.get(0).getLists().get(0).getId());
        Assert.assertEquals("List4_Name", mappingResult.get(0).getLists().get(0).getName());
        Assert.assertFalse(mappingResult.get(0).getLists().get(0).isClosed());
    }

    @Test
    public void mapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Card", "Description", "Pos", "Id");
        //When
        TrelloCard mappingResult = trelloMapper.mapToCard(trelloCardDto);
        //Then
        Assert.assertEquals("Card", mappingResult.getName());
        Assert.assertEquals("Description", mappingResult.getDescription());
        Assert.assertEquals("Pos", mappingResult.getPos());
        Assert.assertEquals("Id", mappingResult.getListId());
    }

    @Test
    public void mapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Name", "Description", "Pos", "Id");
        //When
        TrelloCardDto mappingResult = trelloMapper.mapToCardDto(trelloCard);
        //Then
        Assert.assertEquals("Name", mappingResult.getName());
        Assert.assertEquals("Description", mappingResult.getDescription());
        Assert.assertEquals("Pos", mappingResult.getPos());
        Assert.assertEquals("Id", mappingResult.getListId());
    }
}

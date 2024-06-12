package com.example.springExample.services;

import com.example.springExample.dto.CommentDto;
import com.example.springExample.entity.AuthorTable;
import com.example.springExample.entity.CommentTable;
import com.example.springExample.repository.AuthorRepository;
import com.example.springExample.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Slf4j
@Service
public class CommentCRUDService implements CRUDService<CommentDto>{

private final CommentRepository commentRepository;
private final AuthorRepository authorRepository;

    @Override
    public CommentDto getById(Integer id) {
        log.info("Get by id: " + id);
        CommentTable commentTable = commentRepository.findById(id).orElseThrow();
        return mapToDto(commentTable);
    }

    @Override
    public Collection<CommentDto> getAll() {
        log.info("Get all");
        return commentRepository.findAll()
                .stream()
                .map(CommentCRUDService::mapToDto)
                .toList();
    }

    @Override
    public void create(CommentDto commentDto) {
        log.info("create");
        CommentTable commentTable = mapToEntity(commentDto);
        Integer authorId = commentDto.getAuthorId();
        AuthorTable authorTable = authorRepository.findById(authorId).orElseThrow();
        commentTable.setAuthor(authorTable);
        commentRepository.save(commentTable);
    }

    @Override
    public void update(CommentDto commentDto) {
        log.info("update ");
        CommentTable commentTable = mapToEntity(commentDto);
        Integer authorId = commentDto.getAuthorId();
        AuthorTable authorTable = authorRepository.findById(authorId).orElseThrow();
        commentTable.setAuthor(authorTable);
        commentRepository.save(commentTable);
    }

    @Override
    public void delete(Integer id) {
        log.info("Delete " + id);
        commentRepository.deleteById(id);
    }

    public static CommentDto mapToDto(CommentTable commentTable) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(commentTable.getId());
        commentDto.setText(commentTable.getText());
        commentDto.setAuthorId(commentTable.getAuthor().getId());
        return commentDto;
    }


    public static CommentTable mapToEntity (CommentDto commentDto) {
        CommentTable commentTable = new CommentTable();
        commentTable.setId(commentDto.getId());
        commentTable.setText(commentDto.getText());
        return commentTable;
    }

}

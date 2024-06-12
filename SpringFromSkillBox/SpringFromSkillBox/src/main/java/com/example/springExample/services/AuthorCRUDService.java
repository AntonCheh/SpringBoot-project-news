package com.example.springExample.services;

import com.example.springExample.dto.AuthorDto;
import com.example.springExample.entity.AuthorTable;
import com.example.springExample.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthorCRUDService implements CRUDService<AuthorDto> {

    private final AuthorRepository repository;

    @Override
    public AuthorDto getById(Integer id) {
       log.info("Get by id " + id);
       return mapToDto(repository.findById(id).orElseThrow());
    }

    @Override
    public Collection getAll() {
        return repository.findAll().stream()
                .map(AuthorCRUDService::mapToDto).toList();
    }

    @Override
    public void create(AuthorDto authorDto) {
        repository.save(mapToEntity(authorDto));
    }

    @Override
    public void update(AuthorDto authorDto) {
        repository.save(mapToEntity(authorDto));

    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);

    }

    public static AuthorTable mapToEntity (AuthorDto authorDto) {
        AuthorTable authorTable = new AuthorTable();
        authorTable.setId(authorDto.getId());
        authorTable.setFirstName(authorDto.getFirstName());
        authorTable.setLastName(authorDto.getLastName());
        authorTable.setRating(authorDto.getRating());
        authorTable.setComments(authorDto.getComments()
                .stream()
                .map(CommentCRUDService::mapToEntity).toList());
        return authorTable;
    }

    public static AuthorDto mapToDto(AuthorTable authorTable) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(authorTable.getId());
        authorDto.setFirstName(authorTable.getFirstName());
        authorDto.setLastName(authorTable.getLastName());
        authorDto.setRating(authorTable.getRating());
        authorDto.setComments(authorTable.getComments()
                .stream()
                .map(CommentCRUDService::mapToDto)
                .toList());
        return authorDto;
    }

}

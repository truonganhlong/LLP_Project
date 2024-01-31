package com.llp.courseservice.services.impl;

import com.llp.courseservice.dtos.Language.LanguageCreateRequest;
import com.llp.courseservice.dtos.Language.LanguageResponse;
import com.llp.courseservice.dtos.Language.LanguageUpdateRequest;
import com.llp.courseservice.entities.Language;
import com.llp.courseservice.mappers.LanguageMapper;
import com.llp.courseservice.repositories.LanguageRepository;
import com.llp.courseservice.services.LanguageService;
import com.llp.sharedproject.exceptions.BadRequestException;
import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.sharedproject.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository languageRepository;

    @Override
    public List<LanguageResponse> getAll() {
        try {
            List<Language> languages = languageRepository.findAll();
            return languages.stream().map(LanguageMapper::convertToResponse).collect(Collectors.toList());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public LanguageResponse getById(int id) {
        try {
            Language language = languageRepository.getById(id);
            if(Objects.isNull(language)){
                throw new NotFoundException("Not found in database");
            }
            return LanguageMapper.convertToResponse(language);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public void create(LanguageCreateRequest request) {
        try {
            Language language = Language.builder()
                    .name(request.getName())
                    .build();
            languageRepository.save(language);
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public void update(int id, LanguageUpdateRequest request) {
        try {
            if(Objects.isNull(request)){
                throw new BadRequestException("Input must has at least one value");
            }
            Language language = languageRepository.getById(id);
            if(Objects.isNull(language)){
                throw new NotFoundException("Not found in database");
            }
            language.setName(request.getName() != null ? request.getName() : language.getName());
            languageRepository.save(language);
        } catch (BadRequestException e){
            throw new BadRequestException(e.getMessage());
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public void delete(int id) {
        try {
            Language language = languageRepository.getById(id);
            if(Objects.isNull(language)){
                throw new NotFoundException("Not found in database");
            }
            languageRepository.delete(language);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }
}

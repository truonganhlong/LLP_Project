package com.llp.courseservice.services.impl;

import com.llp.courseservice.dtos.Level.LevelCreateRequest;
import com.llp.courseservice.dtos.Level.LevelResponse;
import com.llp.courseservice.dtos.Level.LevelUpdateRequest;
import com.llp.courseservice.entities.Level;
import com.llp.courseservice.mappers.LevelMapper;
import com.llp.courseservice.repositories.LevelRepository;
import com.llp.courseservice.services.LevelService;
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
public class LevelServiceImpl implements LevelService {
    private final LevelRepository levelRepository;

    @Override
    public List<LevelResponse> getAll() {
        try {
            List<Level> levels = levelRepository.findAll();
            return levels.stream().map(LevelMapper::convertToResponse).collect(Collectors.toList());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public LevelResponse getById(int id) {
        try {
            Level level = levelRepository.getById(id);
            if(Objects.isNull(level)){
                throw new NotFoundException("Not found in database");
            }
            return LevelMapper.convertToResponse(level);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public void create(LevelCreateRequest request) {
        try {
            Level level = Level.builder()
                    .name(request.getName())
                    .build();
            levelRepository.save(level);
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public void update(int id, LevelUpdateRequest request) {
        try {
            if(Objects.isNull(request)){
                throw new BadRequestException("Input must has at least one value");
            }
            Level level = levelRepository.getById(id);
            if(Objects.isNull(level)){
                throw new NotFoundException("Not found in database");
            }
            level.setName(request.getName() != null ? request.getName() : level.getName());
            levelRepository.save(level);
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
            Level level = levelRepository.getById(id);
            if(Objects.isNull(level)){
                throw new NotFoundException("Not found in database");
            }
            levelRepository.delete(level);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }
}

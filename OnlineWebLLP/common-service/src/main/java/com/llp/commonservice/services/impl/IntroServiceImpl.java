package com.llp.commonservice.services.impl;

import com.llp.commonservice.dtos.Intro.IntroCreateRequest;
import com.llp.commonservice.dtos.Intro.IntroResponse;
import com.llp.commonservice.dtos.Intro.IntroUpdateRequest;
import com.llp.commonservice.entities.Intro;
import com.llp.commonservice.mappers.IntroMapMapper;
import com.llp.commonservice.mappers.IntroMapper;
import com.llp.commonservice.repositories.IntroMapRepository;
import com.llp.commonservice.repositories.IntroRepository;
import com.llp.commonservice.services.IntroService;
import com.llp.sharedproject.exceptions.BadRequestException;
import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.sharedproject.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"intro"})
public class IntroServiceImpl implements IntroService {
    private static final String root = System.getProperty("user.dir") + "/shared-project/src/main/resources/static/";
    private static final String directory = "images/intro/";
    private final IntroRepository introRepository;
    private final IntroMapRepository introMapRepository;
    @Override
    @Cacheable(cacheNames = "intro/getAll")
    public List<IntroResponse> getAll() {
        try {
            List<Intro> intros = introRepository.findAll();
            List<IntroResponse> data = intros.stream().map(IntroMapper::convertToResponse).collect(Collectors.toList());
            for (var intro:data) {
                intro.setIntroMap(IntroMapMapper.convertToResponse(introMapRepository.getById(intro.getIntroMapId().intValue())));
            }
            return data;
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    @Cacheable(cacheNames = "intro/getAllByIntroMapId", key = "#introMapId")
    public List<IntroResponse> getAllByIntroMapId(int introMapId) {
        try {
            List<Intro> intros = introRepository.getAllByIntroMapId(introMapId);
            return getIntroResponses(introMapId, intros);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    @Cacheable(cacheNames = "intro/getAllByIntroMapIdByUser", key = "#introMapId")
    public List<IntroResponse> getAllByIntroMapIdByUser(int introMapId) {
        try {
            List<Intro> intros = introRepository.getAllByIntroMapIdByUser(introMapId);
            return getIntroResponses(introMapId, intros);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    @Cacheable(cacheNames = "intro/getById", key = "#id")
    public IntroResponse getById(int id) {
        try {
            Intro intro = introRepository.getById(id);
            if(Objects.isNull(intro)){
                throw new NotFoundException("Not found in database");
            }
            return IntroMapper.convertToResponse(intro);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "intro/getAll"),
                    @CacheEvict(cacheNames = "intro/getAllByIntroMapId", key = "#request.introMapId"),
                    @CacheEvict(cacheNames = "intro/getAllByIntroMapIdByUser", key ="#request.introMapId")
            }
    )
    public void create(IntroCreateRequest request) {
        try {
            Intro intro = Intro.builder()
                    .title(request.getTitle())
                    .content(request.getContent())
                    .status(true)
                    .introMap(introMapRepository.getById(request.getIntroMapId()))
                    .build();
            insertImage(intro, request.getImageLink());
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "intro/getAll"),
                    @CacheEvict(cacheNames = "intro/getAllByIntroMapId", allEntries = true),
                    @CacheEvict(cacheNames = "intro/getAllByIntroMapIdByUser", allEntries = true),
                    @CacheEvict(cacheNames = "intro/getById", key = "#id")
            }
    )
    public void update(int id, IntroUpdateRequest request) {
        try {
            if(Objects.isNull(request)){
                throw new BadRequestException("Input must has at least one value");
            }
            Intro intro = introRepository.getById(id);
            if(Objects.isNull(intro)){
                throw new NotFoundException("Not found in database");
            }
            intro.setTitle(request.getTitle() != null ? request.getTitle() : intro.getTitle());
            intro.setContent(request.getContent() != null ? request.getContent() : intro.getContent());
            intro.setIntroMap(introMapRepository.getById(request.getIntroMapId()) != null ? introMapRepository.getById(request.getIntroMapId()): intro.getIntroMap());
            if(!request.getImageLink().isEmpty()){
                String pastPath = intro.getImageLink();
                insertImage(intro, request.getImageLink());
                deleteImage(pastPath);
            }
        } catch (BadRequestException e){
            throw new BadRequestException(e.getMessage());
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "intro/getAll"),
                    @CacheEvict(cacheNames = "intro/getAllByIntroMapId", allEntries = true),
                    @CacheEvict(cacheNames = "intro/getAllByIntroMapIdByUser", allEntries = true),
                    @CacheEvict(cacheNames = "intro/getById", key = "#id")
            }
    )
    public void updateStatus(int id) {
        try {
            Intro intro = introRepository.getById(id);
            if(Objects.isNull(intro)){
                throw new NotFoundException("Not found in database");
            }
            intro.setStatus(!intro.isStatus());
            introRepository.save(intro);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "intro/getAll"),
                    @CacheEvict(cacheNames = "intro/getAllByIntroMapId", allEntries = true),
                    @CacheEvict(cacheNames = "intro/getAllByIntroMapIdByUser", allEntries = true),
                    @CacheEvict(cacheNames = "intro/getById", key = "#id")
            }
    )
    public void delete(int id) {
        try {
            Intro intro = introRepository.getById(id);
            if(Objects.isNull(intro)){
                throw new NotFoundException("Not found in database");
            }
            deleteImage(intro.getImageLink());
            introRepository.delete(intro);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    private List<IntroResponse> getIntroResponses(int introMapId, List<Intro> intros) {
        List<IntroResponse> data = intros.stream().map(IntroMapper::convertToResponse).collect(Collectors.toList());
        if(Objects.isNull(introMapRepository.getById(introMapId))){
            throw new NotFoundException("Not found in database");
        }
        for (var intro:data) {
            intro.setIntroMap(IntroMapMapper.convertToResponse(introMapRepository.getById(introMapId)));
        }
        return data;
    }
    private void insertImage(Intro intro, MultipartFile imageLink) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String formattedDateTime = now.format(formatter);
        String randomUUID = UUID.randomUUID().toString().replace("-", "");
        String originalFilename = imageLink.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFilename = formattedDateTime + randomUUID + fileExtension;
        Path filePath = Paths.get(root, directory, newFilename);
        try {
            imageLink.transferTo(new File(String.valueOf(filePath)));
            intro.setImageLink(newFilename);
            introRepository.save(intro);
        } catch (IOException e) {
            throw new NotFoundException("Not found file");
        }
    }

    private void deleteImage(String pastPath) {
        Path filePath = Paths.get(root, pastPath);
        File file = new File(String.valueOf(filePath));
        file.delete();
    }
}

package com.reliance.retail.nps.service.impl;

import com.reliance.retail.nps.domain.Link;
import com.reliance.retail.nps.repository.LinkRepository;
import com.reliance.retail.nps.service.LinkService;
import com.reliance.retail.nps.service.dto.LinkDTO;
import com.reliance.retail.nps.service.mapper.LinkMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Link}.
 */
@Service
@Transactional
public class LinkServiceImpl implements LinkService {

    private final Logger log = LoggerFactory.getLogger(LinkServiceImpl.class);

    private final LinkRepository linkRepository;

    private final LinkMapper linkMapper;

    public LinkServiceImpl(LinkRepository linkRepository, LinkMapper linkMapper) {
        this.linkRepository = linkRepository;
        this.linkMapper = linkMapper;
    }

    @Override
    public LinkDTO save(LinkDTO linkDTO) {
        log.debug("Request to save Link : {}", linkDTO);
        Link link = linkMapper.toEntity(linkDTO);
        link = linkRepository.save(link);
        return linkMapper.toDto(link);
    }

    @Override
    public LinkDTO update(LinkDTO linkDTO) {
        log.debug("Request to save Link : {}", linkDTO);
        Link link = linkMapper.toEntity(linkDTO);
        link = linkRepository.save(link);
        return linkMapper.toDto(link);
    }

    @Override
    public Optional<LinkDTO> partialUpdate(LinkDTO linkDTO) {
        log.debug("Request to partially update Link : {}", linkDTO);

        return linkRepository
            .findById(linkDTO.getId())
            .map(existingLink -> {
                linkMapper.partialUpdate(existingLink, linkDTO);

                return existingLink;
            })
            .map(linkRepository::save)
            .map(linkMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LinkDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Links");
        return linkRepository.findAll(pageable).map(linkMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LinkDTO> findOne(Long id) {
        log.debug("Request to get Link : {}", id);
        return linkRepository.findById(id).map(linkMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Link : {}", id);
        linkRepository.deleteById(id);
    }
}

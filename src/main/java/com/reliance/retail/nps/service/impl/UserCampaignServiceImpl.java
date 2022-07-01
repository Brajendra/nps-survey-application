package com.reliance.retail.nps.service.impl;

import com.reliance.retail.nps.domain.UserCampaign;
import com.reliance.retail.nps.repository.UserCampaignRepository;
import com.reliance.retail.nps.service.UserCampaignService;
import com.reliance.retail.nps.service.dto.UserCampaignDTO;
import com.reliance.retail.nps.service.mapper.UserCampaignMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link UserCampaign}.
 */
@Service
@Transactional
public class UserCampaignServiceImpl implements UserCampaignService {

    private final Logger log = LoggerFactory.getLogger(UserCampaignServiceImpl.class);

    private final UserCampaignRepository userCampaignRepository;

    private final UserCampaignMapper userCampaignMapper;

    public UserCampaignServiceImpl(UserCampaignRepository userCampaignRepository, UserCampaignMapper userCampaignMapper) {
        this.userCampaignRepository = userCampaignRepository;
        this.userCampaignMapper = userCampaignMapper;
    }

    @Override
    public UserCampaignDTO save(UserCampaignDTO userCampaignDTO) {
        log.debug("Request to save UserCampaign : {}", userCampaignDTO);
        UserCampaign userCampaign = userCampaignMapper.toEntity(userCampaignDTO);
        userCampaign = userCampaignRepository.save(userCampaign);
        return userCampaignMapper.toDto(userCampaign);
    }

    @Override
    public UserCampaignDTO update(UserCampaignDTO userCampaignDTO) {
        log.debug("Request to save UserCampaign : {}", userCampaignDTO);
        UserCampaign userCampaign = userCampaignMapper.toEntity(userCampaignDTO);
        userCampaign = userCampaignRepository.save(userCampaign);
        return userCampaignMapper.toDto(userCampaign);
    }

    @Override
    public Optional<UserCampaignDTO> partialUpdate(UserCampaignDTO userCampaignDTO) {
        log.debug("Request to partially update UserCampaign : {}", userCampaignDTO);

        return userCampaignRepository
            .findById(userCampaignDTO.getId())
            .map(existingUserCampaign -> {
                userCampaignMapper.partialUpdate(existingUserCampaign, userCampaignDTO);

                return existingUserCampaign;
            })
            .map(userCampaignRepository::save)
            .map(userCampaignMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserCampaignDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UserCampaigns");
        return userCampaignRepository.findAll(pageable).map(userCampaignMapper::toDto);
    }

    /**
     *  Get all the userCampaigns where CampaignLink is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<UserCampaignDTO> findAllWhereCampaignLinkIsNull() {
        log.debug("Request to get all userCampaigns where CampaignLink is null");
        return StreamSupport
            .stream(userCampaignRepository.findAll().spliterator(), false)
            .filter(userCampaign -> userCampaign.getCampaignLink() == null)
            .map(userCampaignMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserCampaignDTO> findOne(Long id) {
        log.debug("Request to get UserCampaign : {}", id);
        return userCampaignRepository.findById(id).map(userCampaignMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserCampaign : {}", id);
        userCampaignRepository.deleteById(id);
    }
}

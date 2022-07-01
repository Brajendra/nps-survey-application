package com.reliance.retail.nps.service.impl;

import com.reliance.retail.nps.domain.UserAnswers;
import com.reliance.retail.nps.domain.UserCampaign;
import com.reliance.retail.nps.repository.CampaignLinkRepository;
import com.reliance.retail.nps.repository.UserAnswersRepository;
import com.reliance.retail.nps.repository.UserCampaignRepository;
import com.reliance.retail.nps.service.UserCampaignResponseService;
import com.reliance.retail.nps.service.dto.UserCampaignResponseDetailsDTO;
import com.reliance.retail.nps.service.mapper.UserAnswersMapper;
import com.reliance.retail.nps.service.mapper.UserCampaignMapper;
import com.reliance.retail.nps.web.rest.errors.BadRequestAlertException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserCampaignResponseServiceImpl implements UserCampaignResponseService {

    private final Logger log = LoggerFactory.getLogger(UserCampaignServiceImpl.class);

    private static final String ENTITY_NAME = "userResponse";

    private final UserCampaignRepository userCampaignRepository;
    private final UserCampaignMapper userCampaignMapper;
    private final UserAnswersMapper userAnswersMapper;
    private final UserAnswersRepository userAnswersRepository;
    private final CampaignLinkRepository campaignLinkRepository;


    public UserCampaignResponseServiceImpl(UserCampaignRepository userCampaignRepository, UserAnswersRepository userAnswersRepository, UserCampaignMapper userCampaignMapper, UserAnswersMapper userAnswersMapper, CampaignLinkRepository campaignLinkRepository) {
        this.userCampaignRepository = userCampaignRepository;
        this.userAnswersRepository = userAnswersRepository;
        this.userCampaignMapper = userCampaignMapper;
        this.userAnswersMapper = userAnswersMapper;
        this.campaignLinkRepository = campaignLinkRepository;
    }

    @Override
    @Transactional
    public boolean save(UserCampaignResponseDetailsDTO responseDetails) {

        validateRequestData(responseDetails);



        UserCampaign userCampaign = userCampaignMapper.toEntity(responseDetails.getUserCampaign());
        userCampaign.setCode(responseDetails.getUserCampaign().getCode());
        final UserCampaign savedUserCampaign = userCampaignRepository.save(userCampaign);
        if (responseDetails.getUserAnswers() != null && !responseDetails.getUserAnswers().isEmpty()) {
            List<UserAnswers> userAnswers = responseDetails.getUserAnswers().stream().map(userAnswersDTO -> {
                userAnswersDTO.setUserCampaignId(userCampaign.getId());
                return userAnswersMapper.toEntity(userAnswersDTO);
            }).collect(Collectors.toList());
            userAnswersRepository.saveAll(userAnswers);
        }
        return true;
    }


    private void validateRequestData(UserCampaignResponseDetailsDTO responseDetails) {
        if(responseDetails.getUserCampaign() == null) {
            throw new BadRequestAlertException("Campaign Details Required", ENTITY_NAME, "CampaignNUll");
        }
        if(StringUtils.isEmpty(responseDetails.getUserCampaign().getCode() )) {
            throw new BadRequestAlertException("Campaign Code Required", ENTITY_NAME, "CampaignNUll");
        }
       Boolean exist =  campaignLinkRepository.existsByCode(responseDetails.getUserCampaign().getCode()).get();
        if(!exist) {
            throw new BadRequestAlertException("Campaign Code is not valid", ENTITY_NAME, "CampaignNotyValid");
        }

        exist =  userCampaignRepository.existsByCode(responseDetails.getUserCampaign().getCode()).get();
        if(exist) {
            throw new BadRequestAlertException("Response already saved", ENTITY_NAME, "ResponseSaved");
        }

    }
}

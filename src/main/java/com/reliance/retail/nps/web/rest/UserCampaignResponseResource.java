package com.reliance.retail.nps.web.rest;

import com.reliance.retail.nps.service.UserCampaignResponseService;
import com.reliance.retail.nps.service.dto.UserCampaignResponseDetailsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.web.util.HeaderUtil;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class UserCampaignResponseResource {


    private final Logger log = LoggerFactory.getLogger(UserAnswersResource.class);
    private static final String ENTITY_NAME = "userResponse";


    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserCampaignResponseService userCampaignResponseService;


    public UserCampaignResponseResource(UserCampaignResponseService UserCampaignResponseService) {
        this.userCampaignResponseService = UserCampaignResponseService;
    }


    @PostMapping("/user-answers-response")
    public ResponseEntity<Boolean> createUserAnswers(@RequestBody UserCampaignResponseDetailsDTO userCampaignResponseDetails) throws URISyntaxException {
        log.debug("REST request to save UserCampaignResponseDetails : {}", userCampaignResponseDetails);

        Boolean result = userCampaignResponseService.save(userCampaignResponseDetails);
        return ResponseEntity
            .created(new URI("/api/user-answers-response" +result))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.toString()))
            .body(result);
    }
}

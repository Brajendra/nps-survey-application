package com.reliance.retail.nps.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.reliance.retail.nps.IntegrationTest;
import com.reliance.retail.nps.domain.UserCampaign;
import com.reliance.retail.nps.repository.UserCampaignRepository;
import com.reliance.retail.nps.service.dto.UserCampaignDTO;
import com.reliance.retail.nps.service.mapper.UserCampaignMapper;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link UserCampaignResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class UserCampaignResourceIT {

    private static final String DEFAULT_HASH_CODE = "AAAAAAAAAA";
    private static final String UPDATED_HASH_CODE = "BBBBBBBBBB";

    private static final Integer DEFAULT_ATTEMPT_QUESTION_COUNT = 1;
    private static final Integer UPDATED_ATTEMPT_QUESTION_COUNT = 2;

    private static final String DEFAULT_EVENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_EVENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_EVENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_EVENT_TYPE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/user-campaigns";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UserCampaignRepository userCampaignRepository;

    @Autowired
    private UserCampaignMapper userCampaignMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserCampaignMockMvc;

    private UserCampaign userCampaign;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserCampaign createEntity(EntityManager em) {
        UserCampaign userCampaign = new UserCampaign()
            .hashCode(DEFAULT_HASH_CODE)
            .attemptQuestionCount(DEFAULT_ATTEMPT_QUESTION_COUNT)
            .eventId(DEFAULT_EVENT_ID)
            .eventType(DEFAULT_EVENT_TYPE)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        return userCampaign;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserCampaign createUpdatedEntity(EntityManager em) {
        UserCampaign userCampaign = new UserCampaign()
            .hashCode(UPDATED_HASH_CODE)
            .attemptQuestionCount(UPDATED_ATTEMPT_QUESTION_COUNT)
            .eventId(UPDATED_EVENT_ID)
            .eventType(UPDATED_EVENT_TYPE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        return userCampaign;
    }

    @BeforeEach
    public void initTest() {
        userCampaign = createEntity(em);
    }

    @Test
    @Transactional
    void createUserCampaign() throws Exception {
        int databaseSizeBeforeCreate = userCampaignRepository.findAll().size();
        // Create the UserCampaign
        UserCampaignDTO userCampaignDTO = userCampaignMapper.toDto(userCampaign);
        restUserCampaignMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(userCampaignDTO))
            )
            .andExpect(status().isCreated());

        // Validate the UserCampaign in the database
        List<UserCampaign> userCampaignList = userCampaignRepository.findAll();
        assertThat(userCampaignList).hasSize(databaseSizeBeforeCreate + 1);
        UserCampaign testUserCampaign = userCampaignList.get(userCampaignList.size() - 1);
        assertThat(testUserCampaign.getHashCode()).isEqualTo(DEFAULT_HASH_CODE);
        assertThat(testUserCampaign.getAttemptQuestionCount()).isEqualTo(DEFAULT_ATTEMPT_QUESTION_COUNT);
        assertThat(testUserCampaign.getEventId()).isEqualTo(DEFAULT_EVENT_ID);
        assertThat(testUserCampaign.getEventType()).isEqualTo(DEFAULT_EVENT_TYPE);
        assertThat(testUserCampaign.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testUserCampaign.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    void createUserCampaignWithExistingId() throws Exception {
        // Create the UserCampaign with an existing ID
        userCampaign.setId(1L);
        UserCampaignDTO userCampaignDTO = userCampaignMapper.toDto(userCampaign);

        int databaseSizeBeforeCreate = userCampaignRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserCampaignMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(userCampaignDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserCampaign in the database
        List<UserCampaign> userCampaignList = userCampaignRepository.findAll();
        assertThat(userCampaignList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkHashCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = userCampaignRepository.findAll().size();
        // set the field null
        userCampaign.setHashCode(null);

        // Create the UserCampaign, which fails.
        UserCampaignDTO userCampaignDTO = userCampaignMapper.toDto(userCampaign);

        restUserCampaignMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(userCampaignDTO))
            )
            .andExpect(status().isBadRequest());

        List<UserCampaign> userCampaignList = userCampaignRepository.findAll();
        assertThat(userCampaignList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllUserCampaigns() throws Exception {
        // Initialize the database
        userCampaignRepository.saveAndFlush(userCampaign);

        // Get all the userCampaignList
        restUserCampaignMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userCampaign.getId().intValue())))
            .andExpect(jsonPath("$.[*].hashCode").value(hasItem(DEFAULT_HASH_CODE)))
            .andExpect(jsonPath("$.[*].attemptQuestionCount").value(hasItem(DEFAULT_ATTEMPT_QUESTION_COUNT)))
            .andExpect(jsonPath("$.[*].eventId").value(hasItem(DEFAULT_EVENT_ID)))
            .andExpect(jsonPath("$.[*].eventType").value(hasItem(DEFAULT_EVENT_TYPE)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }

    @Test
    @Transactional
    void getUserCampaign() throws Exception {
        // Initialize the database
        userCampaignRepository.saveAndFlush(userCampaign);

        // Get the userCampaign
        restUserCampaignMockMvc
            .perform(get(ENTITY_API_URL_ID, userCampaign.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userCampaign.getId().intValue()))
            .andExpect(jsonPath("$.hashCode").value(DEFAULT_HASH_CODE))
            .andExpect(jsonPath("$.attemptQuestionCount").value(DEFAULT_ATTEMPT_QUESTION_COUNT))
            .andExpect(jsonPath("$.eventId").value(DEFAULT_EVENT_ID))
            .andExpect(jsonPath("$.eventType").value(DEFAULT_EVENT_TYPE))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()));
    }

    @Test
    @Transactional
    void getNonExistingUserCampaign() throws Exception {
        // Get the userCampaign
        restUserCampaignMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewUserCampaign() throws Exception {
        // Initialize the database
        userCampaignRepository.saveAndFlush(userCampaign);

        int databaseSizeBeforeUpdate = userCampaignRepository.findAll().size();

        // Update the userCampaign
        UserCampaign updatedUserCampaign = userCampaignRepository.findById(userCampaign.getId()).get();
        // Disconnect from session so that the updates on updatedUserCampaign are not directly saved in db
        em.detach(updatedUserCampaign);
        updatedUserCampaign
            .hashCode(UPDATED_HASH_CODE)
            .attemptQuestionCount(UPDATED_ATTEMPT_QUESTION_COUNT)
            .eventId(UPDATED_EVENT_ID)
            .eventType(UPDATED_EVENT_TYPE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        UserCampaignDTO userCampaignDTO = userCampaignMapper.toDto(updatedUserCampaign);

        restUserCampaignMockMvc
            .perform(
                put(ENTITY_API_URL_ID, userCampaignDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userCampaignDTO))
            )
            .andExpect(status().isOk());

        // Validate the UserCampaign in the database
        List<UserCampaign> userCampaignList = userCampaignRepository.findAll();
        assertThat(userCampaignList).hasSize(databaseSizeBeforeUpdate);
        UserCampaign testUserCampaign = userCampaignList.get(userCampaignList.size() - 1);
        assertThat(testUserCampaign.getHashCode()).isEqualTo(UPDATED_HASH_CODE);
        assertThat(testUserCampaign.getAttemptQuestionCount()).isEqualTo(UPDATED_ATTEMPT_QUESTION_COUNT);
        assertThat(testUserCampaign.getEventId()).isEqualTo(UPDATED_EVENT_ID);
        assertThat(testUserCampaign.getEventType()).isEqualTo(UPDATED_EVENT_TYPE);
        assertThat(testUserCampaign.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testUserCampaign.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void putNonExistingUserCampaign() throws Exception {
        int databaseSizeBeforeUpdate = userCampaignRepository.findAll().size();
        userCampaign.setId(count.incrementAndGet());

        // Create the UserCampaign
        UserCampaignDTO userCampaignDTO = userCampaignMapper.toDto(userCampaign);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserCampaignMockMvc
            .perform(
                put(ENTITY_API_URL_ID, userCampaignDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userCampaignDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserCampaign in the database
        List<UserCampaign> userCampaignList = userCampaignRepository.findAll();
        assertThat(userCampaignList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchUserCampaign() throws Exception {
        int databaseSizeBeforeUpdate = userCampaignRepository.findAll().size();
        userCampaign.setId(count.incrementAndGet());

        // Create the UserCampaign
        UserCampaignDTO userCampaignDTO = userCampaignMapper.toDto(userCampaign);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserCampaignMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userCampaignDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserCampaign in the database
        List<UserCampaign> userCampaignList = userCampaignRepository.findAll();
        assertThat(userCampaignList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamUserCampaign() throws Exception {
        int databaseSizeBeforeUpdate = userCampaignRepository.findAll().size();
        userCampaign.setId(count.incrementAndGet());

        // Create the UserCampaign
        UserCampaignDTO userCampaignDTO = userCampaignMapper.toDto(userCampaign);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserCampaignMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(userCampaignDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the UserCampaign in the database
        List<UserCampaign> userCampaignList = userCampaignRepository.findAll();
        assertThat(userCampaignList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateUserCampaignWithPatch() throws Exception {
        // Initialize the database
        userCampaignRepository.saveAndFlush(userCampaign);

        int databaseSizeBeforeUpdate = userCampaignRepository.findAll().size();

        // Update the userCampaign using partial update
        UserCampaign partialUpdatedUserCampaign = new UserCampaign();
        partialUpdatedUserCampaign.setId(userCampaign.getId());

        partialUpdatedUserCampaign.hashCode(UPDATED_HASH_CODE).eventId(UPDATED_EVENT_ID).updatedAt(UPDATED_UPDATED_AT);

        restUserCampaignMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUserCampaign.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUserCampaign))
            )
            .andExpect(status().isOk());

        // Validate the UserCampaign in the database
        List<UserCampaign> userCampaignList = userCampaignRepository.findAll();
        assertThat(userCampaignList).hasSize(databaseSizeBeforeUpdate);
        UserCampaign testUserCampaign = userCampaignList.get(userCampaignList.size() - 1);
        assertThat(testUserCampaign.getHashCode()).isEqualTo(UPDATED_HASH_CODE);
        assertThat(testUserCampaign.getAttemptQuestionCount()).isEqualTo(DEFAULT_ATTEMPT_QUESTION_COUNT);
        assertThat(testUserCampaign.getEventId()).isEqualTo(UPDATED_EVENT_ID);
        assertThat(testUserCampaign.getEventType()).isEqualTo(DEFAULT_EVENT_TYPE);
        assertThat(testUserCampaign.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testUserCampaign.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void fullUpdateUserCampaignWithPatch() throws Exception {
        // Initialize the database
        userCampaignRepository.saveAndFlush(userCampaign);

        int databaseSizeBeforeUpdate = userCampaignRepository.findAll().size();

        // Update the userCampaign using partial update
        UserCampaign partialUpdatedUserCampaign = new UserCampaign();
        partialUpdatedUserCampaign.setId(userCampaign.getId());

        partialUpdatedUserCampaign
            .hashCode(UPDATED_HASH_CODE)
            .attemptQuestionCount(UPDATED_ATTEMPT_QUESTION_COUNT)
            .eventId(UPDATED_EVENT_ID)
            .eventType(UPDATED_EVENT_TYPE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);

        restUserCampaignMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUserCampaign.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUserCampaign))
            )
            .andExpect(status().isOk());

        // Validate the UserCampaign in the database
        List<UserCampaign> userCampaignList = userCampaignRepository.findAll();
        assertThat(userCampaignList).hasSize(databaseSizeBeforeUpdate);
        UserCampaign testUserCampaign = userCampaignList.get(userCampaignList.size() - 1);
        assertThat(testUserCampaign.getHashCode()).isEqualTo(UPDATED_HASH_CODE);
        assertThat(testUserCampaign.getAttemptQuestionCount()).isEqualTo(UPDATED_ATTEMPT_QUESTION_COUNT);
        assertThat(testUserCampaign.getEventId()).isEqualTo(UPDATED_EVENT_ID);
        assertThat(testUserCampaign.getEventType()).isEqualTo(UPDATED_EVENT_TYPE);
        assertThat(testUserCampaign.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testUserCampaign.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void patchNonExistingUserCampaign() throws Exception {
        int databaseSizeBeforeUpdate = userCampaignRepository.findAll().size();
        userCampaign.setId(count.incrementAndGet());

        // Create the UserCampaign
        UserCampaignDTO userCampaignDTO = userCampaignMapper.toDto(userCampaign);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserCampaignMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, userCampaignDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(userCampaignDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserCampaign in the database
        List<UserCampaign> userCampaignList = userCampaignRepository.findAll();
        assertThat(userCampaignList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchUserCampaign() throws Exception {
        int databaseSizeBeforeUpdate = userCampaignRepository.findAll().size();
        userCampaign.setId(count.incrementAndGet());

        // Create the UserCampaign
        UserCampaignDTO userCampaignDTO = userCampaignMapper.toDto(userCampaign);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserCampaignMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(userCampaignDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserCampaign in the database
        List<UserCampaign> userCampaignList = userCampaignRepository.findAll();
        assertThat(userCampaignList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamUserCampaign() throws Exception {
        int databaseSizeBeforeUpdate = userCampaignRepository.findAll().size();
        userCampaign.setId(count.incrementAndGet());

        // Create the UserCampaign
        UserCampaignDTO userCampaignDTO = userCampaignMapper.toDto(userCampaign);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserCampaignMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(userCampaignDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the UserCampaign in the database
        List<UserCampaign> userCampaignList = userCampaignRepository.findAll();
        assertThat(userCampaignList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteUserCampaign() throws Exception {
        // Initialize the database
        userCampaignRepository.saveAndFlush(userCampaign);

        int databaseSizeBeforeDelete = userCampaignRepository.findAll().size();

        // Delete the userCampaign
        restUserCampaignMockMvc
            .perform(delete(ENTITY_API_URL_ID, userCampaign.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserCampaign> userCampaignList = userCampaignRepository.findAll();
        assertThat(userCampaignList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

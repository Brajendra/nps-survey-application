package com.reliance.retail.nps.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.reliance.retail.nps.IntegrationTest;
import com.reliance.retail.nps.domain.Link;
import com.reliance.retail.nps.repository.LinkRepository;
import com.reliance.retail.nps.service.dto.LinkDTO;
import com.reliance.retail.nps.service.mapper.LinkMapper;
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
 * Integration tests for the {@link LinkResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class LinkResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_USER_INFO = "AAAAAAAAAA";
    private static final String UPDATED_USER_INFO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/links";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private LinkMapper linkMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLinkMockMvc;

    private Link link;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Link createEntity(EntityManager em) {
        Link link = new Link().code(DEFAULT_CODE).userInfo(DEFAULT_USER_INFO).createdAt(DEFAULT_CREATED_AT).updatedAt(DEFAULT_UPDATED_AT);
        return link;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Link createUpdatedEntity(EntityManager em) {
        Link link = new Link().code(UPDATED_CODE).userInfo(UPDATED_USER_INFO).createdAt(UPDATED_CREATED_AT).updatedAt(UPDATED_UPDATED_AT);
        return link;
    }

    @BeforeEach
    public void initTest() {
        link = createEntity(em);
    }

    @Test
    @Transactional
    void createLink() throws Exception {
        int databaseSizeBeforeCreate = linkRepository.findAll().size();
        // Create the Link
        LinkDTO linkDTO = linkMapper.toDto(link);
        restLinkMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(linkDTO)))
            .andExpect(status().isCreated());

        // Validate the Link in the database
        List<Link> linkList = linkRepository.findAll();
        assertThat(linkList).hasSize(databaseSizeBeforeCreate + 1);
        Link testLink = linkList.get(linkList.size() - 1);
        assertThat(testLink.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testLink.getUserInfo()).isEqualTo(DEFAULT_USER_INFO);
        assertThat(testLink.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testLink.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    void createLinkWithExistingId() throws Exception {
        // Create the Link with an existing ID
        link.setId(1L);
        LinkDTO linkDTO = linkMapper.toDto(link);

        int databaseSizeBeforeCreate = linkRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLinkMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(linkDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Link in the database
        List<Link> linkList = linkRepository.findAll();
        assertThat(linkList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = linkRepository.findAll().size();
        // set the field null
        link.setCode(null);

        // Create the Link, which fails.
        LinkDTO linkDTO = linkMapper.toDto(link);

        restLinkMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(linkDTO)))
            .andExpect(status().isBadRequest());

        List<Link> linkList = linkRepository.findAll();
        assertThat(linkList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllLinks() throws Exception {
        // Initialize the database
        linkRepository.saveAndFlush(link);

        // Get all the linkList
        restLinkMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(link.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].userInfo").value(hasItem(DEFAULT_USER_INFO)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }

    @Test
    @Transactional
    void getLink() throws Exception {
        // Initialize the database
        linkRepository.saveAndFlush(link);

        // Get the link
        restLinkMockMvc
            .perform(get(ENTITY_API_URL_ID, link.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(link.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.userInfo").value(DEFAULT_USER_INFO))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()));
    }

    @Test
    @Transactional
    void getNonExistingLink() throws Exception {
        // Get the link
        restLinkMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewLink() throws Exception {
        // Initialize the database
        linkRepository.saveAndFlush(link);

        int databaseSizeBeforeUpdate = linkRepository.findAll().size();

        // Update the link
        Link updatedLink = linkRepository.findById(link.getId()).get();
        // Disconnect from session so that the updates on updatedLink are not directly saved in db
        em.detach(updatedLink);
        updatedLink.code(UPDATED_CODE).userInfo(UPDATED_USER_INFO).createdAt(UPDATED_CREATED_AT).updatedAt(UPDATED_UPDATED_AT);
        LinkDTO linkDTO = linkMapper.toDto(updatedLink);

        restLinkMockMvc
            .perform(
                put(ENTITY_API_URL_ID, linkDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(linkDTO))
            )
            .andExpect(status().isOk());

        // Validate the Link in the database
        List<Link> linkList = linkRepository.findAll();
        assertThat(linkList).hasSize(databaseSizeBeforeUpdate);
        Link testLink = linkList.get(linkList.size() - 1);
        assertThat(testLink.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testLink.getUserInfo()).isEqualTo(UPDATED_USER_INFO);
        assertThat(testLink.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testLink.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void putNonExistingLink() throws Exception {
        int databaseSizeBeforeUpdate = linkRepository.findAll().size();
        link.setId(count.incrementAndGet());

        // Create the Link
        LinkDTO linkDTO = linkMapper.toDto(link);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLinkMockMvc
            .perform(
                put(ENTITY_API_URL_ID, linkDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(linkDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Link in the database
        List<Link> linkList = linkRepository.findAll();
        assertThat(linkList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchLink() throws Exception {
        int databaseSizeBeforeUpdate = linkRepository.findAll().size();
        link.setId(count.incrementAndGet());

        // Create the Link
        LinkDTO linkDTO = linkMapper.toDto(link);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLinkMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(linkDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Link in the database
        List<Link> linkList = linkRepository.findAll();
        assertThat(linkList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamLink() throws Exception {
        int databaseSizeBeforeUpdate = linkRepository.findAll().size();
        link.setId(count.incrementAndGet());

        // Create the Link
        LinkDTO linkDTO = linkMapper.toDto(link);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLinkMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(linkDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Link in the database
        List<Link> linkList = linkRepository.findAll();
        assertThat(linkList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateLinkWithPatch() throws Exception {
        // Initialize the database
        linkRepository.saveAndFlush(link);

        int databaseSizeBeforeUpdate = linkRepository.findAll().size();

        // Update the link using partial update
        Link partialUpdatedLink = new Link();
        partialUpdatedLink.setId(link.getId());

        partialUpdatedLink.code(UPDATED_CODE).userInfo(UPDATED_USER_INFO);

        restLinkMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLink.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLink))
            )
            .andExpect(status().isOk());

        // Validate the Link in the database
        List<Link> linkList = linkRepository.findAll();
        assertThat(linkList).hasSize(databaseSizeBeforeUpdate);
        Link testLink = linkList.get(linkList.size() - 1);
        assertThat(testLink.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testLink.getUserInfo()).isEqualTo(UPDATED_USER_INFO);
        assertThat(testLink.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testLink.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    void fullUpdateLinkWithPatch() throws Exception {
        // Initialize the database
        linkRepository.saveAndFlush(link);

        int databaseSizeBeforeUpdate = linkRepository.findAll().size();

        // Update the link using partial update
        Link partialUpdatedLink = new Link();
        partialUpdatedLink.setId(link.getId());

        partialUpdatedLink.code(UPDATED_CODE).userInfo(UPDATED_USER_INFO).createdAt(UPDATED_CREATED_AT).updatedAt(UPDATED_UPDATED_AT);

        restLinkMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLink.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLink))
            )
            .andExpect(status().isOk());

        // Validate the Link in the database
        List<Link> linkList = linkRepository.findAll();
        assertThat(linkList).hasSize(databaseSizeBeforeUpdate);
        Link testLink = linkList.get(linkList.size() - 1);
        assertThat(testLink.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testLink.getUserInfo()).isEqualTo(UPDATED_USER_INFO);
        assertThat(testLink.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testLink.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void patchNonExistingLink() throws Exception {
        int databaseSizeBeforeUpdate = linkRepository.findAll().size();
        link.setId(count.incrementAndGet());

        // Create the Link
        LinkDTO linkDTO = linkMapper.toDto(link);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLinkMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, linkDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(linkDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Link in the database
        List<Link> linkList = linkRepository.findAll();
        assertThat(linkList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchLink() throws Exception {
        int databaseSizeBeforeUpdate = linkRepository.findAll().size();
        link.setId(count.incrementAndGet());

        // Create the Link
        LinkDTO linkDTO = linkMapper.toDto(link);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLinkMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(linkDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Link in the database
        List<Link> linkList = linkRepository.findAll();
        assertThat(linkList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamLink() throws Exception {
        int databaseSizeBeforeUpdate = linkRepository.findAll().size();
        link.setId(count.incrementAndGet());

        // Create the Link
        LinkDTO linkDTO = linkMapper.toDto(link);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLinkMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(linkDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Link in the database
        List<Link> linkList = linkRepository.findAll();
        assertThat(linkList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteLink() throws Exception {
        // Initialize the database
        linkRepository.saveAndFlush(link);

        int databaseSizeBeforeDelete = linkRepository.findAll().size();

        // Delete the link
        restLinkMockMvc
            .perform(delete(ENTITY_API_URL_ID, link.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Link> linkList = linkRepository.findAll();
        assertThat(linkList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

package com.altuna.challenge.web.rest;

import com.altuna.challenge.ChallengeApp;

import com.altuna.challenge.domain.FriendlyFraudNotification;
import com.altuna.challenge.repository.FriendlyFraudNotificationRepository;
import com.altuna.challenge.service.FriendlyFraudNotificationService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the FriendlyFraudNotificationResource REST controller.
 *
 * @see FriendlyFraudNotificationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChallengeApp.class)
public class FriendlyFraudNotificationResourceIntTest {

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_ATTACHED_LETTER = "AAAAAAAAAA";
    private static final String UPDATED_ATTACHED_LETTER = "BBBBBBBBBB";

    @Inject
    private FriendlyFraudNotificationRepository friendlyFraudNotificationRepository;

    @Inject
    private FriendlyFraudNotificationService friendlyFraudNotificationService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restFriendlyFraudNotificationMockMvc;

    private FriendlyFraudNotification friendlyFraudNotification;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        FriendlyFraudNotificationResource friendlyFraudNotificationResource = new FriendlyFraudNotificationResource();
        ReflectionTestUtils.setField(friendlyFraudNotificationResource, "friendlyFraudNotificationService", friendlyFraudNotificationService);
        this.restFriendlyFraudNotificationMockMvc = MockMvcBuilders.standaloneSetup(friendlyFraudNotificationResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FriendlyFraudNotification createEntity(EntityManager em) {
        FriendlyFraudNotification friendlyFraudNotification = new FriendlyFraudNotification()
                .email(DEFAULT_EMAIL)
                .attachedLetter(DEFAULT_ATTACHED_LETTER);
        return friendlyFraudNotification;
    }

    @Before
    public void initTest() {
        friendlyFraudNotification = createEntity(em);
    }

    @Test
    @Transactional
    public void createFriendlyFraudNotification() throws Exception {
        int databaseSizeBeforeCreate = friendlyFraudNotificationRepository.findAll().size();

        // Create the FriendlyFraudNotification

        restFriendlyFraudNotificationMockMvc.perform(post("/api/friendly-fraud-notifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(friendlyFraudNotification)))
            .andExpect(status().isCreated());

        // Validate the FriendlyFraudNotification in the database
        List<FriendlyFraudNotification> friendlyFraudNotificationList = friendlyFraudNotificationRepository.findAll();
        assertThat(friendlyFraudNotificationList).hasSize(databaseSizeBeforeCreate + 1);
        FriendlyFraudNotification testFriendlyFraudNotification = friendlyFraudNotificationList.get(friendlyFraudNotificationList.size() - 1);
        assertThat(testFriendlyFraudNotification.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testFriendlyFraudNotification.getAttachedLetter()).isEqualTo(DEFAULT_ATTACHED_LETTER);
    }

    @Test
    @Transactional
    public void createFriendlyFraudNotificationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = friendlyFraudNotificationRepository.findAll().size();

        // Create the FriendlyFraudNotification with an existing ID
        FriendlyFraudNotification existingFriendlyFraudNotification = new FriendlyFraudNotification();
        existingFriendlyFraudNotification.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFriendlyFraudNotificationMockMvc.perform(post("/api/friendly-fraud-notifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingFriendlyFraudNotification)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<FriendlyFraudNotification> friendlyFraudNotificationList = friendlyFraudNotificationRepository.findAll();
        assertThat(friendlyFraudNotificationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFriendlyFraudNotifications() throws Exception {
        // Initialize the database
        friendlyFraudNotificationRepository.saveAndFlush(friendlyFraudNotification);

        // Get all the friendlyFraudNotificationList
        restFriendlyFraudNotificationMockMvc.perform(get("/api/friendly-fraud-notifications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(friendlyFraudNotification.getId().intValue())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].attachedLetter").value(hasItem(DEFAULT_ATTACHED_LETTER.toString())));
    }

    @Test
    @Transactional
    public void getFriendlyFraudNotification() throws Exception {
        // Initialize the database
        friendlyFraudNotificationRepository.saveAndFlush(friendlyFraudNotification);

        // Get the friendlyFraudNotification
        restFriendlyFraudNotificationMockMvc.perform(get("/api/friendly-fraud-notifications/{id}", friendlyFraudNotification.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(friendlyFraudNotification.getId().intValue()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.attachedLetter").value(DEFAULT_ATTACHED_LETTER.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFriendlyFraudNotification() throws Exception {
        // Get the friendlyFraudNotification
        restFriendlyFraudNotificationMockMvc.perform(get("/api/friendly-fraud-notifications/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFriendlyFraudNotification() throws Exception {
        // Initialize the database
        friendlyFraudNotificationService.save(friendlyFraudNotification);

        int databaseSizeBeforeUpdate = friendlyFraudNotificationRepository.findAll().size();

        // Update the friendlyFraudNotification
        FriendlyFraudNotification updatedFriendlyFraudNotification = friendlyFraudNotificationRepository.findOne(friendlyFraudNotification.getId());
        updatedFriendlyFraudNotification
                .email(UPDATED_EMAIL)
                .attachedLetter(UPDATED_ATTACHED_LETTER);

        restFriendlyFraudNotificationMockMvc.perform(put("/api/friendly-fraud-notifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFriendlyFraudNotification)))
            .andExpect(status().isOk());

        // Validate the FriendlyFraudNotification in the database
        List<FriendlyFraudNotification> friendlyFraudNotificationList = friendlyFraudNotificationRepository.findAll();
        assertThat(friendlyFraudNotificationList).hasSize(databaseSizeBeforeUpdate);
        FriendlyFraudNotification testFriendlyFraudNotification = friendlyFraudNotificationList.get(friendlyFraudNotificationList.size() - 1);
        assertThat(testFriendlyFraudNotification.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testFriendlyFraudNotification.getAttachedLetter()).isEqualTo(UPDATED_ATTACHED_LETTER);
    }

    @Test
    @Transactional
    public void updateNonExistingFriendlyFraudNotification() throws Exception {
        int databaseSizeBeforeUpdate = friendlyFraudNotificationRepository.findAll().size();

        // Create the FriendlyFraudNotification

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFriendlyFraudNotificationMockMvc.perform(put("/api/friendly-fraud-notifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(friendlyFraudNotification)))
            .andExpect(status().isCreated());

        // Validate the FriendlyFraudNotification in the database
        List<FriendlyFraudNotification> friendlyFraudNotificationList = friendlyFraudNotificationRepository.findAll();
        assertThat(friendlyFraudNotificationList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteFriendlyFraudNotification() throws Exception {
        // Initialize the database
        friendlyFraudNotificationService.save(friendlyFraudNotification);

        int databaseSizeBeforeDelete = friendlyFraudNotificationRepository.findAll().size();

        // Get the friendlyFraudNotification
        restFriendlyFraudNotificationMockMvc.perform(delete("/api/friendly-fraud-notifications/{id}", friendlyFraudNotification.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<FriendlyFraudNotification> friendlyFraudNotificationList = friendlyFraudNotificationRepository.findAll();
        assertThat(friendlyFraudNotificationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

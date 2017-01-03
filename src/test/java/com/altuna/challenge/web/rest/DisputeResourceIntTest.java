package com.altuna.challenge.web.rest;

import com.altuna.challenge.ChallengeApp;

import com.altuna.challenge.domain.Dispute;
import com.altuna.challenge.repository.DisputeRepository;
import com.altuna.challenge.service.DisputeService;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.altuna.challenge.domain.enumeration.DisputeStatus;
import com.altuna.challenge.domain.enumeration.DisputeType;
import com.altuna.challenge.domain.enumeration.CardType;
import com.altuna.challenge.domain.enumeration.AnalystDesition;
/**
 * Test class for the DisputeResource REST controller.
 *
 * @see DisputeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChallengeApp.class)
public class DisputeResourceIntTest {

    private static final DisputeStatus DEFAULT_STATUS = DisputeStatus.NEW;
    private static final DisputeStatus UPDATED_STATUS = DisputeStatus.PROCESSING;

    private static final DisputeType DEFAULT_DISPUTE_TYPE = DisputeType.CHARGEBACK;
    private static final DisputeType UPDATED_DISPUTE_TYPE = DisputeType.RETRIEVAL_REQUEST;

    private static final String DEFAULT_CASE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CASE_NUMBER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FILED_DAY = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FILED_DAY = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_AMOUNT = 1D;
    private static final Double UPDATED_AMOUNT = 2D;

    private static final CardType DEFAULT_CARD_TYPE = CardType.VISA;
    private static final CardType UPDATED_CARD_TYPE = CardType.MASTERCARD;

    private static final String DEFAULT_REASON_CODE = "AAAAAAAAAA";
    private static final String UPDATED_REASON_CODE = "BBBBBBBBBB";

    private static final AnalystDesition DEFAULT_ANALYST_DESITION = AnalystDesition.TRUE_FRAUD;
    private static final AnalystDesition UPDATED_ANALYST_DESITION = AnalystDesition.FRIENDLY_FRAUD;

    private static final String DEFAULT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_NOTES = "BBBBBBBBBB";

    @Inject
    private DisputeRepository disputeRepository;

    @Inject
    private DisputeService disputeService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restDisputeMockMvc;

    private Dispute dispute;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DisputeResource disputeResource = new DisputeResource();
        ReflectionTestUtils.setField(disputeResource, "disputeService", disputeService);
        this.restDisputeMockMvc = MockMvcBuilders.standaloneSetup(disputeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Dispute createEntity(EntityManager em) {
        Dispute dispute = new Dispute()
                .status(DEFAULT_STATUS)
                .disputeType(DEFAULT_DISPUTE_TYPE)
                .caseNumber(DEFAULT_CASE_NUMBER)
                .filedDay(DEFAULT_FILED_DAY)
                .amount(DEFAULT_AMOUNT)
                .cardType(DEFAULT_CARD_TYPE)
                .reasonCode(DEFAULT_REASON_CODE)
                .analystDesition(DEFAULT_ANALYST_DESITION)
                .notes(DEFAULT_NOTES);
        return dispute;
    }

    @Before
    public void initTest() {
        dispute = createEntity(em);
    }

    @Test
    @Transactional
    public void createDispute() throws Exception {
        int databaseSizeBeforeCreate = disputeRepository.findAll().size();

        // Create the Dispute

        restDisputeMockMvc.perform(post("/api/disputes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dispute)))
            .andExpect(status().isCreated());

        // Validate the Dispute in the database
        List<Dispute> disputeList = disputeRepository.findAll();
        assertThat(disputeList).hasSize(databaseSizeBeforeCreate + 1);
        Dispute testDispute = disputeList.get(disputeList.size() - 1);
        assertThat(testDispute.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDispute.getDisputeType()).isEqualTo(DEFAULT_DISPUTE_TYPE);
        assertThat(testDispute.getCaseNumber()).isEqualTo(DEFAULT_CASE_NUMBER);
        assertThat(testDispute.getFiledDay()).isEqualTo(DEFAULT_FILED_DAY);
        assertThat(testDispute.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testDispute.getCardType()).isEqualTo(DEFAULT_CARD_TYPE);
        assertThat(testDispute.getReasonCode()).isEqualTo(DEFAULT_REASON_CODE);
        assertThat(testDispute.getAnalystDesition()).isEqualTo(DEFAULT_ANALYST_DESITION);
        assertThat(testDispute.getNotes()).isEqualTo(DEFAULT_NOTES);
    }

    @Test
    @Transactional
    public void createDisputeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = disputeRepository.findAll().size();

        // Create the Dispute with an existing ID
        Dispute existingDispute = new Dispute();
        existingDispute.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDisputeMockMvc.perform(post("/api/disputes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingDispute)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Dispute> disputeList = disputeRepository.findAll();
        assertThat(disputeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDisputes() throws Exception {
        // Initialize the database
        disputeRepository.saveAndFlush(dispute);

        // Get all the disputeList
        restDisputeMockMvc.perform(get("/api/disputes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dispute.getId().intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].disputeType").value(hasItem(DEFAULT_DISPUTE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].caseNumber").value(hasItem(DEFAULT_CASE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].filedDay").value(hasItem(DEFAULT_FILED_DAY.toString())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].cardType").value(hasItem(DEFAULT_CARD_TYPE.toString())))
            .andExpect(jsonPath("$.[*].reasonCode").value(hasItem(DEFAULT_REASON_CODE.toString())))
            .andExpect(jsonPath("$.[*].analystDesition").value(hasItem(DEFAULT_ANALYST_DESITION.toString())))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES.toString())));
    }

    @Test
    @Transactional
    public void getDispute() throws Exception {
        // Initialize the database
        disputeRepository.saveAndFlush(dispute);

        // Get the dispute
        restDisputeMockMvc.perform(get("/api/disputes/{id}", dispute.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dispute.getId().intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.disputeType").value(DEFAULT_DISPUTE_TYPE.toString()))
            .andExpect(jsonPath("$.caseNumber").value(DEFAULT_CASE_NUMBER.toString()))
            .andExpect(jsonPath("$.filedDay").value(DEFAULT_FILED_DAY.toString()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.cardType").value(DEFAULT_CARD_TYPE.toString()))
            .andExpect(jsonPath("$.reasonCode").value(DEFAULT_REASON_CODE.toString()))
            .andExpect(jsonPath("$.analystDesition").value(DEFAULT_ANALYST_DESITION.toString()))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDispute() throws Exception {
        // Get the dispute
        restDisputeMockMvc.perform(get("/api/disputes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDispute() throws Exception {
        // Initialize the database
        disputeService.save(dispute);

        int databaseSizeBeforeUpdate = disputeRepository.findAll().size();

        // Update the dispute
        Dispute updatedDispute = disputeRepository.findOne(dispute.getId());
        updatedDispute
                .status(UPDATED_STATUS)
                .disputeType(UPDATED_DISPUTE_TYPE)
                .caseNumber(UPDATED_CASE_NUMBER)
                .filedDay(UPDATED_FILED_DAY)
                .amount(UPDATED_AMOUNT)
                .cardType(UPDATED_CARD_TYPE)
                .reasonCode(UPDATED_REASON_CODE)
                .analystDesition(UPDATED_ANALYST_DESITION)
                .notes(UPDATED_NOTES);

        restDisputeMockMvc.perform(put("/api/disputes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDispute)))
            .andExpect(status().isOk());

        // Validate the Dispute in the database
        List<Dispute> disputeList = disputeRepository.findAll();
        assertThat(disputeList).hasSize(databaseSizeBeforeUpdate);
        Dispute testDispute = disputeList.get(disputeList.size() - 1);
        assertThat(testDispute.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDispute.getDisputeType()).isEqualTo(UPDATED_DISPUTE_TYPE);
        assertThat(testDispute.getCaseNumber()).isEqualTo(UPDATED_CASE_NUMBER);
        assertThat(testDispute.getFiledDay()).isEqualTo(UPDATED_FILED_DAY);
        assertThat(testDispute.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testDispute.getCardType()).isEqualTo(UPDATED_CARD_TYPE);
        assertThat(testDispute.getReasonCode()).isEqualTo(UPDATED_REASON_CODE);
        assertThat(testDispute.getAnalystDesition()).isEqualTo(UPDATED_ANALYST_DESITION);
        assertThat(testDispute.getNotes()).isEqualTo(UPDATED_NOTES);
    }

    @Test
    @Transactional
    public void updateNonExistingDispute() throws Exception {
        int databaseSizeBeforeUpdate = disputeRepository.findAll().size();

        // Create the Dispute

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDisputeMockMvc.perform(put("/api/disputes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dispute)))
            .andExpect(status().isCreated());

        // Validate the Dispute in the database
        List<Dispute> disputeList = disputeRepository.findAll();
        assertThat(disputeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDispute() throws Exception {
        // Initialize the database
        disputeService.save(dispute);

        int databaseSizeBeforeDelete = disputeRepository.findAll().size();

        // Get the dispute
        restDisputeMockMvc.perform(delete("/api/disputes/{id}", dispute.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Dispute> disputeList = disputeRepository.findAll();
        assertThat(disputeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

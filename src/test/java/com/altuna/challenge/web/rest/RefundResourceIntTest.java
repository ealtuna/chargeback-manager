package com.altuna.challenge.web.rest;

import com.altuna.challenge.ChallengeApp;

import com.altuna.challenge.domain.Refund;
import com.altuna.challenge.repository.RefundRepository;
import com.altuna.challenge.service.RefundService;

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

/**
 * Test class for the RefundResource REST controller.
 *
 * @see RefundResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChallengeApp.class)
public class RefundResourceIntTest {

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_AMOUNT = "BBBBBBBBBB";

    @Inject
    private RefundRepository refundRepository;

    @Inject
    private RefundService refundService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restRefundMockMvc;

    private Refund refund;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RefundResource refundResource = new RefundResource();
        ReflectionTestUtils.setField(refundResource, "refundService", refundService);
        this.restRefundMockMvc = MockMvcBuilders.standaloneSetup(refundResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Refund createEntity(EntityManager em) {
        Refund refund = new Refund()
                .date(DEFAULT_DATE)
                .amount(DEFAULT_AMOUNT);
        return refund;
    }

    @Before
    public void initTest() {
        refund = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefund() throws Exception {
        int databaseSizeBeforeCreate = refundRepository.findAll().size();

        // Create the Refund

        restRefundMockMvc.perform(post("/api/refunds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refund)))
            .andExpect(status().isCreated());

        // Validate the Refund in the database
        List<Refund> refundList = refundRepository.findAll();
        assertThat(refundList).hasSize(databaseSizeBeforeCreate + 1);
        Refund testRefund = refundList.get(refundList.size() - 1);
        assertThat(testRefund.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testRefund.getAmount()).isEqualTo(DEFAULT_AMOUNT);
    }

    @Test
    @Transactional
    public void createRefundWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refundRepository.findAll().size();

        // Create the Refund with an existing ID
        Refund existingRefund = new Refund();
        existingRefund.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefundMockMvc.perform(post("/api/refunds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingRefund)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Refund> refundList = refundRepository.findAll();
        assertThat(refundList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRefunds() throws Exception {
        // Initialize the database
        refundRepository.saveAndFlush(refund);

        // Get all the refundList
        restRefundMockMvc.perform(get("/api/refunds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refund.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.toString())));
    }

    @Test
    @Transactional
    public void getRefund() throws Exception {
        // Initialize the database
        refundRepository.saveAndFlush(refund);

        // Get the refund
        restRefundMockMvc.perform(get("/api/refunds/{id}", refund.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(refund.getId().intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRefund() throws Exception {
        // Get the refund
        restRefundMockMvc.perform(get("/api/refunds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefund() throws Exception {
        // Initialize the database
        refundService.save(refund);

        int databaseSizeBeforeUpdate = refundRepository.findAll().size();

        // Update the refund
        Refund updatedRefund = refundRepository.findOne(refund.getId());
        updatedRefund
                .date(UPDATED_DATE)
                .amount(UPDATED_AMOUNT);

        restRefundMockMvc.perform(put("/api/refunds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRefund)))
            .andExpect(status().isOk());

        // Validate the Refund in the database
        List<Refund> refundList = refundRepository.findAll();
        assertThat(refundList).hasSize(databaseSizeBeforeUpdate);
        Refund testRefund = refundList.get(refundList.size() - 1);
        assertThat(testRefund.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testRefund.getAmount()).isEqualTo(UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingRefund() throws Exception {
        int databaseSizeBeforeUpdate = refundRepository.findAll().size();

        // Create the Refund

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRefundMockMvc.perform(put("/api/refunds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refund)))
            .andExpect(status().isCreated());

        // Validate the Refund in the database
        List<Refund> refundList = refundRepository.findAll();
        assertThat(refundList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteRefund() throws Exception {
        // Initialize the database
        refundService.save(refund);

        int databaseSizeBeforeDelete = refundRepository.findAll().size();

        // Get the refund
        restRefundMockMvc.perform(delete("/api/refunds/{id}", refund.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Refund> refundList = refundRepository.findAll();
        assertThat(refundList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

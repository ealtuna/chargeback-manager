package com.altuna.challenge.web.rest;

import com.altuna.challenge.ChallengeApp;

import com.altuna.challenge.domain.Shipping;
import com.altuna.challenge.repository.ShippingRepository;
import com.altuna.challenge.service.ShippingService;

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

import com.altuna.challenge.domain.enumeration.OrderStatus;
import com.altuna.challenge.domain.enumeration.ShippingAgency;
/**
 * Test class for the ShippingResource REST controller.
 *
 * @see ShippingResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChallengeApp.class)
public class ShippingResourceIntTest {

    private static final String DEFAULT_CUSTOMER_ID = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_IP_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_IP_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_ID = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_ORDER_ID = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_IN_CAMPAIGN = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_IN_CAMPAIGN = "BBBBBBBBBB";

    private static final Float DEFAULT_ORDER_AMOUNT = 1F;
    private static final Float UPDATED_ORDER_AMOUNT = 2F;

    private static final LocalDate DEFAULT_ORDER_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ORDER_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final OrderStatus DEFAULT_ORDER_STATUS = OrderStatus.SHIPPED;
    private static final OrderStatus UPDATED_ORDER_STATUS = OrderStatus.NOT_SHIPPED;

    private static final ShippingAgency DEFAULT_SHIPPING_AGENCY = ShippingAgency.UPS;
    private static final ShippingAgency UPDATED_SHIPPING_AGENCY = ShippingAgency.USPS;

    @Inject
    private ShippingRepository shippingRepository;

    @Inject
    private ShippingService shippingService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restShippingMockMvc;

    private Shipping shipping;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ShippingResource shippingResource = new ShippingResource();
        ReflectionTestUtils.setField(shippingResource, "shippingService", shippingService);
        this.restShippingMockMvc = MockMvcBuilders.standaloneSetup(shippingResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Shipping createEntity(EntityManager em) {
        Shipping shipping = new Shipping()
                .customerId(DEFAULT_CUSTOMER_ID)
                .ipAddress(DEFAULT_IP_ADDRESS)
                .productId(DEFAULT_PRODUCT_ID)
                .email(DEFAULT_EMAIL)
                .orderId(DEFAULT_ORDER_ID)
                .productInCampaign(DEFAULT_PRODUCT_IN_CAMPAIGN)
                .orderAmount(DEFAULT_ORDER_AMOUNT)
                .orderDate(DEFAULT_ORDER_DATE)
                .orderStatus(DEFAULT_ORDER_STATUS)
                .shippingAgency(DEFAULT_SHIPPING_AGENCY);
        return shipping;
    }

    @Before
    public void initTest() {
        shipping = createEntity(em);
    }

    @Test
    @Transactional
    public void createShipping() throws Exception {
        int databaseSizeBeforeCreate = shippingRepository.findAll().size();

        // Create the Shipping

        restShippingMockMvc.perform(post("/api/shippings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shipping)))
            .andExpect(status().isCreated());

        // Validate the Shipping in the database
        List<Shipping> shippingList = shippingRepository.findAll();
        assertThat(shippingList).hasSize(databaseSizeBeforeCreate + 1);
        Shipping testShipping = shippingList.get(shippingList.size() - 1);
        assertThat(testShipping.getCustomerId()).isEqualTo(DEFAULT_CUSTOMER_ID);
        assertThat(testShipping.getIpAddress()).isEqualTo(DEFAULT_IP_ADDRESS);
        assertThat(testShipping.getProductId()).isEqualTo(DEFAULT_PRODUCT_ID);
        assertThat(testShipping.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testShipping.getOrderId()).isEqualTo(DEFAULT_ORDER_ID);
        assertThat(testShipping.getProductInCampaign()).isEqualTo(DEFAULT_PRODUCT_IN_CAMPAIGN);
        assertThat(testShipping.getOrderAmount()).isEqualTo(DEFAULT_ORDER_AMOUNT);
        assertThat(testShipping.getOrderDate()).isEqualTo(DEFAULT_ORDER_DATE);
        assertThat(testShipping.getOrderStatus()).isEqualTo(DEFAULT_ORDER_STATUS);
        assertThat(testShipping.getShippingAgency()).isEqualTo(DEFAULT_SHIPPING_AGENCY);
    }

    @Test
    @Transactional
    public void createShippingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = shippingRepository.findAll().size();

        // Create the Shipping with an existing ID
        Shipping existingShipping = new Shipping();
        existingShipping.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restShippingMockMvc.perform(post("/api/shippings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingShipping)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Shipping> shippingList = shippingRepository.findAll();
        assertThat(shippingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllShippings() throws Exception {
        // Initialize the database
        shippingRepository.saveAndFlush(shipping);

        // Get all the shippingList
        restShippingMockMvc.perform(get("/api/shippings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(shipping.getId().intValue())))
            .andExpect(jsonPath("$.[*].customerId").value(hasItem(DEFAULT_CUSTOMER_ID.toString())))
            .andExpect(jsonPath("$.[*].ipAddress").value(hasItem(DEFAULT_IP_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].productId").value(hasItem(DEFAULT_PRODUCT_ID.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].orderId").value(hasItem(DEFAULT_ORDER_ID.toString())))
            .andExpect(jsonPath("$.[*].productInCampaign").value(hasItem(DEFAULT_PRODUCT_IN_CAMPAIGN.toString())))
            .andExpect(jsonPath("$.[*].orderAmount").value(hasItem(DEFAULT_ORDER_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].orderDate").value(hasItem(DEFAULT_ORDER_DATE.toString())))
            .andExpect(jsonPath("$.[*].orderStatus").value(hasItem(DEFAULT_ORDER_STATUS.toString())))
            .andExpect(jsonPath("$.[*].shippingAgency").value(hasItem(DEFAULT_SHIPPING_AGENCY.toString())));
    }

    @Test
    @Transactional
    public void getShipping() throws Exception {
        // Initialize the database
        shippingRepository.saveAndFlush(shipping);

        // Get the shipping
        restShippingMockMvc.perform(get("/api/shippings/{id}", shipping.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(shipping.getId().intValue()))
            .andExpect(jsonPath("$.customerId").value(DEFAULT_CUSTOMER_ID.toString()))
            .andExpect(jsonPath("$.ipAddress").value(DEFAULT_IP_ADDRESS.toString()))
            .andExpect(jsonPath("$.productId").value(DEFAULT_PRODUCT_ID.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.orderId").value(DEFAULT_ORDER_ID.toString()))
            .andExpect(jsonPath("$.productInCampaign").value(DEFAULT_PRODUCT_IN_CAMPAIGN.toString()))
            .andExpect(jsonPath("$.orderAmount").value(DEFAULT_ORDER_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.orderDate").value(DEFAULT_ORDER_DATE.toString()))
            .andExpect(jsonPath("$.orderStatus").value(DEFAULT_ORDER_STATUS.toString()))
            .andExpect(jsonPath("$.shippingAgency").value(DEFAULT_SHIPPING_AGENCY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingShipping() throws Exception {
        // Get the shipping
        restShippingMockMvc.perform(get("/api/shippings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateShipping() throws Exception {
        // Initialize the database
        shippingService.save(shipping);

        int databaseSizeBeforeUpdate = shippingRepository.findAll().size();

        // Update the shipping
        Shipping updatedShipping = shippingRepository.findOne(shipping.getId());
        updatedShipping
                .customerId(UPDATED_CUSTOMER_ID)
                .ipAddress(UPDATED_IP_ADDRESS)
                .productId(UPDATED_PRODUCT_ID)
                .email(UPDATED_EMAIL)
                .orderId(UPDATED_ORDER_ID)
                .productInCampaign(UPDATED_PRODUCT_IN_CAMPAIGN)
                .orderAmount(UPDATED_ORDER_AMOUNT)
                .orderDate(UPDATED_ORDER_DATE)
                .orderStatus(UPDATED_ORDER_STATUS)
                .shippingAgency(UPDATED_SHIPPING_AGENCY);

        restShippingMockMvc.perform(put("/api/shippings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedShipping)))
            .andExpect(status().isOk());

        // Validate the Shipping in the database
        List<Shipping> shippingList = shippingRepository.findAll();
        assertThat(shippingList).hasSize(databaseSizeBeforeUpdate);
        Shipping testShipping = shippingList.get(shippingList.size() - 1);
        assertThat(testShipping.getCustomerId()).isEqualTo(UPDATED_CUSTOMER_ID);
        assertThat(testShipping.getIpAddress()).isEqualTo(UPDATED_IP_ADDRESS);
        assertThat(testShipping.getProductId()).isEqualTo(UPDATED_PRODUCT_ID);
        assertThat(testShipping.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testShipping.getOrderId()).isEqualTo(UPDATED_ORDER_ID);
        assertThat(testShipping.getProductInCampaign()).isEqualTo(UPDATED_PRODUCT_IN_CAMPAIGN);
        assertThat(testShipping.getOrderAmount()).isEqualTo(UPDATED_ORDER_AMOUNT);
        assertThat(testShipping.getOrderDate()).isEqualTo(UPDATED_ORDER_DATE);
        assertThat(testShipping.getOrderStatus()).isEqualTo(UPDATED_ORDER_STATUS);
        assertThat(testShipping.getShippingAgency()).isEqualTo(UPDATED_SHIPPING_AGENCY);
    }

    @Test
    @Transactional
    public void updateNonExistingShipping() throws Exception {
        int databaseSizeBeforeUpdate = shippingRepository.findAll().size();

        // Create the Shipping

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restShippingMockMvc.perform(put("/api/shippings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shipping)))
            .andExpect(status().isCreated());

        // Validate the Shipping in the database
        List<Shipping> shippingList = shippingRepository.findAll();
        assertThat(shippingList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteShipping() throws Exception {
        // Initialize the database
        shippingService.save(shipping);

        int databaseSizeBeforeDelete = shippingRepository.findAll().size();

        // Get the shipping
        restShippingMockMvc.perform(delete("/api/shippings/{id}", shipping.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Shipping> shippingList = shippingRepository.findAll();
        assertThat(shippingList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

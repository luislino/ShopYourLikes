package com.sylconnexity.spring18;

import com.sylconnexity.spring18.controller.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


// Ensures that the application context is creating controllers

/**
 * Test Cases that test integration of Controllers into Spring Application
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllersCreated {

    @Autowired
    private Controller_Error404Page controller404Page;
    @Test
    public void Error404Page() throws Exception {
        assertThat(controller404Page).isNotNull();
    }

    @Autowired
    private Controller_index controllerIndex;
    @Test
    public void index() throws Exception {
        assertThat(controllerIndex).isNotNull();
    }

    @Autowired
    private Controller_sampleUI controllerSampleUI;
    @Test
    public void sampleUI() throws Exception {
        assertThat(controllerSampleUI).isNotNull();
    }

    @Autowired
    private DBController_Click dbControllerClick;
    @Test
    public void click() throws Exception {
        assertThat(dbControllerClick).isNotNull();
    }

    @Autowired
    private DBController_Link dbControllerLink;
    @Test
    public void link() throws Exception {
        assertThat(dbControllerLink).isNotNull();
    }

    @Autowired
    private DBController_Merchant dbControllerMerchant;
    @Test
    public void merchant() throws Exception {
        assertThat(dbControllerMerchant).isNotNull();
    }

    @Autowired
    private DBController_Publisher dbControllerPublisher;
    @Test
    public void publisher() throws Exception {
        assertThat(dbControllerPublisher).isNotNull();
    }

}
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
    public void Error404ControllerCreated() throws Exception {
        assertThat(controller404Page).isNotNull();
    }

    @Autowired
    private Controller_index controllerIndex;
    @Test
    public void IndexControllerCreated() throws Exception {
        assertThat(controllerIndex).isNotNull();
    }

    /*@Autowired
    private Controller_sampleUI controllerSampleUI;
    @Test
    public void sampleUI() throws Exception {
        assertThat(controllerSampleUI).isNotNull();
    }*/

    @Autowired
    private DBClickController dbControllerClick;
    @Test
    public void ClickControllerCreated() throws Exception {
        assertThat(dbControllerClick).isNotNull();
    }

    @Autowired
    private DBLinkController dbControllerLink;
    @Test
    public void LinkControllerCreated() throws Exception {
        assertThat(dbControllerLink).isNotNull();
    }

    @Autowired
    private DBMerchantController dbControllerMerchant;
    @Test
    public void MerchantControllerCreated() throws Exception {
        assertThat(dbControllerMerchant).isNotNull();
    }

    @Autowired
    private DBPublisherController dbControllerPublisher;
    @Test
    public void PublisherControllerCreated() throws Exception {
        assertThat(dbControllerPublisher).isNotNull();
    }

}
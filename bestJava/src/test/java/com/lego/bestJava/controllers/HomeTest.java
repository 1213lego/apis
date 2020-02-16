package com.lego.bestJava.controllers;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.concurrent.ExecutorService;

public class HomeTest {
    @Mock
    private ExecutorService executor;
    private Home homeController;

    @BeforeClass
    public void setUp() {
        homeController = new Home(executor);
    }

    @Test
    public void testHelloReturnsStringHappyCase() {
        String string = homeController.hello();
        Assert.assertNotNull(string);
    }

    @Test(expected = NullPointerException.class)
    public void testHelloFailsBecauseOfExecutorNullPointerException() {
        Mockito.when(executor.submit(() -> null))
                .thenThrow(new NullPointerException());
    }

}

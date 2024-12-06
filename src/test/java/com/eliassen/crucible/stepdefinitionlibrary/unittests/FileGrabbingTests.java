package com.eliassen.crucible.stepdefinitionlibrary.unittests;

import com.eliassen.crucible.stepdefinitionlibrary.shared.StepDefinitionModel;
import com.eliassen.crucible.stepdefinitionlibrary.worker.StepDefinitionGrabber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class FileGrabbingTests
{
    private StepDefinitionGrabber stepDefinitionGrabber;

    @BeforeEach
    public void init()
    {
        stepDefinitionGrabber = new StepDefinitionGrabber();
    }

    @Test
    public void canGrabStepDefinitionFiles()
    {
        try
        {
            Map<String, Object> stepDefinitionFiles = stepDefinitionGrabber.getStepDefinitionFiles();
            assertTrue(stepDefinitionFiles.size() > 0);
        } catch (Exception e)
        {
            fail("Exception was thrown: " + e.getMessage());
        }
    }

    @Test
    public void canFindStepDefinitions() throws ClassNotFoundException, InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException
    {
        Set<StepDefinitionModel> stepDefinitions = stepDefinitionGrabber.getStepDefinitionsFromLibrary();
        assertTrue(stepDefinitions.size() > 0);
    }

    @Test
    public void canFindStepDefinitionsWithoutComments() throws ClassNotFoundException, InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException
    {
            Set<StepDefinitionModel> stepDefinitions = stepDefinitionGrabber.getStepDefinitionsFromLibrary();
            Set<StepDefinitionModel> stepDefinitionsWithoutDocumentation = stepDefinitionGrabber
                    .getStepDefinitionsMissingDocumentation(stepDefinitions);
            assertTrue(stepDefinitionsWithoutDocumentation.size() > 0);
    }

    @Test
    public void canOrganizeStepDefinitionsByParentClass() throws ClassNotFoundException, InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException
    {
        Set<StepDefinitionModel> stepDefinitions = stepDefinitionGrabber.getStepDefinitionsFromLibrary();
        Map<String,Set<StepDefinitionModel>> stepDefinitionsByParentClass = stepDefinitionGrabber
                .organizeStepDefinitionsByParentClass(stepDefinitions);
        assertTrue(stepDefinitionsByParentClass.size() > 0);
    }
}

package org.example;

import ApprivakCycle.CreateApprovalCycle;
import helpers.Data;
import Common.GetSessionID;
import org.junit.jupiter.api.Test;

import static ApprivakCycle.CreateApprovalCycle.createApprovalCycle;
import static ApprivakCycle.CreateApprovalCycleWithTemplate.createApprovalCycleWithTemplate;
import static ApprivakCycle.CreateApprovalWithCycleSourceApprovalCycle.createApprovalCycleWithSourceApprovalCycleRowID;
import static helpers.Urls.GREEN_5050;
import static Common.GetSessionID.getSessionID;

public class CreateApprovalCycleTest {

    @Test
    public void testCreateApprovalCycle() throws Exception {
        // Тест для метода createApprovalCycle
        createApprovalCycle(getSessionID(GREEN_5050), Data.regCardIdGREEN);
    }

    @Test
    public void testCreateApprovalCycleWithTemplate() throws Exception {
        // Тест для метода createApprovalCycleWithTemplate
        createApprovalCycleWithTemplate(
                GetSessionID.getSessionID(GREEN_5050),
                Data.regCardIdGREEN,
                Data.templateId,
                true // Пример использования copyAppendix
        );
    }

    @Test
    public void testCreateApprovalCycleWithSourceApprovalCycleRowID() throws Exception {
        createApprovalCycleWithSourceApprovalCycleRowID(
                GetSessionID.getSessionID(GREEN_5050),
                Data.regCardIdGREEN, CreateApprovalCycle.createApprovalCycle(
                        GetSessionID.getSessionID(GREEN_5050),
                        Data.regCardIdGREEN),
                        true);
    }

}

package org.example;

import ApprovalCycle.CreateApprovalCycle;
import helpers.Data;
import Common.GetSessionID;
import org.junit.jupiter.api.Test;

import static ApprovalCycle.CreateApprovalCycle.createApprovalCycle;
import static ApprovalCycle.CreateApprovalCycleWithTemplate.createApprovalCycleWithTemplate;
import static ApprovalCycle.CreateApprovalWithCycleSourceApprovalCycle.createApprovalCycleWithSourceApprovalCycleRowID;
import static helpers.Urls.SERVER_LINUX;
import static Common.GetSessionID.getSessionID;

public class CreateApprovalCycleTest {

    @Test
    public void testCreateApprovalCycle() throws Exception {
        // Тест для метода createApprovalCycle
        createApprovalCycle(getSessionID(SERVER_LINUX), SERVER_LINUX, Data.regCardIdLinux);
    }

    @Test
    public void testCreateApprovalCycleWithTemplate() throws Exception {
        // Тест для метода createApprovalCycleWithTemplate
        createApprovalCycleWithTemplate(
                GetSessionID.getSessionID(SERVER_LINUX),
                SERVER_LINUX,
                Data.regCardIdLinux,
                Data.templateIdLinux,
                true
        );
    }

    @Test
    public void testCreateApprovalCycleWithSourceApprovalCycleRowID() throws Exception {
        createApprovalCycleWithSourceApprovalCycleRowID(
                GetSessionID.getSessionID(SERVER_LINUX),
                SERVER_LINUX,
                Data.regCardIdLinux,
                CreateApprovalCycle.createApprovalCycle(
                        GetSessionID.getSessionID(SERVER_LINUX),
                        SERVER_LINUX,
                        Data.regCardIdLinux),
                true);
    }

}

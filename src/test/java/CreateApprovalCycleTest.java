package org.example;

import org.example.helpers.Data;
import org.example.linux.GetSessionID_LINUX;
import org.junit.jupiter.api.Test;

import static org.example.linux.CreateApprovalCycle.createApprovalCycle;

public class CreateApprovalCycleTest {

    @Test
    public void testCreateApprovalCycle() throws Exception {

        //createApprovalCycleWithTemplate(GetSessionID_Linux.getSessionID(), Data.regCardIdGREEN, Data.templateId, true);

        createApprovalCycle(GetSessionID_Linux.getSessionID(), Data.regCardIdGREEN);
    }
}

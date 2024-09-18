import ApprovalCycle.CreateApprovalCycle;
import Common.GetSessionID;
import helpers.Data;
import org.junit.jupiter.api.Test;

import static ApprovalCycle.CreateApprovalCycle.createApprovalCycle;
import static ApprovalCycle.CreateApprovalCycleWithTemplate.createApprovalCycleWithTemplate;
import static ApprovalCycle.CreateApprovalWithCycleSourceApprovalCycle.createApprovalCycleWithSourceApprovalCycleRowID;
import static Common.GetSessionID.getSessionID;
import static helpers.Urls.SERVER_LINUX;
import static org.junit.Assert.assertNotNull;

public class CreateApprovalCycleTest {

    @Test
    public void testCreateApprovalCycle() throws Exception {
        // Тест для метода createApprovalCycle
        String approvalCycleRowId = createApprovalCycle(getSessionID(SERVER_LINUX), SERVER_LINUX, Data.regCardIdLinux);
        assertNotNull(approvalCycleRowId, "Значение approvalCycleRowId пустое");
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

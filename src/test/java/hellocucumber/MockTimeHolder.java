package hellocucumber;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import dtu.superPlanner.ProjectManagementApp;
import dtu.superPlanner.TimeServer;
import dtu.superPlanner.WeekBasedCalendar;

public class MockTimeHolder {
    private TimeServer timeServer = mock(TimeServer.class);


    public MockTimeHolder(ProjectManagementApp pma){
        pma.setTimeServer(timeServer);
    }
    public void setDate(WeekBasedCalendar date){
        when(this.timeServer.getCurrentDate()).thenReturn(date);
    }
}

package hellocucumber;

import java.time.LocalDate;
import java.time.temporal.IsoFields;

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

    public void setDate(LocalDate date){
        when(this.timeServer.getCurrentDate()).thenReturn(date);
        int week = date.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
        int year = date.get(IsoFields.WEEK_BASED_YEAR);
        when(this.timeServer.getCurrentWeekDate()).thenReturn(new WeekBasedCalendar(week, year));
    }
}

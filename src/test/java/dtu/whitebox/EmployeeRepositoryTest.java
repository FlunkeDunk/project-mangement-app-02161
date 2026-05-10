package dtu.whitebox;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import dtu.superPlanner.Activity;
import dtu.superPlanner.Employee;
import dtu.superPlanner.FileEmployeeRepository;
import dtu.superPlanner.TimeFrame;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;


public class EmployeeRepositoryTest {
    FileEmployeeRepository spyRepo;

    public EmployeeRepositoryTest() {
        FileEmployeeRepository repo = new FileEmployeeRepository();
        spyRepo = spy(repo);
    }


    // A
    @Test
    public void testFindAvailableEmployeesWithEmpty() {
        Activity mockActivity = getMockActivityWithOverlappingTimeFrame();
        List<Employee> emptyList = List.<Employee>of();
        doReturn(emptyList).when(spyRepo).getAllEmployees();
        
        assertEquals(emptyList, spyRepo.findAvailableEmployeeInitials(mockActivity));
    }

    // B
    @Test
    public void testFindAvailableEmployeesWithOneUneligibleEmployee() {
        Activity mockActivity = getMockActivityWithOverlappingTimeFrame();
        Employee e1 = getMockEmployee(false, 0);
        doReturn(List.of(e1)).when(spyRepo).getAllEmployees();
        assertEquals(0, spyRepo.findAvailableEmployees(mockActivity).size());
    }


    // C
    @Test
    public void testFindAvailableEmployeesWithOneEligibleEmployee() {
        Activity mockActivity = getMockActivityWithOverlappingTimeFrame();
        Employee e2 = getMockEmployee(true, 0);
        List<Employee> list = List.of(e2);
        doReturn(list).when(spyRepo).getAllEmployees();
        assertEquals(list, spyRepo.findAvailableEmployees(mockActivity));
    }

    // D
    @Test
    public void testFindAvailableEmployeesWithLessComparison() {
        Activity mockActivity = getMockActivityWithOverlappingTimeFrame();
        Employee e2 = getMockEmployee(true, 0);
        Employee e3 = getMockEmployee(true, 1);

        List<Employee> list = List.of(e2, e3);
        doReturn(list).when(spyRepo).getAllEmployees();
        assertEquals(list, spyRepo.findAvailableEmployees(mockActivity));
    }

    // E
    @Test
    public void testFindAvailableEmployeesWithGreaterComparison() {
        Activity mockActivity = getMockActivityWithOverlappingTimeFrame();
        Employee e2 = getMockEmployee(true, 0);
        Employee e3 = getMockEmployee(true, 1);

        List<Employee> list = List.of(e3, e2);
        doReturn(list).when(spyRepo).getAllEmployees();
        assertEquals(List.of(e2, e3), spyRepo.findAvailableEmployees(mockActivity));
    }

    // F
    @Test
    public void testFindAvailableEmployeesWithEqualsComparison() {
        Activity mockActivity = getMockActivityWithOverlappingTimeFrame();
        Employee e3 = getMockEmployee(true, 1);
        Employee e4 = getMockEmployee(true, 1);

        List<Employee> list = List.of(e3, e4);
        doReturn(list).when(spyRepo).getAllEmployees();
        assertEquals(List.of(e3, e4), spyRepo.findAvailableEmployees(mockActivity));
    }


    private Employee getMockEmployee(boolean isEligible, int overlaps) {
        Employee mockEmployee = mock(Employee.class);
        Set<Activity> spySet = spy(new HashSet<Activity>());
        for (int i = 0; i < overlaps; i++) {
            spySet.add(getMockActivityWithOverlappingTimeFrame());
        }

        doReturn(isEligible).when(mockEmployee).isAvailable(any(TimeFrame.class));
        doReturn(!isEligible).when(spySet).contains(any(Activity.class));
        doReturn(spySet).when(mockEmployee).getActivities();
    
        return mockEmployee;
    }


    private Activity getMockActivityWithOverlappingTimeFrame() {
        Activity mockActivity = mock(Activity.class);
        TimeFrame mockTimeFrame = mock(TimeFrame.class);
        doReturn(mockTimeFrame).when(mockActivity).getTimeFrame();
        doReturn(true).when(mockTimeFrame).overlaps(any(TimeFrame.class));
        return mockActivity;
    }
}


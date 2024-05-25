package org.example.tripperbackend.services;

import org.example.tripperbackend.models.Schedule;
import org.example.tripperbackend.models.StopInfo;

import java.util.List;

public interface ScheduleService {

    List<Schedule> findAllVisibleSchedules();

    Schedule findScheduleById(String scheduleId);

    List<Schedule> findSchedulesByOwner(String userId);

    Schedule createSchedule(Schedule schedule, String userId);

    Schedule updateSchedule(Schedule schedule, String userId) throws Exception;

    // Schedule addStopToSchedule(String scheduleId, String time, StopInfo stopInfo, String userId) throws Exception;

    void deleteSchedule(String scheduleId, String userId) throws Exception;
}

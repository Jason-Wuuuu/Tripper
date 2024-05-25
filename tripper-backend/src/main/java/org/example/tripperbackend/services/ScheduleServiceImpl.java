package org.example.tripperbackend.services;

import org.example.tripperbackend.models.Schedule;
import org.example.tripperbackend.models.StopInfo;
import org.example.tripperbackend.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public List<Schedule> findAllVisibleSchedules() {
        return scheduleRepository.findByVisibility(true);
    }

    @Override
    public Schedule findScheduleById(String scheduleId) {
        return scheduleRepository.findById(scheduleId).filter(Schedule::getVisibility).orElse(null);
    }

    @Override
    public List<Schedule> findSchedulesByOwner(String userId) {
        return scheduleRepository.findByOwner(userId);
    }

    @Override
    public Schedule createSchedule(Schedule schedule, String userId) {
        schedule.setOwner(userId);
        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule updateSchedule(Schedule schedule, String userId) throws Exception {
        Schedule existingSchedule = findScheduleById(schedule.getScheduleId());
        if (!existingSchedule.getOwner().equals(schedule.getOwner()) && !existingSchedule.getCollaborators().contains(userId)) {
            throw new Exception("Unauthorized to update this schedule.");
        }

        return scheduleRepository.save(schedule);
    }

    @Override
    public void deleteSchedule(String scheduleId, String userId) throws Exception {
        Schedule schedule = findScheduleById(scheduleId);
        if (!schedule.getOwner().equals(userId)) {
            throw new Exception("Unauthorized to delete this schedule.");
        }

        scheduleRepository.delete(schedule);
    }
}

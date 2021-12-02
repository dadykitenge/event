package com.kumojin.event.service;

import com.kumojin.event.exception.RecordNotFoundException;
import com.kumojin.event.model.Event;
import com.kumojin.event.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<Event> getAllEvents() {

        List<Event> result = (List<Event>) eventRepository.findAll();
        if (result.size() > 0) {
            return result;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Event createOrUpdateEvent(Event entity) {
        if (entity.getId() == null) {
            entity = eventRepository.save(entity);
            return entity;
        } else {
            Optional<Event> evt = eventRepository.findById(entity.getId());

            if (evt.isPresent()) {

                Event newEntity = evt.get();
                newEntity.setId(entity.getId());
                newEntity.setEventName(entity.getEventName());
                newEntity.setEventDescription(entity.getEventDescription());
                newEntity.setStartDate(entity.getStartDate());
                newEntity.setEndDate(entity.getStartDate());
                newEntity.setZoneOffset(entity.getZoneOffset());

                newEntity = eventRepository.save(newEntity);

                return newEntity;
            } else {
                entity = eventRepository.save(entity);

                return entity;
            }
        }
    }

    @Override
    public Event getEventById(Long id) throws RecordNotFoundException {

        Optional<Event> event = eventRepository.findById(id);
        if (event.isPresent()) {
            return event.get();
        } else {
            throw new RecordNotFoundException("No event record exist for specified id");
        }
    }

    @Override
    public void deleteEventById(Long id) throws RecordNotFoundException {

        Optional<Event> event = eventRepository.findById(id);

        if (event.isPresent()) {
            eventRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No event record exist for given id");
        }
    }
}

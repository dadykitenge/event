package com.kumojin.event.service;

import com.kumojin.event.exception.RecordNotFoundException;
import com.kumojin.event.model.Event;

import java.util.List;

public interface EventService {

    List<Event> getAllEvents();

    Event createOrUpdateEvent(Event entity);

    Event getEventById(Long id) throws RecordNotFoundException;

    void deleteEventById(Long id) throws RecordNotFoundException;
}
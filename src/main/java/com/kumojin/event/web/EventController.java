package com.kumojin.event.web;

import com.kumojin.event.exception.RecordNotFoundException;
import com.kumojin.event.model.Event;
import com.kumojin.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class EventController {

    @Autowired
    EventService eventService;

    @RequestMapping(path = "/")
    public String getAllEvents(Model model) {
        List<Event> list = eventService.getAllEvents();
        model.addAttribute("events", list);
        return "list-events";
    }

    @PostMapping(path = "/createEvent")
    public String createOrUpdateEvent(Event event) {
        eventService.createOrUpdateEvent(event);
        return "redirect:/";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editEventById(Model model,
                                @PathVariable("id") Optional<Long> id) throws RecordNotFoundException {
        if (id.isPresent()) {
            Event entity = eventService.getEventById(id.get());
            model.addAttribute("event", entity);
        } else {
            model.addAttribute("event", new Event());
        }
        return "add-edit-event";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteEventById(Model model, @PathVariable("id") Long id)
            throws RecordNotFoundException {
        eventService.deleteEventById(id);
        return "redirect:/";
    }

}
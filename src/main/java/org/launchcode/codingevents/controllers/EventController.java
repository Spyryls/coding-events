package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @GetMapping
    public String displayAllEvents(Model model) {
        model.addAttribute("title", "All Events");
        model.addAttribute("events", eventRepository.findAll());
        return "events/index";
    }

    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title","Create Event");
        model.addAttribute("event", new Event());
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "events/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent, Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "All Events");
            return "events/create";
        }
        eventRepository.save(newEvent);
        return"redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Event");
        model.addAttribute("events", eventRepository.findAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {
        if (eventIds != null) {
            for (int id : eventIds) {
                eventRepository.deleteById(id);
            }
        }
        return "redirect:";
    }

    @GetMapping("edit/{eventId}")
    public String displayEditForm(Model model, @PathVariable int eventId) {
        Event eventToEdit = eventRepository.findById(eventId).get();
        model.addAttribute("title", "Edit Event: " + eventToEdit.getDescription());
        model.addAttribute(eventToEdit);
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "events/edit";
    }

    @PostMapping("edit")
    public String processEditForm(int eventId, String name, String description, String contactEmail, String location, EventCategory eventCategory) {
        Event eventToEdit = eventRepository.findById(eventId).get();
        eventToEdit.setName(name);
        eventToEdit.setDescription(description);
        eventToEdit.setContactEmail(contactEmail);
        eventToEdit.setLocation(location);
        eventToEdit.setEventCategory(eventCategory);
        return "redirect:";
    }

}

package com.minhaarea.planner.link;

import com.minhaarea.planner.trip.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LinkService {

    @Autowired
    private LinkRepository repository;

    public LinkCreateResponse registerLink(LinkRequestPayload payload, Trip trip){
        Link link = new Link(payload.title(), payload.url(), trip);
        this.repository.save(link);

        return new LinkCreateResponse(link.getId());
    }

    public List<LinkData> getAllLinks(UUID tripId){
        return this.repository.findByTripId(tripId).stream().map(link -> new LinkData(link.getId(), link.getTitle(), link.getUrl())).toList();
    }
}

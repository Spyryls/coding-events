package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class EventDetails extends AbstractEntity{

    @Size(max = 500, message = "Description has too many characters.")
    private String description;

    @Email(message = "Invalid Email. Re-input your email address.")
    private String contactEmail;

    @NotBlank(message = "No location mentioned. Please include the location of the event.")
    private String location;

    @OneToOne(mappedBy = "eventDetails")
    private Event event;

    public EventDetails(@Size(max = 500, message = "Description has too many characters.") String description, @Email(message = "Invalid Email. Re-input your email address.") String contactEmail, @NotBlank(message = "No location mentioned. Please include the location of the event.") String location) {
        this.description = description;
        this.contactEmail = contactEmail;
        this.location = location;
    }

    public EventDetails() {}

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}

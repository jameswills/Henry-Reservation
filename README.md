# Henry-Reservation - If I Had More Time

I was unable to fully finish the project from the ground up given the 3-hour time limit. I nearly have it completed outside of a few things in the Provider Scheduling to get done. I was able to get up to the Provider selecting a date, and the date being displayed in a `Text()` below the "choose a date" button. The intention after this was to display yet another view below the date that would allow them to input a time window they would work. At the bottom is an actionable button that would pass this data to a confirmation page.

I was able to complete the Client Reservation Screen with mock/hardcoded JSON. This loads on page load and gives the Client a grid of times that are available according to the JSON response. On selection of a time, we enable a button below to continue. Interacting with this button will proceed to the Confirmation Screen and display the selected reservation to the Client with a "Confirm" footer button. In a real-world situation, this would fire off an API call with the data.

### Things I Would Have Expanded on with More Time:

#### ProviderScheduleScreen

- This needs some love and finishing up.
- The Composables are not separated out like they are in `ClientSelectionScreen`.
- This page is not code complete as described above.

#### Whole App

- In a real-world situation, we would need to set up network calls to properly handle the data. This would be done with Retrofit.
- Unit Tests
- UI Tests
- UI/UX Designs

---

# Requirements

## Reservation - Frontend

### Welcome

Working in a fast-paced startup is a challenge - features are needed yesterday, resources are limited, and requirements are vague! 

This scenario is based on a real-world challenge faced at Henry - the solution became a core component of the business's success. The idea is to both give the developer a taste of that lifestyle, and allow Henry to evaluate their coding and decision-making capacity!  

### Scenario

Henry has two kinds of users, **providers** and **clients**. Providers have a schedule where they are available to see clients. Clients want to book time, in advance, on that schedule.

#### Providers

- Have an id.
- Have a schedule:
  - Example: On Friday the 13th of August, I want to work between 8am and 3pm.

#### Clients

- Have an id.
- Want to reserve a 15-minute time slot from a provider's schedule:
  - Reservations expire after 30 minutes if not confirmed.
  - Reservations must be made at least 24 hours in advance.
- Want to be able to confirm a reservation.

### Task

Build the front end for a mobile web application that covers as many of the following as possible in the time allotted:

- Allows providers to submit times they’d like to work on the schedule.
- Allows clients to list available slots.
- Allows clients to reserve an available slot.
- Allows clients to confirm their reservation.

Use whichever toolset you think is reasonable!

### Assumptions

Assume the API is being worked on in parallel and, for the time being, you can create a mock API or use hardcoded data from a file.

### Limitations

Try to limit development time to about 3

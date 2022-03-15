## Situation 1 :

The Tunisian ministry of health is organizing a software development hackathon in order to be able to recruit the best talent that can develop a mobile application for the EVAX platform. Motivated by the interesting work opportunity (and the even more interesting cash prize), everyone in the GL4 class has decided to participate.

Keeping in mind that this is a 24 hour highly competitive hackathon where we need to be fast at developing this proof of concept, I will be choosing **MVC architecture** :
- Ease of use (we can put everything in the controller).
- Less code than MVVM and MVP.
- Lower learning curve.
- More resources and bigger community ...


## Situation 2 :

Now that we have won the hackathon, it is time to actually build the mobile application. It goes without saying that this application should be robust with minimal
bugs, so that it can be safely deployed and adopted by the ministry. So, in order for us to be conCdent with our code, it is recommended to follow a TDD approach in order to catch bugs early on and make sure that we have a higher overall test coverage. One thing to keep in mind as well is that this application is not a basic CRUD one. It has multiple views with eventual complex operations (like sending vaccine SMS and their reminders, automatically rescheduling missed appointments, etc.).

Having taken all of the above into consideration, I will be applying the MVVM architecture (because of the ***testing requirements and reducing risks, multiple views and complex logic*** ) to develop the following use cases in Kotlin :

:fast_forward: **Schedule notification at vaccination time.**

:fast_forward: **List saved people and display a person's details and vaccinations.**

:fast_forward: **Add a new person.**

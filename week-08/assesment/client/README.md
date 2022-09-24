# WebFieldAgentAssessment

## Tasks
_TODO_ Add time estimates to each of the top-level tasks
* []Continue working in the repo from last week'sFieldAgentAPIrepository(0.10hours)
  * []AddaREADMEinthe`client`folderwiththecontentsfromthisfile
* []Reviewtherequirements(0.30hours)
* []IdentifyanyresearchthatIneedtodo(0.40hours)


### Part1: Project Set up and Agents List
* []Create an`index.html`and`main.js`file as a starting point for your project (0.07hours)
* []Display a list of agents (0.40hours - 1.30hours)
  * []Use`fetch`to`GET`a list of agents from the Field AgentAPI when the website is first loaded
  * []Use HTML and JavaScript to render the agents array
  * []Stub out click event handlers for the "AddAgent","EditAgent", and "DeleteAgent" buttons
**CommitallchangesandpushtoGitHub**


### Part2:AddAgentandDeleteAgent
* []Createaformtoaddanagent (2.15hours)
  * []Add form HTML
  * []Add onsubmit event handler to the form element(be sure to prevent the form from submitting!)
  * []Create an agent object
  * []Use `fetch` to `POST` then new agent's information to the FieldAgentAPI
  * []On success, refresh the agents list, or on failure, display any validation errors from the API in the UI
* []Support deleting agents (0.35hours)
  * []Confirm the deletion with the user
  * []Use `fetch` to `DELETE` the agent from the Field Agent API
  * []On success, refresh the agents list
**CommitallchangesandpushtoGitHub**


### Part3:EditAgent
* []Supporteditingagents(2.30hours)
  * []Retrieve the agent to edit
  * []Update the form with the agent's property values
  * []Update the onsubmit event handler to handle both`POST`and`PUT`requests
  * []Set the agent's ID on the agent object
  * []Use`fetch`to`PUT`the updated agent's information to the Field AgentAPI
  * []On success,refresh the agents list,or on failure,display any validation errors from the API in the UI


### Part4:Refinements
* []Conditionally render sections of the page (1.50hours)
  * []Add a state variable to track the current view
  * []Add a method to update the current view and conditionally render the list or the form
  * []Call the method to update the current where needed
* []Add Bootstrap to the`public/index.html`file(.13hours)
  * []Add a link to the BootstrapCSSusingthe[CDNfromtheofficialdocs](https://getbootstrap.com/docs/4.6/getting-started/introduction/#css)
  * []Add the[`container`CSSclass](https://getbootstrap.com/docs/4.6/layout/overview/#containers)tothe`<divid="root"></div>`element
* []Apply Bootstrapstyling(1.22hours)
  * []Styleallbuttons
  * []Styletheagentslist
  * []Styletheform
**CommitallchangesandpushtoGitHub**


## High-LevelRequirements
Implement a full CRUD UI for agents.
* Displayallagents
* Addanagent
* Updateanagent
* Deleteanagent

## TechnicalRequirements
* Alwaysusesemanticallycorrectmarkup.
* WiththeexceptionofBootstrap(oranotherCSSframework)forstyles,don'tuseanylibrariesorframeworks.
* Use`fetch`forasyncHTTP.
* YouarenotallowedtochangetheFieldAgentHTTPServiceordatabase(unlessthere'saconfirmedbugandyourinstructorapproves).
import {Link, useHistory } from "react-router-dom";
import {useState} from 'react';
import FormInput from '../FormInput/FormInput';
import "./AddAgent.css";

  const baseUrl = "http://localhost:8080/api/agent";

function AddAgent(){

    const DEFAULT_AGENT = {
        firstName: "",
        middleName: "",
        lastName: "",
        dob: "",
        heightInInches: ""
      };

    const[agent, setAgent] = useState(DEFAULT_AGENT);//state that we track about the page, that way when it does update it will refresh the component

    const history = useHistory();


    function handleSubmit(event){//take in an event to prevent it from posting
        event.preventDefault();

        //Use fetch to POST to the service
        fetch(baseUrl,{
            method: "POST",
            body: JSON.stringify(agent),
            headers: {
                "Content-Type": "application/json"
            }
        })
        //fetch returns a response
        .then(async response => {
            if(response.status === 201){
                //Invoking this hook returns an object
                //if successful...
                history.push("/agents");
            }
            return Promise.reject(await response.json());
            
        })
        //when response.json happens...
        //returns hydrated agents
        .then(addedAgent =>  history.push("/agents"))
        .catch(error => {
            if(error instanceof TypeError){
                showErrors(["Could not connect to the api."]);//put string into an array because it's handeling multiple error messages
            } else{
                showErrors(error);
            }
        });
    }

    function showErrors( listOfErrorMessages ){

        //looks for 'messages' div.
        const messageContainer = document.getElementById("messages");
    
        //converts error message into a sequence of paragraphs
        //becoming the innerHTML of the <div id="messages"></div>
        //in my html file
        messageContainer.innerHTML = listOfErrorMessages.map( m => "<p>" + m + "</p>" ).reduce( (prev, curr) => prev + curr ); //reduce into one big string
    
    }

    //tracks the fields and detects if changes are being made to them
    //changes the the 'onChangeHandler' as we make changes
    //For example: We make changes to our input, which fires the onChange event
                // which triggers this function
    function inputChangeHandler(inputChangedEvent){
        const propertyName = inputChangedEvent.target.name;//We are using the property name to update the value
        const newValue = inputChangedEvent.target.value;

        //Made a copy of agent, change the name of the copy and then call set course...

        //Made copy of original object using the spread syntax
        //this is taking all the properties, spreads them out into a sort of comma
        //seperated list & then builds an object with those properties
        const agentCopy = {...agent};

        //update property of the copy
        //put the propertyName in square brackets to 
        //get access to whichever property was altered
        agentCopy[propertyName] = newValue;

        //overwrite copy with setAgent()
        //as we are making changes we are immediately changing the agent variable
        setAgent(agentCopy);
    }


    return (

        <div className='container'>
            <h4>Add Agent:</h4>
            <form onSubmit={handleSubmit}>
                <FormInput 
                    inputType={"text"} 
                    identifier={"firstName"} 
                    labelText={"First Name"}
                    currVal={agent.firstName} 
                    onChangeHandler={inputChangeHandler}/>
                <FormInput 
                    inputType={"text"} 
                    identifier={"middleName"} 
                    labelText={"Middle Name"}
                    currVal={agent.middleName} 
                    onChangeHandler={inputChangeHandler}/>
                <FormInput 
                    inputType={"text"} 
                    identifier={"lastName"} 
                    labelText={"Last Name"}
                    currVal={agent.lastName} 
                    onChangeHandler={inputChangeHandler}/>
                <FormInput 
                    inputType={"date"} 
                    identifier={"dob"} 
                    labelText={"Date of Birth"}
                    currVal={agent.dob} 
                    onChangeHandler={inputChangeHandler}/>
                <FormInput 
                    inputType={"number"} 
                    identifier={"heightInInches"} 
                    labelText={"Height in inches"} 
                    currVal={agent.heightInInches} 
                    onChangeHandler={inputChangeHandler}/>

                <button className='btn btn-primary btn-add'>Add</button>
                <Link to="/agents" className="btn btn-danger btn-can">Cancel</Link>

                <h2> Error Messages</h2>
                <div id="messages" className="alert alert-danger" role="alert"></div>
            </form> 
        </div>
    );
}
export default AddAgent;
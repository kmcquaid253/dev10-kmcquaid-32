import {Link, useHistory } from "react-router-dom";
import {useState} from 'react';
import FormInput from '../FormInput/FormInput';

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

    function inputChangeHandler(inputChangedEvent){
        const propertyName = inputChangedEvent.target.name;
        const newValue = inputChangedEvent.target.value;

        //Made a copy of the original value
        const agentCopy = {...agent};

        //update property of the copy
        agentCopy[propertyName] = newValue;

        //overwrite copy with setAgent()
        setAgent(agentCopy);
    }


    return (
        // <h2>
        //     {agent ? agent.firstName : "Agent not loaded"}
        // </h2>

        <div className='container'>
            
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

                <button className='btn btn-primary'>Add</button>
                <Link to="/agents" className="btn btn-danger">Cancel</Link>

                <h5>Messages</h5>
                <div id="messages" className="alert alert-info" role="alert"></div>
            </form> 
        </div>
    );
}
export default AddAgent;
import {useEffect, useState} from 'react';
import { useParams, Link, useHistory } from "react-router-dom";
import FormInput from '../FormInput/FormInput';
import "./EditAgent.css";

function EditAgent(){

    //'useParams' allows us to pull the id off of the URL
    const {id} = useParams();

    const[agent, setAgent] = useState(null);//state that we track about the page, that way when it does update it will refresh the component

    const history = useHistory();

    useEffect( () => { //useEffect will only make the fetch happen once when the page first load up
        fetch("http://localhost:8080/api/agent/" + id)
        .then(
            response => {
                if(response.status === 200){//getting the single course object
                    return response.json(); //produces new 2nd promise
                } else{
                    //TODO: proper error handling
                    showErrors(["Could not find matching agent to edit"])
                }
            }
        )
        .then(selectedAgent => {//variable comes from the api
            setAgent(selectedAgent);
        });
    }, []); //sending an empty array so it doesnt re-run


    function handleSubmit(event){//take in an event to prevent it from posting
        event.preventDefault();

        //create PUT request
        fetch("http://localhost:8080/api/agent/" + id, {
            method: "PUT",
            body: JSON.stringify(agent),//we can just stringify agent bacause we've been changing it as we go
            headers: {
                "Content-Type": "application/json",
            }
        })
        .then(async response => {
            if(response.status === 204){
                //if successful get all agents & clear all agents
                history.push("/agents")
            }else if(response.status === 409){
                return Promise.reject(["Id mismatch between url and sent agent."]);//put string into an array because it's handeling multiple error messages
            } else if( response.status === 400 ){
                return Promise.reject( await response.json());
            }
        })
        .catch(errorList => {
            if(errorList instanceof TypeError){
                showErrors(["Could not connect to the api."]);
            } else{
                showErrors(errorList);
            }
        });
    }

    //tracks the fields and detects if changes are being made to them
    //changes the the 'onChangeHandler' as we make changes
    //For example: We make changes to our input, which fires the onChange event
                // which triggers this function
    function inputChangeHandler(inputChangedEvent){
        const propertyName = inputChangedEvent.target.name; //We are using the property name to update the value
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

    function showErrors( listOfErrorMessages ){

        //looks for 'messages' div in index.html
        const messageContainer = document.getElementById("messages");
    
        //converts error message into a sequence of paragraphs
        //becoming the innerHTML of the <div id="messages"></div>
        //in my html file
        messageContainer.innerHTML = listOfErrorMessages.map( m => "<p>" + m + "</p>" ).reduce( (prev, curr) => prev + curr ); //reduce into one big string
    
    }

    return (

        <div className='container'>
            <h4>Edit Agent:</h4>

            {agent ? 
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

                <button className='btn btn-primary ed-btn'>Edit</button>
                <Link to="/agents" className="btn btn-warning can-btn">Cancel</Link>

                <h2>Error Messages</h2>
                <div id="messages" className="alert alert-danger" role="alert"></div>
            </form> :
            null}
        </div>
    );
}

export default EditAgent;
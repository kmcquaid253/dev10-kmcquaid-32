import {useState, useEffect} from 'react';
import {Link} from 'react-router-dom';
import Agent from '../Agent/Agent';

function Agents(){

    const [agents, setAgents] = useState([]);

    function loadAllAgents(){
        fetch("http://localhost:8080/api/agent")
            .then(response => {
                if(response.status === 200){
                    return response.json();
                } else (console.log(response))
            })
            .then(agentList => {
                setAgents(agentList);
            })
            .catch(error => {
                if(error instanceof TypeError){
                    showErrors(["Could not connect to the api."]);
                } else{
                    showErrors(error);
                }
            });
    }

    //Use effect -> lambda -> to only deal with once
    //UseEffect = when the page first loads do whats in line 35
    //Keeps it from re rendering over and over again
    useEffect( 
        () => {
            loadAllAgents();
        },
    []); //useEffect takes a second argument, an array of stuff to track
        //if anything is in that array & we change those variables. then 
        //the useEffect will fire again the nect time the page renders

    function onAgentDeleted(){
        //TODO: do something here
        //This method is triggered specifically because someone clicked a button
        loadAllAgents();
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
        //When we call the map, we're going to take in each agent one at a time
        //and from that produce a agent JSX object.
        //sending the whole agent object to be displayed
        <>
        {
            <div className='container'>
                <div id="messages" role="alert"></div>
                <button className='btn btn-info add-btn'><Link to={"/agent/add"}>Add</Link></button>
                <p></p>
            </div>
            
        }
        {agents.map(a => <Agent key={a.agentId} agentData={a} onAgentDeleted={onAgentDeleted}/>)} 
        </>
    );
}

export default Agents;
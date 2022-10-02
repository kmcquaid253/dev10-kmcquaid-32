function DeleteFormInput(props){

    return(
        <div>
            <label className="form-label" htmlFor={props.identifier}>{props.labelText}</label>
            <input className="form-control" 
                    type={props.inputType} 
                    id={props.identifier} 
                    name={props.identifier} 
                    value={props.currVal}
                    />
        </div>
    );

}

export default DeleteFormInput;
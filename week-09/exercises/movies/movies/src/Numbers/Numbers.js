function Numbers(props){
    const numbersToDisplay = [];

    for(let x = props.min; x <= props.max; x++){
        numbersToDisplay.push(x);
    }

    return (
        <ul>{numbersToDisplay.map(n => <li>{n}</li>)}</ul>
    );
}

export default Numbers;
import React, { useState } from 'react';
import './styles/CoinPanel.css';

// NEW: 1. Destructure total and onClick props.
function CoinPanel({
    className,
    amount = 0.25, maxClicks = 10,
    total, onClick }) {

    // 1. Hooks must never run conditionally.
    const [clicks, setClicks] = useState(0);

    // NEW: 2. a click now does two things:
    // - increment the `click` state
    // - call the `onClick` callback to increase the total
    const handleClick = function () {
        setClicks(clicks + 1);
        onClick(amount);
    };

    const pennies = (amount * 100).toFixed(0);
    const contributed = (amount * clicks).toFixed(2);

    // NEW 3. calculate the percent of the total
    let percent = 0;
    if (total > 0) {
        percent = (contributed / total * 100).toFixed(1);
    }


    // Simplified JSX
    return (
        <div className={className}>
            <button className="btn" onClick={handleClick}>
                Insert {pennies}&cent;
            </button>
            <div>Contributed: ${contributed}</div>
            <div>{percent}% of total</div>
        </div>
    ); 
}

export default CoinPanel;
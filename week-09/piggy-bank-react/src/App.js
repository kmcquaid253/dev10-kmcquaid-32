import React from 'react';
import CoinPanel from './CoinPanel';

function App() {
  return (
    <div className="container">
      <h1>Piggy Bank</h1>
      <div className="row">
        <CoinPanel className="col" amount={0.25} maxClicks={5} />
        <CoinPanel className="col" amount={0.10} maxClicks={3} />
      </div>
    </div>
  );
}

export default App;
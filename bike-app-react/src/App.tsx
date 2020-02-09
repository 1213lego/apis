import React from 'react';
import logo from './logo.svg';
import './App.css';
import ProfileList from "./components/ProfileList";

const App = () => {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <ProfileList/>
      </header>
    </div>
  );
}

export default App;

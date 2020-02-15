import React from 'react';
import logo from './logo.svg';
import './App.css';
import ProfileList from "./components/ProfileList";
import Test from "./components/Test";

const App = () => {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <Test/>
        <ProfileList/>
      </header>
    </div>
  );
}

export default App;

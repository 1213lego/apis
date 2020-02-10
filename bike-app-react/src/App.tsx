import React from 'react';
import logo from './logo.svg';
import './App.css';
import ProfileList from "./components/ProfileList";
import Chat from './components/Chat';

const App = () => {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <Chat/>
      </header>
    </div>
  );
}

export default App;

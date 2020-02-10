
import React, { Component } from 'react'
import {Stomp} from "@stomp/stompjs";
import * as SockJS from 'sockjs-client';


class Chat extends Component {
    constructor(props) {
        super(props);
        this.state = {
            stompClient: null,
            sender: ''
        };
        this.connectionSuccess = this.connectionSuccess.bind(this);
        this.onChangeName = this.onChangeName.bind(this);
        this.sendMessage = this.sendMessage.bind(this);
    }
    componentDidMount() {
        const socket = new SockJS("/websocketApp");
        const stompClient = Stomp.over(socket);
        this.setState({stompClient: stompClient});
        stompClient.connect({},this.connectionSuccess())
    }
    onMessage(data) {
        console.log(data);
    }
    connectionSuccess(){
        console.log('====================================');
        console.log("Connected");
        console.log('====================================');
    }
    render() {
        return (
            <div>
                Chat
                <br/>
                <label>Name</label>
                <input onChange={this.onChangeName}/>
                <br/>
                <label>Message</label>
                <input onChange={this.sendMessage}/>
            </div>
        )
    }

    onChangeName(event) {
        this.setState({sender: event.target.value});
        const  {stompClient} = this.state;
        stompClient.send('/app/chat.newUser',{},JSON.stringify({sender: event.target.value, type : 'newUser'}));
        stompClient.subscribe('/topic/message',this.onMessage);
        event.preventDefault();
    }

    sendMessage(event) {
        const  {stompClient, sender} = this.state;
        stompClient.send('/app/chat.sendMessage',{},JSON.stringify({sender: sender,type : 'CHAT', content: event.target.value}));
    }
}

export default Chat

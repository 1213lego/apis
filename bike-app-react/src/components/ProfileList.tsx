import React, {Component} from 'react';
import SockJS from "sockjs-client";
import {CompatClient, IMessage, Stomp} from "@stomp/stompjs";

interface Profile {
    id: number;
    email: string;
}

interface ProfileListProps {
}

interface ProfileListState {
    profiles: Array<Profile>;
    isLoading: boolean;
}

class ProfileList extends Component<ProfileListProps, ProfileListState> {
    private stompClient: CompatClient;

    constructor(props: ProfileListProps) {
        super(props);

        this.state = {
            profiles: [],
            isLoading: false
        };
        this.onConnected = this.onConnected.bind(this);
        this.onMessageReceived = this.onMessageReceived.bind(this);
        this.stompClient = Stomp.over( new WebSocket('ws://localhost:8080/chat-example'));
        this.stompClient.connect({}, this.onConnected, (error: any) => console.log(error));
    }

    sockJSFactory() {
        return new SockJS('/chat-example');
    }

    webSocketFactory() {
        return new WebSocket('ws://localhost:8080/chat-example')
    }

    onConnected() {
        console.log('Connected with server');
        this.stompClient.subscribe('/topic/public', this.onMessageReceived)
        this.stompClient.publish({
            destination: "/app/chat.newUser",
            body: JSON.stringify({sender: 'React App' + new Date(), type: 'CONNECT'})
        });
    }

    onMessageReceived(message: IMessage) {
        console.log('New Message ', message);
    }

    render() {
        const {profiles, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        return (
            <div>
                <h2>Profile List</h2>
                {profiles.map((profile: Profile) =>
                    <div key={profile.id}>
                        {profile.email}<br/>
                    </div>
                )}
            </div>
        );
    }
}

export default ProfileList;

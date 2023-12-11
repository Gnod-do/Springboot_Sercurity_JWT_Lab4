import React, {useState} from "react";
import store from "../../app/store";
import {InputText} from "primereact/inputtext"
import {Button} from "primereact/button";
import {Messages} from 'primereact/messages';
import "./login.css"

let MessageInstance;

const serverPort = 6060;


function Login() {

    const signIn = e => {
        let information = {
            "login": username,
            "password": password
        };
    
        fetch(`http://localhost:${serverPort}/api/v1/auth/signin`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(information)
        }).then(respone => respone.json().then(json => {
            if (respone.ok) {
                // console.log(json)
                // console.log(json.login)
                store.dispatch({type: "change", value: json.token});
            } else {
                let errorText = json.error;
                MessageInstance.show({severity: 'error', summary: errorText});
            }
        }))
    }
    
    const signUp = e => {
        let information = {
            "login": username,
            "password": password
        };

        fetch(`http://localhost:${serverPort}/api/v1/auth/signup`, {
            method: "POST",
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(information)
        }).then(response => response.json().then(json => {
            if (response.ok) {
                MessageInstance.show({severity: 'success', summary: 'Successful Registration'});
                // console.log(json)
                // console.log(json.login)
            } else {
                let errorText = json.error;
                MessageInstance.show({severity: 'error', summary: errorText});
            }
        }))
    }

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    return (
        <div className="login_form">
            <form style={{
                fontFamily: "monospace",
                fontWeight: "bolder",
                fontSize: "170%",
                color: "#1e154a",
            }}>
                <div className="log-field">
                <label style={{color: "rgb(112 88 143)"}}>Username </label>
                <InputText type="text" id="username" value={username} onChange={(e) => setUsername(e.target.value)}/>
                </div>
                <div className="log-field">
                    <label style={{color: "rgb(112 88 143)"}}>Password </label>
                <InputText type="password" id="password" value={password}
                           onChange={(e) => setPassword(e.target.value)}/>
                </div>
                <Button className="button" type="button" onClick={signUp}>Sign Up</Button>
                <Button className="button" type="button" onClick={signIn}>Log In</Button>
                <Messages icon ref={(el) => MessageInstance = el}/>
            </form>
        </div>
    )
}

export default Login
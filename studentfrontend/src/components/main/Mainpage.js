import React, {useEffect, useState} from "react";
import Logout from "./Logout";
import CoordinatesForm from "./Form";
import Resulttable from "./Table";
import Graph from "./Graph";
import "./main.css";
import store from "../../app/store";


const serverPort = 6061;
function Mainpage() {
    const [x_form, setX] = useState('0');
    const [y_form, setY] = useState('0');
    const [r_form, setR] = useState('1');
    const [checks, setChecks] = useState(null);
    useEffect(() => {
        let information = {
            "token": `${store.getState().token.token}`
        };
        console.log(`${store.getState().token.token}`)
        console.log(information)
        if (checks === null) {
            fetch(`http://localhost:${serverPort}/api/v1/auth/getAllPoint`, {
                method: "POST",
                headers: {
                    'Content-type': 'application/json'
                },
                body: JSON.stringify(information)
            }).then(response => response.text().then(text => {
                console.log(JSON.parse(text));
                setChecks(JSON.parse(text));
            }))
        }
    })

    const checkNumbers = (q, a, b) => {
        return (( q > a) && ( q < b));
    }

    const validate = () => {
        return (checkNumbers(x_form,-3,3) && checkNumbers(y_form, -3, 5) && checkNumbers(r_form, -3, 3));
    }

    const showChecks = () => {
        console.log(checks);
    }

    return (<div id="maindiv">
    <Logout/>
    <Graph r={r_form} setChecks={setChecks} checks={checks}/>
    <CoordinatesForm validate={validate} x_form={x_form} y_form={y_form} r_form={r_form} setX={setX} setY={setY}
                     setR={setR} setChecks={setChecks} checks={checks} showChecks={showChecks()}/>
    <Resulttable checks={checks}/>
</div>)
}

export default Mainpage
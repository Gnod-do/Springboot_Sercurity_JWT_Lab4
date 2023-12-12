import React from "react";
import {InputNumber} from 'primereact/inputnumber';
import {Button} from "primereact/button";
import store from "../../app/store";


const url = "http://localhost:6061/api";
function CoordinatesForm(props) {
    const submit = () => {
        let information = {
            "x": props.x_form,
            "y": props.y_form,
            "r": props.r_form
        };
        if (props.validate()) {
            fetch(`${url}/auth/point`, {
                method: "POST",
                headers: {
                    'Content-type': 'application/json',
                    'Authorization': `${store.getState().token.token}`
                },
                body: JSON.stringify(information)
            }).then(response => response.text().then(text => {
                props.setChecks(JSON.parse(text));
            }))
        }
    }

    return (
        <div>
            <form id="form">
                <div className="nums-field">
                <label>X </label>
                <InputNumber value={props.x_form} onValueChange={(e) => props.setX(e.value)} mode="decimal"
                             min={-3} max={3}
                             minFractionDigits={1} maxFractionDigits={5} placeholder="Enter X(-3 .. 3)"/>
                             <br/>
                </div>
                <div className="nums-field">
                <label>Y </label>
                <InputNumber value={props.y_form} onValueChange={(e) => props.setY(e.value)} mode="decimal"
                             min={-3} max={5}
                             minFractionDigits={1} maxFractionDigits={5} placeholder="Enter Y(-3 .. 5)"/>
                             <br/>
                </div>
                <div className="nums-field">
                <label>R </label>
                <InputNumber value={props.r_form} onValueChange={(e) => props.setR(e.value)} mode="decimal"
                             min={-3} max={3}
                             minFractionDigits={1} maxFractionDigits={5} placeholder="Enter R(-3 .. 3)"/>
                             <br/>
                </div>
                <div className="nums-field">
                <Button type="button" onClick={submit} icon="pi">OK</Button>
                </div>
            </form>
        </div>
    )
}

export default CoordinatesForm
import React from "react";
import store from "../../app/store";

function Logout() {
    const logout = e => {
        store.dispatch({type: "setToNull"});
    }
    return (
        <div style={{ width: "10%",}} id="but" type="button" onClick={logout}>Logout</div>
    )
}

export default Logout
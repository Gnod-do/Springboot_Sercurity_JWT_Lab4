import { createStore, combineReducers  } from "redux";

const initialState = {
    login: sessionStorage.getItem("login")
};


function loginReducer(state = {login: null}, action) {
    switch(action.type) {
        case "change":
            sessionStorage.setItem("login", action.value)
            return {login: action.value};
        case "setToNull":
            return { ...state, login: null };
        default:
            return state;
    }
}

function tokenReducer(state = null, action) {
    switch(action.type) {
        case "dame":
            sessionStorage.setItem("token", action.value)
            return {token: action.value};
        default:
            return state;
    }
}

const rootReducer = combineReducers({
    login: loginReducer,
    token: tokenReducer,
})

const store = createStore(rootReducer);
export default store;

/*
    Su dung redux de quan ly trang thai cua ung dung
*/
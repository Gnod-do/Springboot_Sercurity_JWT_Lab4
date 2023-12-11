import { createStore } from "redux";

const initialState = {
    students: [],
    selectedStudent: null
}

const addStudent = (student) => ({
    type: "ADD",
    payload: student
})

const deleteStudent = (studentId) => ({
    type: "DELETE",
    payload: studentId
})

const updateStudent = (student) => ({
    type: "UPDATE",
    payload: student
})

const selectedStudent = (student) => ({
    type: "SELECT",
    payload: student
})

const reducer = (state = initialState, action) => {
    switch (action.type) {
        case "ADD":
            return {
                ...state,
                students: [...state.students, action.payload]
            }
        case "DELETE":
            return {
                ...state,
                students: state.students.filter(student => student.id != action.payload),
                selectedStudent: null
            }
        case "UPDATE":
            return{
                ...state,
                students: state.students.map(student => 
                    student.id === action.payload.id ? action.payload : student),
                    selectedStudent: null
            }
        case "SELECT":
            return{
                ...state,
                selectedStudent: action.payload
            }
        default:
            return state
    }
}
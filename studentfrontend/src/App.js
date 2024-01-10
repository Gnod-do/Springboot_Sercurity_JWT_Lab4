import React, { useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import Heading from "./components/Heading";
import Loginpage from "./components/login/Loginpage";
import Mainpage from "./components/main/Mainpage";

const App = () => {
  const token = useSelector((state) => state.token);
  const dispatch = useDispatch()

  console.log(token);
  useEffect(() => {
    if (localStorage.getItem("token")) {
      dispatch({ type: "dame", value: localStorage.getItem("token") });
    }
  }, []);
  // componentDidMount() {
  //   if(localStorage.getItem('token')){
  //     store.dispatch({type: "dame", value: localStorage.getItem('token')})
  //   }
  //   this.props.store.subscribe(() => {
  //     this.setState({reduxState: this.props.store.getState()});
  //   })
  //   console.log()
  // }

  return (
    <div
      className="firstPage"
      style={{
        backgroundImage: "url(images/wallpaper3.jpg)",
        backgroundSize: "45%",
        backgroundRepeat: "repeat",
      }}
    >
      <Heading />
      <br />

      {token !== null ? <Mainpage /> : <Loginpage />}
    </div>
  );
};

export default App;

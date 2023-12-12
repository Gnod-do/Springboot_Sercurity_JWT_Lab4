import React, {Component} from 'react';
import Heading from "./components/Heading";
import Loginpage from "./components/login/Loginpage";
import Mainpage from "./components/main/Mainpage";

class App extends Component {
  componentDidMount() {
    this.props.store.subscribe(() => {
      this.setState({reduxState: this.props.store.getState()});
    })
  }

  render() {
    return (<div  className="firstPage" style={{
      backgroundImage: "url(images/wallpaper3.jpg)",
      backgroundSize: "45%",
      backgroundRepeat: "repeat"
    }}><Heading/><br/>

      {this.props.store.getState().login.login !== null ? <Mainpage/> :<Loginpage/>}
      
    </div>)
  }
}

export default App;

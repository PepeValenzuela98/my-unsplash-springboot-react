import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import AuthRoute from "./components/routes/AuthRoute";

import Login from "./components/auth/Login";
import Register from "./components/auth/Register";
import GlobalStyles from "./components/GlobalStyles";
import Images from "./components/images/Images";
import Modal from "./components/Modal";

import AuthState from "./context/auth/AuthState";
import ImageState from "./context/image/ImageState";
import ModalState from "./context/modal/ModalState";
import tokenAuth from "./config/tokenAuth";

const token = localStorage.getItem("token");

if (token) {
  tokenAuth(token);
}
function App() {
  return (
    <AuthState>
      <ImageState>
        <ModalState>
          <Router>
            <GlobalStyles />
            <Switch>
              <Route exact path={["/", "/login"]} component={Login}></Route>
              <Route exact path="/register" component={Register}></Route>
              <AuthRoute exact path="/images" component={Images}></AuthRoute>
            </Switch>
          </Router>
          <Modal />
        </ModalState>
      </ImageState>
    </AuthState>
  );
}

export default App;

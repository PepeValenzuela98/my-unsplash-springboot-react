import React, { useReducer } from "react";

import AuthContext from "./authContext";
import authReducer from "./authReducer";
import {
  SUCCESSFUL_LOGIN,
  SUCCESSFUL_REGISTRATION,
  ERROR_LOGIN,
  ERROR_REGISTRATION,
  GET_USER,
  LOGOUT,
} from "../../types/index";

import axios from "../../config/axios";
import tokenAuth from "../../config/tokenAuth";

const AuthState = (props) => {
  const initialState = {
    token: localStorage.getItem("token"),
    authenticated: null,
    user: null,
    loading: null,
    error: null,
  };

  const [state, dispatch] = useReducer(authReducer, initialState);

  const registerUser = async (userData) => {
    try {
      const response = await axios.post("/user/register", userData);
      const token = response.data.jwt;
      dispatch({ type: SUCCESSFUL_REGISTRATION, payload: token });
      userAuthenticated();
    } catch (error) {
      dispatch({ type: ERROR_REGISTRATION });
    }
  };

  const login = async (userData) => {
    try {
      const response = await axios.post("/login", userData);
      const token = response.headers.authorization;
      dispatch({ type: SUCCESSFUL_LOGIN, payload: token });
      userAuthenticated();
    } catch (error) {
      dispatch({ type: ERROR_LOGIN });
    }
  };

  const logout = () => {
    dispatch({ type: LOGOUT });
  };

  const userAuthenticated = async () => {
    const token = localStorage.getItem("token");
    if (token) {
      tokenAuth(token);
    }
    try {
      const response = await axios.get("/user/authenticated");
      const user = response.data.user;
      dispatch({ type: GET_USER, payload: user });
    } catch (error) {
      dispatch({ type: ERROR_LOGIN });
    }
  };

  return (
    <AuthContext.Provider
      value={{
        token: state.token,
        authenticated: state.authenticated,
        user: state.user,
        loading: state.loading,
        error: state.error,

        registerUser: registerUser,
        login: login,
        logout: logout,
        userAuthenticated: userAuthenticated,
      }}
    >
      {props.children}
    </AuthContext.Provider>
  );
};

export default AuthState;

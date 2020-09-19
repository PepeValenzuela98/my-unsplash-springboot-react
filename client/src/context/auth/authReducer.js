import {
  SUCCESSFUL_LOGIN,
  SUCCESSFUL_REGISTRATION,
  ERROR_LOGIN,
  ERROR_REGISTRATION,
  GET_USER,
  LOGOUT,
} from "../../types/index";

export default (state, action) => {
  switch (action.type) {
    case SUCCESSFUL_LOGIN:
    case SUCCESSFUL_REGISTRATION:
      localStorage.setItem("token", action.payload);
      return { ...state, authenticated: true, loading: false, error: false };

    case GET_USER:
      return { ...state, user: action.payload, authenticated: true };

    case LOGOUT:
      localStorage.removeItem("token");
      return {
        ...state,
        token: null,
        authenticated: null,
        user: null,
        loading: null,
        error: null,
      };

    case ERROR_LOGIN:
    case ERROR_REGISTRATION:
      localStorage.removeItem("token");
      return {
        ...state,
        token: null,
        error: true,
        loading: false,
      };

    default:
      return state;
  }
};

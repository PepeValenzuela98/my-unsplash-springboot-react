import React, { useReducer } from "react";
import ModalContext from "./modalContext";
import modalReducer from "./modalReducer";
import { SHOW_MODAL, HIDE_MODAL } from "../../types/index";

const ModalState = (props) => {
  const initialState = {
    show: false,
    title: null,
    component: null,
  };

  const [state, dispatch] = useReducer(modalReducer, initialState);
  const showModal = (title, component) => {
    dispatch({ type: SHOW_MODAL, payload: { title, component } });
  };

  const hideModal = () => {
    dispatch({ type: HIDE_MODAL });
  };

  return (
    <ModalContext.Provider
      value={{
        show: state.show,
        title: state.title,
        component: state.component,

        showModal: showModal,
        hideModal: hideModal,
      }}
    >
      {props.children}
    </ModalContext.Provider>
  );
};

export default ModalState;

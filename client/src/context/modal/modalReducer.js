import { SHOW_MODAL, HIDE_MODAL } from "../../types/index";

export default (state, action) => {
  switch (action.type) {
    case SHOW_MODAL:
      return {
        ...state,
        show: true,
        title: action.payload.title,
        component: action.payload.component,
      };

    case HIDE_MODAL:
      return { ...state, show: false, title: null, component: null };

    default:
      return state;
  }
};
